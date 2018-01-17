package com.test.domain;

import java.util.HashSet;
import java.util.Set;

public class Student {

	private Integer sid;
	private String sname;
	private String ssex;
	private String sdept;
	private Integer sage;
	private String saddress;
	private Set<StudCourse> stuCourse=new HashSet<StudCourse>();
	public Integer getSid() {
		return sid;
	}
	public void setSid(Integer sid) {
		this.sid = sid;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getSsex() {
		return ssex;
	}
	public void setSsex(String ssex) {
		this.ssex = ssex;
	}
	public String getSdept() {
		return sdept;
	}
	public void setSdept(String sdept) {
		this.sdept = sdept;
	}
	public Integer getSage() {
		return sage;
	}
	public void setSage(Integer sage) {
		this.sage = sage;
	}
	public String getSaddress() {
		return saddress;
	}
	public void setSaddress(String saddress) {
		this.saddress = saddress;
	}
	public Set<StudCourse> getStuCourse() {
		return stuCourse;
	}
	public void setStuCourse(Set<StudCourse> stuCourse) {
		this.stuCourse = stuCourse;
	}
}
