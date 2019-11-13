package repository.web.model;

import com.google.gson.annotations.SerializedName;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

public class RasporedApiModel {

    @SerializedName("id")
    private int id;

    @SerializedName("predmet")
    private String predmet;

    @SerializedName("tip")
    private String tip;

    @SerializedName("nastavnik")
    private String nastavnik;

    @SerializedName("grupe")
    private String grupe;

    @SerializedName("dan")
    private String dan;

    @SerializedName("termin")
    private String termin;

    @SerializedName("ucionica")
    private String ucionica;


    public String getPredmet() {
        return predmet;
    }

    public void setPredmet(@NonNull String predmet) {
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
}








