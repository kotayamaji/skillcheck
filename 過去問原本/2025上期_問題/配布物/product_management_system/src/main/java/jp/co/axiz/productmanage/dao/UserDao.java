package jp.co.axiz.productmanage.dao;

import jp.co.axiz.productmanage.entity.User;

/*
 * usersテーブル用DAO (インターフェース)
 */
public interface UserDao {
    public User findByUserNameAndPassword(String userName, String password);
}
