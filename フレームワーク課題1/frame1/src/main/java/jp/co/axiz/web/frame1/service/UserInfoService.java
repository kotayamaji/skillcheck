package jp.co.axiz.web.frame1.service;

import jp.co.axiz.web.frame1.entity.User;

public interface UserInfoService {

    User authentication(String loginId, String password);

}
