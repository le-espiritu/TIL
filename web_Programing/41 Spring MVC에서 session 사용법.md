# Spring MVC에서 Session 사용하기



### Spring MVC에서 Session을 이용한 상태유지



### @SessionAttributes 과 @ModelAttribute를 함께 사용할 때

+ @SessionAttributes파라미터로 지정된 이름과 같은 이름이 @ModelAttribute에 지정되어 있을 경우 메소드가 반환되는 값은 세션에 저장된다.

+ 아래의 예제는 세션에 값을 초기화하는 목적으로 사용되었다.

  ~~~java
  @SessionAttributes("user")
  public class LoginController{
    @ModelAttribute("user")
    public User setUpUserForm(){
      return new User();
    }
  }
  ~~~

  + 인자로 전달할 이름이 같은 경우에 메서드가 return 한 값은 argumnet의 이름을 key로 하여 세션에 저장된다.
  + 위 코드에서는 setUpUserForm()이라는 메서드에서 return 하고 있는 User 객체가 "user"라는 이름으로 세션에 저장된다.

+ @SessionAttributes의 파라미터와 같은 이름이 @ModelAttribute에 있을 경우 세션에 있는 객체를 가져온 후, 클라이언트로 전송받은 값을 설정한다.

  ~~~java
  @Controller
  @SessionAttributes("user")
  public class LoginController {
  ......
    @PostMapping("/dologin")
    public String doLogin(@ModelAttribute("user") User user, Model model) {
  ......
    }
  }
  ~~~



### @SessionAttribute

+ 메소드에 @SessionAttribute가 있을 경우 파라미터로 지정된 이름으로 등록된 세션 정보를 읽어와서 변수에 할당한다.

  ~~~java
  @GetMapping("/info")
  public String userInfo(@SessionAttribute("user") User user) {
  //...
  //...
  return "user";
  }
  ~~~

  

### SessionStatus

+ SessionStatus는 컨트롤러 메소드의 파라미터로 사용할 수 있는 스프링 내장 타입이다.

+ 이 오브젝트를 이용하면 현재 컨트롤러의 @SessionAttribute에 의해 저장된 오브젝트를 제거할 수 있다.

  ~~~java
  @Controller
  @SessionAttributes("user")
  public class UserController{
    .....
      @RequestMapping(value="/user/add",method = RequestMethod.POST)
      public String submit(@ModelAttribute("user")User user,SessionStatus sessionStatus){
      ....
        sessionStatus.setComplete();
      ....
    }
  }
  ~~~



### Spring MVC - form tag 라이브러리

+ modelAttribute속성으로 지정된 이름의 객체를 세션에서 읽어와서 form 태그로 설정된 태그에 값을 설정한다.

  ~~~
  <form:form action="login" method="post" modelAttribute="user">
  Email : <form:input path="email"/><br>
  password : <form:password path="password"/><br>
  <button type="submit">Login</button>
  </form:form>
  ~~~

  

### 실습

+ 관리자는 /loginform에서 암호를 입력해 로그인을 한다.
+ 관리자가 암호를 맞게 입력할 경우 세션에 로그인 정보가 저장된다.
+ 세션에 로그인 정보가 있을 경우 방명록에는 "삭제"링크가 보여진다.
+ 삭제 링크를 누르면 삭제가 된다. 삭제 작업에서도 로그인 정보가 있는지를 검사해야 한다.



### 컨트롤러 생성 및 view 생성

+ Kr.or.connect.guestbook.controller 패키지에 GuestbookAdminController.java를 생성한다.

  ~~~java
  //GuestbookAdminController.java
  
  package kr.or.connect.guestbook.controller;
  
  import org.springframework.stereotype.Controller;
  import org.springframework.web.bind.annotation.GetMapping;
  
  @Controller
  public class GuestbookAdminController {
  	@GetMapping(path="/loginform")
  	public String loginform() {
  		return "loginform";
  	}
  }
  ~~~

+ views에 loginform.jsp 를 생성한다.

  ~~~jsp
  <!-- loginform.jsp -->
  
  <%@ page language="java" contentType="text/html; charset=UTF-8"
      pageEncoding="UTF-8"%>
  <!DOCTYPE html>
  <html>
  <head>
  <meta charset="UTF-8">
  <title>Insert title here</title>
  </head>
  <body>
  	<h1>관리자 로그인 </h1>
  	<br>
  	<br> ${errorMessage}
  	<br>
  	
  	<form method="post" action="login">
  		암호 : <input type="password" name="passwd"><br>
  		<input type="submit">
  	</form>
  
  </body>
  </html>
  ~~~

