package jp.co.axiz.productmanage.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * 検索画面用フォーム
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchForm {

    private String productName;
    private Integer categoryId;
    private String categoryName;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

}
