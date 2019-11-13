package model;

import java.util.Random;

public class Student {


    private String name;
    private int userId;
    private String mail;


    public Student(String Mname, String Mmail){

        name = Mname;
        mail = Mmail;
        userId = new Random().nextInt(100);

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

}
