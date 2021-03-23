package com.zoolife.app.ResponseModel.ShowComment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataItem {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("itemId")
    @Expose
    private int itemId;
    @SerializedName("userId")
    @Expose
    private int userId;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("co")
    @Expose
    private String co;
    @SerializedName("uo")
    @Expose
    private String uo;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("disclaimer")
    @Expose
    private int disclaimer;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("msg_badge")
    @Expose
    private int msgBadge;
    @SerializedName("noti_badge")
    @Expose
    private int notiBadge;
    @SerializedName("login")
    @Expose
    private String login;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("otp")
    @Expose
    private String otp;
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
     */
    public DataItem() {
    }

    /**
     * @param deviceType
     * @param otp
     * @param message
     * @param co
     * @param login
     * @param userId
     * @param deviceToken
     * @param itemId
     * @param createdAt
     * @param password
     * @param notiBadge
     * @param phone
     * @param name
     * @param verify
     * @param uo
     * @param id
     * @param msgBadge
     * @param disclaimer
     * @param email
     * @param updatedAt
     * @param status
     * @param username
     */
    public DataItem(int id, int itemId, int userId, String message, String co, String uo, String name, int disclaimer, String updatedAt, String createdAt, int msgBadge, int notiBadge, String login, String email, String status, String otp, String phone, int verify, String username, String password, String deviceToken, String deviceType) {
        super();
        this.id = id;
        this.itemId = itemId;
        this.userId = userId;
        this.message = message;
        this.co = co;
        this.uo = uo;
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

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCo() {
        return co;
    }

    public void setCo(String co) {
        this.co = co;
    }

    public String getUo() {
        return uo;
    }

    public void setUo(String uo) {
        this.uo = uo;
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

    public int getMsgBadge() {
        return msgBadge;
    }

    public void setMsgBadge(int msgBadge) {
        this.msgBadge = msgBadge;
    }

    public int getNotiBadge() {
        return notiBadge;
    }

    public void setNotiBadge(int notiBadge) {
        this.notiBadge = notiBadge;
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