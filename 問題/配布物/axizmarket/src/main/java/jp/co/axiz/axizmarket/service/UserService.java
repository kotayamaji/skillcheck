package jp.co.axiz.axizmarket.service;

import jp.co.axiz.axizmarket.entity.User;

/*
 * usersテーブル用サービスインターフェース
 */
public interface UserService {
    public User authentication(String userName, String pass);
}
