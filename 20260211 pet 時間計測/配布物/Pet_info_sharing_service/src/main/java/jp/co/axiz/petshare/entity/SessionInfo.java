package jp.co.axiz.petshare.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * セッション情報を纏めて管理するためのクラス
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SessionInfo {
    List<Pet> petSearchResult; // ペット検索結果
    User loginUser;
    List<AnimalType> animalTypes;

    public List<Pet> getPetSearchResult() {
        return petSearchResult;
    }

    public void setPetSearchResult(List<Pet> petSearchResult) {
        this.petSearchResult = petSearchResult;
    }
}
