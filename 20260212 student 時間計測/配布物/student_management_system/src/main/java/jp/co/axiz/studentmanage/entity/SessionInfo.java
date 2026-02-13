package jp.co.axiz.studentmanage.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * セッション情報を纏めて管理するためのクラス
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SessionInfo {
    User loginUser; // ログインユーザ情報
    List<Student> studentSearchResult; // 学生検索結果
    List<Major> majorList;

    public User getLoginUser() {
        return loginUser;
    }

    public void setLoginUser(User loginUser) {
        this.loginUser = loginUser;
    }

    public List<Student> getStudentSearchResult() {
        return studentSearchResult;
    }

    public void setStudentSearchResult(List<Student> studentSearchResult) {
        this.studentSearchResult = studentSearchResult;
    }
}
