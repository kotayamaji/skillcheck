package jp.co.axiz.petshare.service;

import java.util.List;

import jp.co.axiz.petshare.entity.Pet;

/*
 * petsテーブル用サービスインターフェース
 */
public interface PetService {
    public List<Pet> findAll();

    public List<Pet> find(Pet pet);

    public Pet findById(Integer id);

    public void register(Pet pet, Integer string);

    public void delete(Integer attribute);
}
