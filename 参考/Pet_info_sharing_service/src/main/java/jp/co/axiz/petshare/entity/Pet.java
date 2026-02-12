package jp.co.axiz.petshare.entity;

import jp.co.axiz.petshare.util.ParamUtil;

/**
 * petsテーブルのEntity
 */
public class Pet {
	private Integer id;
	private String name;
	private Integer animalTypeId;
	private String description;
	private Integer userId;
	private Boolean isDeleted;
	private String userName;
	private String animalTypesName;

	public Pet() {
	}

	public Pet(Integer id, String name, Integer animalTypeId, String description, Integer userId,
			Boolean isDeleted) {
		this.id = id;
		this.name = name;
		this.animalTypeId = animalTypeId;
		this.description = description;
		this.userId = userId;
		this.isDeleted = isDeleted;
	}

	public Pet(Integer id, String name, Integer animalTypeId, String description, Integer userId, Boolean isDeleted,
			String userName, String animalTypesName) {
		super();
		this.id = id;
		this.name = name;
		this.animalTypeId = animalTypeId;
		this.description = description;
		this.userId = userId;
		this.isDeleted = isDeleted;
		this.userName = userName;
		this.animalTypesName = animalTypesName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAnimalTypesName() {
		return animalTypesName;
	}

	public void setAnimalTypesName(String animalTypesName) {
		this.animalTypesName = animalTypesName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAnimalTypeId() {
		return animalTypeId;
	}

	public void setAnimalTypeId(Integer animalTypeId) {
		this.animalTypeId = animalTypeId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	/**
	 * 検索条件用の項目が全て未入力かを確認する
	 * (全て未入力ならtrue)
	 */
	public boolean isEmptyCondition() {
		return id == null && ParamUtil.isNullOrEmpty(name) && animalTypeId == null;
	}

}