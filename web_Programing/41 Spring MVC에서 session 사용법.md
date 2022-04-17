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

  