package jp.co.axiz.studentmanage.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * studentsテーブルのEntity
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Major {

    private String majorName;
    private Integer majorId;

}