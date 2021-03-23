package com.zoolife.app.models.notification;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum implements Parcelable
{

    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("ads_id")
    @Expose
    private Integer adsId;
    @SerializedName("sender_id")
    @Expose
    private Integer senderId;
    @SerializedName("content")
    @Expose
    private String content;
    @SerializedName("isread")
    @Expose
    private Integer isread;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    public final static Parcelable.Creator<Datum> CREATOR = new Creator<Datum>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Datum createFromParcel(Parcel in) {
            return new Datum(in);
        }

        public Datum[] newArray(int size) {
            return (new Datum[size]);
        }

    }
            ;

    protected Datum(Parcel in) {
        this.userId = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.adsId = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.senderId = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.content = ((String) in.readValue((String.class.getClassLoader())));
        this.isread = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.createdAt = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Datum() {
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getAdsId() {
        return adsId;
    }

    public void setAdsId(Integer adsId) {
        this.adsId = adsId;
    }

    public Integer getSenderId() {
        return senderId;
    }

    public void setSenderId(Integer senderId) {
        this.senderId = senderId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getIsread() {
        return isread;
    }

    public void setIsread(Integer isread) {
        this.isread = isread;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(userId);
        dest.writeValue(adsId);
        dest.writeValue(senderId);
        dest.writeValue(content);
        dest.writeValue(isread);
        dest.writeValue(createdAt);
    }

    public int describeContents() {
        return 0;
    }

}