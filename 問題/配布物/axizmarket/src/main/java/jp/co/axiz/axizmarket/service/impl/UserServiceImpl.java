package jp.co.axiz.axizmarket.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.axiz.axizmarket.dao.UserDao;
import jp.co.axiz.axizmarket.entity.User;
import jp.co.axiz.axizmarket.service.UserService;

/*
 * user_infoテーブル用サービス実装クラス
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    /**
     * 認証処理
     */
    @Override
    public User authentication(String loginId, String pass) {
        return userDao.findByUserNameAndPassword(loginId, pass);
    }

}
