package jp.co.axiz.petshare.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class RegisterForm {

	@NotBlank
	private String name;
	@NotNull
	private Integer animalTypeId;
	private String description;
	private Integer userId;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAnimalTypeId() {
		return animalTypeId;
	}

	public void setAnimalTypeId(Integer animalTypeId) {
		this.animalTypeId = animalTypeId;
	}

	public String getDescription() {
		return description;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
