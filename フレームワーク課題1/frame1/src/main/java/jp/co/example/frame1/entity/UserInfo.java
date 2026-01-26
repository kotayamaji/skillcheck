package jp.co.example.frame1.entity;

import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * user_infoテーブルのEntity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {
    private static final Integer ADMIN_ROLE_ID = 1; // 管理者を表すrole_idの値

    private Integer userId; // user_id
    private String loginId; // login_id
    private String userName; // user_name
    private String telephone; // telephone
    private String password; // password
    private Integer roleId; // role_id
    private String roleName; // role_idに対応するrole_name
    private Timestamp createDatetime; // create_datetime
    private Timestamp updateDatetime; // update_datetime

}
