package com.zoolife.app.firebase.models;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.PropertyName;

public class Thread {

    public static final String CLASS_NAME = "thread";

    public static final String CONTENT = "content";
    public static final String CREATED = "created";
    public static final String RECIPIENT_USERNAME = "recipientUsername";
    public static final String RECIPIENT_PROFILE_PICTURE_URL = "recipientProfilePictureURL";
    public static final String RECIPIENT_ID = "recipientID";
    public static final String SEEN_BY_RECIPIENT = "seenByRecipient";
    public static final String SENDER_USERNAME = "senderUsername";
    public static final String SENDER_PROFILE_PICTURE_URL = "senderProfilePictureURL";
    public static final String SENDER_DATABASE_ID = "senderDatabaseID";
    public static final String SENDER_ID = "senderID";
    public static final String URL = "url";

    private String content;
    private Timestamp created;
    private String recipientUsername;
    private String recipientProfilePictureUrl;
    private String recipientId;
    private Boolean seenByRecipient;
    private String senderUsername;
    private String senderProfilePictureUrl;
    private String senderDatabaseId;
    private String senderId;
    private String url;

    public Thread(String content, Timestamp created, String recipientUsername, String recipientProfilePictureUrl, String recipientId, Boolean sennByRecipient, String senderUsername, String senderProfilePictureUrl, String senderDatabaseId, String senderId, String url) {
        this.content = content;
        this.created = created;
        this.recipientUsername = recipientUsername;
        this.recipientProfilePictureUrl = recipientProfilePictureUrl;
        this.recipientId = recipientId;
        this.seenByRecipient = sennByRecipient;
        this.senderUsername = senderUsername;
        this.senderProfilePictureUrl = senderProfilePictureUrl;
        this.senderDatabaseId = senderDatabaseId;
        this.senderId = senderId;
        this.url = url;
    }

    public Thread(DocumentSnapshot document) {
        this.content = document.getString(CONTENT);
        this.created = document.getTimestamp(CREATED);
        this.recipientUsername = document.getString(RECIPIENT_USERNAME);
        this.recipientProfilePictureUrl = document.getString(RECIPIENT_PROFILE_PICTURE_URL);
        this.recipientId = document.getString(RECIPIENT_ID);
        this.seenByRecipient = document.getBoolean(SEEN_BY_RECIPIENT);
        this.senderUsername = document.getString(SENDER_USERNAME);
        this.senderProfilePictureUrl = document.getString(SENDER_PROFILE_PICTURE_URL);
        this.senderDatabaseId = document.getString(SENDER_DATABASE_ID);
        this.senderId = document.getString(SENDER_ID);
        this.url = document.getString(URL);
    }

    @PropertyName(CONTENT)
    public String getContent() {
        return content;
    }

    @PropertyName(CONTENT)
    public void setContent(String content) {
        this.content = content;
    }

    @PropertyName(CREATED)
    public Timestamp getCreated() {
        return created;
    }

    @PropertyName(CREATED)
    public void setCreated(Timestamp created) {
        this.created = created;
    }

    @PropertyName(RECIPIENT_USERNAME)
    public String getRecipientFirstName() {
        return recipientUsername;
    }

    @PropertyName(RECIPIENT_USERNAME)
    public void setRecipientFirstName(String recipientUsername) {
        this.recipientUsername = recipientUsername;
    }

    @PropertyName(RECIPIENT_PROFILE_PICTURE_URL)
    public String getRecipientProfilePictureUrl() {
        return recipientProfilePictureUrl;
    }

    @PropertyName(RECIPIENT_PROFILE_PICTURE_URL)
    public void setRecipientProfilePictureUrl(String recipientProfilePictureUrl) {
        this.recipientProfilePictureUrl = recipientProfilePictureUrl;
    }

    @PropertyName(RECIPIENT_ID)
    public String getRecipientId() {
        return recipientId;
    }

    @PropertyName(RECIPIENT_ID)
    public void setRecipientId(String recipientId) {
        this.recipientId = recipientId;
    }

    @PropertyName(SEEN_BY_RECIPIENT)
    public Boolean getSeenByRecipient() {
        return seenByRecipient;
    }

    @PropertyName(SEEN_BY_RECIPIENT)
    public void setSeenByRecipient(Boolean seenByRecipient) {
        this.seenByRecipient = seenByRecipient;
    }

    @PropertyName(SENDER_USERNAME)
    public String getSenderFirstName() {
        return senderUsername;
    }

    @PropertyName(SENDER_USERNAME)
    public void setSenderFirstName(String senderUsername) {
        this.senderUsername = senderUsername;
    }

    @PropertyName(SENDER_PROFILE_PICTURE_URL)
    public String getSenderProfilePictureUrl() {
        return senderProfilePictureUrl;
    }

    @PropertyName(SENDER_PROFILE_PICTURE_URL)
    public void setSenderProfilePictureUrl(String senderProfilePictureUrl) {
        this.senderProfilePictureUrl = senderProfilePictureUrl;
    }

    @PropertyName(SENDER_DATABASE_ID)
    public String getSenderDatabaseId() {
        return senderDatabaseId;
    }

    @PropertyName(SENDER_DATABASE_ID)
    public void setSenderDatabaseId(String senderDatabaseId) {
        this.senderDatabaseId = senderDatabaseId;
    }

    @PropertyName(SENDER_ID)
    public String getSenderId() {
        return senderId;
    }

    @PropertyName(SENDER_ID)
    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    @PropertyName(URL)
    public String getUrl() {
        return url;
    }

    @PropertyName(URL)
    public void setUrl(String url) {
        this.url = url;
    }
}
