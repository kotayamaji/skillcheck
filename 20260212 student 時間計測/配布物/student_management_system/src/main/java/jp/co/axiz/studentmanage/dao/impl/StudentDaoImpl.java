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

    private static final String SELECT = "SELECT students.student_name, students.grade, students.hometown, students.is_deleted, majors.major_name, students.major_id FROM students INNER JOIN majors ON majors.major_id = students.major_id";

    private static final String ORDER_BY = " ORDER BY student_id";

    private static final String INSERT = "INSERT INTO students (student_name, grade) " +
            "VALUES (:studentName, :grade)";

    private static final String DELETE = "DELETE FROM students WHERE student_id = :studentId";

    /**
     * 全件取得
     */
    @Override
    public List<Student> findAll() {
        List<Student> resultList = jdbcTemplate.query(SELECT + " WHERE students.is_deleted = 0 " + ORDER_BY,
                new BeanPropertyRowMapper<Student>(Student.class));

        return resultList;
    }

    /**
     * 条件を指定した検索
     */
    @Override
    public List<Student> find(Student student) {
        if (student.getGrade() == null) {
            if (student == null || student.isEmptyCondition()) {
                // 検索条件が無い場合は全検索
                return findAll();
            }
        }

        // 検索条件の有無に応じて、sqlのWHERE句に指定する条件文、
        // Parameterをストックしていく。
        List<String> condition = new ArrayList<String>();
        MapSqlParameterSource param = new MapSqlParameterSource();

        String studentName = student.getStudentName();

        if (!ParamUtil.isNullOrEmpty(studentName)) {
            condition.add("students.student_name = :studentName");
            param.addValue("studentName", studentName);
        }

        if (student.getGrade() != null) {
            condition.add("students.grade = :grade");
            param.addValue("grade", student.getGrade());
        }

        // WHERE句の文字列生成
        String whereString = String.join(" AND ", condition.toArray(new String[] {}));

        // SQL文生成
        String sql = SELECT + " WHERE " + whereString + " AND students.is_deleted = 0 " + ORDER_BY;

        // SQL文実行
        List<Student> resultList = jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<Student>(Student.class));

        return resultList;
    }

    /**
     * 登録
     */
    @Override
    public void insert(Student student) {
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("studentName", student.getStudentName());
        param.addValue("grade", student.getGrade());

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
}
