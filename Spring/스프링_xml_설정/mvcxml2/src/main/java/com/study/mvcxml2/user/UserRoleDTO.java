package com.study.mvcxml2.user;

public class UserRoleDTO {
	private Long id;
	private Long memberId;
	private String roleName;
	
	public UserRoleDTO(Long memberId, String roleName) {
		this.memberId = memberId;
		this.roleName = roleName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	

}
