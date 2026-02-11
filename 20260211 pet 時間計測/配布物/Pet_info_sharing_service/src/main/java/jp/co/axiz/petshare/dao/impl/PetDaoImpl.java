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

    private static final String SELECT = "SELECT pets.id, pets.name, pets.animal_type_id, pets.description, pets.is_deleted, users.password, users.user_name, users.disp_name, pets.user_id, animal_types.name AS animalTypeName FROM animal_types INNER JOIN pets ON animal_types.id = pets.animal_type_id INNER JOIN users ON pets.user_id = users.id WHERE is_deleted = FALSE ";

    private static final String ORDER_BY = " ORDER BY id";

    private static final String INSERT = "INSERT INTO pet_info_sharing.pets(name, animal_type_id, description, user_id) VALUES (:name, :animal_type_id, :description, :user_id)";

    private static final String DELETE = "UPDATE pets SET is_deleted = '1' WHERE id = :id ";

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
        if (pet.getAnimalTypeId() != null) {

            if (pet.isEmptyCondition()) {
                // 検索条件が無い場合は全検索
                return findAll();
            }
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
            condition.add("pets.animal_type_id = :animalTypeId");
            param.addValue("animalTypeId", pet.getAnimalTypeId());
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
    public void register(Pet student) {
        // (:name, :animal_type_id, :description, :user_id)
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("name", student.getName());
        param.addValue("animal_type_id", student.getAnimalTypeId());
        param.addValue("description", student.getDescription());
        param.addValue("user_id", student.getUserId());
        jdbcTemplate.update(INSERT, param);
    }

    @Override
    public void delete(Integer attribute) {
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("id", attribute);

        jdbcTemplate.update(DELETE, param);
    }
}
