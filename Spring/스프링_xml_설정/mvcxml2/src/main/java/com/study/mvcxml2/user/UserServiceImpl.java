package com.study.mvcxml2.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {
	
	//생성자에 위해 주입되는 객체이고, 해당 객체를 초기화할 필요가 이후에 없기 때문에 final로 선언하였다.
	private final UserDao userDao;
	private final UserRoleDao userRoleDao;
	
	@Autowired
	public UserServiceImpl(UserDao userDao, UserRoleDao userRoleDao) {
		this.userDao=userDao;
		this.userRoleDao=userRoleDao;
	}

	@Override
	@Transactional
	public UserEntity getUser(String loginUserId) {
		UserDTO userDTO = userDao.getUserByEmail(loginUserId);
		return new UserEntity(userDTO.getEmail(), userDTO.getPassword());
	}

	@Override
	@Transactional
	public List<UserRoleEntity> getUserRoles(String loginUserId) {
		
		List<UserRoleDTO> userRoles = userRoleDao.getRolesByEmail(loginUserId);
		
		List<UserRoleEntity> list = new ArrayList<>();
		
		for(UserRoleDTO userRoleDTO : userRoles) {
			list.add(new UserRoleEntity(loginUserId,userRoleDTO.getRoleName()));
			
		}
		
		
        return list;
	}

	@Override
	public int addUser(UserDTO userDTO) {
		
		int rs = userDao.addUser(userDTO);
		System.out.println("===============rs========================== : "+rs);
		if(rs>0) {
			UserDTO selectedUser = userDao.getUserByEmail(userDTO.getEmail());
			Long userId = selectedUser.getId();
			userRoleDao.addUserRole(userId);
		}
		
		return rs;
	}

	@Override
	public UserDTO getUserByEmail(String loginId) {
		return userDao.getUserByEmail(loginId);
	}

}

// UserService인터페이스를 구현한 UserServiceImple 클래스.(강의에서는 MemberServiceImple)

/*
 * UserService는 회원과 관련된 기능을가지게 됩니다.
 * 
 * UserService인터페이스를 구현한다는 것은 
 * UserDbService 역시 구현해야한다는 것을 의미합니다.
 * 
 * 여기서 데이터베이스를 읽어 들인다. 
 */

