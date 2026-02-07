package jp.co.axiz.petshare.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * animal_typesテーブルのEntity
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnimalType {

    private Integer id;
    private String name;

}