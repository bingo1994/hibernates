package com.test.domain;

import java.util.HashSet;
import java.util.Set;

public class Course {

	private Integer cid;
	private String cname;
	private Integer ccredit;
	private Set<StudCourse> stuCourse=new HashSet<StudCourse>();
	public Set<StudCourse> getStuCourse() {
		return stuCourse;
	}
	public void setStuCourse(Set<StudCourse> stuCourse) {
		this.stuCourse = stuCourse;
	}
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public Integer getCcredit() {
		return ccredit;
	}
	public void setCcredit(Integer ccredit) {
		this.ccredit = ccredit;
	}
}
