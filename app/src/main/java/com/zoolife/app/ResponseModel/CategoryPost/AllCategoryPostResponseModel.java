package com.zoolife.app.ResponseModel.CategoryPost;

import com.google.gson.annotations.SerializedName;
import com.zoolife.app.ResponseModel.AllPost.DataItem;

import java.util.List;

public class AllCategoryPostResponseModel {

    @SerializedName("data")
    private List<DataItem> data;

    @SerializedName("error")
    private boolean error;

    @SerializedName("status")
    private int status;

    public List<DataItem> getData(){
        return data;
    }

    public boolean isError(){
        return error;
    }

    public int getStatus(){
        return status;
    }
}
