package jp.co.axiz.axizmarket.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.axiz.axizmarket.dao.CategoryDao;
import jp.co.axiz.axizmarket.entity.Category;
import jp.co.axiz.axizmarket.service.CategoryService;

/*
 * categoriesテーブル用サービス実装クラス
 */
@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    private CategoryDao categoryDao;

    public CategoryServiceImpl(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Override
    public List<Category> findAll() {
        return categoryDao.findAll();
    }

}
