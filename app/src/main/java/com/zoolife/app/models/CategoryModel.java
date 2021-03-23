package com.zoolife.app.models;

public class CategoryModel {
    public String name;
    public String image;
    public String id;

    public CategoryModel(String name) {
        this.name = name;
    }

    public CategoryModel(String name, String image, String id) {
        this.name = name;
        this.image = image;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
