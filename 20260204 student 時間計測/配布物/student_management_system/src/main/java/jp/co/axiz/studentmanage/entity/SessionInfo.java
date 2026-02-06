package jp.co.axiz.studentmanage.entity;

import java.util.List;

/**
 * セッション情報を纏めて管理するためのクラス
 */
public class SessionInfo {
    User loginUser; // ログインユーザ情報
    List<Student> studentSearchResult; // 学生検索結果
    List<Major> majorsList;

    Student prevUpdatestudent;

    public User getLoginUser() {
        return loginUser;
    }

    public Student getPrevUpdatestudent() {
        return prevUpdatestudent;
    }

    public void setPrevUpdatestudent(Student prevUpdatestudent) {
        this.prevUpdatestudent = prevUpdatestudent;
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

    public List<Major> getMajorsList() {
        return majorsList;
    }

    public void setMajorsList(List<Major> majorsList) {
        this.majorsList = majorsList;
    }

}
