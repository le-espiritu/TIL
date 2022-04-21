# 상태유지기술(Cookie & Session)



## 상태정보란?



### 웹에서의 상태 유지 기술

+ HTTP프로토콜은 상태 유지가 되지 않는 프로토콜이다.
  + 이전에 무엇을 했고, 지금 무엇을 했는지에 대한 정보를 갖고 있지 않음
  + 웹 브라우저(클라이언트)의 요청에 대한 응답을 하고 나면 해당 클라이언트와의 연결을 지속하지 않음.
+ 상태 유지를 위해 Cookie와 Session기술이 등장함.



### 쿠키(Cooke)와 세션(Session)

+ 쿠키

  + 유지해야 할 정보를 사용자 컴퓨터에 저장
  + 저장된 정보를 다른 사람 또는 시스템이 볼 수 있는 단점
  + 유효기간이 지나면 사라짐

+ 세션

  + 서버에 저장
  + 서버가 종료되거나 유효시간이 지나면 사라짐

  

### 쿠키(Cookie) 동작 이해

![1](https://user-images.githubusercontent.com/88477839/163569857-dd3e98f9-468c-4535-9446-59f500f7592f.png)

+ 클라이언트가 서버 쪽에 요청을 보냄
+ 이때 유지해야 할 정보가 있다면 서버는 유지할 정보를 가지고 쿠키를 생성함
  + 이때 쿠키는 이름과 값으로 구성이 되어 있고 유지 시간 등의 정보를 가지게 된다.
+ 이렇게 만들어진 쿠키는 반드시 응답 결과에 포함되어서 클라이언트한테 보내져야 한다.



![2 (1)](https://user-images.githubusercontent.com/88477839/163570106-4613bb1b-0d77-4d39-850d-d0e925914fad.png)

+ 만들어진 쿠키는 클라이언트가 갖고 있게 되고, 요청을 보낼때 항상 쿠키를 같이 포함 시켜서 서버에게 보낸다.
+  서버는 지난번에 만들어준 쿠키가 있는지 검사를 한다.
  + 지난번에 만든 쿠키가 있다고 한다면 이 사용자가 전에 접속했던 사용자인지 또는 사용자가 유지해야 되는 정보가 이것인지 인식하게 된다.



### 세션 동작 이해

![3 (1)](https://user-images.githubusercontent.com/88477839/163570420-c00c0342-ff9b-4b88-8301-df567f5fca68.png)

+ 클라이언트가 요청을 했을 때 유지해야 되는 정보가 있다면 서버는 세션키를 만들어낸다.
+ 그리고 세션키를 이용한 저장소를 하나 생성한다. 
  + 이 저장소에다가 유지해야 되는 정보들을 저장하게 된다.
  + 그런데 저장소만 만들어놓고 그냥 끝나버리면, 추후에 클라이언트가 요청을 했을때 이 클라이언트의 저장소가 어떤 곳인지 서버는 알아낼 길이 없다.
+ 그래서 서버는 세션키를 담은 쿠키를 생성해서 클라이언트한테 보낸다.

![4 (1)](https://user-images.githubusercontent.com/88477839/163570759-ff11e124-ba13-45f5-9fea-912585a77b6e.png)

+ 클라이언트는 다시 요청할때마다 서버가 만들어서 보낸 쿠키를 가지고 매번 들어온다.
+ 서버는 쿠키로부터 세션키를 얻고, 이 세션키에 해당하는 저장소를 찾아서 저장소 안에 있는 정보를 활용하거나 저장소에다가 원하는 정보를 저장하는 일을 수행하게 된다.
+ 이때 세션의 정보를 담기 위해 생성되는 객체가  HttpSession이라는 객체를 이용하게 된다.



## 쿠키란?



### 쿠키 정의

+ 정의
  + 클라이언트 단에 저장되는 작은 정보의 단위
    + 쿠키는 자체적으로 key 하나와 value 하나 이렇게 값을 가지고 있다.
  + 클라이언트에서 생성하고 저장될 수 있고, 서버단에서 전송한 쿠키가 클라이언트에 저장될 수 있다.
+ 이용 방법
  + 서버에서 클라이언트의 브라우저로 전송되어 사용자의 컴퓨터에 저장
  + 저장된 쿠키는 다시 해당하는 웹 페이지에 접속 할 때, 브라우저에서 서버로 쿠키를 전송
  + 쿠키는 이름(name)과 값(value)으로 구성된 자료를 저장
    + 이름과 값 외에도 주석(comment), 경로(path), 유효기간(Max-Age,Expires), 버전(version), 도메인(domain)과 같은 추가적인 정보를 저장
+ 쿠키는 그 수와 크키에 제한
  + 하나의 쿠키는 4K Byte 크기로 제한
  + 브라우저는 각각의 웹사이트 당 20개의 쿠키를 허용
  + 모든 웹 사이트를 합쳐 최대 300개를 허용
  + 그러므로 클라이언트 당 쿠키의 최대 용량은 1.2M Byte



### javax.servlet.http.Cookie

+ 쿠키 객체의 기본 사용법은 서버에서 쿠키 객체를 생성한 후에 응답에 담아서 보내는 것이다.

+ 서버에서 쿠키 생성, Response의 addCookie메소드를 이용해 클라이언트에게 전송

  + ~~~java
    Cookie cookie = new Cookie(이름, 값);
    response.addCookei(cookie);
    ~~~

  + 쿠키는 (이름,값)의 쌍 정보를 입력하여 생성

  + 쿠키의 이름은 알파벳과 숫자로만 구성되고, 쿠키 값은 공백, 괄호, 등호, 콤마, 콜론, 세미콜론 등은 포함 불가능

+ 클라이언트가 보낸 쿠키 정보 읽기

  + ~~~java
    Cookie[] cookies = request.getCookies();
    ~~~

    + 하나의 서버가 쿠키를 여러 개 보낼 수 있기 때문에 클라이언트는 요청을 할때 서버가 보낸 쿠키를 몽땅 같이 담아서 요청을 한다. 그래서 배열이 리턴된다.

  + 쿠키 값이 없으면 null이 반환된다.

  + Cookie가 가지고 있는  getName()과 getValue()메소드를 이용해서 원하는 쿠키정보를 찾아 사용한다.

+ 클라이언트에게 쿠키 삭제 요청

  + 쿠키를 삭제하는 명령은 없고 maxAge(유효기간)가 0인, 내가 삭제하고자 하는 쿠키의 이름과 같은 이름의 쿠키를 만들어 전송한다.

    + 쿠키의 관리는 웹 클라이언트가 하기 때문에 서버쪽에서 삭제할 수 없다.

  + ~~~java
    Cookie cookie = new Cookie("이름",null);
    cookie.setMaxAge(0);
    response.addCookie(cookie);
    ~~~

+ 쿠키의 유효기간 설정

  + 메소드 setMaxAge()

    + 인자는 유효기간을 나타내는 초 단위의 정수형
    + 만일 유효기간을 0으로 지정하면 쿠키의 삭제
    + 음수를 지정하면 브라우저가 종료될 때 쿠키가 삭제

  + 유효기간을 10분으로 지정하려면

    + ~~~java
      cookie.setMaxAge(10*60); // 초 단위 : 10분
      ~~~

    + 1주일로 지정하려면 (7\*24\*60\*60)로 설정한다.

  ![1](https://user-images.githubusercontent.com/88477839/163658396-246c229f-f147-424b-9ef4-a92bf3e33ea5.png)



### Spring MVC에서의 Cookie 사용

+ @CookieValue 어노테이션 사용

  + 컨트롤러 메소드의 파라미터에서 CookieValue 어노테이션을 사용함으로써 원하는 쿠키정보를 파라미터 변수에 담아 사용할 수 있다.

  + ~~~java
    컨트롤러메소드(@CookieValue(value="쿠키이름",required=false,defaultValue="기본값")String 변수명)
    ~~~



## 쿠키를 이용한 상태정보 유지하기 1



### 페이지 방문수를 기억하는 쿠키 만들기

+ 기존 guestbook 프로젝트의 GuestbookController에 작업한다.

+ 아래는 기존 GuestbookController 코드

  ~~~java
  package kr.or.connect.guestbook.controller;
  
  import java.util.ArrayList;
  import java.util.List;
  
  import javax.servlet.http.HttpServletRequest;
  
  import org.springframework.beans.factory.annotation.Autowired;
  import org.springframework.stereotype.Controller;
  import org.springframework.ui.ModelMap;
  import org.springframework.web.bind.annotation.GetMapping;
  import org.springframework.web.bind.annotation.ModelAttribute;
  import org.springframework.web.bind.annotation.PostMapping;
  import org.springframework.web.bind.annotation.RequestParam;
  
  import kr.or.connect.guestbook.dto.Guestbook;
  import kr.or.connect.guestbook.service.GuestbookService;
  
  @Controller
  public class GuestbookController {
  	@Autowired // 서비스 이용하기 위함
  	GuestbookService guestbookService;
  	
  	@GetMapping(path="/list")
  	public String list(@RequestParam(name="start", required=false, defaultValue="0") int start,
  						ModelMap model) {
  		
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
  	
  }
  ~~~

   

+ 기존 GuestbookController 코드 에 쿠키와 관련된 코드 추가

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
  import org.springframework.web.bind.annotation.GetMapping;
  import org.springframework.web.bind.annotation.ModelAttribute;
  import org.springframework.web.bind.annotation.PostMapping;
  import org.springframework.web.bind.annotation.RequestParam;
  
  import kr.or.connect.guestbook.dto.Guestbook;
  import kr.or.connect.guestbook.service.GuestbookService;
  
  @Controller
  public class GuestbookController {
  	@Autowired // 서비스 이용하기 위함
  	GuestbookService guestbookService;
  	
  	@GetMapping(path="/list")
  	public String list(@RequestParam(name="start", required=false, defaultValue="0") int start,ModelMap model,HttpServletRequest request,HttpServletResponse response) {
  		
  		// V 쿠키
  		String value = null;
  		boolean find = false;
  		Cookie[] cookies = request.getCookies();
  		if(cookies != null) {
  			for(Cookie cookie : cookies) {
  				if("count".equals(cookie.getName())) {
  					find = true;
  					value = cookie.getValue();
  					break;
  				}
  			}
  		}
  		
  		if(!find) {
  			value="1"; // 쿠키는 String으로 된 value를 가진다.
  		}else {
  			try {
  				int i = Integer.parseInt(value);
  				value = Integer.toString(++i);
  			}catch(Exception ex) {
  				value="1";
  			}
  		}
  		
  		Cookie cookie = new Cookie("count",value); // 변경된 value의 값을 클라이언트쪽에도 적용하게 하려면 반드시 쿠키는 매번 새로 만들어서 보내줘야 한다. 그렇게 되면 클라이언트 쪽은 똑같은 이름의 쿠키가 들어왔을 때 기존의 쿠키와 교체한다.  
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
  	
  }
  ~~~
  
  + ~~~java
    @GetMapping(path="/list")
    	public String list(@RequestParam(name="start", required=false, defaultValue="0") int start,ModelMap model,HttpServletRequest request,HttpServletResponse response) 
        
    ~~~
  
    + HttpServletRequest와 HttpServletResponse를 메서드 인자에 추가

  + ~~~java
    // V 쿠키
    		String value = null;
    		boolean find = false;
    		Cookie[] cookies = request.getCookies();
    		if(cookies != null) { // ==>cookies가 null이면 처음 요청이 들어온것이다.
    			for(Cookie cookie : cookies) {
    				if("count".equals(cookie.getName())) {
    					find = true;
    					value = cookie.getValue();
    					break;
    				}
    			}
    		}
    		
    		if(!find) { //=> find가 false면 이라는 의미.
    			value="1"; // 쿠키는 String으로 된 value를 가진다.
    		}else {
    			try {
    				int i = Integer.parseInt(value);
    				value = Integer.toString(++i); //밸류값을 1 증가해서 다시 value에 넣어준다.
    			}catch(Exception ex) {
    				value="1";
    			}
    		}
    ~~~
  
  + ~~~java
    model.addAttribute("cookieCount", value);
    ~~~



+ view 코드

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

  + ~~~jsp
    방문한 수 : ${cookieCount}
    ~~~

    

## 쿠키를 이용한 상태정보 유지하기 2



### 스프링 MVC가 지원하는 @CookieValue 어노테이션을 활용하여 쿠키 만들기

+ HttpServletRequest 대신 스프링 MVC가 지원하는 @CookieValue 어노테이션 활용

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
  
  import kr.or.connect.guestbook.dto.Guestbook;
  import kr.or.connect.guestbook.service.GuestbookService;
  
  @Controller
  public class GuestbookController {
  	@Autowired // 서비스 이용하기 위함
  	GuestbookService guestbookService;
  	
  	@GetMapping(path="/list")
  	public String list(@RequestParam(name="start", required=false, defaultValue="0") int start,ModelMap model,@CookieValue(value="count", defaultValue="0", required = true) String value,HttpServletResponse response) {
  		
  
      try {
        int i = Integer.parseInt(value);
        value = Integer.toString(++i);
      }catch(Exception ex) {
        value="1";
      }
  
  		
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
  	
  }
  ~~~

  + ~~~java
    	@GetMapping(path="/list")
    	public String list(@RequestParam(name="start", required=false, defaultValue="0") int start,ModelMap model,@CookieValue(value="count", defaultValue="0", required = true) String value,HttpServletResponse response)
    ~~~

  + ~~~java
        try {
          int i = Integer.parseInt(value);
          value = Integer.toString(++i);
        }catch(Exception ex) {
          value="1";
        }
    
    		
    		Cookie cookie = new Cookie("count",value); // 변경된 value의 값을 클라이언트쪽에도 적용하게 하려면 반드시 쿠키는 매번 새로 만들어서 보내줘야 한다. 그렇게 되면 클라이언트 쪽은 똑같은 이림의 쿠키가 들어왔을 때 기존의 쿠키와 교체한다.  
    		cookie.setMaxAge(60*60*24*365);//1년 동안 유지.
    		cookie.setPath("/"); // /경로 이하에 모두 쿠키 적용.
    		response.addCookie(cookie); // 쿠키가 클라이언트한테 저장
    ~~~

  + ~~~java
    		model.addAttribute("cookieCount", value);
    ~~~



## Session 이란?



### 세션 정의

+ 정의
  + 클라이언트 별로 서버에 저장되는 정보
+ 이용 방법
  + 웹 클라이언트가 서버측에 요청을 보내게 되면 서버는 클라이언트를 식별하는 session id를 생성
  + 서버는 session id를 이용해서 key와 value를 이용한 저장소인 HttpSession을 생성
  + 서버는 session id를 저장하고 있는 쿠키를 생성하여 클라이언트에 전송
  + 클라이언트는 서버측에 요청을 보낼때 session id를 가지고 있는 쿠키를 전송
  + 서버는 쿠키에 있는 session id를 이용해서 그 전 요청에서 생성한 HttpSession을 찾고 사용한다.



### 세션 생성 및 얻기

+ ~~~java
  HttpSession session = request.getSession();
  HttpSession session = request.getSession(true);
  ~~~

  + 개발자가 직접 new해서 만들지 않고, 클라이언트의 요청이 들어오면 서버가 알아서 저절로 session을 만든다. (Session id를 만들고 sesion 객체를 만들고 해당하는 쿠키를 만들어서 응답을 보내고 이런 일련의 과정)
  + request의 getSession()메소드는 서버에 생성된 세션이 있다면 세션을 반환하고,
  + 없다면 새롭게 세션을 생성하여 반환한다.
  + 새롭게 생성된 세션인지는  HttpSession이 가지고 있는 isNew()메소드를 통해 알 수 있다.



+ ~~~java
  HttpSession session = request.getSession(false);
  ~~~

  + request의 getSession()메소드에 파라미터로 false를 전달하면, 이미 생성된 세션이 있다면 반환하고 없으면 null을 반환한다.



### 세션에 값 저장

+ setAttribute(String name, Object value)

+ ~~~java
  setAttribute(String name, Object value)
  session.setAttribute(이름,값)
  ~~~

  + 사용방법은 sope에서 setAttribute(), getAttribute() 사용했던 것과 동일하다.
  + name과 value의 쌍으로 객체 Object를 저장하는 메소드
  + 세션이 유지되는 동안 저장할 자료를 저장



### 세션 값 조회

+ getAttribute(String name) 메소드

  + 세션에 저장된 자료는 다시 getAttribute(String name) 메소드를 이용해 조회

  + 반환 값은 Object 유형이므로 저장된 객체로 자료유형 변환이 필요

  + 메소드 setAttribute()에 이용한 name인 "id"를 알고 있다면 다음과 같이 바로 조회

    ~~~
    String value = (String) session.getAttribute("id");
    ~~~

    

### 세션에 값 삭제

+ removeAttribute(String name)메소드
  + name값에 해당하는 세션 정보를 삭제한다.
+ invalidate()메소드
  + 모든 세션 정보를 삭제한다.



### javax.servlet.http.HttpSession

![1 (1)](https://user-images.githubusercontent.com/88477839/163679054-7181ac6b-bc89-4225-b2a0-b0c40f38313c.png)

![2 (1)](https://user-images.githubusercontent.com/88477839/163679085-745bcd69-66ae-4d3d-ac78-c4b964d22a18.png)



+ 세션은 클라이언트가 서버에 접속하는 순간 생성

  + 특별히 지정하지 않으면 세션의 유지 시간은 기본 값으로 30분 설정

  + 세션의 유지 시간이란 서버에 접속한 후 서버에 요청을 하지 않는 최대 시간

  + 30분 이상 서버에 전혀 반응을 보이지 않으면 세션이 자동으로 끊어짐.

  + 이 세션 유지 시간은 web.xml파일에서 설정 가능

    ~~~xml
    <session-config>
    	<session-timeout>30</session-timeout>
    </session-config>
    ~~~

    

## Session을 이용한 상태정보 유지하기



### 세션 실습 조건

+ /guess로 요청을 하면 컴퓨터가 1부터 100사이의 임의의 값중의 하나를 맞춰보라는 메시지를 출력함. 해당 값은 세션에 저장함
+ 사용자는 1부터 100사이의 값을 입력함
+ 입력한 값이 세션값보다 작으면, 입력한 값이 작다고 출력함
+ 입력한 값이 세션값보다 크면, 입력한 값이 크다고 출력함
+ 입력한 값이 세션값과 같다면 몇번째에 맞췄다고 출력함



### 컨트롤러 생성

+ controller 패키지에 GuessNumberController 생성함.

  ~~~java
  package kr.or.connect.guestbook.controller;
  
  import javax.servlet.http.HttpSession;
  
  import org.springframework.stereotype.Controller;
  import org.springframework.ui.ModelMap;
  import org.springframework.web.bind.annotation.GetMapping;
  import org.springframework.web.bind.annotation.RequestParam;
  
  @Controller
  public class GuessNumberController {
  	@GetMapping("/guess")
  	public String guess(@RequestParam(name="number", required=false)Integer number,HttpSession session, ModelMap model) {
  		String message = null;
  		if(number == null) { // 요청이 최초로 들어왔을때가 number == null 이다.
  			session.setAttribute("count", 0);
  			session.setAttribute("randomNumber", (int)(Math.random()*100)+1);
  			message = "내가 생각한 숫자를 맞춰보세요.";
  		}else {
  			int count = (Integer)session.getAttribute("count"); //세션의 값이 object로 들어있었을 거기 때문에 Integer
  			int randomNumber = (Integer)session.getAttribute("randomNumber");
  			
  			if(number < randomNumber) {
  				message = "입력한 값은 내가 생각하고 있는 숫자보다 작습니다.";
  				session.setAttribute("count", ++count);
  			}else if (number > randomNumber) {
  				message = "입력한 값은 내가 생각하고 있는 숫자보다 큽니다.";
  				session.setAttribute("count", ++count);
  			}else {
  				message="OK ^^" + ++count + "번째 맞췄습니다.내가 생각한 숫자는"+number+"입니다.";
  				session.removeAttribute("count");
  				session.removeAttribute("randomNumber");
  			}
  		}
  		
  		model.addAttribute("message", message);
  		return "guess";
  	}
  	
  }
  ~~~

  + ~~~java
    @GetMapping("/guess")
    	public String guess(@RequestParam(name="number", required=false)Integer number,HttpSession session, ModelMap model) 
    ~~~

    + 스프링을 사용하지 않을때는 세션을 사용하기 위해 request로 부터 getSession()메서드를 이용하여 얻어와야 했었는데 스프링은 이 일을 대신 처리해준다. 그래서 스프링에서는 session하고 선언만 해주면 된다.

+ guess.jsp 생성

  ~~~jsp
  <%@ page language="java" contentType="text/html; charset=UTF-8"
      pageEncoding="UTF-8"%>
  
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
   
  <!DOCTYPE html>
  <html>
  <head>
  <meta charset="UTF-8">
  <title>숫자 맞추기 게임 </title>
  </head>
  <body>
  	<h1>숫자 맞추기 게임</h1>
  	<hr>
  	<h3>${message }</h3>
  	
  	<c:if test="${sessionScope.count != null }"> <!-- 정답을 맞췄을 때에는 해당 폼이 보여지지 않을 것 --> 
  		<form method="get" action="guess">
  			1부터 100까지의 숫자로 맞춰주세요.<br> <input type="text" name="number"><br>
  			<input type="submit" value="확인">
  		</form>
  	</c:if>
  	
  	<a href="guess">게임 다시 시작하기 </a>
  </body>
  </html>
  ~~~

  