package jp.co.axiz.petshare.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * 検索画面用フォーム
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchForm {

    private String name;
    private Integer Id;
    private String animalTypeName;

}
