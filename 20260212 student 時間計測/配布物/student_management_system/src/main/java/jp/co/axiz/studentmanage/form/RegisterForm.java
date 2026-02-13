package jp.co.axiz.studentmanage.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * 登録画面用フォーム
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterForm {

    @NotBlank
    private String studentName;

    @NotNull
    private Integer grade;

    private String majorName;
    private Integer majorId;
    private String hometown;

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
