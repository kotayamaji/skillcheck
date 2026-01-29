package jp.co.axiz.studentmanage.service;

import java.util.List;

import jp.co.axiz.studentmanage.entity.Majors;
import jp.co.axiz.studentmanage.entity.User;

/*
 * usersテーブル用サービスインターフェース
 */
public interface UserService {
    public User authentication(String userName, String pass);

    public List<Majors> majorsList();
}
