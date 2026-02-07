package jp.co.axiz.petshare.form;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterForm {
    @NotBlank
    private String petName;
    private Integer petTypeId;
    private String description;
    private Integer userId;

}
