package kr.or.bbs.BBSSpring.service;

import kr.or.bbs.BBSSpring.dto.User;

public interface UserService {
	public int login(String userID, String userPassword);
	public int join(User user);
}
