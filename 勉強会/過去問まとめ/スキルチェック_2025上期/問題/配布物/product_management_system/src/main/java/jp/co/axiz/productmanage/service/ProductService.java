package jp.co.axiz.productmanage.service;

import java.util.List;

import jp.co.axiz.productmanage.entity.Product;

/*
 * productsテーブル用サービスインターフェース
 */
public interface ProductService {
    public List<Product> findAll();

    public List<Product> find(Product product);

    public Product findByProductId(Integer productId);
}
