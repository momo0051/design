package com.zoolife.app.ResponseModel.GetUserProfile;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Data implements Parcelable {

    @SerializedName("id")
    private String id;

    @SerializedName("fullname")
    private String fullname;

    @SerializedName("email")
    private String email;

    @SerializedName("phone")
    private String phone;

    @SerializedName("device_token")
    private String device_token;

    @SerializedName("username")
    private String username;

    public String getId(){
        return id;
    }

    public String getFullname() {
        return fullname;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getDevice_token() {
        return device_token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.fullname);
        dest.writeString(this.email);
        dest.writeString(this.phone);
        dest.writeString(this.device_token);
        dest.writeString(this.username);
    }

    public Data() {
    }

    protected Data(Parcel in) {
        this.id = in.readString();
        this.fullname = in.readString();
        this.email = in.readString();
        this.phone = in.readString();
        this.device_token = in.readString();
        this.username = in.readString();
    }

    public static final Parcelable.Creator<Data> CREATOR = new Parcelable.Creator<Data>() {
        @Override
        public Data createFromParcel(Parcel source) {
            return new Data(source);
        }

        @Override
        public Data[] newArray(int size) {
            return new Data[size];
        }
    };
}
