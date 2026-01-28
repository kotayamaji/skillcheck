package jp.co.axiz.studentmanage.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import jp.co.axiz.studentmanage.dao.MajorDao;
import jp.co.axiz.studentmanage.entity.Major;

/*
 * usersテーブル用DAO
 */
@Repository
public class MajorDaoImpl implements MajorDao {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    private static final String FINDALL ="SELECT * from majors order by major_id ";
    /**
     * user_name、passwordによる検索
     */
    @Override
    public List<Major> findAll() {
        

        List<Major> resultList = jdbcTemplate.query(FINDALL,
                new BeanPropertyRowMapper<Major>(Major.class));

        return resultList;
    }

}
