package jp.co.axiz.productmanage.entity;

import jp.co.axiz.productmanage.util.ParamUtil;

/**
 * productsテーブルのEntity
 */
public class Product {
    private Integer productId;
    private String productName;
    private Integer price;
    private Integer categoryId;
    private String categoryName;
    private String remarks;
    private Integer userId;
    private String userDispName;
    private Boolean isDeleted;

    public Product() {
    }

    public Product(Integer productId, String productName, Integer price, Integer categoryId, String categoryName,
            String remarks, Integer userId, String userDispName, Boolean isDeleted) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.remarks = remarks;
        this.userId = userId;
        this.userDispName = userDispName;
        this.isDeleted = isDeleted;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserDispName() {
        return userDispName;
    }

    public void setUserDispName(String userDispName) {
        this.userDispName = userDispName;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    /**
     * 検索条件用の項目が全て未入力かを確認する
     * (全て未入力ならtrue)
     */
    public boolean isEmptyCondition() {
        return this.productId == null && ParamUtil.isNullOrEmpty(this.productName) && this.price == null &&
                this.categoryId == null && ParamUtil.isNullOrEmpty(this.remarks) && this.userId == null;
    }

}