package jp.co.axiz.petshare.entity;

import java.util.List;

/**
 * セッション情報を纏めて管理するためのクラス
 */
public class SessionInfo {
    List<Pet> petSearchResult; // ペット検索結果
    User user; //ログインユーザー情報
    List<AnimalType> animalTypes;

    public List<AnimalType> getAnimalTypes() {
		return animalTypes;
	}

	public void setAnimalTypes(List<AnimalType> animalTypes) {
		this.animalTypes = animalTypes;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Pet> getPetSearchResult() {
        return petSearchResult;
    }

    public void setPetSearchResult(List<Pet> petSearchResult) {
        this.petSearchResult = petSearchResult;
    }
}
