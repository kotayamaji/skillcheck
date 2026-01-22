package jp.co.axiz.petshare.service;

import jp.co.axiz.petshare.entity.User;

/*
 * usersテーブル用サービスインターフェース
 */
public interface UserService {
    public User authentication(String userName, String pass);
}
