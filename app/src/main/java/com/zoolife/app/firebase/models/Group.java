package com.zoolife.app.firebase.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.PropertyName;

import java.util.List;
import java.util.Map;

public class Group implements Parcelable {

    public static final String CLASS_NAME = "groups";

    public static final String GROUP_ID = "groupID";
    public static final String GROUP_IMAGE = "groupImage";
    public static final String LAST_MESSAGE = "lastMessage";
    public static final String LAST_MESSAGE_DATE = "lastMessageDate";
    public static final String SENDER_NAME = "senderName";
    public static final String SENDER_EMAIL = "senderEmail";
    public static final String RECIPIENT_NAME = "recipientName";
    public static final String RECEIVER_EMAIL = "recipientEmail";
    public static final String AD_ID = "adId";
    public static final String AD_TITLE = "adTitle";
    public static final String AD_IMAGE = "adImage";
    public static final String AD_CREATED_USER = "adCreatedUser";
    public static final String FRIEND_MODEL = "friendModel";
    public static final String BADGES = "badges";
    public static final String RECIPIENT_PHONE = "recipientPhone";
    public static final String SENDER_PHONE = "senderPhone";

    private String groupId;
    private String groupImage;
    private String lastMessage;
    private Timestamp lastMessageDate;
    private String senderName;
    private String senderEmail;
    private String recipientName;
    private String recipientEmail;
    private String adId;
    private String adTitle;
    private String adImage;
    private String adCreatedUser;
    private String recipientPhone;
    private String senderPhone;
    private List<String> friendModel;
    private Map<String, Long> badges;

    public Group(String groupId, String groupImage, String lastMessage, Timestamp lastMessageDate, String senderName, String senderEmail, String recipientName, String recipientEmail, String adId, String adTitle, String adImage, String adCreatedUser, List<String> friendModel, Map<String, Long> badges,
                 String recipientPhone, String senderPhone) {
        this.groupId = groupId;
        this.groupImage = groupImage;
        this.lastMessage = lastMessage;
        this.lastMessageDate = lastMessageDate;
        this.senderName = senderName;
        this.senderEmail = senderEmail;
        this.recipientName = recipientName;
        this.recipientEmail = recipientEmail;
        this.adId = adId;
        this.adTitle = adTitle;
        this.adImage = adImage;
        this.adCreatedUser = adCreatedUser;
        this.friendModel = friendModel;
        this.badges = badges;
        this.recipientPhone = recipientPhone;
        this.senderPhone = senderPhone;
    }

    public Group(DocumentSnapshot document) {
        this.groupId = document.getString(GROUP_ID);
        this.groupImage = document.getString(GROUP_IMAGE);
        this.lastMessage = document.getString(LAST_MESSAGE);
        this.lastMessageDate = document.getTimestamp(LAST_MESSAGE_DATE);
        this.senderName = document.getString(SENDER_NAME);
        this.senderEmail = document.getString(SENDER_EMAIL);
        this.recipientName = document.getString(RECIPIENT_NAME);
        this.recipientEmail = document.getString(RECEIVER_EMAIL);
        this.adId = document.getString(AD_ID);
        this.adTitle = document.getString(AD_TITLE);
        this.adImage = document.getString(AD_IMAGE);
        this.adCreatedUser = document.getString(AD_CREATED_USER);
        this.friendModel = (List<String>) document.get(FRIEND_MODEL);
        this.badges = (Map<String, Long>) document.get(BADGES);
        this.recipientPhone = document.getString(RECIPIENT_PHONE);
        this.senderPhone = document.getString(SENDER_PHONE);

    }

    @PropertyName(GROUP_ID)
    public String getGroupId() {
        return groupId;
    }

    @PropertyName(GROUP_ID)
    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    @PropertyName(GROUP_IMAGE)
    public String getGroupImage() {
        return groupImage;
    }

    @PropertyName(GROUP_IMAGE)
    public void setGroupImage(String groupImage) {
        this.groupImage = groupImage;
    }

    @PropertyName(LAST_MESSAGE)
    public String getLastMessage() {
        return lastMessage;
    }

