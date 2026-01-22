package jp.co.axiz.productmanage.dao;

import java.util.List;

import jp.co.axiz.productmanage.entity.User;
import jp.co.axiz.productmanage.entity.categories;

/*
 * usersテーブル用DAO (インターフェース)
 */
public interface UserDao {
    public User findByUserNameAndPassword(String userName, String password);

    public List<categories> categories();
}
