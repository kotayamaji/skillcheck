package jp.co.axiz.petshare.service;

import java.util.List;

import jp.co.axiz.petshare.entity.AnimalType;
import jp.co.axiz.petshare.entity.User;

/*
 * usersテーブル用サービスインターフェース
 */
public interface UserService {
    public User authentication(String userName, String pass);

    public List<AnimalType> animalTypes();
}
