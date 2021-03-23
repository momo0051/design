package com.zoolife.app.ResponseModel.GetFavourites;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetFavouritesResponse {

    @SerializedName("error")
    @Expose
    private boolean error;
    @SerializedName("status")
    @Expose
    private int status;
    @SerializedName("data")
    @Expose
    private List<Datum> data = null;

    /**
     * No args constructor for use in serialization
     */
    public GetFavouritesResponse() {
    }

    /**
     * @param data
     * @param error
     * @param status
     */
    public GetFavouritesResponse(boolean error, int status, List<Datum> data) {
        super();
        this.error = error;
        this.status = status;
        this.data = data;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

}