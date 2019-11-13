package model;

import java.util.Date;

public class Trosak {


    private String name;
    private int cost;
    private String category;
    private Date date;


    public Trosak(String name, int cost, String category, Date date) {
        this.name = name;
        this.cost = cost;
        this.category = category;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
