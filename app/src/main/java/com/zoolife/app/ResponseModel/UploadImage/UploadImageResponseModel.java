package com.zoolife.app.ResponseModel.UploadImage;

import com.google.gson.annotations.SerializedName;

public class UploadImageResponseModel{

	@SerializedName("path")
	private String path;

	@SerializedName("error")
	private boolean error;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private int status;

	public String getPath(){
		return path;
	}

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