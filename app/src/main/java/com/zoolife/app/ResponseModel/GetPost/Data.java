package com.zoolife.app.ResponseModel.GetPost;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.zoolife.app.models.RelatedAdModel;

import java.util.ArrayList;
import java.util.List;

public class Data implements Parcelable {

    public static final Parcelable.Creator<Data> CREATOR = new Parcelable.Creator<Data>() {
        @Override
        public Data createFromParcel(Parcel source) {
            return new Data(source);
        }

        @Override
        public Data[] newArray(int size) {
            return new Data[size];
        }
    };
    @SerializedName("country")
    private String country;
    @SerializedName("subCategory")
    private String subCategory;
    @SerializedName("itemContent")
    private String itemContent;
    @SerializedName("rating")
    private String rating;
    @SerializedName("reviewsCount")
    private String reviewsCount;
    @SerializedName("likesCount")
    private String likesCount;
    @SerializedName("price")
    private String price;
    @SerializedName("rejectedAt")
    private String rejectedAt;
    @SerializedName("id")
    private String id;
    @SerializedName("lat")
    private String lat;
    @SerializedName("area")
    private String area;
    @SerializedName("images")
    private List<Image> images;
    @SerializedName("related_add")
    private List<RelatedAdModel> relatedAdImages;
    @SerializedName("lng")
    private String lng;
    @SerializedName("totalAbuses")
    private int totalAbuses;
    @SerializedName("previewImgUrl")
    private String previewImgUrl;
    @SerializedName("viewsCount")
    private String viewsCount;
    @SerializedName("soldAt")
    private String soldAt;
    @SerializedName("inactiveAt")
    private String inactiveAt;
    @SerializedName("gifUrl")
    private String gifUrl;
    @SerializedName("phoneNumber")
    private String phoneNumber;
    @SerializedName("removeAt")
    private String removeAt;
    @SerializedName("totalLikes")
    private int totalLikes;
    @SerializedName("moderatedAt")
    private String moderatedAt;
    @SerializedName("itemType")
    private String itemType;
    @SerializedName("itemTitle")
    private String itemTitle;
    @SerializedName("city")
    private String city;
    @SerializedName("sharesCount")
    private String sharesCount;
    @SerializedName("showPhoneNumber")
    private String showPhoneNumber;
    @SerializedName("showMessage")
    private String showMessage;
    @SerializedName("showComments")
    private String showComments;
    @SerializedName("showWhatsapp")
    private String showWhatsapp;
    @SerializedName("reportsCount")
    private String reportsCount;
    @SerializedName("created_at")
    private String createAt;
    @SerializedName("videoUrl")
    private String videoUrl;
    @SerializedName("moderatedId")
    private String moderatedId;
    @SerializedName("appType")
    private String appType;
    @SerializedName("currency")
    private String currency;
    @SerializedName("modifyAt")
    private String modifyAt;
    @SerializedName("previewVideoImgUrl")
    private String previewVideoImgUrl;
    @SerializedName("previewGifUrl")
    private String previewGifUrl;
    @SerializedName("fromUserId")
    private String fromUserId;
    @SerializedName("username")
    private String username;
    @SerializedName("email")
    private String email;
    @SerializedName("phone")
    private String phone;
    @SerializedName("imagesCount")
    private String imagesCount;
    @SerializedName("u_agent")
    private String uAgent;
    @SerializedName("categoryTitle")
    private String categoryTitle;
    @SerializedName("itemDesc")
    private String itemDesc;
    @SerializedName("imgUrl")
    private String imgUrl;
    @SerializedName("allowComments")
    private String allowComments;
    @SerializedName("commentsCount")
    private String commentsCount;
    @SerializedName("category")
    private String category;
    @SerializedName("phoneViewsCount")
    private String phoneViewsCount;
    @SerializedName("ip_addr")
    private String ipAddr;
    @SerializedName("favrtitem_status")
    private String favItemStatus;
    @SerializedName("likeitem_status")
    private String likeItemStatus;
    @SerializedName("report_status")
    private String reportStatus;
    @SerializedName("itemUrl")
    private String itemUrl;
    @SerializedName("rejectedId")
    private String rejectedId;
    @SerializedName("userid")
    private String userid;

