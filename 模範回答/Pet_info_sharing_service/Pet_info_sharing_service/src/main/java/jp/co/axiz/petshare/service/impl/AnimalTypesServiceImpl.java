package jp.co.axiz.petshare.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.axiz.petshare.dao.AnimalTypesDao;
import jp.co.axiz.petshare.entity.AnimalType;
import jp.co.axiz.petshare.service.AnimalTypesService;

@Service
public class AnimalTypesServiceImpl implements AnimalTypesService {

	@Autowired
	AnimalTypesDao animalTypesDao;

	@Override
	public List<AnimalType> findAll() {
		// TODO 自動生成されたメソッド・スタブ
		return animalTypesDao.findAll();
	}

}
