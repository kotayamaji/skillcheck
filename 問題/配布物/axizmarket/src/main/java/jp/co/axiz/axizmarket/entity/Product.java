package jp.co.axiz.axizmarket.entity;

import jp.co.axiz.axizmarket.util.ParamUtil;

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
    private Boolean isDeleted;

    public Product() {
    }

    public Product(Integer productId, String productName, Integer price, Integer categoryId, String categoryName,
            String remarks, Boolean isDeleted) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.remarks = remarks;
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
                this.categoryId == null && ParamUtil.isNullOrEmpty(this.remarks);
    }

}