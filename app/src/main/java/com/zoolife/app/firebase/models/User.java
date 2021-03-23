package com.zoolife.app.firebase.models;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.PropertyName;

public class User {

    public static final String CLASS_NAME = "users";

    public static final String DATABASE_ID = "databaseID";
    public static final String EMAIL = "email";
    public static final String USER_NAME = "username";
    public static final String PROFILE_PICTURE_URL = "profilePictureUrl";
    public static final String USER_ID = "userID";
    public static final String ONLINE = "online";
    public static final String LAST_ACTIVE = "lastActive";
    public static final String TOKEN = "token";

    private String databaseId;
    private String email;
    private String username;
    private String profilePictureUrl;
    private String userId;
    private Boolean online;
    private Timestamp lastActive;
    private String token;

    public User(String databaseId, String email, String username, String profilePictureUrl, String userId, Boolean online, Timestamp lastActive, String token) {
        this.databaseId = databaseId;
        this.email = email;
        this.username = username;
        this.profilePictureUrl = profilePictureUrl;
        this.userId = userId;
        this.online = online;
        this.lastActive = lastActive;
        this.token = token;
    }

    public User(DocumentSnapshot document) {
        this.databaseId = document.getString(DATABASE_ID);
        this.email = document.getString(EMAIL);
        this.username = document.getString(USER_NAME);
        this.profilePictureUrl = document.getString(PROFILE_PICTURE_URL);
        this.userId = document.getString(USER_ID);
        this.online = document.getBoolean(ONLINE);
        this.lastActive = document.getTimestamp(LAST_ACTIVE);
        this.token = document.getString(TOKEN);
    }

    @PropertyName(DATABASE_ID)
    public String getDatabaseId() {
        return databaseId;
    }

    @PropertyName(DATABASE_ID)
    public void setDatabaseId(String databaseId) {
        this.databaseId = databaseId;
    }

    @PropertyName(EMAIL)
    public String getEmail() {
        return email;
    }

    @PropertyName(EMAIL)
    public void setEmail(String email) {
        this.email = email;
    }

    @PropertyName(USER_NAME)
    public String getFirstName() {
        return username;
    }

    @PropertyName(USER_NAME)
    public void setFirstName(String username) {
        this.username = username;
    }

    @PropertyName(PROFILE_PICTURE_URL)
    public String getProfilePictureUrl() {
        return profilePictureUrl;
    }

    @PropertyName(PROFILE_PICTURE_URL)
    public void setProfilePictureUrl(String profilePictureUrl) {
        this.profilePictureUrl = profilePictureUrl;
    }

    @PropertyName(USER_ID)
    public String getUserId() {
        return userId;
    }

    @PropertyName(USER_ID)
    public void setUserId(String userId) {
        this.userId = userId;
    }

    @PropertyName(ONLINE)
    public Boolean getOnline() {
        return online;
    }

    @PropertyName(ONLINE)
    public void setOnline(Boolean online) {
        this.online = online;
    }

    @PropertyName(LAST_ACTIVE)
    public Timestamp getLastActive() {
        return lastActive;
    }

    @PropertyName(LAST_ACTIVE)
    public void setLastActive(Timestamp lastActive) {
        this.lastActive = lastActive;
    }

    @PropertyName(TOKEN)
    public String getToken() {
        return token;
    }

    @PropertyName(TOKEN)
    public void setToken(String token) {
        this.token = token;
    }
}
