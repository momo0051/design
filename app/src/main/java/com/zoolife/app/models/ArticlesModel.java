package com.zoolife.app.models;

import java.io.Serializable;

public class ArticlesModel implements Serializable {

    public String image1;
    public String image2;
    public String image3;
    public String id;
    public String title;
    public String description;
    public String date;
    public String status;

    public ArticlesModel(String image1, String image2, String image3, String id, String title, String description, String date, String status) {
        this.image1 = image1;
        this.image2 = image2;
        this.image3 = image3;
        this.id = id;
        this.title = title;
        this.description = description;
        this.date = date;
        this.status = status;
    }
}
