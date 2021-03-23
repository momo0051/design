package com.zoolife.app.ResponseModel.ShowComment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ViewCommentsResponseModel {

    @SerializedName("status")
    @Expose
    private int status;
    @SerializedName("error")
    @Expose
    private boolean error;
    @SerializedName("data")
    @Expose
    private List<DataItem> data = null;

    /**
     * No args constructor for use in serialization
     */
    public ViewCommentsResponseModel() {
    }

    /**
     * @param data
     * @param error
     * @param status
     */
    public ViewCommentsResponseModel(int status, boolean error, List<DataItem> data) {
        super();
        this.status = status;
        this.error = error;
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<DataItem> getData() {
        return data;
    }

    public void setData(List<DataItem> data) {
        this.data = data;
    }

}