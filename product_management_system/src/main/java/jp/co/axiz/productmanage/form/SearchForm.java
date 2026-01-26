package jp.co.axiz.productmanage.form;

/*
 * 検索画面用フォーム
 */
public class SearchForm {

	private String productName;
	private Integer categoryId;

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
}
