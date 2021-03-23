package com.zoolife.app.ResponseModel.GetFavourites;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RelatedAdd {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("imgUrl")
    @Expose
    private String imgUrl;

    /**
     * No args constructor for use in serialization
     */
    public RelatedAdd() {
    }

    /**
     * @param imgUrl
     * @param id
     */
    public RelatedAdd(int id, String imgUrl) {
        super();
        this.id = id;
        this.imgUrl = imgUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

}