package jp.co.axiz.studentmanage.entity;

import jp.co.axiz.studentmanage.util.ParamUtil;

/**
 * studentsテーブルのEntity
 */
public class Student {
	private Integer studentId;
	private String studentName;
	private Integer grade;
	private String hometown;
	private Integer majorId;

	public String getHometown() {
		return hometown;
	}

	public void setHometown(String hometown) {
		this.hometown = hometown;
	}

	public Integer getMajorId() {
		return majorId;
	}

	public void setMajorId(Integer majorId) {
		this.majorId = majorId;
	}

	public String getMajorName() {
		return majorName;
	}

	public void setMajorName(String majorName) {
		this.majorName = majorName;
	}

	private String majorName;

	public Student() {
	}

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