    @PropertyName(LAST_MESSAGE)
    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    @PropertyName(LAST_MESSAGE_DATE)
    public Timestamp getLastMessageDate() {
        return lastMessageDate;
    }

    @PropertyName(LAST_MESSAGE_DATE)
    public void setLastMessageDate(Timestamp lastMessageDate) {
        this.lastMessageDate = lastMessageDate;
    }

    @PropertyName(SENDER_NAME)
    public String getSenderName() {
        return senderName;
    }

    @PropertyName(SENDER_NAME)
    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    @PropertyName(SENDER_EMAIL)
    public String getSenderEmail() {
        return senderEmail;
    }

    @PropertyName(SENDER_EMAIL)
    public void setSenderEmail(String senderEmail) {
        this.senderEmail = senderEmail;
    }

    @PropertyName(RECIPIENT_NAME)
    public String getRecipientName() {
        return recipientName;
    }

    @PropertyName(RECIPIENT_NAME)
    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    @PropertyName(RECEIVER_EMAIL)
    public String getRecipientEmail() {
        return recipientEmail;
    }

    @PropertyName(RECEIVER_EMAIL)
    public void setRecipientEmail(String recipientEmail) {
        this.recipientEmail = recipientEmail;
    }

    @PropertyName(AD_ID)
    public String getAdId() {
        return adId;
    }

    @PropertyName(AD_ID)
    public void setAdId(String adId) {
        this.adId = adId;
    }

    @PropertyName(AD_TITLE)
    public String getAdTitle() {
        return adTitle;
    }

    @PropertyName(AD_TITLE)
    public void setAdTitle(String adTitle) {
        this.adTitle = adTitle;
    }

    @PropertyName(AD_IMAGE)
    public String getAdImage() {
        return adImage;
    }

    @PropertyName(AD_IMAGE)
    public void setAdImage(String adImage) {
        this.adImage = adImage;
    }

    @PropertyName(AD_CREATED_USER)
    public String getAdCreatedUser() {
        return adCreatedUser;
    }

    @PropertyName(AD_CREATED_USER)
    public void setAdCreatedUser(String adCreatedUser) {
        this.adCreatedUser = adCreatedUser;
    }

    @PropertyName(FRIEND_MODEL)
    public List<String> getFriendModel() {
        return friendModel;
    }

    @PropertyName(FRIEND_MODEL)
    public void setFriendModel(List<String> friendModel) {
        this.friendModel = friendModel;
    }

    @PropertyName(BADGES)
    public Map<String, Long> getBadges() {
        return badges;
    }

    @PropertyName(BADGES)
    public void setBadges(Map<String, Long> badges) {
        this.badges = badges;
    }



    @PropertyName(SENDER_PHONE)
    public String getSenderPhone() {
        return senderPhone;
    }

    @PropertyName(SENDER_PHONE)
    public void setSenderPhone(String senderPhone) {
        this.senderPhone = senderPhone;
    }

    @PropertyName(RECIPIENT_PHONE)
    public String getRecipientPhone() {
        return recipientPhone;
    }

    @PropertyName(RECIPIENT_PHONE)
    public void setRecipientPhone(String recipientPhone) {
        this.recipientPhone = recipientPhone;
    }



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.groupId);
        dest.writeString(this.groupImage);
        dest.writeString(this.lastMessage);
        dest.writeParcelable(this.lastMessageDate, flags);
        dest.writeString(this.senderName);
        dest.writeString(this.recipientName);
        dest.writeString(this.adId);
        dest.writeString(this.recipientPhone);
        dest.writeString(this.senderPhone);
    }

    protected Group(Parcel in) {
        this.groupId = in.readString();
        this.groupImage = in.readString();
        this.lastMessage = in.readString();
        this.lastMessageDate = in.readParcelable(Timestamp.class.getClassLoader());
        this.senderName = in.readString();
        this.recipientName = in.readString();
        this.adId = in.readString();
        this.recipientPhone = in.readString();
        this.senderPhone = in.readString();
    }

    public static final Parcelable.Creator<Group> CREATOR = new Parcelable.Creator<Group>() {
        @Override
        public Group createFromParcel(Parcel source) {
            return new Group(source);
        }

        @Override
        public Group[] newArray(int size) {
            return new Group[size];
        }
    };
}
