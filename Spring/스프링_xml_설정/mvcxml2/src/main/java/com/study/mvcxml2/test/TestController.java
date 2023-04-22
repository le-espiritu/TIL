package com.study.mvcxml2.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {
	
	@GetMapping("/test1")
	@ResponseBody
	public String test1() {
		return "test1 complete";
	}
	
	@GetMapping("/test2")
	public String test2() {
		return "test2";
	}

}
