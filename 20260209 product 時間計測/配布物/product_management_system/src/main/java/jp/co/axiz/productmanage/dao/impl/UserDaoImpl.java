package jp.co.axiz.productmanage.dao.impl;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import jp.co.axiz.productmanage.dao.UserDao;
import jp.co.axiz.productmanage.entity.Category;
import jp.co.axiz.productmanage.entity.User;

/*
 * usersテーブル用DAO
 */
@Repository
public class UserDaoImpl implements UserDao {

    private NamedParameterJdbcTemplate jdbcTemplate;
    // "SELECT products.product_id, products.product_name, products.price,
    // products.remarks, products.user_id, products.is_deleted,
    // categories.category_name, users.user_name, users.password, users.disp_name,
    // users.role_id, users.is_active, roles.role_name FROM users INNER JOIN
    // products ON users.user_id = products.user_id INNER JOIN categories ON
    // products.category_id = categories.category_id INNER JOIN roles ON
    // roles.role_id = users.role_id "

    private static final String SELECT_BY_USER_NAME_AND_PASSWORD = "SELECT users.user_id, users.user_name, users.password, users.disp_name, users.role_id, users.is_active, roles.role_name FROM users INNER JOIN roles ON roles.role_id = users.role_id "
            +
            "WHERE user_name = :userName AND password = :password AND is_active = 1 ";

    private static final String CATEGORY = "SELECT * FROM categories";

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

    @Override
    public List<Category> categorys() {
        List<Category> resultList = jdbcTemplate.query(
                CATEGORY,
                new BeanPropertyRowMapper<Category>(Category.class));

        return resultList;
    }

}
