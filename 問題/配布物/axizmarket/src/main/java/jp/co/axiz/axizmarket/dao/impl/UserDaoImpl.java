package jp.co.axiz.axizmarket.dao.impl;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import jp.co.axiz.axizmarket.dao.UserDao;
import jp.co.axiz.axizmarket.entity.User;

/*
 * usersテーブル用DAO
 */
@Repository
public class UserDaoImpl implements UserDao {

    private NamedParameterJdbcTemplate jdbcTemplate;

    private static final String SELECT_BY_USER_NAME_AND_PASSWORD = "SELECT user_id, user_name, password, disp_name " +
            "FROM users u " +
            "WHERE user_name = :userName AND password = :password AND is_active = true ";

    public UserDaoImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * user_name、passwordによる検索
     */
    @Override
    public User findByUserNameAndPassword(String userName, String password) {
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("userName", userName);
        param.addValue("password", password);

        List<User> resultList = jdbcTemplate.query(SELECT_BY_USER_NAME_AND_PASSWORD, param,
                new BeanPropertyRowMapper<User>(User.class));

        return resultList.isEmpty() ? null : resultList.get(0);
    }

}
