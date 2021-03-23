package com.zoolife.app.models;

public class NotificationModel {
    public String notif_title,notif_posted,adId;

    public NotificationModel(String notif_title, String notif_posted,String adId) {
        this.notif_title = notif_title;
        this.notif_posted = notif_posted;
        this.adId = adId;
    }
}
