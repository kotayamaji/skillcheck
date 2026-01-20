package jp.co.axiz.web.dao;

import java.util.List;

import jp.co.axiz.web.entity.UserInfo;

public interface UserInfoDao {
    public UserInfo findByLoginIdAndPass(String loginId, String pass);

    public List<UserInfo> select(UserInfo userInfo);

    public List<UserInfo> findAll();

    public UserInfo findByLoginId(String loginId);

    public void insert(UserInfo user);

    public void update(UserInfo user);

    public UserInfo findByLoginIdExcludingUserId(String loginId, int excludingUserId);

    public void delete(Integer id);

    public UserInfo findByUserId(Integer userId);

    public void findByUserIdMulti(Integer[] userIds);
}
