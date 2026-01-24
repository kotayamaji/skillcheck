package jp.co.axiz.petshare.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import jp.co.axiz.petshare.dao.AnimalTypesDao;
import jp.co.axiz.petshare.entity.AnimalType;

@Repository
public class AnimalTypesDaoImpl implements AnimalTypesDao {

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	private static final String SELECT_ALL = "SELECT id, name FROM animal_types ORDER BY id";

	/**
	 * 全件取得
	 */
	@Override
	public List<AnimalType> findAll() {
		List<AnimalType> resultList = jdbcTemplate.query(SELECT_ALL,
				new BeanPropertyRowMapper<AnimalType>(AnimalType.class));

		return resultList;
	}

}
