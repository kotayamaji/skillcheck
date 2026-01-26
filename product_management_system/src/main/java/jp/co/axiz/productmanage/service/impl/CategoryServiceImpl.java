package jp.co.axiz.productmanage.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.axiz.productmanage.controller.CategoryService;
import jp.co.axiz.productmanage.dao.CategoryDao;
import jp.co.axiz.productmanage.entity.Category;

/*
 * productsテーブル用サービス実装クラス
 */
@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

	private CategoryDao categoryDao;

	public CategoryServiceImpl(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	@Override
	public List<Category> findCategories() {
		return categoryDao.getCategories();
	}

}