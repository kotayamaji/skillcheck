package jp.co.axiz.web.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import jp.co.axiz.web.dao.RoleDao;
import jp.co.axiz.web.entity.Role;

@Repository
public class RoleDaoImple implements RoleDao {
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    private static final String ALL ="SELECT * FROM role;";

    @Override
    public List<Role> findAll() {
        List<Role> roleList = jdbcTemplate.query(ALL, new BeanPropertyRowMapper<Role>(Role.class));
        return roleList;
    }
}
