package com.zoolife.app.ResponseModel.CityNameResponseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CityNameResponseModel {

    @SerializedName("status")
    @Expose
    private int status;
    @SerializedName("error")
    @Expose
    private boolean error;
    @SerializedName("data")
    @Expose
    private List<Datum> data = null;

    /**
     * No args constructor for use in serialization
     */
    public CityNameResponseModel() {
    }

    /**
     * @param data
     * @param error
     * @param status
     */
    public CityNameResponseModel(int status, boolean error, List<Datum> data) {
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

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

}