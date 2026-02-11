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
    private String name;
    private Integer animalTypeId;
    private String description;

}
