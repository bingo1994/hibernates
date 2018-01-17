package com.test.domain;

import java.io.Serializable;
//这是一个domain对象(实际上就是javabean/有些人pojo)
//他和Employee对应   要序列化
public class Employee implements Serializable{

	//默认版本id
	private static final long serialVersionUID = 9036336380628354686L;
	private Integer id;
	private String name;
	private String sex;
	private String phone;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
}
