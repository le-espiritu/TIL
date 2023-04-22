package com.study.mvcxml2.practice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PracticeController {
	
	private PracticeService practiceService;
	
	@Autowired
	public PracticeController(PracticeService practiceService) {
		this.practiceService=practiceService;
	}
	
	@GetMapping("/practice")
	public ModelAndView getPractices() {
		ModelAndView mv = new ModelAndView();
		
		List<Practice> list = practiceService.practiceList();
		
		mv.addObject("no", list.get(0).getNo());
		mv.addObject("name", list.get(0).getName());
		mv.addObject("score", list.get(0).getScore());
		
		mv.setViewName("test");
		
		return mv;
	}

}
