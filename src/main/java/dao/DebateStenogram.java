package dao;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Uber on 18-6-2017. tkDocAnalyser
 */
public class DebateStenogram {
    String titel;
    String onderwerp;
    List<String> sprekers;

    public DebateStenogram() {
        this.sprekers = new ArrayList<>();
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public String getOnderwerp() {
        return onderwerp;
    }

    public void setOnderwerp(String onderwerp) {
        this.onderwerp = onderwerp;
    }

    public List<String> getSprekers() {
        return sprekers;
    }

    public void setSprekers(List<String> sprekers) {
        this.sprekers = sprekers;
    }

}
