package jp.co.axiz.studentmanage.entity;

import java.util.List;

/**
 * セッション情報を纏めて管理するためのクラス
 */
public class SessionInfo {
    User loginUser; // ログインユーザ情報
    List<Student> studentSearchResult; // 学生検索結果

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
