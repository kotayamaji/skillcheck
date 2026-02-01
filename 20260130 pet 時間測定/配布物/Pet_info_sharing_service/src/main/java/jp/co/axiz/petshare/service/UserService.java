package jp.co.axiz.petshare.service;

import jp.co.axiz.petshare.entity.User;

import java.util.List;

import jp.co.axiz.petshare.entity.AnimalType;

/*
 * usersテーブル用サービスインターフェース
 */
public interface UserService {
    public User authentication(String userName, String pass);

    public List<AnimalType> animalTypes();
}
