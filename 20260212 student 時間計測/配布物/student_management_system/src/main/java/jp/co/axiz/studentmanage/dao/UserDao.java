package jp.co.axiz.studentmanage.dao;

import java.util.List;

import jp.co.axiz.studentmanage.entity.Major;
import jp.co.axiz.studentmanage.entity.User;

/*
 * usersテーブル用DAO (インターフェース)
 */
public interface UserDao {
    public User findByUserNameAndPassword(String userName, String password);

    public List<Major> majors();
}
