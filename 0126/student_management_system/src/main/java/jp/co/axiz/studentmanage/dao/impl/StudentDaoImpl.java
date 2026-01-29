package jp.co.axiz.studentmanage.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import jp.co.axiz.studentmanage.dao.StudentDao;
import jp.co.axiz.studentmanage.entity.Student;
import jp.co.axiz.studentmanage.util.ParamUtil;

/*
 * studentsテーブル用DAO
 */
@Repository
public class StudentDaoImpl implements StudentDao {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    private static final String SELECT = "SELECT student_id, student_name, grade, hometown, m.major_name " +
            "FROM students s " +
            "JOIN majors m ON s.major_id = m.major_id ";

    private static final String ORDER_BY = " ORDER BY student_id";

    private static final String INSERT = "INSERT INTO student_management.students(student_name, grade, hometown, major_id) VALUES (:studentName, :grade, :hometown, :major_id)";

    private static final String DELETE = "UPDATE students SET is_deleted = 1 WHERE student_id = :studentId";

    private static final String FINDBYID = "SELECT student_name , grade , hometown , major_id FROM students WHERE student_id = :student_id AND is_deleted = 0 ORDER BY student_id ;";
    private static final String UPDATE = "UPDATE student_management.students SET student_name = :student_name, grade = :grade, hometown = :hometown, major_id = :major_id WHERE student_id = :student_id";

    /**
     * 全件取得
     */
    @Override
    public List<Student> findAll() {
        List<Student> resultList = jdbcTemplate.query(SELECT + " WHERE is_deleted = 0 " + ORDER_BY,
                new BeanPropertyRowMapper<Student>(Student.class));

        return resultList;
    }

    /**
     * 条件を指定した検索
     */
    @Override
    public List<Student> find(Student student) {
        if (student == null || student.isEmptyCondition()) {
            // 検索条件が無い場合は全検索
            return findAll();
        }

        // 検索条件の有無に応じて、sqlのWHERE句に指定する条件文、
        // Parameterをストックしていく。
        List<String> condition = new ArrayList<String>();
        MapSqlParameterSource param = new MapSqlParameterSource();

        String studentName = student.getStudentName();
        Integer grade = student.getGrade();

        if (!ParamUtil.isNullOrEmpty(studentName)) {
            condition.add("student_name = :studentName");
            param.addValue("studentName", studentName);
        }

        if (grade != null) {
            condition.add("grade = :grade");
            param.addValue("grade", grade);
        }

        // WHERE句の文字列生成
        String whereString = String.join(" AND ", condition.toArray(new String[] {}));

        // SQL文生成
        String sql = SELECT + " WHERE " + whereString + " AND is_deleted = 0 " + ORDER_BY;

        // SQL文実行
        List<Student> resultList = jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<Student>(Student.class));

        return resultList;
    }

    /**
     * 登録
     */
    @Override
    public void insert(Student student) {
        // :student_name, :grade, :hometown, :major_id
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("studentName", student.getStudentName());
        param.addValue("grade", student.getGrade());
        param.addValue("hometown", student.getHometown());
        param.addValue("major_id", student.getMajorId());

        jdbcTemplate.update(INSERT, param);
    }

    /**
     * 削除
     */
    @Override
    public void delete(Integer studentId) {
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("studentId", studentId);

        jdbcTemplate.update(DELETE, param);
    }

    @Override
    public List<Student> findById(Integer id) {
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("student_id", id);
        List<Student> resultList = jdbcTemplate.query(FINDBYID, param,
                new BeanPropertyRowMapper<Student>(Student.class));
        return resultList;
    }

    @Override
    public void update(Student student) {
        // :student_name, grade = :grade, hometown = :hometown, major_id = :major_id
        // WHERE student_id = :student_id
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("student_name", student.getStudentName());
        param.addValue("student_id", student.getStudentId());
        param.addValue("grade", student.getGrade());
        param.addValue("hometown", student.getHometown());
        param.addValue("major_id", student.getMajorId());
        jdbcTemplate.update(UPDATE, param);;
    }
}
