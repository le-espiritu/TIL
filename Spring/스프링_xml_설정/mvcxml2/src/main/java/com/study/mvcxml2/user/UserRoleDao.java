package com.study.mvcxml2.user;

import java.util.List;


public interface UserRoleDao {
	public List<UserRoleDTO> getRolesByEmail(String email);
	public int addUserRole(Long userId);
}
