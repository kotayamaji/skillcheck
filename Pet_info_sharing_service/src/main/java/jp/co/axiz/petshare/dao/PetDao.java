package jp.co.axiz.petshare.dao;

import java.util.List;

import jp.co.axiz.petshare.entity.Pet;

/*
 * petsテーブル用DAO (インターフェース)
 */
public interface PetDao {
    public List<Pet> findAll();

    public List<Pet> find(Pet pet);

    public void register(Pet pet);
}
