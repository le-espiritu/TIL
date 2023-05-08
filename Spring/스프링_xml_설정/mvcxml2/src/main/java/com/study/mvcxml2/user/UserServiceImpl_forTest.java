package com.study.mvcxml2.user;

import java.util.ArrayList;
import java.util.List;


//@Service
public class UserServiceImpl_forTest implements UserService {

	@Override
	public UserEntity getUser(String loginUserId) {
		 return new UserEntity("carami", "$2a$10$G/ADAGLU3vKBd62E6GbrgetQpEKu2ukKgiDR5TWHYwrem0cSv6Z8m");
	}

	@Override
	public List<UserRoleEntity> getUserRoles(String loginUserId) {
		List<UserRoleEntity> list = new ArrayList<>();
        list.add(new UserRoleEntity("carami", "ROLE_USER"));
        list.add(new UserRoleEntity("carami", "ROLE_ADMIN"));
        return list;
	}

	@Override
	public int addUser(UserDTO userDTO) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public UserDTO getUserByEmail(String loginId) {
		// TODO Auto-generated method stub
		return null;
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

