package model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;

public class Raspored {


    private int id;

    private String predmet;

    private String tip;

    private String nastavnik;

    private String grupe;

    private String dan;

    private String termin;

    private String ucionica;

    public Raspored(int id, String predmet, String tip, String nastavnik, String grupe, String dan, String termin, String ucionica) {
        this.predmet = predmet;
        this.tip = tip;
        this.nastavnik = nastavnik;
        this.grupe = grupe;
        this.dan = dan;
        this.termin = termin;
        this.ucionica = ucionica;
        this.id = id;
    }


    public String getPredmet() {
        return predmet;
    }

    public void setPredmet(String predmet) {
        this.predmet = predmet;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public String getNastavnik() {
        return nastavnik;
    }

    public void setNastavnik(String nastavnik) {
        this.nastavnik = nastavnik;
    }

    public String getGrupe() {
        return grupe;
    }

    public void setGrupe(String grupe) {
        this.grupe = grupe;
    }

    public String getDan() {
        return dan;
    }

    public void setDan(String dan) {
        this.dan = dan;
    }

    public String getTermin() {
        return termin;
    }

    public void setTermin(String termin) {
        this.termin = termin;
    }

    public String getUcionica() {
        return ucionica;
    }

    public void setUcionica(String ucionica) {
        this.ucionica = ucionica;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String toString() {
        return "RasporedEntity{" +
                "id= " + id +
                "predmet= " + predmet +
                "tip= " + tip +
                "nastavnik= " + nastavnik +
                "grupe= " + grupe +
                "dan= " + dan +
                "termin= " + termin +
                "ucionica= " + ucionica +
                "}";
    }
}
