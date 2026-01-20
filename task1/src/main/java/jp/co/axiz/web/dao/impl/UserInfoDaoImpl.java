package jp.co.axiz.web.dao.impl;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import jp.co.axiz.web.dao.UserInfoDao;
import jp.co.axiz.web.entity.UserInfo;
import jp.co.axiz.web.util.ParamUtil;

@Repository
public class UserInfoDaoImpl implements UserInfoDao{
    
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    private static final String LOGIN_ID_AND_PASS = "SELECT * FROM user_info JOIN role ON user_info.role_id = role.role_id WHERE login_id =:loginId AND password = :password;";
    private static final String SELECT = "SELECT user_id, login_id, user_name, telephone, password, r.role_id, role_name , create_datetime, update_datetime FROM user_info u JOIN role r ON u.role_id = r.role_id " ;
    private static final String SELECTALL = "SELECT * FROM user_info JOIN role ON user_info.role_id = role.role_id ";
    private static final String ORDER_BY = " ORDER BY user_info.user_id";
    private static final String INSERT = "INSERT INTO user_info (login_id, user_name, telephone, password, role_id,create_datetime,update_datetime) VALUES (:loginId, :userName, :telephone, :password, :roleId, now(), now() )";
    private static final String SELECT_BY_LOGIN_ID = "SELECT user_id, login_id, user_name, telephone, password, r.role_id, role_name FROM user_info u JOIN role r ON u.role_id = r.role_id WHERE login_id = :loginId";
    private static final String UPDATE = "UPDATE user_info SET login_id = :loginId, user_name = :userName, telephone = :telephone, password = :password, role_id = :roleId , update_datetime = now() WHERE user_id = :userId";
    private static final String SELECT_BY_LOGIN_ID_EXCLUDING_USER_ID = "SELECT user_id, login_id, user_name, telephone, password, r.role_id, role_name FROM user_info u JOIN role r ON u.role_id = r.role_id WHERE login_id = :loginId AND user_id <> :userId";
    private static final String DELETE ="DELETE FROM user_info WHERE user_id = :userId";

    private static final String SELECT_BY_USER_ID = "SELECT user_id, login_id, user_name, telephone, password, r.role_id, role_name , create_datetime, update_datetime FROM user_info u JOIN role r ON u.role_id = r.role_id WHERE user_id = :userId";


    @Override
    public void update(UserInfo user) {
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("loginId", user.getLoginId());
        param.addValue("userName", user.getUserName());
        param.addValue("telephone", user.getTelephone());
        param.addValue("password", user.getPassword());
        param.addValue("userId", user.getUserId());
        param.addValue("roleId", user.getRoleId());

        jdbcTemplate.update(UPDATE, param);
    }

    @Override
    public UserInfo findByLoginIdAndPass(String loginId, String pass) {
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("loginId",loginId );
        param.addValue("password",pass );

        List<UserInfo> userInfo = jdbcTemplate.query(LOGIN_ID_AND_PASS,param,new BeanPropertyRowMapper<UserInfo>(UserInfo.class));
        
        return userInfo.isEmpty() ? null : userInfo.get(0);
    }

    @Override
    public List<UserInfo> select(UserInfo userInfo) {
                
        if (userInfo == null || userInfo.isEmptyCondition()) {
            // 検索条件が無い場合は全検索
        	return findAll();
        }

        // 検索条件の有無に応じて、sqlのWHERE句に指定する条件文、
        // Parameterをストックしていく。
        List<String> condition = new ArrayList<String>();
        MapSqlParameterSource param = new MapSqlParameterSource();

        String name = userInfo.getUserName();
        String tel = userInfo.getTelephone();

        if (!ParamUtil.isNullOrEmpty(name)) {
            condition.add("user_info.user_name = :userName");
            param.addValue("userName", name);
        }

        if (!ParamUtil.isNullOrEmpty(tel)) {
            condition.add("user_info.telephone = :telephone");
            param.addValue("telephone", tel);
        }

        // WHERE句の文字列生成
        String whereString = String.join(" AND ", condition.toArray(new String[] {}));

        // SQL文生成
        String sql = SELECT + " AND " + whereString + ORDER_BY;

        // SQL文実行
        List<UserInfo> resultList = jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<UserInfo>(UserInfo.class));

        return resultList;

    }

    @Override
    public List<UserInfo> findAll() {
        List<UserInfo> allList = jdbcTemplate.query(SELECTALL + ORDER_BY,  new BeanPropertyRowMapper<UserInfo>(UserInfo.class));
        return allList;
    }

    /**
     * user_idによる検索
     */
    @Override
    public UserInfo findByLoginId(String loginId) {
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("loginId", loginId);

        List<UserInfo> resultList = jdbcTemplate.query(SELECT_BY_LOGIN_ID, param,
                new BeanPropertyRowMapper<UserInfo>(UserInfo.class));

        return resultList.isEmpty() ? null : resultList.get(0);
    }

    /**
     * 登録
     */
    @Override
    public void insert(UserInfo user) {
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("loginId", user.getLoginId());
        param.addValue("userName", user.getUserName());
        param.addValue("telephone", user.getTelephone());
        param.addValue("password", user.getPassword());
        param.addValue("roleId", user.getRoleId());

        jdbcTemplate.update(INSERT, param);
    }
    
       
    /**
     * user_idによる検索
     * ※指定したuser_idは除外する
     */
    @Override
    public UserInfo findByLoginIdExcludingUserId(String loginId, int excludingUserId) {
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("loginId", loginId);
        param.addValue("userId", excludingUserId);

        List<UserInfo> resultList = jdbcTemplate.query(SELECT_BY_LOGIN_ID_EXCLUDING_USER_ID, param,
                new BeanPropertyRowMapper<UserInfo>(UserInfo.class));

        return resultList.isEmpty() ? null : resultList.get(0);
    }

    @Override
    public void delete(Integer id) {
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("userId", id);

        jdbcTemplate.update(DELETE, param);
    }

    @Override
    public UserInfo findByUserId(Integer userId) {
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("userId", userId);

        List<UserInfo> resultList = jdbcTemplate.query(SELECT_BY_USER_ID, param,
                new BeanPropertyRowMapper<UserInfo>(UserInfo.class));

        return resultList.isEmpty() ? null : resultList.get(0);
    }

    @Override
    public void findByUserIdMulti(Integer[] userIds) {
        MapSqlParameterSource param = new MapSqlParameterSource();
        ArrayList<UserInfo> list = new ArrayList<>();
        
        if(userIds == null || userIds.length == 0){
            return;
        }

        for (Integer userId : userIds) {
            param.addValue("userId", userId);

            jdbcTemplate.update(DELETE, param);

        }

    }
    
}
