package jp.co.axiz.productmanage.dao;

import java.util.List;

import jp.co.axiz.productmanage.entity.Product;

/*
 * productsテーブル用DAO (インターフェース)
 */
public interface ProductDao {
    public List<Product> findAll();

    public List<Product> find(Product product);
}
