

# scope



## scope란?

> Servlet과 JSP를 개발하다보면 변수를 많이 다루게 된다.
>
> 어떤 변수는 웹 어플리케이션에서 공유하고 싶은 변수가 있을 수도 있고, 어떤 변수는 사용자 별로 유지하고 싶은 변수가 있을 수 있다. 또 어 떤 변수는 포워딩 되는 동안 유지하고 싶은 변수가 있다.
>
> 이렇게 변수를 어떤 범위 내에서 사용하기 위해서는 스코프(scope)라는 것에 대해서 알아야 한다.



### 4가지 Scope

![2_5_1_scope_](https://user-images.githubusercontent.com/88477839/158007152-4a734bbd-c699-4304-a4cc-631dbf9572a5.jpeg)

+ page : 페이지 내에서 지역변수처럼 사용
+ Request : http요청을 WAS가 받아서 웹 브라우저에게 응답할 때까지 변수가 유지되는 경우 사용
+ Session : 웹 브라우저 별로 변수가 관리되는 경우 사용
+ Application : 웹 어플리케이션이 시작되고 종료될 때까지 변수가 유지되는 경우 사용





## Page scope

> 특정 서블릿이나 JSP가 실행되는 동안에만 정보를 유지 하고 싶은 경우가 있다.
>
> 이 경우에 사용되는 것이 page scope이다.



### page scope

+ PageContext 추상 클래스를 사용한다.

+ JSP페이지에서 pageContext 라는 내장 객체로 사용 가능하다.

  + pageContext.setAttribute 로 사용
  + pageContext.getAttribute 로 사용

+ forward가 될 경우 해당 Page scope에 지정된 변수는 사용할 수 없다.

+ 사용방법은 Application scope나 Sessionn scope, request scope와 같다.

+ 마치 지역변수처럼 사용된다는 것이 다른 Scope들과 다르다.

+ jsp에서 pageScope에 값을 저정 한 후 해당 값을 EL표기법등에서 사용할 때 사용된다.

  지역 변수 처럼 해당 jsp나 서블릿이 실행되는 동안에만 정보를 유지하고자 할 때 사용된다.



## Request scope

> 웹 브라우저로부터 WAS가 요청을 받은 후, 포워드 되는 동안 유지하고 싶은 정보가 있을 경우 request scope를 사용한다.



### Request scope

+ http요청을 WAS가 받아서 웹 브라우저에게 응답할 때까지 변수값을 유지하고자 할 경우 사용한다.
+ HttpServletRequest객체를 사용한다.
+ JSP에서는 request내장 변수를 사용한다.
+ 서블릿에서는 HttpServletRequest객체를 사용한다.
+ 값을 저장할 때는 request객체의 setAttribute()메소드를 사용한다.
+ 값을 읽어들일 때는 request객체의 getAttribute()메소드를 사용한다.
+ forward시 값을 유지하고자 사용한다.
+ 앞에서 forward에 대하여 배울때 forward하기 전에 request객체의 setAttribute()메소드로 값을 설정한 후, 서블릿이나 jsp에게 결과를 전달하여 값을 출력하도록 하였는데 이렇게 포워드 되는 동안 값이 유지되는 것이 Request scope를 이용했다고 한다.



## session scope

> 접속한 웹 브라우저 별로 정보를 관리 하고 싶을 때 세션 scope를 사용한다.



### Session scope

+ 웹 브라우저 별로 변수를 관리하고자 할 경우 사용한다.
+ 웹 브라우저간의 탭간에는 세션정보가 공유되기 때문에, 각각의 탭에서는 같은 세션정보를 사용할 수 있다.
+ HttpSession인터페이스를 구현한 객체를 사용한다.
+ JSP에서는 session내장 변수를 사용한다.
  + session.setAttribute()
  + session.getAttribute()
+ 서블릿에서는 HttpServletRequest의 getSession()메소드를 시용하여 session 객체를 얻는다.
+ 값을 저장할 때는 session객체의 setAttribute()메소드를 사용한다.
+ 값을 읽어들일 때는 session객체의 getAttribue()메소드를 사용한다.
+ 장바구니 처럼 사용자별로 유지가 되어야 할 정보가 있을 때 사용한다.



## application scope

> 하나의 웹 어플리케이션에서 공유하고 싶은 변수가 있을 때 application scope를 사용한다.



### Application scope

+ 어플리케이션 스코프는 하나의 웹 어플리케이션 내에 객체 하나라고 볼 수 있다. 하나의 어플리케이션 내에서는 다 이 어플리케이션 스코프 객체에 뭔가 값을 저장해놓고 사용할 수 있는 영역이다.
+ 웹 어플리케이션이 사직되고 종료될 때까지 변수를 사용할 수 있다.
+ ServletContext 인터페이스를 구현한 객체를 사용한다.
+ jsp에서는 application 내장 객체를 이용한다.
+ 서블릿의 경우는 getServletContext()메소드를 이용하여 application객체를 이용한다.
+ 웹 어플리케이션 하나당 하나의 application 객체가 사용된다.
+ 값을 저장할 때는 application객체의 setAttribute()메소드를 사용한다.
+ 값을 읽어들일 때는 application 객체의 getAttribute()메소드를 사용한다.
+ 모든 클라이언트가 공통으로 사용해야할 값들이 있을 때 사용한다.



### Application scope 실습

~~~java
// ApplicationScope01

package examples;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ApplicationScope
 */
@WebServlet("/ApplicationScope01")
public class ApplicationScope01 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApplicationScope01() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		
		ServletContext application = getServletContext();
		int value = 1;
		application.setAttribute("value", value);
		
		out.println("<h1>value :" + value + "</h1>");
	}

}

~~~

~~~java
//ApplicationScope02

package examples;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ApplicationScope02
 */
@WebServlet("/ApplicationScope02")
public class ApplicationScope02 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApplicationScope02() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		
		ServletContext application = getServletContext();
		try {
			int value = (int)application.getAttribute("value");
			value++;
			application.setAttribute("value", value);
			
			out.println("<h1>value :" + value + "</h1>");
		} catch (NullPointerException e) {
			out.println("value의 값이 설정되지 않았습니다.");
		}
		
	}

}
~~~

~~~jsp
<!-- ApplicationScope01.jsp -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	try{
		int value = (int)application.getAttribute("value");
		value = value +2;
		application.setAttribute("value", value);
%>
		<h1><%=value %></h1>
<%
	}catch(NullPointerException ex){
%>
		<h1>설정된 값이 없습니다.</h1>
<%
	}
%>	
	
</body>
</html>
~~~

