package com.study.mvcxml2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeBodyController {
	
	@GetMapping("/homebody")
	public String homeBody() {
		//System.out.println(10/0); // 500번 에러 발생시키는코드
		return"home/homeBody";
	}
	
	@GetMapping("/homebody2")
	public String homeBody2() {
		return"home/homeBody2";
	}

}
