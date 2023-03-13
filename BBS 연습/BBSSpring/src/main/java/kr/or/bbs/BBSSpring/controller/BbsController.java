package kr.or.bbs.BBSSpring.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.bbs.BBSSpring.dto.Bbs;
import kr.or.bbs.BBSSpring.service.BbsService;

@Controller
public class BbsController {
	
	BbsService bbsService;
	
	@Autowired
	public BbsController(BbsService bbsService) {
		this.bbsService=bbsService;
	}
	
	@GetMapping(path="/bbs")
	public String getBbsPage(@RequestParam(name="start", required = false, defaultValue="0") int start,
							ModelMap model) {
		
		//start 값으로 시작하는 bbs 목록 가져오기 
		List<Bbs> list = bbsService.getBbs(start);
		
		// 전체 페이지수 구하기
		int count = bbsService.getCount();
		int pageCount = count/BbsService.LIMIT;
		if(pageCount%BbsService.LIMIT>0) {
			pageCount++;
		}
		
		List<Integer> pageStartList = new ArrayList<>();
		for(int i=0; i<pageCount; i++) {
			int startNumber = i*BbsService.LIMIT;
			pageStartList.add(startNumber);
		}
		
		model.addAttribute("list", list);
		model.addAttribute("count", count);
		model.addAttribute("pageStartList", pageStartList);
		
		return"bbs";
	}

}
