package jp.co.axiz.axizmarket.dao.impl;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import jp.co.axiz.axizmarket.dao.CategoryDao;
import jp.co.axiz.axizmarket.entity.Category;

/*
 * categoriesテーブル用DAO
 */
@Repository
public class CategoryDaoImpl implements CategoryDao {

    private NamedParameterJdbcTemplate jdbcTemplate;

    public CategoryDaoImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final String SELECT_ALL = "SELECT category_id, category_name " +
            "FROM categories " +
            "ORDER BY category_id";

    /**
     * 全件取得
     */
    @Override
    public List<Category> findAll() {
        List<Category> resultList = jdbcTemplate.query(SELECT_ALL,
                new BeanPropertyRowMapper<Category>(Category.class));

        return resultList;
    }

}
