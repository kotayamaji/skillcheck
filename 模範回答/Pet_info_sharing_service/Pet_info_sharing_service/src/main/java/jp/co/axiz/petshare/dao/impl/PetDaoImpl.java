package jp.co.axiz.petshare.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import jp.co.axiz.petshare.dao.PetDao;
import jp.co.axiz.petshare.entity.Pet;
import jp.co.axiz.petshare.util.ParamUtil;

/*
 * user_infoテーブル用DAO
 */
@Repository
public class PetDaoImpl implements PetDao {

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	private static final String SELECT = "SELECT pets.id, pets.name, animal_type_id, description, user_id, is_deleted, "
			+ "disp_name as user_name , animal_types.name as animal_types_name "
			+ "FROM pets "
			+ "JOIN users ON user_id = users.id "
			+ "JOIN animal_types ON animal_type_id = animal_types.id WHERE is_deleted = FALSE";

	private static final String ORDER_BY = " ORDER BY pets.id";

	private static final String REGISTER = "INSERT INTO pets (name, animal_type_id, description, user_id, is_deleted) VALUES(:name, :typeId, :description, :userId, :isDeleted)";

	private static final String DELETE = "UPDATE pets SET is_deleted = TRUE WHERE id = :id";

	/**
	 * 全件取得
	 */
	@Override
	public List<Pet> findAll() {
		List<Pet> resultList = jdbcTemplate.query(SELECT + ORDER_BY, new BeanPropertyRowMapper<Pet>(Pet.class));

		return resultList;
	}

	/**
	 * 条件を指定した検索
	 */
	@Override
	public List<Pet> find(Pet pet) {

		if (pet == null || pet.isEmptyCondition()) {
			// 検索条件が無い場合は全検索
			return findAll();
		}

		// 検索条件の有無に応じて、sqlのWHERE句に指定する条件文、
		// Parameterをストックしていく。
		List<String> condition = new ArrayList<String>();
		MapSqlParameterSource param = new MapSqlParameterSource();

		Integer id = pet.getId();
		String name = pet.getName();

		if (id != null) {
			condition.add("pets.id = :id");
			param.addValue("id", id);
		}

		if (!ParamUtil.isNullOrEmpty(name)) {
			condition.add("pets.name = :name");
			param.addValue("name", name);
		}

		if (pet.getAnimalTypeId() != null) {
			condition.add("pets.animal_type_id = :id");
			param.addValue("id", pet.getAnimalTypeId());
		}

		// WHERE句の文字列生成
		String whereString = String.join(" AND ", condition.toArray(new String[] {}));

		// SQL文生成
		String sql = SELECT + " AND " + whereString + ORDER_BY;

		// SQL文実行
		List<Pet> resultList = jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<Pet>(Pet.class));

		return resultList;
	}

	@Override
	public void register(Pet pet) {
		// TODO 自動生成されたメソッド・スタブ
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("name", pet.getName());
		param.addValue("typeId", pet.getAnimalTypeId());
		param.addValue("description", pet.getDescription());
		param.addValue("userId", pet.getUserId());
		param.addValue("isDeleted", pet.getIsDeleted());

		jdbcTemplate.update(REGISTER, param);
	}

	@Override
	public void delete(Integer id) {
		// TODO 自動生成されたメソッド・スタブ
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("id", id);
		jdbcTemplate.update(DELETE, param);
	}
}
