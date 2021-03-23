package com.zoolife.app.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddAdModel {

    @SerializedName("authenticity_token")
    @Expose
    private String authenticityToken;
    @SerializedName("lat")
    @Expose
    private String lat;
    @SerializedName("lng")
    @Expose
    private String lng;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("citt")
    @Expose
    private String citt;
    @SerializedName("ajax")
    @Expose
    private Integer ajax;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("categoryId")
    @Expose
    private String categoryId;
    @SerializedName("subcategoryId")
    @Expose
    private String subcategoryId;
    @SerializedName("currency")
    @Expose
    private String currency;
    @SerializedName("price")
    @Expose
    private Integer price;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("images[0][name]")
    @Expose
    private String images0Name;
    @SerializedName("images[0][type]")
    @Expose
    private String images0Type;
    @SerializedName("images[1][name]")
    @Expose
    private String images1Name;
    @SerializedName("images[1][type]")
    @Expose
    private String images1Type;
    @SerializedName("phoneNumber")
    @Expose
    private String phoneNumber;
    @SerializedName("user_id")
    @Expose
    private String userId;

    public String getAuthenticityToken() {
        return authenticityToken;
    }

    public void setAuthenticityToken(String authenticityToken) {
        this.authenticityToken = authenticityToken;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCitt() {
        return citt;
    }

    public void setCitt(String citt) {
        this.citt = citt;
    }

    public Integer getAjax() {
        return ajax;
    }

    public void setAjax(Integer ajax) {
        this.ajax = ajax;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getSubcategoryId() {
        return subcategoryId;
    }

    public void setSubcategoryId(String subcategoryId) {
        this.subcategoryId = subcategoryId;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImages0Name() {
        return images0Name;
    }

    public void setImages0Name(String images0Name) {
        this.images0Name = images0Name;
    }

    public String getImages0Type() {
        return images0Type;
    }

    public void setImages0Type(String images0Type) {
        this.images0Type = images0Type;
    }

    public String getImages1Name() {
        return images1Name;
    }

    public void setImages1Name(String images1Name) {
        this.images1Name = images1Name;
    }

    public String getImages1Type() {
        return images1Type;
    }

    public void setImages1Type(String images1Type) {
        this.images1Type = images1Type;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
