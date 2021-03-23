package com.zoolife.app.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RelatedAdModel implements Parcelable
{

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("imgUrl")
    @Expose
    private String imgUrl;
    public final static Parcelable.Creator<RelatedAdModel> CREATOR = new Creator<RelatedAdModel>() {


        @SuppressWarnings({
                "unchecked"
        })
        public RelatedAdModel createFromParcel(Parcel in) {
            return new RelatedAdModel(in);
        }

        public RelatedAdModel[] newArray(int size) {
            return (new RelatedAdModel[size]);
        }

    }
            ;

    protected RelatedAdModel(Parcel in) {
        this.id = ((String) in.readValue((String.class.getClassLoader())));
        this.imgUrl = ((String) in.readValue((String.class.getClassLoader())));
    }

    public RelatedAdModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(imgUrl);
    }

    public int describeContents() {
        return 0;
    }

}
