package jp.co.axiz.petshare.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import jp.co.axiz.petshare.dao.UserDao;
import jp.co.axiz.petshare.entity.AnimalType;
import jp.co.axiz.petshare.entity.User;

/*
 * usersテーブル用DAO
 */
@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    private static final String SELECT_BY_USER_NAME_AND_PASSWORD = "SELECT id , user_name , password , disp_name FROM pet_info_sharing.users WHERE user_name = :user_name AND password = :password ;";

    private static final String ANIMALTYPE = "SELECT * FROM animal_types ;";

    /**
     * user_name、passwordによる検索
     */
    @Override
    public User findByUserNameAndPassword(String userName, String password) {
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("user_name", userName);
        param.addValue("password", password);

        List<User> resultList = jdbcTemplate.query(SELECT_BY_USER_NAME_AND_PASSWORD, param,
                new BeanPropertyRowMapper<User>(User.class));

        return resultList.isEmpty() ? null : resultList.get(0);
    }

    @Override
    public List<AnimalType> animaltype() {
        List<AnimalType> resultList = jdbcTemplate.query(ANIMALTYPE,
                new BeanPropertyRowMapper<AnimalType>(AnimalType.class));

        return resultList;
    }

}
