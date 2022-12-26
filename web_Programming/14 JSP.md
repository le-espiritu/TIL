# JSP(Java Server Pages)



+ 모든  JSP는 서블릿으로 바뀌어서 동작한다.



### page 지시자

~~~jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
~~~



### scriptlet

~~~jsp
<%
	int total = 0;
	for(int i = 1; i <= 10; i ++){
		total = total + i;
	}
%>
~~~

### 표현식

~~~jsp
<%=total%>
<!-- 위 jsp의 표현식이 서블릿으로 바뀔때 out.print(total); 로 바뀐다.-->
~~~



### JSP 등장 배경

+ 마이크로소프트에서 ASP(Active Server Page)라는 쉽게 웹을 개발할 수 있는 스크립트(script)엔진을 발표함.(1998년)
+ 1997년에 발표된 서블릿은 ASP에 비하여 상대적으로 개발방식이 불편함.
+ ASP에 대항하기 위하여 1999년 썬마이크로시스템즈에서 JSP를 발표
+ JSP는 실제로 서블릿 기술을 사용



## JSP 라이프싸이클

> WAS는 웹 브라우저로부터 JSP에 대한 요청을 받게 되면, JSP코드를 서블릿 소스코드로 변환한 후 컴파일 하여 실행되게 된다.



### 서블릿으로 바뀐 jsp 파일의 경로

+ 워크스페이스 ->.metadata->.plugins-> org.eclipse.wst.server.core ->Temp0 -> work-> Catalina -> localhost -> firstweb(웹 어플리케이션 디렉토리) -> org -> apache -> jsp 디렉토리 안에 있음



### sum10.jsp가 실행될 때 벌어지는 일

+ 이클립스 워크스페이스 아래의 .metadata폴더에 sum10_jsp.java 파일이 생성된다.
+ 해당 파일의 _jspService()메소드 안을 살펴 보면 jsp파일의 내용이 변환되서 들어가 있는 것은 확인할 수 있다.
+ Sum10_jsp.java는 서블릿 소스로 자동으로 컴파일 되면서 실행되서 그 결과가 브라우저에 보여진다.



### JSP의 실행순서

+ 브라우저가 웹서버에 JSP에 대한 요청 정보를 전달한다.
+ 브라우저가 요청한 JSP가 최초로 요청했을 경우만
  + JSP로 작성된 코드가 서블릿 코드로 변환한다. (java 파일 생성)
  + 서블릿 코드를 컴파일해서 실행가능한 bytecode로 변환한다. (class 파일 생성)
  + 서블릿 클래스를 로딩하고 인스턴스를 생성한다.
+ 서블릿이 실행되어 요청을 처리하고 응답 정보를 생성한다.



### JSP 라이프싸이클

~~~jsp
<%
	System.out.print("jspService()");
%>

<%!
	public void jspInit(){
	System.out.print("JspInit()");
}
%>

<%! <!--선언식 -->
	public void jspDestroy(){
	System.out.print("jspDestroy()");
}
%>
~~~



## JSP 문법

> JSP는 HTML태그와 자바코드를 섞어서 개발할 수 있다.



### 스크립트 요소의 이해

+ JSP페이지에서는 선언문(Declaration), 스크립트릿(Scriptlet), 표현식(Expression) 이라는 3가지의 스크립트 요소를 제공.

  ~~~
  선언문(Declaration) - <%! %> : 전역변수 선언 및 메소드 선언에 사용
  스크립트릿(Scriptlet) - <% %> : 프로그래밍 코드 기술에 사용
  표현식(Expression) - <%=%> : 화면에 출력할 내용 기술에 사용
  ~~~

#### 선언문(Declaration)

+ 선언문:<%! %>

+ 선언문은 JSP페이지 내에서 필요한 멤버변수나 메소드가 필요할 때 선언해 사용하는 요소

+ 선언문의 문법

  + <%! 문장%>

  ~~~jsp
  id : <%=getId() %>
  <%!
  	String id = "u001"; // 멤버변수 선언
  	public String getId(){ //메소드 선언
  		return id;
  	}
  %>
  ~~~



#### 스크립트릿(Scriptlet)

+ 스크립트릿 : <% %>

+ 가장 일반적으로 많이 쓰이는 스크립트 요소

+ 주로 프로그래밍의 로직을 기술할 때 사용

+ 스크립트릿에서 선언된 변수는 지역변수

  + 왜냐하면 스크립트 안에서 선언된 변수는 다 Service라는 메서드 안에 선언되는 변수일 것이기 때문

+ 스크립트의 문법

  + <% 문장 %>

  ~~~jsp
  <%
  	for(int i =1; i<=5; i++){
  %>
  <h<%=i%>>아름다운 한글</h<%=i%>>
  <%
  	}
  %>
  ~~~



#### 표현식(Expression)

+ 표현식 : <%=%>

+ JSP페이지에서 웹 브라우저에 출력할 부분을 표현.

  즉, 화면에 출력하기 위한 것

+ 스크립트릿내에서 출력할 부분은 내장객체인 out객체의 print()또는 println()메소드를 사용해서 출력

+ 표현식의 문법

  + <%=문장%>



#### 주석(Comment)

+ JSP페이지에서 사용할 수 있는 주석

  + HTML주석, 자바주석, JSP주석

+ HTML 주석

  + HTML 주석은 \<!-- 로 시작해서 \--> 로 끝나는 형태
  + HTML 주석은 HTML주석을 사용한 페이지를 웹에서 서비스 할 때 화면에 주석의 내용이 표시되지는 않으나, [소스보기] 수행하면 HTML주석의 내용이 화면에 표시
  + HTML주석의 예시
    + \<!-- html 주석입니다. -->

+ JSP주석

  + JSP페이지에서만 사용되며 \<%--로 시작해서 \--%>로 끝나는 형태
  + jsp주석은 해당 페이지를 웹 브라우저를 통해 출력 결과로서 표시하거나, 웹 브라우저 상에서 소스 보기를 해도 표시 되지 않음. 또한 JSP주석 내에 실행코드를 넣어도 그 코드는 실행되지 않음
  + JSP주석의 예시
    + <%--JSP 주석입니다. --%>

+ 자바주석

  + 자바 주석은 //,/**/을 사용해서 작성

  + //은 한 줄짜리 주석을 작성할 때 사용되고, /**/은 여러 줄의 주석을 작성할 때 사용

  + 스크립트릿이나 선언문에서 사용되는 주석으로, 자바와 주석 처리 방법이 같은

  + 자바주석의 예시

    //주석

    /* 여러 줄에 걸친 주석이다. */





## JSP 내장객체

> JSP에서는 개발자가 선언하지 않아도, 사용할 수 있는 미리 선언된 변수가 존재한다. 이를 내장객체라고 한다.



### JSP 내장 객체란?

+  JSP를 실행하면 서블릿 소스가 생성되고 실행된다.
+ JSP에 입력한 대부분의 코드는 생성되는 서블릿 소스의 _jspService() 메소드 안에 삽입되는 코드로 생성된다.
+ _jspService()에 삽입된 코드의 윗 부분에 미리 선언된 객체들이 있는데, 해당 객체들은 jsp에서도 사용가능하다.
+ Response,request,application,session,out과 같은 변수를 내장객체라고 한다.



### 내장객체의 종류

![2_3_4_jsp_](https://user-images.githubusercontent.com/88477839/157896132-c6de6a86-6bb8-4d2b-b15f-7f9d4d841385.png)

