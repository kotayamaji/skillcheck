package jp.co.axiz.web.frame1.service.Impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.axiz.web.frame1.dao.UserDao;
import jp.co.axiz.web.frame1.entity.User;
import jp.co.axiz.web.frame1.service.UserInfoService;

@Service
@Transactional
public class UserInfoServiceimpl implements UserInfoService {

    private UserDao userDao;

    @Override
    public User authentication(String loginId, String pass) {
        return userDao.findByUserNameAndPassword(loginId, pass);
    }

}