    public Data() {
    }

    protected Data(Parcel in) {
        this.country = in.readString();
        this.subCategory = in.readString();
        this.itemContent = in.readString();
        this.rating = in.readString();
        this.reviewsCount = in.readString();
        this.likesCount = in.readString();
        this.price = in.readString();
        this.rejectedAt = in.readString();
        this.id = in.readString();
        this.lat = in.readString();
        this.area = in.readString();
        this.images = new ArrayList<Image>();
        this.relatedAdImages = new ArrayList<RelatedAdModel>();
        in.readList(this.images, String.class.getClassLoader());
        this.lng = in.readString();
        this.totalAbuses = in.readInt();
        this.previewImgUrl = in.readString();
        this.viewsCount = in.readString();
        this.soldAt = in.readString();
        this.inactiveAt = in.readString();
        this.gifUrl = in.readString();
        this.phoneNumber = in.readString();
        this.removeAt = in.readString();
        this.totalLikes = in.readInt();
        this.moderatedAt = in.readString();
        this.itemType = in.readString();
        this.itemTitle = in.readString();
        this.city = in.readString();
        this.sharesCount = in.readString();
        this.showPhoneNumber = in.readString();
        this.showMessage = in.readString();
        this.showComments = in.readString();
        this.showWhatsapp = in.readString();
        this.reportsCount = in.readString();
        this.createAt = in.readString();
        this.videoUrl = in.readString();
        this.moderatedId = in.readString();
        this.appType = in.readString();
        this.currency = in.readString();
        this.modifyAt = in.readString();
        this.showComments = in.readString();
        this.previewVideoImgUrl = in.readString();
        this.previewGifUrl = in.readString();
        this.fromUserId = in.readString();
        this.favItemStatus = in.readString();
        this.likeItemStatus = in.readString();
        this.reportStatus = in.readString();
        this.username = in.readString();
        this.email = in.readString();
        this.phone = in.readString();
        this.imagesCount = in.readString();
        this.uAgent = in.readString();
        this.categoryTitle = in.readString();
        this.itemDesc = in.readString();
        this.imgUrl = in.readString();
        this.allowComments = in.readString();
        this.commentsCount = in.readString();
        this.category = in.readString();
        this.phoneViewsCount = in.readString();
        this.ipAddr = in.readString();
        this.itemUrl = in.readString();
        this.rejectedId = in.readString();
        this.userid = in.readString();
    }

