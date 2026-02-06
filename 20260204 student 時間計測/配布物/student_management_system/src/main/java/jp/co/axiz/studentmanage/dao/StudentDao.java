package jp.co.axiz.studentmanage.dao;

import java.util.List;

import jp.co.axiz.studentmanage.entity.Student;

/*
 * studentsテーブル用DAO (インターフェース)
 */
public interface StudentDao {
	public List<Student> findAll();

	public List<Student> find(Student student);

	public void insert(Student student);

	public void delete(Integer userId);

	public Student findById(Integer updateStudentId);
}
