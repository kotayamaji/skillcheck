package jp.co.axiz.petshare.dao;

import jp.co.axiz.petshare.entity.User;

/*
 * usersテーブル用DAO (インターフェース)
 */
public interface UserDao {
    public User findByUserNameAndPassword(String userName, String password);
}
