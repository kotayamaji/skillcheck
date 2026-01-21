package jp.co.example.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * トップ画面用フォーム
 */
public class ProductForm {
    private Integer productId;

    @NotBlank
    private String productName;

    @NotNull
    private Integer price;

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

    public void setPrice(Integer _price) {
        this.price = _price;
    }

    public Integer getPrice() {
        return price;
    }
}
