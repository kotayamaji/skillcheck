package jp.co.axiz.petshare.entity;

import jp.co.axiz.petshare.util.ParamUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * petsテーブルのEntity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pet {
    private Integer id;
    private String name;
    private Integer animalTypeId;
    private String animalTypeName;
    private String description;
    private Integer userId;
    private Boolean isDeleted;
    private String dispName;

    /**
     * 検索条件用の項目が全て未入力かを確認する
     * (全て未入力ならtrue)
     */
    public boolean isEmptyCondition() {
        return id == null && ParamUtil.isNullOrEmpty(name);
    }

}