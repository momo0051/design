package com.zoolife.app.models;

public class MessageModels {

    public String nickname,username,msg,date,newornot;
    public int color;

    public MessageModels(String nickname, String username, String msg, String date, String newornot, int color) {
        this.nickname = nickname;
        this.username = username;
        this.msg = msg;
        this.date = date;
        this.newornot = newornot;
        this.color = color;
    }
}
