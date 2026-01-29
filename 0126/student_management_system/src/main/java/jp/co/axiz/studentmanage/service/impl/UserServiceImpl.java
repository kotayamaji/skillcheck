package jp.co.axiz.studentmanage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.axiz.studentmanage.dao.UserDao;
import jp.co.axiz.studentmanage.entity.Majors;
import jp.co.axiz.studentmanage.entity.User;
import jp.co.axiz.studentmanage.service.UserService;

/*
 * usersテーブル用サービス実装クラス
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userInfoDao;

    /**
     * 認証処理
     */
    @Override
    public User authentication(String loginId, String pass) {
        return userInfoDao.findByUserNameAndPassword(loginId, pass);
    }

    @Override
    public List<Majors> majorsList() {
        return userInfoDao.majorList();
    }

}
