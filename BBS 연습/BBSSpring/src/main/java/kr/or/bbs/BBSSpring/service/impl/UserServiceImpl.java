package kr.or.bbs.BBSSpring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.bbs.BBSSpring.dao.UserDao;
import kr.or.bbs.BBSSpring.dto.User;
import kr.or.bbs.BBSSpring.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserDao userDao;

	@Override
	//Transactional이라는 어노테이션이 붙은 메서드는 코드 실행이 트랙잭셔널하게 처리된다.
	@Transactional(readOnly=false)
	public int login(String userID, String userPassword) {
		int result = userDao.login(userID, userPassword);
		return result;
	}

	@Override
	@Transactional(readOnly=false)
	public int join(User user) {
		return userDao.join(user);
	}

}
