package jp.co.axiz.axizmarket.service;

import java.util.List;

import jp.co.axiz.axizmarket.entity.Product;
import jp.co.axiz.axizmarket.entity.User;

/*
 * productsテーブル用サービスインターフェース
 */
public interface ProductService {
    public List<Product> findAll();

    public List<Product> find(Product product);

    public Product findByProductId(Integer productId);

    public void insert(Integer book, User userId);

    public List<Product> findBook(User user);

    public void delete(User loginUser, Integer productId);
}
