package com.zoolife.app.ResponseModel.AllPost;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AllPostResponseModel {

    @SerializedName("status")
    @Expose
    private int status;
    @SerializedName("data")
    @Expose
    private List<DataItem> data = null;
    @SerializedName("error")
    @Expose
    private boolean error;

    /**
     * No args constructor for use in serialization
     */
    public AllPostResponseModel() {
    }

    /**
     * @param data
     * @param error
     * @param status
     */
    public AllPostResponseModel(int status, List<DataItem> data, boolean error) {
        super();
        this.status = status;
        this.data = data;
        this.error = error;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<DataItem> getData() {
        return data;
    }

    public void setData(List<DataItem> data) {
        this.data = data;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

}