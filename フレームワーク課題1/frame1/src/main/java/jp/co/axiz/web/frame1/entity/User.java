package jp.co.axiz.web.frame1.entity;

import jp.co.example.frame1.entity.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Integer userId;
    private Integer loginId;
    private String userName;
    private Integer telephone;
    private String password;
    private Integer roleId;

}
