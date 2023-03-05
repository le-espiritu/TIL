package kr.or.bbs.BBSSpring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.bbs.BBSSpring.dao.UserDao;
import kr.or.bbs.BBSSpring.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserDao userDao;

	@Override
	public int login(String userID, String userPassword) {
		int result = userDao.login(userID, userPassword);
		return result;
	}

}
