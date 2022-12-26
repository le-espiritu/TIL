# Controller - RestController



## RestController란?

> Spring MVC에서 제공하는 RestController



### @RestController

+ Spring4에서 Rest API또는 Web API를 개발하기 위해 등장한 어노테이션
+ 이전 버전의 @Contoller와 @ResponseBody를 포함한다.



### MessageConverter

+ 자바 객체와 HTTP요청/응답 바디를 변환하는 역할
+ @ResponseBody, @RequestBody
+ @EnableWebMvc로 인한 기본 설정
  + WebMvcConfigurationSupport를 사용하여 Spring MVC 구현을 하고 있음
  + Default MessageConverter를 제공하고 있음
  + [링크 바로가기](https://github.com/spring-projects/spring-framework/blob/master/spring-webmvc/src/main/java/org/springframework/web/servlet/config/annotation/WebMvcConfigurationSupport.java) 의 addDefaultHttpMessageConverters메소드 항목 참조
+ RestController를 사용하기 위해서는 MessageConverter가 굉장히 중요한다.
  + 예를 들면 외부에서 전달받은 JSON 메서드를 내부에서 사용할 수 있는 객체로 변환하거나 
  + 컨트롤러를 리턴 한 객체가 클라이언트에게 JSON으로 변환해서 전달할 수 있도록 하는 역할을 수행
+ 이런 MessageConverter를 @EnableWebMvc로 사용하게 되면 기본으로 제공이 된다.



### MessageConvertor 종류

![1](https://user-images.githubusercontent.com/88477839/162403289-9b29703d-af7d-43c2-86e8-c8331e948145.png)



### json 응답하기

+ 컨트롤러의 메소드에서는 json으로 변환될 객체를 반환한다.
+ jackson라이브러리를 추가할 경우 객체를 json으로 변환하는 메시지 컨버터가 사용되도록 @EnableWebMvc에서 기본으로 설정되어 있다.
+ jackson라이브러리를 추가하지 않으면 json메시지로 변환할 수 없어 500오류가 발생한다.
+ 사용자가 임의의 메시지 컨버터(MessageConverter)를 사용하도록 하려면 WebMvcConfigurerAdapter의 configureMessageConverters메소드를 오버라이딩 하도록 한다.



## RestController를 이용하여  web api작성하기



### RestController 생성

+ guestbook 프로젝트 kr.or.connect.guestbook.controller 패키지에 생성한다.

+ GuestbookApiController.java

  ~~~java
  package kr.or.connect.guestbook.controller;
  
  import java.util.ArrayList;
  import java.util.Collections;
  import java.util.HashMap;
  import java.util.List;
  import java.util.Map;
  
  import javax.servlet.http.HttpServletRequest;
  
  import org.springframework.beans.factory.annotation.Autowired;
  import org.springframework.web.bind.annotation.DeleteMapping;
  import org.springframework.web.bind.annotation.GetMapping;
  import org.springframework.web.bind.annotation.PathVariable;
  import org.springframework.web.bind.annotation.PostMapping;
  import org.springframework.web.bind.annotation.RequestBody;
  import org.springframework.web.bind.annotation.RequestMapping;
  import org.springframework.web.bind.annotation.RequestParam;
  import org.springframework.web.bind.annotation.RestController;
  
  import kr.or.connect.guestbook.dto.Guestbook;
  import kr.or.connect.guestbook.service.GuestbookService;
  
  @RestController
  @RequestMapping(path="/guestbooks")
  public class GuestbookApiController {
  	@Autowired
  	GuestbookService guestbookService;
  	
  	// /questbooks URL로 요청이 들어오면서 Content-Type이 application/json , Get방식으로 들어오면 아래 메서드 실행해라 라는 의미 
  	// 결과값으로 Map 객체를 반환하는데, application/json 요청이기 때문에 DispatcherServlet은 jsonMessageConvert를 내부적으로 사용해서
  	// 해당 Map객체르로 json으로 변환해서 전송을 하게 된다.
  	@GetMapping // path가 따로 존재하지 않는 이유는 위에 RequestMapping이 있어서
  	public Map<String, Object> list(@RequestParam(name="start", required=false, defaultValue="0") int start) {
  		
  		List<Guestbook> list = guestbookService.getGuestbooks(start);
  		
  		int count = guestbookService.getCount();
  		int pageCount = count / GuestbookService.LIMIT;
  		if(count % GuestbookService.LIMIT > 0)
  			pageCount++;
  		
      //array는 크기가 고정되어 있기 때문에
      //아래 코드와 같이 크기가 가변적인 상황에서 쓸 수 없다.
  		List<Integer> pageStartList = new ArrayList<>();
  		for(int i = 0; i < pageCount; i++) {
  			pageStartList.add(i * GuestbookService.LIMIT);
  		}
  		
  		Map<String, Object> map = new HashMap<>();
  		map.put("list", list);
  		map.put("count", count);
  		map.put("pageStartList", pageStartList);
  		
  		return map;
  	}
  	
  	//클라이언트한테 응답이 갈 때는 json으로 바뀌어서 간다.
  	@PostMapping
  	public Guestbook write(@RequestBody Guestbook guestbook,
  						HttpServletRequest request) {
  		String clientIp = request.getRemoteAddr();
  		// id가 입력된 guestbook이 반환된다.
  		Guestbook resultGuestbook = guestbookService.addGuestbook(guestbook, clientIp);
  		return resultGuestbook;
  	}
  	
  	@DeleteMapping("/{id}")
  	public Map<String, String> delete(@PathVariable(name="id") Long id,
  			HttpServletRequest request) {
  		String clientIp = request.getRemoteAddr();
  		
  		int deleteCount = guestbookService.deleteGuestbook(id, clientIp);
  		return Collections.singletonMap("success", deleteCount > 0 ? "true" : "false");
  	}
  }
  
  ~~~



### Rest API 테스트

+ 크롬 브라우저에서 Restlet Client라는 확장 프로그램 설치
+ 크롬 웹스토어에서 Restlet Client 검색해서 설치한다.
+ <img width="1128" alt="스크린샷 2022-04-08 18 55 34" src="https://user-images.githubusercontent.com/88477839/162424425-8581c27d-c2ef-4551-bd02-272f2c26c3b2.png">
  + 왼쪽 하단의 프로젝트 버튼을 눌러서 프로젝트를 생성한다.



### GetMapping 테스트

+ <img width="1129" alt="스크린샷 2022-04-08 18 56 13" src="https://user-images.githubusercontent.com/88477839/162424584-6d747233-d07f-4cff-bf37-d40f9d7b16c9.png">
  + Add a request를 클릭한다
+ <img width="1131" alt="스크린샷 2022-04-08 18 56 44" src="https://user-images.githubusercontent.com/88477839/162424725-e5014b84-a121-4a82-b229-ce401cd248f2.png">
+ <img width="1174" alt="스크린샷 2022-04-08 20 14 37" src="https://user-images.githubusercontent.com/88477839/162425156-8fd1005b-ebf6-4182-9396-01ef0359ee04.png">
  + method를 선택할 수 있고 테스트할 url을 넣어준다.
+ <img width="1280" alt="스크린샷 2022-04-08 20 17 51" src="https://user-images.githubusercontent.com/88477839/162425563-4aa73136-1eb8-4fb7-b3a5-3266dd9309ea.png">
  + HEADERS에 Add header 를 누르고, Content-Type은 application/josn으로 지정해준다.
  + 그리고나서 send를 클릭해준다.
+ <img width="1280" alt="스크린샷 2022-04-08 20 26 47" src="https://user-images.githubusercontent.com/88477839/162427054-88a37536-abd2-464e-9624-08c93c0473e6.png">
  + response로 list가 보여지는 것을 확인 할 수 있다.
  + 지금 여기서 나오는 데이터들은 데이터베이스에서 guestbook을 select 했을때 나오는 값과 동일하다.



### PostMapping 테스트

+ <img width="1280" alt="스크린샷 2022-04-08 20 38 19" src="https://user-images.githubusercontent.com/88477839/162428522-50974573-d5dc-4018-9ddc-e5ea80cff190.png">
  + method를 Post로 선택해준다.
  + URL은 동일
  + Content-Type 동일
  + 실제 입력할 값을 바디에다가 json형태로 추가해 준다.
  + send를 누른다.
+ <img width="1280" alt="스크린샷 2022-04-08 20 41 08" src="https://user-images.githubusercontent.com/88477839/162428796-e0ea217a-0e8b-48f3-81bf-edd459318c79.png">
  + 200번이 잘 응답 되면서 성공했다는 것을 볼 수 있다.
+ <img width="883" alt="스크린샷 2022-04-08 20 43 25" src="https://user-images.githubusercontent.com/88477839/162429076-c9d47c62-0aac-4220-9fcb-99f192ad1581.png">
  + db에서도 잘 입력된 것을 확인 할 수 있다.



### DeleteMapping 테스트

+ <img width="1280" alt="스크린샷 2022-04-08 20 49 09" src="https://user-images.githubusercontent.com/88477839/162430016-b2890d9a-2709-4bc5-95a0-562e8bf4dbbc.png">
  + method를 DELETE로 변경
  + URL 뒤에 삭제하고자 하는 id 입력 (/3)
  + send를 누른다.
+ <img width="1280" alt="스크린샷 2022-04-08 20 49 18" src="https://user-images.githubusercontent.com/88477839/162430335-ee0f9196-fae7-407c-afaf-77a0c189bc04.png">
  + Success : "true"가 리턴되는 것을 확인할 수 있다.

+ <img width="880" alt="스크린샷 2022-04-08 20 53 48" src="https://user-images.githubusercontent.com/88477839/162430587-3603da62-3182-4727-b380-5e65556a0d63.png">
  + db에서도 3번 데이터가 삭제된 것을 확인 할 수 있다.