package jp.co.axiz.web.service.imple;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.axiz.web.dao.UserInfoDao;
import jp.co.axiz.web.entity.UserInfo;
import jp.co.axiz.web.service.UserInfoService;

@Service
@Transactional
public class UserInfoServiceImple implements UserInfoService {

    @Autowired
    private UserInfoDao userInfoDao;

    @Override
    public UserInfo authentication(String loginId, String pass) {
        return userInfoDao.findByLoginIdAndPass(loginId,pass);
    }

    @Override
    public List<UserInfo> select(UserInfo userInfo) {
        return userInfoDao.select(userInfo);
    }

    /**
     * user_idによる検索
     */
    @Override
    public UserInfo findByLoginId(String loginId) {
        return userInfoDao.findByLoginId(loginId);
    }

    @Override
    public boolean existsUserByLoginId(String loginId) {
        return findByLoginId(loginId) != null;
    }

    /**
     * 登録
     */
    @Override
    public void insert(UserInfo userInfo) {
        userInfoDao.insert(userInfo);
    }
    
        
    @Override
    public void update(UserInfo userInfo) {
        userInfoDao.update(userInfo);
    }

    @Override
    public boolean existsUserByLoginIdExcludingUserId(String loginId, int findByLoginIdExcludingUserId) {
        return userInfoDao.findByLoginIdExcludingUserId(loginId, findByLoginIdExcludingUserId) != null;
    }

    @Override
    public void delete(int id) {
        userInfoDao.delete(id);
    }

    @Override
    public UserInfo findByUserId(Integer userId) {
       return userInfoDao.findByUserId(userId);
    }

    @Override
    public List<UserInfo> findByUserIdMulti(Integer[] userIds) {
        return userInfoDao.findByUserIdMulti(userIds);
    }

    
    
}
