package jp.co.axiz.productmanage.service;

import jp.co.axiz.productmanage.entity.User;

/*
 * usersテーブル用サービスインターフェース
 */
public interface UserService {
    public User authentication(String userName, String pass);
}
