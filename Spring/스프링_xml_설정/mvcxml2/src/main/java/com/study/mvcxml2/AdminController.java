package com.study.mvcxml2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AdminController {
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	@RequestMapping(value="/admin/adminMain", method = RequestMethod.GET)
	public String adminMain() {
		logger.info("메인 관리자창 호출");
		
		return "admin/adminMain";
	}
}
