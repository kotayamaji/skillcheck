package jp.co.axiz.axizmarket.dao;

import jp.co.axiz.axizmarket.entity.User;

/*
 * usersテーブル用DAO (インターフェース)
 */
public interface UserDao {
    public User findByUserNameAndPassword(String userName, String password);
}
