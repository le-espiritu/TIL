package kr.or.bbs.BBSSpring.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.bbs.BBSSpring.service.UserService;


@Controller // @RestController 어노테이션을 사용하면 아래 컨트롤러 메소드 리턴시 페이지로 리턴되는것이 아니라 데이터가 리턴됨
public class UserController {
	
	@Autowired
	UserService userService;
	
	@GetMapping(path="/login")
	public String loginPage(HttpSession session) {
		if(session.getAttribute("userID")!=null) { // 이미 로그인 되어 있는 경우 
			return"redirect:/";
		}
		else { // 로그인 안되어 있는 경우 
			return"login";
		}
		
	}
	
	@PostMapping(path="/login") // RequestParam 어노테이션 대신 ModelAttribute 어노테이션 사용해서 User객체로 한꺼번에 받아와도 됨 
	public String login(@RequestParam(name="userID", required=true) String userID,
			@RequestParam(name="userPassword", required=true)String userPassword, HttpSession session) {
		int result = userService.login(userID, userPassword);
		System.out.println("login result : "+result);
		
		if(result==1) {
			// 스프링을 사용하지 않을때는 세션을 사용하기 위해 request로 부터 getSession()메서드를 이용하여 얻어와야 했었는데 스프링은 이 일을 대신 처리해준다. 
			// 그래서 스프링에서는 컨트롤러 메소드 인자에 session 선언만 해주면 된다.
			session.setAttribute("userID", userID);
			return"redirect:/";
		}else {
			return"login";
		}
		
	}
	
	@GetMapping(path="/join")
	public String joinPage(HttpSession session) {
		if(session.getAttribute("userID")!=null) {
			return "redirect:/";
		}else {
			return "join";
		}
		
	}
	
	@GetMapping(path="/logout")
	public String logout(HttpSession session) {
		//System.out.println("로그아웃 컨트롤로 호출 완료");
		session.removeAttribute("userID");
		return"redirect:/";
		
	}
}
