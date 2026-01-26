package jp.co.axiz.productmanage.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Lombok
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {
	Integer categoryId;
	String categoryName;
}