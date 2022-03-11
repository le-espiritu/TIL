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

