package jp.co.axiz.productmanage.dao;

import java.util.List;

import jp.co.axiz.productmanage.entity.Product;
import jp.co.axiz.productmanage.form.InsertForm;

/*
 * productsテーブル用DAO (インターフェース)
 */
public interface ProductDao {
	public List<Product> findAll();

	public List<Product> find(Product product);

	public void insert(InsertForm insertForm, Integer userId);

}
