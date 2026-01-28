package jp.co.axiz.studentmanage.dao;

import java.util.List;

import jp.co.axiz.studentmanage.entity.Major;

/*
 * usersテーブル用DAO (インターフェース)
 */
public interface MajorDao {
    public List<Major> findAll();
}
