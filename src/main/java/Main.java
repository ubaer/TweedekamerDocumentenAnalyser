import dao.DebateTalkingpoint;
import docx.DebateAnalyser;

import java.util.List;

/**
 * Created by Uber on 18-6-2017. tkDocAnalyser
 */
public class Main {
    public static void main(String [] args) {
        String debat1 = "F:\\Downloads\\Debat_over_criminele_familienetwerken_in_Noord-Brabant_(ongecorrigeerd_stenogram).docx";
        String debat2 = "F:\\Downloads\\Debat_over_de_voortgang_van_de_kabinetsformatie_(ongecorrigeerd_stenogram).docx";

        DebateAnalyser analyser = new DebateAnalyser(debat2);
        analyser.readDocument();

        List<DebateTalkingpoint> talkingpointList = analyser.getTalkingpoints();
        for(DebateTalkingpoint talkingpoint : talkingpointList){
            System.out.println(talkingpoint);
        }
    }
}
