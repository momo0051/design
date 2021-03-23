package com.zoolife.app.ResponseModel.AllPost;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RelatedAd {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("imgUrl")
    @Expose
    private String imgUrl;

    /**
     * No args constructor for use in serialization
     */
    public RelatedAd() {
    }

    /**
     * @param imgUrl
     * @param id
     */
    public RelatedAd(int id, String imgUrl) {
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