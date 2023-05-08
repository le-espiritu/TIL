package com.study.mvcxml2.user;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(path="/users")
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	
	
	private final UserService userService;
	private final PasswordEncoder passwordEncoder; //bcrypt
	
	@Autowired
	public UserController(UserService userService, PasswordEncoder passwordEncoder) {
		this.userService=userService;
		this.passwordEncoder=passwordEncoder;
	}
	
	
	
	@GetMapping("/loginForm")
	public String loginForm() {
		logger.info("DB 연동한 로그인 페이지 호출");
		
		return "user/loginForm";
	}
	
	@GetMapping("/authDenied")
	public String accessDenied() {
		logger.info("접근이 거부되었습니다.");
		
		return "user/authDenied";
	}
	
	@GetMapping("/joinForm")
	public String joinForm() {
		return "user/joinForm";
	}
	
	@PostMapping("/join")
	public String join(@ModelAttribute UserDTO userDTO) {
		userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
		int rs = userService.addUser(userDTO);
		
		if(rs<=0) {
			System.out.println(rs+" 회원가입 실패");
			return"redirect:/users/joinForm?error=-1";
		}
		
		return "redirect:/";
	}
	
	@GetMapping("/memberinfo")
	public ModelAndView memberInfo(Principal principal) {
		ModelAndView mv = new ModelAndView();
		
		String loginId = principal.getName();
		UserDTO userDTO = userService.getUserByEmail(loginId);
		
		mv.addObject("user", userDTO);
		mv.setViewName("user/memberinfo");
		
		return mv;
	}

}
