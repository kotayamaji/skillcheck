package jp.co.axiz.web.frame1.dao;

import jp.co.axiz.web.frame1.entity.User;

public interface UserDao {

    User findByUserNameAndPassword(String loginId, String pass);

}