+ login 메서드 작성

  ~~~java
  package kr.or.connect.guestbook.controller;
  
  import javax.servlet.http.HttpSession;
  
  import org.springframework.stereotype.Controller;
  import org.springframework.web.bind.annotation.GetMapping;
  import org.springframework.web.bind.annotation.PostMapping;
  import org.springframework.web.bind.annotation.RequestParam;
  import org.springframework.web.servlet.mvc.support.RedirectAttributes;
  
  @Controller
  public class GuestbookAdminController {
  	@GetMapping(path="/loginform")
  	public String loginform() {
  		return "loginform";
  	}
  	
  	@PostMapping(path="/login")
  	public String login(@RequestParam(name="passwd", required=true)String passwd,
  			HttpSession session,
  			RedirectAttributes redirectAttr) {
  		
  		if("1234".equals(passwd)) {
  			session.setAttribute("isAdmin", "true");
  		}else {
  			redirectAttr.addFlashAttribute("errorMessage", "암호가 틀렸습니다.");
  			return "redirect:/loginform";
  		}
  		return "redirect:/list";
  	}
  	
  }
  ~~~

  + RedirectAttributes는 dispatcherServlet이 관리하는 FlashMap에 값을 저장하게 된다.
    + Redirect할 때 딱 한 번만 값을 유지할 목적으로 사용한다.



### list.jsp 수정

+ 로그인이 잘 됐다면(로그인 정보가 있다면) 게시글마다 '삭제'링크가 생겨나게 수정한다.

+ ~~~jsp
  <c:if test="${sessionScope.isAdmin == 'true'}"><a href="delete?id=${guestbook.id}">삭제</a><br><br></c:if>
  ~~~

  ~~~jsp
  <%@ page language="java" contentType="text/html; charset=UTF-8"
  	pageEncoding="UTF-8"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
  <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
  <html>
  <head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>방명록 목록</title>
  </head>
  <body>
  
  	<h1>방명록</h1>
  	<br> 방명록 전체 수 : ${count }, 방문한 수 : ${cookieCount}
  	<br>
  	<br>
  
  	<c:forEach items="${list}" var="guestbook">
  		${guestbook.id }<br>
  		${guestbook.name }<br>
  		${guestbook.content }<br>
  		${guestbook.regdate }<br>
  		<c:if test="${sessionScope.isAdmin == 'true'}"><a href="delete?id=${guestbook.id}">삭제 </a><br><br></c:if>
  	</c:forEach>
  	
  	<br>
  
  	<c:forEach items="${pageStartList}" var="pageIndex" varStatus="status">
  		<a href="list?start=${pageIndex}">${status.index +1 }</a>&nbsp; &nbsp;
  	</c:forEach>
  
  	<br>
  	<br>
  	<form method="post" action="write">
  		name : <input type="text" name="name"><br>
  		<textarea name="content" cols="60" rows="6"></textarea>
  		<br> <input type="submit" value="등록">
  	</form>
  </body>
  </html>
  ~~~

  

### 삭제 메서드 작성

+ GuestbookController.java에 작성한다.

