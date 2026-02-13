package jp.co.axiz.axizmarket.entity;

/**
 * bookmarksテーブルのEntity
 */
public class Bookmark {
    private Integer userId;
    private Integer productId;

    public Bookmark() {
    }

    public Bookmark(Integer userId, Integer productId) {
        this.userId = userId;
        this.productId = productId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

}