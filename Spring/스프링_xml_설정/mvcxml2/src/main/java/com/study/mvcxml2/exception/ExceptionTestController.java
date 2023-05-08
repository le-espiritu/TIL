package com.study.mvcxml2.exception;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ExceptionTestController {
	
	@GetMapping("exception1")
	public void exception1() throws Exception {
		throw new Exception();
	}
	
	@GetMapping("exception2")
	public void exception2() {
		throw new CustomException("커스텀 예외 발생");
	}

}
