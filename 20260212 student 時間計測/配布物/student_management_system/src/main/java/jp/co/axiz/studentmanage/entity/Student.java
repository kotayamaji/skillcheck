package jp.co.axiz.studentmanage.entity;

import jp.co.axiz.studentmanage.util.ParamUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * studentsテーブルのEntity
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
	private Integer studentId;
	private String studentName;
	private Integer grade;
	private String majorName;
	private Integer majorId;
	private String hometown;

	public Student(Integer studentId, String studentName, Integer grade) {
		this.studentId = studentId;
		this.studentName = studentName;
		this.grade = grade;
	}

	public Integer getStudentId() {
		return studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

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

	/**
	 * 検索条件用の項目が全て未入力かを確認する
	 * (全て未入力ならtrue)
	 */
	public boolean isEmptyCondition() {
		return ParamUtil.isNullOrEmpty(studentName);
	}

}