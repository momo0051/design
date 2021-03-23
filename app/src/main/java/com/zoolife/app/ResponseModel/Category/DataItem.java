package com.zoolife.app.ResponseModel.Category;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataItem{

	@SerializedName("id")
	@Expose
	private int id;
	@SerializedName("mainCategoryId")
	@Expose
	private int mainCategoryId;
	@SerializedName("title")
	@Expose
	private String title;
	@SerializedName("description")
	@Expose
	private Object description;
	@SerializedName("img_unSelected")
	@Expose
	private String imgUnSelected;
	@SerializedName("img_selected")
	@Expose
	private String imgSelected;
	@SerializedName("updated_at")
	@Expose
	private String updatedAt;
	@SerializedName("created_at")
	@Expose
	private String createdAt;
	@SerializedName("status")
	@Expose
	private String status;

	public DataItem() {
	}

	/**
	 *
	 * @param createdAt
	 * @param imgSelected
	 * @param imgUnSelected
	 * @param description
	 * @param id
	 * @param title
	 * @param mainCategoryId
	 * @param updatedAt
	 * @param status
	 */
	public DataItem(int id, int mainCategoryId, String title, Object description, String imgUnSelected, String imgSelected, String updatedAt, String createdAt, String status) {
		super();
		this.id = id;
		this.mainCategoryId = mainCategoryId;
		this.title = title;
		this.description = description;
		this.imgUnSelected = imgUnSelected;
		this.imgSelected = imgSelected;
		this.updatedAt = updatedAt;
		this.createdAt = createdAt;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMainCategoryId() {
		return mainCategoryId;
	}

	public void setMainCategoryId(int mainCategoryId) {
		this.mainCategoryId = mainCategoryId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Object getDescription() {
		return description;
	}

	public void setDescription(Object description) {
		this.description = description;
	}

	public String getImgUnSelected() {
		return imgUnSelected;
	}

	public void setImgUnSelected(String imgUnSelected) {
		this.imgUnSelected = imgUnSelected;
	}

	public String getImgSelected() {
		return imgSelected;
	}

	public void setImgSelected(String imgSelected) {
		this.imgSelected = imgSelected;
	}

	public String getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}