    public String getCountry() {
        return country;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public String getItemContent() {
        return itemContent;
    }

    public String getRating() {
        return rating;
    }

    public String getReviewsCount() {
        return reviewsCount;
    }

    public String getLikesCount() {
        return likesCount;
    }

    public String getPrice() {
        return price;
    }

    public String getRejectedAt() {
        return rejectedAt;
    }

    public String getId() {
        return id;
    }

    public String getLat() {
        return lat;
    }

    public String getArea() {
        return area;
    }

    public List<Image> getImages() {
        return images;
    }

    public List<RelatedAdModel> getrelatedAdImages() {
        return relatedAdImages;
    }

    public String getLng() {
        return lng;
    }

    public int getTotalAbuses() {
        return totalAbuses;
    }

    public String getPreviewImgUrl() {
        return previewImgUrl;
    }

    public String getViewsCount() {
        return viewsCount;
    }

    public String getSoldAt() {
        return soldAt;
    }

    public String getInactiveAt() {
        return inactiveAt;
    }

    public String getGifUrl() {
        return gifUrl;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getRemoveAt() {
        return removeAt;
    }

    public int getTotalLikes() {
        return totalLikes;
    }

    public String getModeratedAt() {
        return moderatedAt;
    }

    public String getItemType() {
        return itemType;
    }

    public String getItemTitle() {
        return itemTitle;
    }

    public String getCity() {
        return city;
    }

    public String getSharesCount() {
        return sharesCount;
    }

    public String getShowPhoneNumber() {
        return showPhoneNumber;
    }

    public String getShowMessage() {
        return showMessage;
    }

    public String getReportsCount() {
        return reportsCount;
    }

    public String getCreateAt() {
        return createAt;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public String getModeratedId() {
        return moderatedId;
    }

    public String getAppType() {
        return appType;
    }

    public String getCurrency() {
        return currency;
    }

    public String getModifyAt() {
        return modifyAt;
    }

    public String getShowComments() {
        return showComments;
    }

    public String getPreviewVideoImgUrl() {
        return previewVideoImgUrl;
    }

    public String getPreviewGifUrl() {
        return previewGifUrl;
    }

    public String getFromUserId() {
        return fromUserId;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getImagesCount() {
        return imagesCount;
    }

    public String getUAgent() {
        return uAgent;
    }

    public String getCategoryTitle() {
        return categoryTitle;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public String getAllowComments() {
        return allowComments;
    }

    public String getCommentsCount() {
        return commentsCount;
    }

    public String getCategory() {
        return category;
    }

    public String getPhoneViewsCount() {
        return phoneViewsCount;
    }

    public String getIpAddr() {
        return ipAddr;
    }

    public String getFavItemStatus() {
        return favItemStatus;
    }

    public String getLikeItemStatus() {
        return likeItemStatus;
    }

    public void setLikeItemStatus(String likeItemStatus) {
        this.likeItemStatus = likeItemStatus;
    }

    public String getReportStatus() {
        return reportStatus;
    }

    public void setReportStatus(String reportStatus) {
        this.reportStatus = reportStatus;
    }

    public String getItemUrl() {
        return itemUrl;
    }

    public String getRejectedId() {
        return rejectedId;
    }

    public String getShowWhatsapp() {
        return showWhatsapp;
    }

    public void setShowWhatsapp(String showWhatsapp) {
        this.showWhatsapp = showWhatsapp;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.country);
        dest.writeString(this.subCategory);
        dest.writeString(this.itemContent);
        dest.writeString(this.rating);
        dest.writeString(this.reviewsCount);
        dest.writeString(this.likesCount);
        dest.writeString(this.price);
        dest.writeString(this.rejectedAt);
        dest.writeString(this.id);
        dest.writeString(this.lat);
        dest.writeString(this.area);
        dest.writeList(this.images);
        dest.writeList(this.relatedAdImages);
        dest.writeString(this.lng);
        dest.writeInt(this.totalAbuses);
        dest.writeString(this.previewImgUrl);
        dest.writeString(this.viewsCount);
        dest.writeString(this.soldAt);
        dest.writeString(this.inactiveAt);
        dest.writeString(this.gifUrl);
        dest.writeString(this.phoneNumber);
        dest.writeString(this.removeAt);
        dest.writeInt(this.totalLikes);
        dest.writeString(this.moderatedAt);
        dest.writeString(this.itemType);
        dest.writeString(this.itemTitle);
        dest.writeString(this.city);
        dest.writeString(this.sharesCount);
        dest.writeString(this.showPhoneNumber);
        dest.writeString(this.showMessage);
        dest.writeString(this.showComments);
        dest.writeString(this.showWhatsapp);
        dest.writeString(this.reportsCount);
        dest.writeString(this.createAt);
        dest.writeString(this.videoUrl);
        dest.writeString(this.moderatedId);
        dest.writeString(this.appType);
        dest.writeString(this.currency);
        dest.writeString(this.modifyAt);
        dest.writeString(this.showComments);
        dest.writeString(this.previewVideoImgUrl);
        dest.writeString(this.previewGifUrl);
        dest.writeString(this.fromUserId);
        dest.writeString(this.favItemStatus);
        dest.writeString(this.likeItemStatus);
        dest.writeString(this.reportStatus);
        dest.writeString(this.username);
        dest.writeString(this.email);
        dest.writeString(this.phone);
        dest.writeString(this.imagesCount);
        dest.writeString(this.uAgent);
        dest.writeString(this.categoryTitle);
        dest.writeString(this.itemDesc);
        dest.writeString(this.imgUrl);
        dest.writeString(this.allowComments);
        dest.writeString(this.commentsCount);
        dest.writeString(this.category);
        dest.writeString(this.phoneViewsCount);
        dest.writeString(this.ipAddr);
        dest.writeString(this.itemUrl);
        dest.writeString(this.rejectedId);
        dest.writeString(this.userid);
    }
}