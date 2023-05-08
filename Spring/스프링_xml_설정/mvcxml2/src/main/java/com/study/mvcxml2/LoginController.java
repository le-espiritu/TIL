package com.study.mvcxml2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@RequestMapping(value="/login/loginForm", method = RequestMethod.GET)
	public String loginForm() {
		logger.info("로그인 페이지 호출");
		
		return "login/loginFormBasic";
	}
	
	@RequestMapping(value="/login/authDenied", method = RequestMethod.GET)
	public String accessDenied() {
		logger.info("접근이 거부되었습니다.");
		
		return "login/authDenied";
	}

}
