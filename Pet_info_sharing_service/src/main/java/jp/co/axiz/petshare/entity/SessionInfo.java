package jp.co.axiz.petshare.entity;

import java.util.List;

/**
 * セッション情報を纏めて管理するためのクラス
 */
public class SessionInfo {
    List<Pet> petSearchResult; // ペット検索結果
    User userInfo;
    List<AnimalType> animalTypes;
    List<AnimalType> animalTypesUp;

    public List<Pet> getPetSearchResult() {
        return petSearchResult;
    }

    public List<AnimalType> getAnimalTypes() {
        return animalTypes;
    }

    public void setAnimalTypes(List<AnimalType> animalTypes) {
        this.animalTypes = animalTypes;
    }

    public void setPetSearchResult(List<Pet> petSearchResult) {
        this.petSearchResult = petSearchResult;
    }

    public User getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(User userinfo) {
        this.userInfo = userinfo;
    }

    public List<AnimalType> getAnimalTypesUp() {
        return animalTypesUp;
    }

    public void setAnimalTypesUp(List<AnimalType> insert) {
        this.animalTypesUp = insert;
    }
}
