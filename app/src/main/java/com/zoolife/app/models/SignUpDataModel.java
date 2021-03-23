package com.zoolife.app.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SignUpDataModel {

    @SerializedName("clientId")
    @Expose
    private String clientId;

    @SerializedName("appType")
    @Expose
    private Object appType;

    @SerializedName("hash")
    @Expose
    private String hash;

    @SerializedName("fcm_regId")
    @Expose
    private String fcm_regId;

    @SerializedName("language")
    @Expose
    private String language;

    @SerializedName("username")
    @Expose
    private String username;

    @SerializedName("fullname")
    @Expose
    private String fullname;

    @SerializedName("password")
    @Expose
    private String password;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("phone")
    @Expose
    private String phone;

    @SerializedName("lang")
    @Expose
    private String lang;

    public SignUpDataModel(String email, String password, String username, String fullname, String phone, String appType, String hash, String clientId, String lang) {
        this.email = email;
        this.password = password;
        this.username = username;
        this.fullname = fullname;
        this.phone = phone;
        this.appType = appType;
        this.hash = hash;
        this.clientId = clientId;
        this.lang = lang;
    }


    public Object getAppType() {
        return appType;
    }

    public void setAppType(Object appType) {
        this.appType = appType;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getFcm_regId() {
        return fcm_regId;
    }

    public void setFcm_regId(String fcm_regId) {
        this.fcm_regId = fcm_regId;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId){
        this.clientId = clientId;
    }


}
