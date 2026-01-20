package jp.co.axiz.petshare.entity;

import java.util.List;

/**
 * セッション情報を纏めて管理するためのクラス
 */
public class SessionInfo {
    List<Pet> petSearchResult; // ペット検索結果

    public List<Pet> getPetSearchResult() {
        return petSearchResult;
    }

    public void setPetSearchResult(List<Pet> petSearchResult) {
        this.petSearchResult = petSearchResult;
    }
}
