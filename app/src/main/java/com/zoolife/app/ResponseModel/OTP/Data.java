package com.zoolife.app.ResponseModel.OTP;

import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("id")
    private int id = 0;

    @SerializedName("name")
    private String name = "";

    @SerializedName("disclaimer")
    private int disclaimer = 0;

    @SerializedName("updated_at")
    private String updated_at = "";

    @SerializedName("created_at")
    private String created_at = "";


    @SerializedName("msg_badge")
    private String msg_badge = "";


    @SerializedName("noti_badge")
    private String noti_badge = "";


    @SerializedName("login")
    private String login = "";


    @SerializedName("email")
    private String email = "";

    @SerializedName("status")
    private String status = "";

    @SerializedName("otp")
    private String otp = "";


    @SerializedName("phone")
    private String phone = "";


    @SerializedName("username")
    private String username = "";


    @SerializedName("password")
    private String password = "";


    @SerializedName("device_token")
    private String device_token = "";


    @SerializedName("device_type")
    private String device_type = "";


    @SerializedName("verify")
    private int verify = 0;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDisclaimer() {
        return disclaimer;
    }

    public void setDisclaimer(int disclaimer) {
        this.disclaimer = disclaimer;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getMsg_badge() {
        return msg_badge;
    }

    public void setMsg_badge(String msg_badge) {
        this.msg_badge = msg_badge;
    }

    public String getNoti_badge() {
        return noti_badge;
    }

    public void setNoti_badge(String noti_badge) {
        this.noti_badge = noti_badge;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDevice_token() {
        return device_token;
    }

    public void setDevice_token(String device_token) {
        this.device_token = device_token;
    }

    public String getDevice_type() {
        return device_type;
    }

    public void setDevice_type(String device_type) {
        this.device_type = device_type;
    }

    public int getVerify() {
        return verify;
    }

    public void setVerify(int verify) {
        this.verify = verify;
    }
}