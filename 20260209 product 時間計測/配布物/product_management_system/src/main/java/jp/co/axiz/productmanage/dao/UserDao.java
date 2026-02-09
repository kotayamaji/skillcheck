package jp.co.axiz.productmanage.dao;

import java.util.List;

import jp.co.axiz.productmanage.entity.Category;
import jp.co.axiz.productmanage.entity.User;

/*
 * usersテーブル用DAO (インターフェース)
 */
public interface UserDao {
    public User findByUserNameAndPassword(String userName, String password);

    public List<Category> categorys();
}
