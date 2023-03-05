package kr.or.bbs.BBSSpring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	
	@GetMapping(path="/main")
	public String mainPage() {
		return"redirect:/";
	}
}
