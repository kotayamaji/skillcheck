package jp.co.axiz.studentmanage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.axiz.studentmanage.dao.MajorDao;
import jp.co.axiz.studentmanage.entity.Major;
import jp.co.axiz.studentmanage.service.MajorService;

/*
 * usersテーブル用サービス実装クラス
 */
@Service
@Transactional
public class MajorServiceImpl implements MajorService {

    @Autowired
    private MajorDao majorDao;

    /**
     * 認証処理
     */
    @Override
    public List<Major> findAll() {
        return majorDao.findAll();
    }

}
