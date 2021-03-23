package com.zoolife.app.ResponseModel.AllPost;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DataItem {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("priority")
    @Expose
    private String priority;
    @SerializedName("allowComments")
    @Expose
    private int allowComments;
    @SerializedName("fromUserId")
    @Expose
    private int fromUserId;
    @SerializedName("category")
    @Expose
    private int category;
    @SerializedName("subCategory")
    @Expose
    private int subCategory;
    @SerializedName("likesCount")
    @Expose
    private int likesCount;
    @SerializedName("itemTitle")
    @Expose
    private String itemTitle;
    @SerializedName("itemDesc")
    @Expose
    private String itemDesc;
    @SerializedName("imgUrl")
    @Expose
    private String imgUrl;
    @SerializedName("area")
    @Expose
    private String area;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("showPhoneNumber")
    @Expose
    private int showPhoneNumber;
    @SerializedName("showMessage")
    @Expose
    private int showMessage;
    @SerializedName("showComments")
    @Expose
    private int showComments;
    @SerializedName("showWhatsapp")
    @Expose
    private int showWhatsapp;
    @SerializedName("phoneViewsCount")
    @Expose
    private int phoneViewsCount;
    @SerializedName("modifyAt")
    @Expose
    private int modifyAt;
    @SerializedName("removeAt")
    @Expose
    private int removeAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("user_id")
    @Expose
    private int userId;
    @SerializedName("device_token")
    @Expose
    private String deviceToken;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("images")
    @Expose
    private List<Image> images = null;
    @SerializedName("related_ads")
    @Expose
    private List<RelatedAd> relatedAds = null;

    public DataItem() {
    }

    public DataItem(int id, String priority, int allowComments, int fromUserId, int category, int subCategory, int likesCount, String itemTitle, String itemDesc, String imgUrl, String area, String country, String city, int showPhoneNumber, int showMessage, int showComments, int showWhatsapp, int phoneViewsCount, int modifyAt, int removeAt, String updatedAt, String createdAt, String username, int userId, String deviceToken, String email, String phone, List<Image> images, List<RelatedAd> relatedAds) {
        super();
        this.id = id;
        this.priority = priority;
        this.allowComments = allowComments;
        this.fromUserId = fromUserId;
        this.category = category;
        this.subCategory = subCategory;
        this.likesCount = likesCount;
        this.itemTitle = itemTitle;
        this.itemDesc = itemDesc;
        this.imgUrl = imgUrl;
        this.area = area;
        this.country = country;
        this.city = city;
        this.showPhoneNumber = showPhoneNumber;
        this.showMessage = showMessage;
        this.showComments = showComments;
        this.showWhatsapp = showWhatsapp;
        this.phoneViewsCount = phoneViewsCount;
        this.modifyAt = modifyAt;
        this.removeAt = removeAt;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
        this.username = username;
        this.userId = userId;
        this.deviceToken = deviceToken;
        this.email = email;
        this.phone = phone;
        this.images = images;
        this.relatedAds = relatedAds;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public int getAllowComments() {
        return allowComments;
    }

    public void setAllowComments(int allowComments) {
        this.allowComments = allowComments;
    }

    public int getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(int fromUserId) {
        this.fromUserId = fromUserId;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public int getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(int subCategory) {
        this.subCategory = subCategory;
    }

    public int getLikesCount() {
        return likesCount;
    }

    public void setLikesCount(int likesCount) {
        this.likesCount = likesCount;
    }

    public String getItemTitle() {
        return itemTitle;
    }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getShowPhoneNumber() {
        return showPhoneNumber;
    }

    public void setShowPhoneNumber(int showPhoneNumber) {
        this.showPhoneNumber = showPhoneNumber;
    }

    public int getShowMessage() {
        return showMessage;
    }

    public void setShowMessage(int showMessage) {
        this.showMessage = showMessage;
    }

    public int getShowComments() {
        return showComments;
    }

    public void setShowComments(int showComments) {
        this.showComments = showComments;
    }

    public int getShowWhatsapp() {
        return showWhatsapp;
    }

    public void setShowWhatsapp(int showWhatsapp) {
        this.showWhatsapp = showWhatsapp;
    }

    public int getPhoneViewsCount() {
        return phoneViewsCount;
    }

    public void setPhoneViewsCount(int phoneViewsCount) {
        this.phoneViewsCount = phoneViewsCount;
    }

    public int getModifyAt() {
        return modifyAt;
    }

    public void setModifyAt(int modifyAt) {
        this.modifyAt = modifyAt;
    }

    public int getRemoveAt() {
        return removeAt;
    }

    public void setRemoveAt(int removeAt) {
        this.removeAt = removeAt;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public List<RelatedAd> getRelatedAds() {
        return relatedAds;
    }

    public void setRelatedAds(List<RelatedAd> relatedAds) {
        this.relatedAds = relatedAds;
    }
}