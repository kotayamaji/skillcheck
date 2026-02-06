package jp.co.axiz.studentmanage.service;

import jp.co.axiz.studentmanage.entity.Major;
import jp.co.axiz.studentmanage.entity.User;

/*
 * usersテーブル用サービスインターフェース
 */
public interface UserService {
    public User authentication(String userName, String pass);

    public java.util.List<Major> major();
}
