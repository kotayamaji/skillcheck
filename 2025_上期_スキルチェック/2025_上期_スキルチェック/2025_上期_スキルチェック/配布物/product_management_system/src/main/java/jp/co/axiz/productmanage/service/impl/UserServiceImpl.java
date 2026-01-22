package jp.co.axiz.productmanage.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.axiz.productmanage.dao.UserDao;
import jp.co.axiz.productmanage.entity.User;
import jp.co.axiz.productmanage.service.UserService;
import jp.co.axiz.productmanage.entity.categories;
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

    @Override
    public List<categories> categories() {
        return userDao.categories();
    }

}
