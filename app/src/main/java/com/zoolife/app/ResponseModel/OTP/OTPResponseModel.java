package com.zoolife.app.ResponseModel.OTP;

import com.google.gson.annotations.SerializedName;

public class OTPResponseModel {

	/*@SerializedName("data")
	private Data data;

	@SerializedName("error")
	private boolean error;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private int status;

	public Data getData(){
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
	}*/


    @SerializedName("message")
    private String message;

    @SerializedName("status")
    private int status;

    @SerializedName("error")
    private boolean error;

//    @SerializedName("data")
//    private Data data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    /*public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }*/
}