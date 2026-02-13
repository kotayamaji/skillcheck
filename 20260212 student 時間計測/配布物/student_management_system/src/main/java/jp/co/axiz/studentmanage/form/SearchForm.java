package jp.co.axiz.studentmanage.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * 検索画面用フォーム
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchForm {

    private String studentName;
    private Integer grade;

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

}
