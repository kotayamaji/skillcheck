package jp.co.axiz.studentmanage.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/*
 * 登録画面用フォーム
 */
public class UpdateForm {

    @NotBlank
    private String upstudentName;

    @NotNull
    private Integer upgrade;
    
    private String hometown;
    
    private Integer major;
    

	public String getUpstudentName() {
		return upstudentName;
	}

	public void setUpstudentName(String upstudentName) {
		this.upstudentName = upstudentName;
	}

	public Integer getUpgrade() {
		return upgrade;
	}

	public void setUpgrade(Integer upgrade) {
		this.upgrade = upgrade;
	}

	public String getHometown() {
		return hometown;
	}

	public void setHometown(String hometown) {
		this.hometown = hometown;
	}

	public Integer getMajor() {
		return major;
	}

	public void setMajor(Integer major) {
		this.major = major;
	}
    
    

}
