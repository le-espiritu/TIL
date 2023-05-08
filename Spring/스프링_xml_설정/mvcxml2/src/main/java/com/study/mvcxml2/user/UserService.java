package com.study.mvcxml2.user;

public interface UserService extends UserDbService {
	public int addUser(UserDTO userDTO);
	public UserDTO getUserByEmail(String loginId);

}

// UserDbService인터페이스를 상속받는 UserService인터페이스
// 강의 에서는 (MeberService인터페이스)

/*
 * UserDbService는 스프링 시큐리티에서 필요로하는 정보를 가지고 오는 메소드를 가지고 있습니다.
 * 
 * UserService는 앞으로 회원과 관련된 모든 정보를 처리하는 서비스가 될 예정입니다.
 * 예를 들어 회원 등록과 관련된 메소드는 UserService에 추가되게 됩니다.
 */