package com.study.mvcxml2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@GetMapping("/hello")
	public String hell() {
		return"hello";
	}
	
	@GetMapping("/test") // jdbcTemplate 테스트 
	public String test(Model model) {
		
		String sql = "select count(*) from bbs";
		int count = jdbcTemplate.queryForObject(sql, Integer.class);
		
		System.out.println(count);
		
		model.addAttribute("count", count);
		return"test";
	}

}
