package jp.co.axiz.productmanage.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

/*
 * 検索画面用フォーム
 */
public class InsertForm {
    @NotBlank
    private String productName;
    @NotBlank
    @Pattern(regexp = "^\\d+$", message = "価格には数値を入力してください")
    private Integer price;
    private Integer categoryId;
    private String remarks;

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

}
