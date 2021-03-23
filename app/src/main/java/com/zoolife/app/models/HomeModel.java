package com.zoolife.app.models;


import java.io.Serializable;

public class HomeModel implements Serializable {

    public String title,postedDate,postedBy,location,username;
    public String image,id,priority;

    public HomeModel(String title, String postedDate, String location, String username, String image, String id,String priority) {
        this.title = title;
        this.postedDate = postedDate;
        this.location = location;
        this.username = username;
        this.image = image;
        this.id = id;
        this.priority = priority;
    }
}
