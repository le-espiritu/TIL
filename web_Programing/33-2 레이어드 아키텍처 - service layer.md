#  레이어드 아키텍처 - Service, Controller



## 레이어드 아키텍처 실습4



### Service Layer 부분 작성

+ kr.or.connect.guestbook.service 패키지 생성 - 서비스 인터페이스 보관

+ kr.or.connect.guestbook.service.impl 패키지 생성 - 실제 구현체 보관

+ kr.or.connect.guestbook.service 패키지에 인터페이스 생성 -GuestbookService.java

  ~~~java
  // GuestbookService.java
  
  package kr.or.connect.guestbook.service;
  
  import java.util.List;
  
  import kr.or.connect.guestbook.dto.Guestbook;
  
  public interface GuestbookService {
  	public static final Integer LIMIT = 5;
  	public List<Guestbook> getGuestbooks(Integer start);
  	public int deleteGuestbook(Long id, String ip);
  	public Guestbook addGuestbook(Guestbook guestbook, String ip);
  	public int getCount();
  }
  ~~~

+ 인터페이스 구현하는 클래스 생성 - GuestbookServiceImpl.java

  ~~~java
  // GuestbookServiceImpl.java
  
  package kr.or.connect.guestbook.service.impl;
  
  import java.util.Date;
  import java.util.List;
  
  import org.springframework.beans.factory.annotation.Autowired;
  import org.springframework.stereotype.Service;
  import org.springframework.transaction.annotation.Transactional;
  
  import kr.or.connect.guestbook.dao.GuestbookDao;
  import kr.or.connect.guestbook.dao.LogDao;
  import kr.or.connect.guestbook.dto.Guestbook;
  import kr.or.connect.guestbook.dto.Log;
  import kr.or.connect.guestbook.service.GuestbookService;
  
  @Service
  public class GuestbookServiceImpl implements GuestbookService{
  	@Autowired
  	GuestbookDao guestbookDao;
  	
  	@Autowired
  	LogDao logDao;
  
  	@Override
  	@Transactional
  	public List<Guestbook> getGuestbooks(Integer start) {
  		List<Guestbook> list = guestbookDao.selectAll(start, GuestbookService.LIMIT);
  		return list;
  	}
  
  	@Override
  	@Transactional(readOnly=false)
  	public int deleteGuestbook(Long id, String ip) {
  		int deleteCount = guestbookDao.deleteById(id);
  		Log log = new Log();
  		log.setIp(ip);
  		log.setMethod("delete");
  		log.setRegdate(new Date());
  		logDao.insert(log);
  		return deleteCount;
  	}
  
  	@Override
  	@Transactional(readOnly=false)
  	public Guestbook addGuestbook(Guestbook guestbook, String ip) {
  		guestbook.setRegdate(new Date());
  		Long id = guestbookDao.insert(guestbook);
  		guestbook.setId(id);
  		
  		Log log = new Log();
  		log.setIp(ip);
  		log.setMethod("insert");
  		log.setRegdate(new Date());
  		logDao.insert(log);
  		
  		return guestbook;
  	}
  
  	@Override
  	public int getCount() {
  		return guestbookDao.selectCount();
  	}
  }
  
  ~~~

  + @Transactional 
    + 읽기만 하는 메서드는 트랜잭션을 처리할 때  @Transactional 이라는 어노테이션을 붙여주면 내부적으로 readOnly라는 connection을 사용하게 된다.
    + 위 어노테이션이 있기때문에 위 어노테이션이 붙은 메서드는 코드 실행이 트랜잭셔널하게 처리된다.
    + 예를 들어 코드 중간에 에러가 발생하면 그 전에 처리됐던 코드도 모두 취소된다.
    + 만약 @Transactional 이라는 어노테이션이 없다면 메서드 실행 중간 코드에 에러가 발생했더라도 에러 발생 전까지는 코드 실행 & 반영이 되는 것을 볼 수 있을 것이다.



+ 위 코드를 테스트하는 클래스 생성 - GuestbookServiceTest.java

  ~~~java
  // GuestbookServiceTest.java
  
  package kr.or.connect.guestbook.service.impl;
  
  import java.util.Date;
  
  import org.springframework.context.ApplicationContext;
  import org.springframework.context.annotation.AnnotationConfigApplicationContext;
  
  import kr.or.connect.guestbook.config.ApplicationConfig;
  import kr.or.connect.guestbook.dto.Guestbook;
  import kr.or.connect.guestbook.service.GuestbookService;
  
  public class GuestbookServiceTest {
  
  	public static void main(String[] args) {
  		ApplicationContext ac = new AnnotationConfigApplicationContext(ApplicationConfig.class);
  		GuestbookService guestbookService = ac.getBean(GuestbookService.class);
  		
  		Guestbook guestbook = new Guestbook();
  		guestbook.setName("이동연");
  		guestbook.setContent("반갑습니다. 여러분 ");
  		guestbook.setRegdate(new Date());
  		Guestbook result = guestbookService.addGuestbook(guestbook, "127.0.0.1");
  		System.out.println(result);
  	}
  
  }
  
  
  // 실행결과
  // Guestbook [id=2, name=이동연, content=반갑습니다. 여러분 , regdate=Thu Apr 07 21:08:04 KST 2022]
  ~~~



##  레이어드 아키텍쳐 실습 5



### Controller 작성

+ kr.or.connect.guestbook.controller 패키지 생성

+ GuestbookController.java controller 생성

  ~~~java
  // GuestbookController.java
  
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
  	public String list(@RequestParam(name="start", required=false, defaultValue="0") int start,ModelMap model) {
  		
  		//start로 시작하는 방명록 목록 구하기
  		List<Guestbook> list = guestbookService.getGuestbooks(start);
  		
  		// 전체 페이지수 구하기
  		int count = guestbookService.getCount();
  		int pageCount = count/GuestbookService.LIMIT;
  		if(count%GuestbookService.LIMIT>0) // 나누어 딱 떨어지지 않으면 페이지수 증가시키겠다.
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
  
  + ~~~java
    @GetMapping(path="/list")
    	public String list(@RequestParam(name="start", required=false, defaultValue="0") int start,ModelMap model)
    ~~~
  
    + /guestbook/list? start=0 를 의미함

### view 작성

+ views에 list.jsp 생성

+ ~~~jsp
  <!-- list.jsp -->
  
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
  	<br> 방명록 전체 수 : ${count }
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

  + varStatus - 상태용 변수