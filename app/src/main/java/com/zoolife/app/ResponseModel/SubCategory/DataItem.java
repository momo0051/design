package com.zoolife.app.ResponseModel.SubCategory;

import com.google.gson.annotations.SerializedName;

public class DataItem extends SubCategoryResponseModel {

	@SerializedName("removeAt")
	private String removeAt;

	@SerializedName("id")
	private String id;

	@SerializedName("title")
	private String title;

	@SerializedName("mainCategoryId")
	private String mainCategoryId;

	@SerializedName("createAt")
	private String createAt;

	public String getRemoveAt(){
		return removeAt;
	}

	public String getId(){
		return id;
	}

	public String getTitle(){
		return title;
	}

	public String getMainCategoryId(){
		return mainCategoryId;
	}

	public String getCreateAt(){
		return createAt;
	}
}