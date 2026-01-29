package jp.co.axiz.studentmanage.service;

import java.util.List;

import jp.co.axiz.studentmanage.entity.Student;

/*
 * studentsテーブル用サービスインターフェース
 */
public interface StudentService {
    public List<Student> findAll();

    public List<Student> find(Student student);

    public void insert(Student student);

    public void delete(Integer id);

    public Student findById(Integer id);

    public void update(Student student);
}
