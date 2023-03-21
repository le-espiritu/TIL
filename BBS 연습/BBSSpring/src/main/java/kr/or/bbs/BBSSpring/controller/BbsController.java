package kr.or.bbs.BBSSpring.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
		
		boolean isNextPage=false; // 다음 페이지가 존재하는지 확인하기 위함 
		
		List<Integer> pageStartList = new ArrayList<>();
		for(int i=0; i<pageCount; i++) {
			int startNumber = i*BbsService.LIMIT;
			pageStartList.add(startNumber);
			
			if(startNumber>start) { // 다음 페이지가 존재하는지 확인하기 위함 
				isNextPage=true;
			}
		}
		
		model.addAttribute("start",start);
		model.addAttribute("isNextPage", isNextPage); // 다음 페이지가 존재하는지 확인하기 위함 
		model.addAttribute("list", list);
		model.addAttribute("count", count);
		model.addAttribute("pageStartList", pageStartList);
		
		return"bbs";
	}
	
	@GetMapping(path="/posts/{id}")
	public String getPostView(@PathVariable(name="id") int id, ModelMap model) {
		
		Bbs bbsForPostView = bbsService.getPostView(id);
		model.addAttribute("bbsForPostView", bbsForPostView);
		
		return "postView";
	}
	
	
	@PatchMapping(path="/posts/{id}")
	public void updateTest(@PathVariable(name="id") int id, @RequestParam(name="bbsTitle") String bbsTitle,
							@RequestParam(name="bbsContent")String bbsContent) {
		System.out.println(bbsTitle);
		System.out.println("bbsContent: "+bbsContent);
		System.out.println("요청이 잘 전달되었습니다.");
	}
	
	@GetMapping(path="/board/{id}")
	public String getUpdateBoard(@PathVariable(name="id")int id, ModelMap model) {
		Bbs bbsForUpdate = bbsService.getPostView(id);
		model.addAttribute("bbsForUpdate", bbsForUpdate);
		return"updateBoard";
	}
	
	@GetMapping(path="/board")
	public String getBoard() {
		return"board";
	}
	
	@PostMapping(path="/board")
	public String write(@ModelAttribute Bbs bbs, HttpSession session, ModelMap model) {
		
		if(bbs.getBbsTitle()==null || bbs.getBbsTitle().equals("") || 
			bbs.getBbsContent()==null || bbs.getBbsContent().equals("")) {
			return"boardFailedView";
		}
		else {
			bbs.setUserID((String)session.getAttribute("userID"));
			bbs.setBbsAvailable(1);
			
			Date date = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // Date를 String으로 파싱해주는 클래스 
			String dateToString = dateFormat.format(date);
			
			bbs.setBbsDate(dateToString);
			
			int writeResult = bbsService.write(bbs);
			
			if(writeResult==-1) { // 글쓰기 실패 
				model.addAttribute("writeResult", writeResult);
				return"boardFailedView";
			}else { // 글쓰기 성공 
				return"redirect:bbs";
			}
			
		}
		
	}

}
