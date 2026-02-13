package jp.co.axiz.axizmarket.service;

import java.util.List;

import jp.co.axiz.axizmarket.entity.Category;

/*
 * categoriesテーブル用サービスインターフェース
 */
public interface CategoryService {
    public List<Category> findAll();
}
