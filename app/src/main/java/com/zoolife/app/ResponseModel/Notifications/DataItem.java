package com.zoolife.app.ResponseModel.Notifications;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class DataItem{

	@SerializedName("notifyFromId")
	private String notifyFromId;

	@SerializedName("itemId")
	private String itemId;

	@SerializedName("item")
	private List<ItemItem> item;

	@SerializedName("notifyType")
	private String notifyType;

	@SerializedName("removeAt")
	private String removeAt;

	@SerializedName("id")
	private String id;

	@SerializedName("createAt")
	private String createAt;

	@SerializedName("notifyToId")
	private String notifyToId;

	public String getNotifyFromId(){
		return notifyFromId;
	}

	public String getItemId(){
		return itemId;
	}

	public List<ItemItem> getItem(){
		return item;
	}

	public String getNotifyType(){
		return notifyType;
	}

	public String getRemoveAt(){
		return removeAt;
	}

	public String getId(){
		return id;
	}

	public String getCreateAt(){
		return createAt;
	}

	public String getNotifyToId(){
		return notifyToId;
	}
}