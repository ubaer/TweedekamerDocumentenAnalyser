package dao;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Uber on 18-6-2017. tkDocAnalyser
 */
public class DebateTalkingpoint {
    String speaker;
    List<String> paragraphs;
    String party;

    public DebateTalkingpoint(){
        paragraphs = new ArrayList<>();
    }

    public DebateTalkingpoint(DebateTalkingpoint debateTalkingpoint){
        this.speaker = debateTalkingpoint.speaker;
        this.paragraphs = debateTalkingpoint.paragraphs;
        this.party = debateTalkingpoint.party;
    }

    public String getSpeaker() {
        return speaker;
    }

    public void setSpeaker(String speaker) {
        this.speaker = speaker;
    }


    public String getParty() {
        return party;
    }

    public void setParty(String party) {
        this.party = party;
    }

    public List<String> getParagraphs() {
        return paragraphs;
    }

    public void setParagraphs(List<String> paragraphs) {
        this.paragraphs = paragraphs;
    }

    public void addParagraph(String paragraph){
        paragraphs.add(paragraph);
    }

    @Override
    public String toString(){
        String totalText = "";
        System.out.println();
        for(String paragraph: paragraphs){
            totalText = totalText.concat(paragraph);
        }
        return this.speaker + " " + this.party + " " + totalText;
    }

    public void removeLastParagraph() {
        paragraphs.remove(paragraphs.size() - 1);
    }
}
