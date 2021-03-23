
package com.zoolife.app.ResponseModel.AddDelivery;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Data {

    @SerializedName("fromUserId")
    @Expose
    private String fromUserId;
    @SerializedName("itemDesc")
    @Expose
    private String itemDesc;
    @SerializedName("category")
    @Expose
    private int category;
    @SerializedName("subCategory")
    @Expose
    private String subCategory;
    @SerializedName("showPhoneNumber")
    @Expose
    private String showPhoneNumber;
    @SerializedName("showComments")
    @Expose
    private String showComments;
    @SerializedName("showMessage")
    @Expose
    private String showMessage;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("imgUrl")
    @Expose
    private String imgUrl;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("favrtitem_status")
    @Expose
    private int favrtitemStatus;
    @SerializedName("likeitem_status")
    @Expose
    private int likeitemStatus;
    @SerializedName("report_status")
    @Expose
    private int reportStatus;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("userid")
    @Expose
    private int userid;
    @SerializedName("device_token")
    @Expose
    private String deviceToken;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("related_add")
    @Expose
    private List<RelatedAdd> relatedAdd = null;

    /**
     * No args constructor for use in serialization
     */
    public Data() {
    }

    /**
     * @param country
     * @param subCategory
     * @param city
     * @param fromUserId
     * @param showPhoneNumber
     * @param showMessage
     * @param likeitemStatus
     * @param reportStatus
     * @param relatedAdd
     * @param favrtitemStatus
     * @param itemDesc
     * @param userid
     * @param deviceToken
     * @param imgUrl
     * @param createdAt
     * @param phone
     * @param id
     * @param category
     * @param showComments
     * @param email
     * @param updatedAt
     * @param username
     */
    public Data(String fromUserId, String itemDesc, int category, String subCategory, String showPhoneNumber, String showComments, String showMessage, String city, String country, String imgUrl, String updatedAt, String createdAt, int id, int favrtitemStatus, int likeitemStatus, int reportStatus, String username, int userid, String deviceToken, String phone, String email, List<RelatedAdd> relatedAdd) {
        super();
        this.fromUserId = fromUserId;
        this.itemDesc = itemDesc;
        this.category = category;
        this.subCategory = subCategory;
        this.showPhoneNumber = showPhoneNumber;
        this.showComments = showComments;
        this.showMessage = showMessage;
        this.city = city;
        this.country = country;
        this.imgUrl = imgUrl;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
        this.id = id;
        this.favrtitemStatus = favrtitemStatus;
        this.likeitemStatus = likeitemStatus;
        this.reportStatus = reportStatus;
        this.username = username;
        this.userid = userid;
        this.deviceToken = deviceToken;
        this.phone = phone;
        this.email = email;
        this.relatedAdd = relatedAdd;
    }

    public String getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(String fromUserId) {
        this.fromUserId = fromUserId;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }

    public String getShowPhoneNumber() {
        return showPhoneNumber;
    }

    public void setShowPhoneNumber(String showPhoneNumber) {
        this.showPhoneNumber = showPhoneNumber;
    }

    public String getShowComments() {
        return showComments;
    }

    public void setShowComments(String showComments) {
        this.showComments = showComments;
    }

    public String getShowMessage() {
        return showMessage;
    }

    public void setShowMessage(String showMessage) {
        this.showMessage = showMessage;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFavrtitemStatus() {
        return favrtitemStatus;
    }

    public void setFavrtitemStatus(int favrtitemStatus) {
        this.favrtitemStatus = favrtitemStatus;
    }

    public int getLikeitemStatus() {
        return likeitemStatus;
    }

    public void setLikeitemStatus(int likeitemStatus) {
        this.likeitemStatus = likeitemStatus;
    }

    public int getReportStatus() {
        return reportStatus;
    }

    public void setReportStatus(int reportStatus) {
        this.reportStatus = reportStatus;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<RelatedAdd> getRelatedAdd() {
        return relatedAdd;
    }

    public void setRelatedAdd(List<RelatedAdd> relatedAdd) {
        this.relatedAdd = relatedAdd;
    }

}
