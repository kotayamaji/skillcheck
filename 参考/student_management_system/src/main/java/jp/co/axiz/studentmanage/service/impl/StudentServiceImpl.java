package jp.co.axiz.studentmanage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.axiz.studentmanage.dao.StudentDao;
import jp.co.axiz.studentmanage.entity.Student;
import jp.co.axiz.studentmanage.service.StudentService;

/*
 * studentsテーブル用サービス実装クラス
 */
@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentDao studentDao;

    @Override
    public List<Student> findAll() {
        return studentDao.findAll();
    }

    @Override
    public List<Student> find(Student student) {
        return studentDao.find(student);
    }

    
    @Override
    public Student findById(Integer studentId) {
    	return studentDao.findById(studentId);
    }
    
    @Override
    public void update(Student student) {
        studentDao.update(student);
    }

    
    @Override
    public void insert(Student student) {
        studentDao.insert(student);
    }

    @Override
    public void delete(Integer id) {
        studentDao.delete(id);
    }
}
