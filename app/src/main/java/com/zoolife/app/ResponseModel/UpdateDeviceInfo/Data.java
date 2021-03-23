package com.zoolife.app.ResponseModel.UpdateDeviceInfo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("name")
    @Expose
    private Object name;
    @SerializedName("disclaimer")
    @Expose
    private int disclaimer;
    @SerializedName("updated_at")
    @Expose
    private Object updatedAt;
    @SerializedName("created_at")
    @Expose
    private Object createdAt;
    @SerializedName("msg_badge")
    @Expose
    private Object msgBadge;
    @SerializedName("noti_badge")
    @Expose
    private int notiBadge;
    @SerializedName("login")
    @Expose
    private Object login;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("otp")
    @Expose
    private int otp;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("verify")
    @Expose
    private int verify;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("device_token")
    @Expose
    private String deviceToken;
    @SerializedName("device_type")
    @Expose
    private String deviceType;

    /**
     * No args constructor for use in serialization
     *
     */
    public Data() {
    }

    /**
     *
     * @param deviceType
     * @param otp
     * @param login
     * @param deviceToken
     * @param createdAt
     * @param password
     * @param notiBadge
     * @param phone
     * @param name
     * @param verify
     * @param id
     * @param msgBadge
     * @param disclaimer
     * @param email
     * @param updatedAt
     * @param status
     * @param username
     */
    public Data(int id, Object name, int disclaimer, Object updatedAt, Object createdAt, Object msgBadge, int notiBadge, Object login, String email, String status, int otp, String phone, int verify, String username, String password, String deviceToken, String deviceType) {
        super();
        this.id = id;
        this.name = name;
        this.disclaimer = disclaimer;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
        this.msgBadge = msgBadge;
        this.notiBadge = notiBadge;
        this.login = login;
        this.email = email;
        this.status = status;
        this.otp = otp;
        this.phone = phone;
        this.verify = verify;
        this.username = username;
        this.password = password;
        this.deviceToken = deviceToken;
        this.deviceType = deviceType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Object getName() {
        return name;
    }

    public void setName(Object name) {
        this.name = name;
    }

    public int getDisclaimer() {
        return disclaimer;
    }

    public void setDisclaimer(int disclaimer) {
        this.disclaimer = disclaimer;
    }

    public Object getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Object updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Object getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Object createdAt) {
        this.createdAt = createdAt;
    }

    public Object getMsgBadge() {
        return msgBadge;
    }

    public void setMsgBadge(Object msgBadge) {
        this.msgBadge = msgBadge;
    }

    public int getNotiBadge() {
        return notiBadge;
    }

    public void setNotiBadge(int notiBadge) {
        this.notiBadge = notiBadge;
    }

    public Object getLogin() {
        return login;
    }

    public void setLogin(Object login) {
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

    public int getOtp() {
        return otp;
    }

    public void setOtp(int otp) {
        this.otp = otp;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getVerify() {
        return verify;
    }

    public void setVerify(int verify) {
        this.verify = verify;
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

    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }


}