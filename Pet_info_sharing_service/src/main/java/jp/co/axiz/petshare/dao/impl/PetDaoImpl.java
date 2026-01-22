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

    private static final String SELECT = "SELECT p.id, p.name, p.animal_type_id, " +
            "p.description, p.user_id, p.is_deleted, u.disp_name, t.name AS type " +
            "FROM pets p JOIN users u ON p.user_id = u.id JOIN animal_types t ON p.animal_type_id = t.id " +
            "WHERE p.is_deleted = FALSE ";

    private static final String ORDER_BY = " ORDER BY p.id";

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
            condition.add("p.id = :id");
            param.addValue("id", id);
        }

        if (!ParamUtil.isNullOrEmpty(name)) {
            condition.add("name = :name");
            param.addValue("p.name", name);
        }

        // WHERE句の文字列生成
        String whereString = String.join(" AND ", condition.toArray(new String[] {}));

        // SQL文生成
        String sql = SELECT + " AND " + whereString + ORDER_BY;

        // SQL文実行
        List<Pet> resultList = jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<Pet>(Pet.class));

        return resultList;
    }
}
