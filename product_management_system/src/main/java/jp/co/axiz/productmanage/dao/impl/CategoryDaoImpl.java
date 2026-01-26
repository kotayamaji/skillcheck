package jp.co.axiz.productmanage.dao.impl;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import jp.co.axiz.productmanage.dao.CategoryDao;
import jp.co.axiz.productmanage.entity.Category;

/*
 * user_infoテーブル用DAO
 */
@Repository
public class CategoryDaoImpl implements CategoryDao {

	private NamedParameterJdbcTemplate jdbcTemplate;

	private static final String SELECT = "select * from categories";
	private static final String ORDER_BY = " ORDER BY category_id";

	public CategoryDaoImpl(NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<Category> getCategories() {
		List<Category> resultList = jdbcTemplate.query(SELECT + ORDER_BY,
				new BeanPropertyRowMapper<Category>(Category.class));

		return resultList;
	}

}