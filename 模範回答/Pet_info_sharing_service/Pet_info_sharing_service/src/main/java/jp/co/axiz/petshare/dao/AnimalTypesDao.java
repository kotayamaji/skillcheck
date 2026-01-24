package jp.co.axiz.petshare.dao;

import java.util.List;

import jp.co.axiz.petshare.entity.AnimalType;

public interface AnimalTypesDao {

	public List<AnimalType> findAll();
}
