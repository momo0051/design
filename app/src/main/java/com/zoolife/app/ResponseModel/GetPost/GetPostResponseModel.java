package com.zoolife.app.ResponseModel.GetPost;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetPostResponseModel implements Parcelable {


    public static final Parcelable.Creator<GetPostResponseModel> CREATOR = new Parcelable.Creator<GetPostResponseModel>() {
        @Override
        public GetPostResponseModel createFromParcel(Parcel source) {
            return new GetPostResponseModel(source);
        }

        @Override
        public GetPostResponseModel[] newArray(int size) {
            return new GetPostResponseModel[size];
        }
    };
    @SerializedName("status")
    @Expose
    private int status;
    @SerializedName("data")
    @Expose
    private Data data;
    @SerializedName("error")
    @Expose
    private boolean error;

    /**
     * @param data
     * @param error
     * @param status
     */
    public GetPostResponseModel(int status, Data data, boolean error) {
        super();
        this.status = status;
        this.data = data;
        this.error = error;
    }

    public GetPostResponseModel() {
    }

    protected GetPostResponseModel(Parcel in) {
        this.data = in.readParcelable(Data.class.getClassLoader());
        this.error = in.readByte() != 0;
        this.status = in.readInt();
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.data, flags);
        dest.writeByte(this.error ? (byte) 1 : (byte) 0);
        dest.writeInt(this.status);
    }
}