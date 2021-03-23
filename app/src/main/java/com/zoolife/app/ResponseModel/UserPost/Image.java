package com.zoolife.app.ResponseModel.UserPost;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Image {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("item_id")
    @Expose
    private int itemId;
    @SerializedName("file_name")
    @Expose
    private String fileName;
    @SerializedName("uploaded_on")
    @Expose
    private String uploadedOn;
    @SerializedName("status")
    @Expose
    private String status;

    /**
     * No args constructor for use in serialization
     */
    public Image() {
    }

    /**
     * @param itemId
     * @param fileName
     * @param uploadedOn
     * @param id
     * @param status
     */
    public Image(int id, int itemId, String fileName, String uploadedOn, String status) {
        super();
        this.id = id;
        this.itemId = itemId;
        this.fileName = fileName;
        this.uploadedOn = uploadedOn;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getUploadedOn() {
        return uploadedOn;
    }

    public void setUploadedOn(String uploadedOn) {
        this.uploadedOn = uploadedOn;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}