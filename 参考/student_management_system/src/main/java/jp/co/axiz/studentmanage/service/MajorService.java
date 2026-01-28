package jp.co.axiz.studentmanage.service;

import java.util.List;

import jp.co.axiz.studentmanage.entity.Major;

/*
 * usersテーブル用サービスインターフェース
 */
public interface MajorService {
    public List<Major> findAll();
}
