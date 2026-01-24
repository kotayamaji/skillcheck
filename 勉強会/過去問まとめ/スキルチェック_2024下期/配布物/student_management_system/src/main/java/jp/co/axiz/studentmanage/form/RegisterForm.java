package jp.co.axiz.studentmanage.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/*
 * 登録画面用フォーム
 */
public class RegisterForm {

    @NotBlank
    private String studentName;

    @NotNull
    private Integer grade;

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

}
