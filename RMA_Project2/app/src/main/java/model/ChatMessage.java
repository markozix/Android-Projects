package model;

public class ChatMessage {

    private String date;
    private String msg;



    public ChatMessage(String date, String msg) {
        this.date = date;
        this.msg = msg;
    }


    public ChatMessage() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
