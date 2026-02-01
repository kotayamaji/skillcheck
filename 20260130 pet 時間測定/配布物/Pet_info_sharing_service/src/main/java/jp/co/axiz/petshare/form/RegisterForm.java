package jp.co.axiz.petshare.form;

import jakarta.validation.constraints.NotBlank;

public class RegisterForm {
    @NotBlank
    private String petName;
    private Integer petTypeId;
    private String description;
    private Integer userId;

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getDescription() {
        return description;
    }

    public Integer getPetTypeId() {
        return petTypeId;
    }

    public void setPetTypeId(Integer petTypeId) {
        this.petTypeId = petTypeId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

}
