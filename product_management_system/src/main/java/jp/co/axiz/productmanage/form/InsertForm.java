package jp.co.axiz.productmanage.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InsertForm {
	@NotBlank(message = "商品名は必須です")
	private String productName;
	@NotNull(message = "価格は必須です")
	private Integer price;
	private Integer categoryId;
	private String remarks;
}
