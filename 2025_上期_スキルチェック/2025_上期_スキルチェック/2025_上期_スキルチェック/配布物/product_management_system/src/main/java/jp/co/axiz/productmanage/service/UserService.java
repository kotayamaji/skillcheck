package jp.co.axiz.productmanage.service;

import java.util.List;

import jp.co.axiz.productmanage.entity.User;
import jp.co.axiz.productmanage.entity.categories;

/*
 * usersテーブル用サービスインターフェース
 */
public interface UserService {
    public User authentication(String userName, String pass);

    public List<categories> categories();
}
