package com.zoolife.app.models;

public class SubCategoryModel {

    public String subCategoryName;
    public String subCategoryId;

    public SubCategoryModel(String subCategoryName) {
        this.subCategoryName = subCategoryName;
    }

    public SubCategoryModel(String subCategoryName, String subCategoryId) {
        this.subCategoryName = subCategoryName;
        this.subCategoryId = subCategoryId;
    }

    public String getSubCategoryId() {
        return subCategoryId;
    }

    public void setSubCategoryId(String subCategoryId) {
        this.subCategoryId = subCategoryId;
    }

    public void setSubCategoryName(String subCategoryName) {
        this.subCategoryName = subCategoryName;
    }

    public String getSubCategoryName() {
        return subCategoryName;
    }
}