+ ~~~java
  	@GetMapping(path="/delete")
  	public String delete(@RequestParam(name="id", required=true)Long id,
  						@SessionAttribute("isAdmin") String isAdmin,
  						HttpServletRequest request,
  						RedirectAttributes redirectAttr) {
  		if(isAdmin == null || !"true".equals(isAdmin)) { // 세션값이 true가 아닐 경
  			redirectAttr.addFlashAttribute("errorMessage", "로그인을 하지 않았습니다.");
  			return "redirect:loginform";
  		}
  		String clientIp=request.getRemoteAddr();
  		guestbookService.deleteGuestbook(id, clientIp);
  		return "redirect:list";
  	}
  ~~~

  ~~~java
  package kr.or.connect.guestbook.controller;
  
  import java.util.ArrayList;
  import java.util.List;
  
  import javax.servlet.http.Cookie;
  import javax.servlet.http.HttpServletRequest;
  import javax.servlet.http.HttpServletResponse;
  
  import org.springframework.beans.factory.annotation.Autowired;
  import org.springframework.stereotype.Controller;
  import org.springframework.ui.ModelMap;
  import org.springframework.web.bind.annotation.CookieValue;
  import org.springframework.web.bind.annotation.GetMapping;
  import org.springframework.web.bind.annotation.ModelAttribute;
  import org.springframework.web.bind.annotation.PostMapping;
  import org.springframework.web.bind.annotation.RequestParam;
  import org.springframework.web.bind.annotation.SessionAttribute;
  import org.springframework.web.servlet.mvc.support.RedirectAttributes;
  
  import kr.or.connect.guestbook.dto.Guestbook;
  import kr.or.connect.guestbook.service.GuestbookService;
  
  @Controller
  public class GuestbookController {
  	@Autowired // 서비스 이용하기 위함
  	GuestbookService guestbookService;
  	
  	@GetMapping(path="/list")
  	public String list(@RequestParam(name="start", required=false, defaultValue="0") int start,
  						ModelMap model,@CookieValue(value="count", defaultValue="0", required = true) String value,
  						HttpServletResponse response) {
  		
  		// V 쿠키
  //		String value = null;
  //		boolean find = false;
  //		Cookie[] cookies = request.getCookies();
  //		if(cookies != null) {
  //			for(Cookie cookie : cookies) {
  //				if("count".equals(cookie.getName())) {
  //					find = true;
  //					value = cookie.getValue();
  //					break;
  //				}
  //			}
  //		}
  		
  //		if(!find) {
  //			value="1"; // 쿠키는 String으로 된 value를 가진다.
  //		}else {
  			try {
  				int i = Integer.parseInt(value);
  				value = Integer.toString(++i);
  			}catch(Exception ex) {
  				value="1";
  			}
  //		}
  		
  		Cookie cookie = new Cookie("count",value); // 변경된 value의 값을 클라이언트쪽에도 적용하게 하려면 반드시 쿠키는 매번 새로 만들어서 보내줘야 한다. 그렇게 되면 클라이언트 쪽은 똑같은 이림의 쿠키가 들어왔을 때 기존의 쿠키와 교체한다.  
  		cookie.setMaxAge(60*60*24*365);//1년 동안 유지.
  		cookie.setPath("/"); // /경로 이하에 모두 쿠키 적용.
  		response.addCookie(cookie); // 쿠키가 클라이언트한테 저장
  		
  		
  		//start로 시작하는 방명록 목록 구하기
  		List<Guestbook> list = guestbookService.getGuestbooks(start);
  		
  		// 전체 페이지수 구하기
  		int count = guestbookService.getCount();
  		int pageCount = count/GuestbookService.LIMIT;
  		if(count%GuestbookService.LIMIT>0)
  			pageCount++;
  		
  		// 페이지 수만큼 start의 값을 리스트로 저장
  		// 예를 들면 페이지수가 3이면
  		// 0, 5, 10 이렇게 저장된다.
  		// lsit?start=0, list?start=5, list?start=10으로 링크가 걸린다.
  		List<Integer> pageStartList = new ArrayList<>();
  		for(int i=0; i< pageCount; i++) {
  			pageStartList.add(i*GuestbookService.LIMIT);
  		}
  		
  		model.addAttribute("list", list);
  		model.addAttribute("count", count);
  		model.addAttribute("pageStartList", pageStartList);
  		model.addAttribute("cookieCount", value);
  		
  		return"list";
  	}
  	
  	@PostMapping(path="/write")
  	public String write(@ModelAttribute Guestbook guestbook,
  			HttpServletRequest request) {
  		String clientIp = request.getRemoteAddr();
  		System.out.println("clientIp : "+clientIp);
  		guestbookService.addGuestbook(guestbook, clientIp);
  		return "redirect:list";
  	}
  	
  	@GetMapping(path="/delete")
  	public String delete(@RequestParam(name="id", required=true)Long id,
  						@SessionAttribute("isAdmin") String isAdmin,
  						HttpServletRequest request,
  						RedirectAttributes redirectAttr) {
  		if(isAdmin == null || !"true".equals(isAdmin)) { // 세션값이 true가 아닐 경
  			redirectAttr.addFlashAttribute("errorMessage", "로그인을 하지 않았습니다.");
  			return "redirect:loginform";
  		}
  		String clientIp=request.getRemoteAddr();
  		guestbookService.deleteGuestbook(id, clientIp);
  		return "redirect:list";
  	}
  	
  }
  
  ~~~



### 로그아웃 메서드 작성

+ GuestbookAdminController.java에 작성한다.

+ ~~~java
  	@GetMapping(path="/logout")
  	public String login(HttpSession session) {
  		session.removeAttribute("isAdmin");
  		return "redirect:/list";
  	}
  ~~~

  ~~~java
  package kr.or.connect.guestbook.controller;
  
  import javax.servlet.http.HttpSession;
  
  import org.springframework.stereotype.Controller;
  import org.springframework.web.bind.annotation.GetMapping;
  import org.springframework.web.bind.annotation.PostMapping;
  import org.springframework.web.bind.annotation.RequestParam;
  import org.springframework.web.servlet.mvc.support.RedirectAttributes;
  
  @Controller
  public class GuestbookAdminController {
  	@GetMapping(path="/loginform")
  	public String loginform() {
  		return "loginform";
  	}
  	
  	@PostMapping(path="/login")
  	public String login(@RequestParam(name="passwd", required=true)String passwd,
  			HttpSession session,
  			RedirectAttributes redirectAttr) {
  		
  		if("1234".equals(passwd)) {
  			session.setAttribute("isAdmin", "true");
  		}else {
  			redirectAttr.addFlashAttribute("errorMessage", "암호가 틀렸습니다.");
  			return "redirect:/loginform";
  		}
  		return "redirect:/list";
  	}
  	
  	@GetMapping(path="/logout")
  	public String login(HttpSession session) {
  		session.removeAttribute("isAdmin");
  		return "redirect:/list";
  	}
  	
  }
  ~~~

  

