package com.study.mvcxml2.user;

import java.util.Date;

public class UserDTO {
	private Long id;
	private String name;
	private String password;
	private String email;
	private Date createDate;
	private Date modifyDate;
	
	public UserDTO() { // 객체 생성할때 인자가 없는경우 날짜 자동 생성될 수 있도록 하는 기본 생성자 코드  
		createDate = new Date();
		modifyDate = new Date();
	}
	
	public UserDTO(String name, String password, String email) {
		this(); // 기본 생성자 호출
		this.name = name;
		this.password = password;
		this.email = email;
	}
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	
	

}
