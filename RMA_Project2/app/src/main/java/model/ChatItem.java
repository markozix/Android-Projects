package model;

import com.google.firebase.database.Exclude;

public class ChatItem {

    private String name;

    private Message msg;

    @Exclude
    private String id;

    public ChatItem(String name, Message msg, String id) {
        this.name = name;
        this.msg = msg;
        this.id = id;
    }

    public ChatItem(){

    }

    public Message getMsg() {
        return msg;
    }

    public void setMsg(Message msg) {
        this.msg = msg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
