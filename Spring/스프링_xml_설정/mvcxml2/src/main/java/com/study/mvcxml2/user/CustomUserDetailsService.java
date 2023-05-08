package com.study.mvcxml2.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	
	@Autowired
	UserDbService userDbService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		//username(id)에 해당하는정보를 데이터베이스 에서 읽어와 UserEntity타입으로 리턴하여 불러온다.
		UserEntity customUser = userDbService.getUser(username);
		if(customUser==null) {
			throw new UsernameNotFoundException("사용자가 입력한 아이디에 해당하는 사용자를 찾을 수 없습니다.");
		}
		
		CustomUserDetails customUserDetails = new CustomUserDetails();
		
		customUserDetails.setUsername(customUser.getLoginUserId());
		customUserDetails.setPassword(customUser.getPassword());
		// 불러운 유저 정보를 CustomUserDetails 객체에 저장한다.
		
		//username(id)에 해당하는 권한 정ㅊ보를 데이터베이스에서 읽어와 List<UserRoleEntity> 타입으로 리턴하여 불러온다.
		List<UserRoleEntity> customRoles = userDbService.getUserRoles(username);
		
		List<GrantedAuthority> authorities = new ArrayList<>();
		if(customRoles != null) {
			for(UserRoleEntity customRole : customRoles) {
				//로그인 한 사용자의 권한 정보를 GrantedAuthority를 구현하고 있는 SimpleGrantedAuthority객체에 담아 리스트에 추가한다.
				authorities.add(new SimpleGrantedAuthority(customRole.getRoleName()));
			}
		}
		
		// CustomUserDetails객체에 권한 목록 (authorities)를 설정한다.
        customUserDetails.setAuthorities(authorities);
        
        customUserDetails.setEnabled(true); // setEnabled(customUser.getEnabled==1? true:false); //데이터베이스에 Enabled 정보가 있는 경우 
        
        customUserDetails.setAccountNonExpired(true);
        customUserDetails.setAccountNonLocked(true);
        customUserDetails.setCredentialsNonExpired(true);
        return customUserDetails;
		
	}

}


// UserDetailsService인터페이스를 구현하는 CustomUserDetailsService를 생성

/*
 * UserDetailsService인터페이스는 1개의 메소드만 선언하고 있습니다.
 * 
 * 바로 public UserDetails loadUserByUsername(String loginId) 메소드입니다. 
 * 
 * 사용자가 로그인을 할 때 아이디를 입력하면 해당 아이디를 loadUserByUsername()메소드의 인자로 전달합니다. 
 * 해당 아이디에 해당하는 정보가 없으면 UsernameNotFoundException이 발생합니다.
 * 
 * 정보가 있을 경우엔 UserDetails인터페이스를 구현한 객체를 리턴 하게 됩니다.
 * 
 * 
 * 데이터베이스에서 로그인 아이디에 해당하는 정보를 읽어 들이기 위해서 UserDbService를 구현한 객체를 주입받고 있습니다.
 * UserDbService도 인터페이스이니 구현한 객체가 필요합니다.
 * 
 * 이렇게 조금은 복잡하게 구현된 이유는 데이터베이스에서 읽어 들이는 코드와 스프링 시큐리티에서 사용되는 코드를 분리하기 위함입니다.
 * 
 * UserDbService에서는 스프링 시큐리티와 관련된 코드가 전혀 사용되지 않고 있는 걸 눈 여겨 봐주세요!
 */
