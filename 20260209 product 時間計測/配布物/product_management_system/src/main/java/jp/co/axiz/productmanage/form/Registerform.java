package jp.co.axiz.productmanage.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Registerform {
    private String productName;
    private Integer categoryId;
    private String categoryName;
    private String remarks;
    private Integer price;
}
