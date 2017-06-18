package docx;

import dao.DebateStenogram;
import dao.DebateTalkingpoint;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Uber on 18-6-2017. tkDocAnalyser
 */
public class DebateAnalyser {

    DebateStenogram debatStenogram;
    XWPFDocument document;
    List<DebateTalkingpoint> talkingpoints;

    public DebateAnalyser(String fileUrl) {
        debatStenogram = new DebateStenogram();
        talkingpoints = new ArrayList<>();
        try {
            document = new XWPFDocument(OPCPackage.open(fileUrl));
        } catch (IOException | InvalidFormatException e) {
            e.printStackTrace();
        }
    }

    public void readDocument() {
        int boldCount = 0;
        boolean initiatedDocument = false;
        boolean differentSpeaker = false;
        boolean differentParty = false;
        DebateTalkingpoint talkingpoint = null;

        documentLoop:
        for (XWPFParagraph paragraph : document.getParagraphs()) {
            for (XWPFRun run : paragraph.getRuns()) {
                String text = run.text().replace("\n", "").replace("\r", "");
                if (run.isBold()) {
                    if (boldCount == 0) {
                        debatStenogram.setTitel(text);
                        System.out.println(text);
                    }
                    if (boldCount == 1) {
                        System.out.println(text);
                        if (!text.equals("debat")) {
                            System.out.println("Document is geen debat");
                            break documentLoop;
                        }
                    }
                    if (boldCount == 2) {
                        debatStenogram.setOnderwerp(text);
                    }
                    if (boldCount > 2) {
                        initiatedDocument = true;
                        if (talkingpoint != null) {
                            talkingpoint.removeLastParagraph();
                            saveTalkingpoint(talkingpoint);
                        }
                        talkingpoint = new DebateTalkingpoint();
                        differentSpeaker = true;
                    }
                    boldCount++;
                }
                if (initiatedDocument) {
                    if (differentSpeaker) {
                        talkingpoint.setSpeaker(text);
                        differentSpeaker = false;
                        differentParty = true;
                    } else if (differentParty) {
                        text = text.replaceAll("[^a-zA-Z0-9]", "");
                        talkingpoint.setParty(text);
                        differentParty = false;
                    } else {
                        if (!text.isEmpty()) {
                            talkingpoint.addParagraph(text);
                        }
                    }
                }
            }
        }
    }

    public List<DebateTalkingpoint> getTalkingpoints() {
        return talkingpoints;
    }

    private void saveTalkingpoint(DebateTalkingpoint talkingpoint) {
        talkingpoints.add(new DebateTalkingpoint(talkingpoint));
    }
}
