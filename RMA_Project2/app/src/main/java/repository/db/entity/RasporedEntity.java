package repository.db.entity;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "raspored")
public class RasporedEntity {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "predmet")
    private String predmet;

    @ColumnInfo(name = "tip")
    private String tip;

    @ColumnInfo(name = "nastavnik")
    private String nastavnik;

    @ColumnInfo(name = "grupe")
    private String grupe;

    @ColumnInfo(name = "dan")
    private String dan;

    @ColumnInfo(name = "termin")
    private String termin;

    @ColumnInfo(name = "ucionica")
    private String ucionica;

    public RasporedEntity(@NonNull int id, String predmet, String tip, String nastavnik, String grupe, String dan, String termin, String ucionica) {
        this.predmet = predmet;
        this.tip = tip;
        this.nastavnik = nastavnik;
        this.grupe = grupe;
        this.dan = dan;
        this.termin = termin;
        this.ucionica = ucionica;
        this.id = id;
    }

    @NonNull
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

    @NonNull
    @Override
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
