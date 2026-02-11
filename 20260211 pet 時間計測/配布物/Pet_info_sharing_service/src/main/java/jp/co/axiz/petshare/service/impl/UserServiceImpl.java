package jp.co.axiz.petshare.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.axiz.petshare.dao.UserDao;
import jp.co.axiz.petshare.entity.AnimalType;
import jp.co.axiz.petshare.entity.User;
import jp.co.axiz.petshare.service.UserService;

/*
 * user_infoテーブル用サービス実装クラス
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
    public List<AnimalType> animalTypes() {
        // TODO Auto-generated method stub
        return userInfoDao.animalType();
    }

}
