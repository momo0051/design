package com.zoolife.app.ResponseModel.UpdateProfile;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class UpdateProfileResponseModel{

	@SerializedName("data")
	private List<DataItem> data;

	@SerializedName("error")
	private boolean error;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private int status;

	public List<DataItem> getData(){
		return data;
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