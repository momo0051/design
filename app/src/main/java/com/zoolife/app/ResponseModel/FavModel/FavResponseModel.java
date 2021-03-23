package com.zoolife.app.ResponseModel.FavModel;

import com.google.gson.annotations.SerializedName;

public class FavResponseModel{

	@SerializedName("error")
	private boolean error;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private int status;

	public boolean isError(){
		return error;
	}

	public String getMessage(){
		return message;
	}

	public int getStatus(){
		return status;
	}
}