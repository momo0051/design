package com.zoolife.app.models;

public class FavouriteModel {

    public String title,postedDate,postedBy,location,username;
    public String image,id;

    public FavouriteModel(String title, String postedDate, String location, String username, String image, String id) {
        this.title = title;
        this.postedDate = postedDate;
        this.location = location;
        this.username = username;
        this.image = image;
        this.id = id;
    }
}
