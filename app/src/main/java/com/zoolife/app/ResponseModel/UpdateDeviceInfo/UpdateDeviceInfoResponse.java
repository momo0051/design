package com.zoolife.app.ResponseModel.UpdateDeviceInfo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UpdateDeviceInfoResponse {

    @SerializedName("status")
    @Expose
    private int status;
    @SerializedName("error")
    @Expose
    private boolean error;
    @SerializedName("data")
    @Expose
    private List<Data> data = null;
    @SerializedName("message")
    @Expose
    private String message;

    public UpdateDeviceInfoResponse() {
    }

    /**
     * @param data
     * @param error
     * @param message
     * @param status
     */
    public UpdateDeviceInfoResponse(int status, boolean error, List<Data> data, String message) {
        super();
        this.status = status;
        this.error = error;
        this.data = data;
        this.message = message;
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

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}