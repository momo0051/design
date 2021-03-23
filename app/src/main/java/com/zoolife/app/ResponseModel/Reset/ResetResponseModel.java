package com.zoolife.app.ResponseModel.Reset;

import com.google.gson.annotations.SerializedName;

public class ResetResponseModel{

	@SerializedName("otp")
	private String otp;

	@SerializedName("error")
	private boolean error;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private int status;

	public String getOtp(){
		return otp;
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