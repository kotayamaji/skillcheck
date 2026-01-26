package jp.co.axiz.web.frame1.dao.Impl;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import jp.co.axiz.web.frame1.dao.UserDao;
import jp.co.axiz.web.frame1.entity.User;

public class UserDaoImple implements UserDao {
    private NamedParameterJdbcTemplate jdbcTemplate;

    private static final String SELECT = "";

    @Override
    public User findByUserNameAndPassword(String loginId, String pass) {

        throw new UnsupportedOperationException("Unimplemented method 'findByUserNameAndPassword'");
    }

}
