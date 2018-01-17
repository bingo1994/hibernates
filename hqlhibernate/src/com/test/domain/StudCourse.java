package com.test.domain;

public class StudCourse {

	private Integer stuCourseId;
	private Integer sid;
	private Integer cid;
	private Integer grade;
	private Course course;
	private Student student;
	public Integer getStuCourseId() {
		return stuCourseId;
	}
	public void setStuCourseId(Integer stuCourseId) {
		this.stuCourseId = stuCourseId;
	}
	public Integer getSid() {
		return sid;
	}
	public void setSid(Integer sid) {
		this.sid = sid;
	}
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public Integer getGrade() {
		return grade;
	}
	public void setGrade(Integer grade) {
		this.grade = grade;
	}
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
}
