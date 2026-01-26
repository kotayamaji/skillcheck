package jp.co.axiz.productmanage.dao;

import java.util.List;

import jp.co.axiz.productmanage.entity.Category;

/*
 * productsテーブル用DAO (インターフェース)
 */
public interface CategoryDao {
	public List<Category> getCategories();
}
