package jp.co.axiz.petshare.dao;

import java.util.List;

import jp.co.axiz.petshare.entity.AnimalType;
import jp.co.axiz.petshare.entity.User;

/*
 * usersテーブル用DAO (インターフェース)
 */
public interface UserDao {
    public User findByUserNameAndPassword(String userName, String password);

    public List<AnimalType> animaltype();
}
