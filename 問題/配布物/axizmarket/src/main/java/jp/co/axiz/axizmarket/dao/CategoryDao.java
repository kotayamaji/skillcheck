package jp.co.axiz.axizmarket.dao;

import java.util.List;

import jp.co.axiz.axizmarket.entity.Category;

/*
 * categoriesテーブル用DAO (インターフェース)
 */
public interface CategoryDao {
    public List<Category> findAll();
}
