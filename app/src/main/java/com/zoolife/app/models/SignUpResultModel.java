package com.zoolife.app.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SignUpResultModel {

    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("error_code")
    @Expose
    private Integer errorCode;
    @SerializedName("accessToken")
    @Expose
    private String accessToken;
    @SerializedName("accountId")
    @Expose
    private String accountId;
    @SerializedName("account")
    @Expose
    private List<Account> account = null;

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public List<Account> getAccount() {
        return account;
    }

    public void setAccount(List<Account> account) {
        this.account = account;
    }

    public class Account {

        @SerializedName("error")
        @Expose
        private Boolean error;
        @SerializedName("error_code")
        @Expose
        private Integer errorCode;
        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("gcm_regid")
        @Expose
        private Object gcmRegid;
        @SerializedName("ios_fcm_regid")
        @Expose
        private Object iosFcmRegid;
        @SerializedName("admob")
        @Expose
        private String admob;
        @SerializedName("ghost")
        @Expose
        private String ghost;
        @SerializedName("gcm")
        @Expose
        private String gcm;
        @SerializedName("balance")
        @Expose
        private String balance;
        @SerializedName("fb_id")
        @Expose
        private String fbId;
        @SerializedName("state")
        @Expose
        private String state;
        @SerializedName("regtime")
        @Expose
        private String regtime;
        @SerializedName("ip_addr")
        @Expose
        private String ipAddr;
        @SerializedName("phone")
        @Expose
        private String phone;
        @SerializedName("username")
        @Expose
        private String username;
        @SerializedName("fullname")
        @Expose
        private String fullname;
        @SerializedName("location")
        @Expose
        private String location;
        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("bio")
        @Expose
        private String bio;
        @SerializedName("fb_page")
        @Expose
        private String fbPage;
        @SerializedName("instagram_page")
        @Expose
        private String instagramPage;
        @SerializedName("verify")
        @Expose
        private String verify;
        @SerializedName("verified")
        @Expose
        private String verified;
        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("sex")
        @Expose
        private String sex;
        @SerializedName("year")
        @Expose
        private String year;
        @SerializedName("month")
        @Expose
        private String month;
        @SerializedName("day")
        @Expose
        private String day;
        @SerializedName("lat")
        @Expose
        private String lat;
        @SerializedName("lng")
        @Expose
        private String lng;
        @SerializedName("language")
        @Expose
        private String language;
        @SerializedName("lowPhotoUrl")
        @Expose
        private String lowPhotoUrl;
        @SerializedName("normalPhotoUrl")
        @Expose
        private String normalPhotoUrl;
        @SerializedName("bigPhotoUrl")
        @Expose
        private String bigPhotoUrl;
        @SerializedName("coverUrl")
        @Expose
        private String coverUrl;
        @SerializedName("originCoverUrl")
        @Expose
        private String originCoverUrl;
        @SerializedName("allowMessages")
        @Expose
        private String allowMessages;
        @SerializedName("allowCommentsGCM")
        @Expose
        private String allowCommentsGCM;
        @SerializedName("allowMessagesGCM")
        @Expose
        private String allowMessagesGCM;
        @SerializedName("allowCommentReplyGCM")
        @Expose
        private String allowCommentReplyGCM;
        @SerializedName("allowReviewsGCM")
        @Expose
        private String allowReviewsGCM;
        @SerializedName("lastNotifyView")
        @Expose
        private String lastNotifyView;
        @SerializedName("lastAuthorize")
        @Expose
        private String lastAuthorize;
        @SerializedName("lastAuthorizeDate")
        @Expose
        private String lastAuthorizeDate;
        @SerializedName("lastAuthorizeTimeAgo")
        @Expose
        private String lastAuthorizeTimeAgo;
        @SerializedName("online")
        @Expose
        private Boolean online;
        @SerializedName("itemsCount")
        @Expose
        private String itemsCount;
        @SerializedName("reviewsCount")
        @Expose
        private String reviewsCount;
        @SerializedName("commentsCount")
        @Expose
        private String commentsCount;
        @SerializedName("notificationsCount")
        @Expose
        private Integer notificationsCount;
        @SerializedName("messagesCount")
        @Expose
        private Integer messagesCount;

        public Boolean getError() {
            return error;
        }

        public void setError(Boolean error) {
            this.error = error;
        }

        public Integer getErrorCode() {
            return errorCode;
        }

        public void setErrorCode(Integer errorCode) {
            this.errorCode = errorCode;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public Object getGcmRegid() {
            return gcmRegid;
        }

        public void setGcmRegid(Object gcmRegid) {
            this.gcmRegid = gcmRegid;
        }

        public Object getIosFcmRegid() {
            return iosFcmRegid;
        }

        public void setIosFcmRegid(Object iosFcmRegid) {
            this.iosFcmRegid = iosFcmRegid;
        }

        public String getAdmob() {
            return admob;
        }

        public void setAdmob(String admob) {
            this.admob = admob;
        }

        public String getGhost() {
            return ghost;
        }

        public void setGhost(String ghost) {
            this.ghost = ghost;
        }

        public String getGcm() {
            return gcm;
        }

        public void setGcm(String gcm) {
            this.gcm = gcm;
        }

        public String getBalance() {
            return balance;
        }

        public void setBalance(String balance) {
            this.balance = balance;
        }

        public String getFbId() {
            return fbId;
        }

        public void setFbId(String fbId) {
            this.fbId = fbId;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getRegtime() {
            return regtime;
        }

        public void setRegtime(String regtime) {
            this.regtime = regtime;
        }

        public String getIpAddr() {
            return ipAddr;
        }

        public void setIpAddr(String ipAddr) {
            this.ipAddr = ipAddr;
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

        public String getFullname() {
            return fullname;
        }

        public void setFullname(String fullname) {
            this.fullname = fullname;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getBio() {
            return bio;
        }

        public void setBio(String bio) {
            this.bio = bio;
        }

        public String getFbPage() {
            return fbPage;
        }

        public void setFbPage(String fbPage) {
            this.fbPage = fbPage;
        }

        public String getInstagramPage() {
            return instagramPage;
        }

        public void setInstagramPage(String instagramPage) {
            this.instagramPage = instagramPage;
        }

        public String getVerify() {
            return verify;
        }

        public void setVerify(String verify) {
            this.verify = verify;
        }

        public String getVerified() {
            return verified;
        }

        public void setVerified(String verified) {
            this.verified = verified;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getYear() {
            return year;
        }

        public void setYear(String year) {
            this.year = year;
        }

        public String getMonth() {
            return month;
        }

        public void setMonth(String month) {
            this.month = month;
        }

        public String getDay() {
            return day;
        }

        public void setDay(String day) {
            this.day = day;
        }

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public String getLng() {
            return lng;
        }

        public void setLng(String lng) {
            this.lng = lng;
        }

        public String getLanguage() {
            return language;
        }

        public void setLanguage(String language) {
            this.language = language;
        }

        public String getLowPhotoUrl() {
            return lowPhotoUrl;
        }

        public void setLowPhotoUrl(String lowPhotoUrl) {
            this.lowPhotoUrl = lowPhotoUrl;
        }

        public String getNormalPhotoUrl() {
            return normalPhotoUrl;
        }

        public void setNormalPhotoUrl(String normalPhotoUrl) {
            this.normalPhotoUrl = normalPhotoUrl;
        }

        public String getBigPhotoUrl() {
            return bigPhotoUrl;
        }

        public void setBigPhotoUrl(String bigPhotoUrl) {
            this.bigPhotoUrl = bigPhotoUrl;
        }

        public String getCoverUrl() {
            return coverUrl;
        }

        public void setCoverUrl(String coverUrl) {
            this.coverUrl = coverUrl;
        }

        public String getOriginCoverUrl() {
            return originCoverUrl;
        }

        public void setOriginCoverUrl(String originCoverUrl) {
            this.originCoverUrl = originCoverUrl;
        }

        public String getAllowMessages() {
            return allowMessages;
        }

        public void setAllowMessages(String allowMessages) {
            this.allowMessages = allowMessages;
        }

        public String getAllowCommentsGCM() {
            return allowCommentsGCM;
        }

        public void setAllowCommentsGCM(String allowCommentsGCM) {
            this.allowCommentsGCM = allowCommentsGCM;
        }

        public String getAllowMessagesGCM() {
            return allowMessagesGCM;
        }

        public void setAllowMessagesGCM(String allowMessagesGCM) {
            this.allowMessagesGCM = allowMessagesGCM;
        }

        public String getAllowCommentReplyGCM() {
            return allowCommentReplyGCM;
        }

        public void setAllowCommentReplyGCM(String allowCommentReplyGCM) {
            this.allowCommentReplyGCM = allowCommentReplyGCM;
        }

        public String getAllowReviewsGCM() {
            return allowReviewsGCM;
        }

        public void setAllowReviewsGCM(String allowReviewsGCM) {
            this.allowReviewsGCM = allowReviewsGCM;
        }

        public String getLastNotifyView() {
            return lastNotifyView;
        }

        public void setLastNotifyView(String lastNotifyView) {
            this.lastNotifyView = lastNotifyView;
        }

        public String getLastAuthorize() {
            return lastAuthorize;
        }

        public void setLastAuthorize(String lastAuthorize) {
            this.lastAuthorize = lastAuthorize;
        }

        public String getLastAuthorizeDate() {
            return lastAuthorizeDate;
        }

        public void setLastAuthorizeDate(String lastAuthorizeDate) {
            this.lastAuthorizeDate = lastAuthorizeDate;
        }

        public String getLastAuthorizeTimeAgo() {
            return lastAuthorizeTimeAgo;
        }

        public void setLastAuthorizeTimeAgo(String lastAuthorizeTimeAgo) {
            this.lastAuthorizeTimeAgo = lastAuthorizeTimeAgo;
        }

        public Boolean getOnline() {
            return online;
        }

        public void setOnline(Boolean online) {
            this.online = online;
        }

        public String getItemsCount() {
            return itemsCount;
        }

        public void setItemsCount(String itemsCount) {
            this.itemsCount = itemsCount;
        }

        public String getReviewsCount() {
            return reviewsCount;
        }

        public void setReviewsCount(String reviewsCount) {
            this.reviewsCount = reviewsCount;
        }

        public String getCommentsCount() {
            return commentsCount;
        }

        public void setCommentsCount(String commentsCount) {
            this.commentsCount = commentsCount;
        }

        public Integer getNotificationsCount() {
            return notificationsCount;
        }

        public void setNotificationsCount(Integer notificationsCount) {
            this.notificationsCount = notificationsCount;
        }

        public Integer getMessagesCount() {
            return messagesCount;
        }

        public void setMessagesCount(Integer messagesCount) {
            this.messagesCount = messagesCount;
        }

    }


}
