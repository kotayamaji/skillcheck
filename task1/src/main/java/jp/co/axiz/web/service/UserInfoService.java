package jp.co.axiz.web.service;

import java.util.List;

import jp.co.axiz.web.entity.UserInfo;

public interface UserInfoService {
    
    public UserInfo authentication(String loginId, String pass);

    public List<UserInfo> select(UserInfo userInfo);

    public UserInfo findByLoginId(String loginId);

    public boolean existsUserByLoginId(String loginId);

    public void insert(UserInfo userInfo);

    public void update(UserInfo userInfo);

    public boolean existsUserByLoginIdExcludingUserId(String loginId, int findByLoginIdExcludingUserId);

    public void delete(int id);

    public UserInfo findByUserId(Integer integer);

    public void findByUserIdMulti(Integer[] userIds);
}
