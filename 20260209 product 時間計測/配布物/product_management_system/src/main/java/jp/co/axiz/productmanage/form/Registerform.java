package jp.co.axiz.productmanage.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Registerform {
    @NotBlank
    private String productName;
    private Integer categoryId;
    private String categoryName;
    private String remarks;
    @NotNull
    private Integer price;
}
