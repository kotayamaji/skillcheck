package jp.co.axiz.axizmarket.dao;

import java.util.List;

import jp.co.axiz.axizmarket.entity.Bookmark;
import jp.co.axiz.axizmarket.entity.Product;
import jp.co.axiz.axizmarket.entity.User;

/*
 * productsテーブル用DAO (インターフェース)
 */
public interface ProductDao {
    public List<Product> findAll();

    public List<Product> find(Product product);

    public void insert(Integer book, User user);

    public Bookmark confirm(Integer book, User user);

    public List<Product> findBook(User user);

    public void delete(User loginUser, Integer productId);
}
