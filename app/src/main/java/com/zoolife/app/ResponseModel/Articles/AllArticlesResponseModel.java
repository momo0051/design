package com.zoolife.app.ResponseModel.Articles;

import com.google.gson.annotations.SerializedName;
import com.zoolife.app.ResponseModel.UserPost.DataItem;

import java.util.List;

public class AllArticlesResponseModel {

    @SerializedName("message")
    private String message;

    @SerializedName("data")
    private List<ArticleDataItem> data;

    @SerializedName("error")
    private boolean error;

    @SerializedName("status")
    private int status;

    public String getMessage() {
        return message;
    }

    public List<ArticleDataItem> getData() {
        return data;
    }

    public boolean isError() {
        return error;
    }

    public int getStatus() {
        return status;
    }
}
