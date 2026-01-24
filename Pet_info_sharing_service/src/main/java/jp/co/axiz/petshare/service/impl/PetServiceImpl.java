package jp.co.axiz.petshare.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.axiz.petshare.dao.PetDao;
import jp.co.axiz.petshare.entity.Pet;
import jp.co.axiz.petshare.service.PetService;

/*
 * petsテーブル用サービス実装クラス
 */
@Service
@Transactional
public class PetServiceImpl implements PetService {

    @Autowired
    private PetDao petDao;

    @Override
    public List<Pet> findAll() {
        return petDao.findAll();
    }

    @Override
    public List<Pet> find(Pet pet) {
        return petDao.find(pet);
    }

    @Override
    public Pet findById(Integer id) {
        // 検索条件をセット
        Pet pet = new Pet();
        pet.setId(id);

        // データ取得
        List<Pet> resultList = petDao.find(pet);
        return resultList.isEmpty() ? null : resultList.get(0);
    }

    @Override
    public void register(Pet pet, Integer name) {
        petDao.register(pet, name);
    }

}
