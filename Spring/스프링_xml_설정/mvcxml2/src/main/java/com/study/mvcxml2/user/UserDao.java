package com.study.mvcxml2.user;

public interface UserDao {
	public UserDTO getUserByEmail(String email);
	public int addUser(UserDTO userDTO);
}
