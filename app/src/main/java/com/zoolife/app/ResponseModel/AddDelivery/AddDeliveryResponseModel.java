
package com.zoolife.app.ResponseModel.AddDelivery;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddDeliveryResponseModel {

    @SerializedName("status")
    @Expose
    private int status;
    @SerializedName("error")
    @Expose
    private boolean error;
    @SerializedName("data")
    @Expose
    private Data data;
    @SerializedName("message")
    @Expose
    private String message;

    /**
     * No args constructor for use in serialization
     */
    public AddDeliveryResponseModel() {
    }

    /**
     * @param data
     * @param error
     * @param message
     * @param status
     */
    public AddDeliveryResponseModel(int status, boolean error, Data data, String message) {
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

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
