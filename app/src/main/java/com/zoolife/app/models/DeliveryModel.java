package com.zoolife.app.models;

import java.io.Serializable;

public class DeliveryModel implements Serializable {

    public String itemTitle, itemDescription, city, username, phone, email, id;

    public DeliveryModel(String itemTitle, String itemDescription, String city, String username, String phone, String email, String id) {
        this.itemTitle = itemTitle;
        this.itemDescription = itemDescription;
        this.city = city;
        this.username = username;
        this.phone = phone;
        this.email = email;
        this.id = id;
    }
}
