# redirect & forward



## redirect

> 게시판 같은 곳에서 글을 작성하고 글 작성 버튼을 클릭하면, 이때 클라이언트가 서버에게 글을 작성해주세요 라는 요청을 보내게 된다. 서버는 해당 글을 저장한 후, 웹 브라우저에게 글 목록으로 이동하라고 응답을 보내게 된다.
>
> 웹 브라우저는 글 목록으로 이동하라는 서버의 요청을 받은 후, 자동으로 서버에게 글 목록을 요청하여 응답받아 출력하게 된다.
>
> 서버가 클라이언트에게 어떤 URL로 이동하라는 요청을 보내는 것을 리다이렉트라고 한다.



### 리다이렉트(redirect)

+ 리다이렉트는 http프로토콜로 정해진 규칙이다.
+ 서버는 클라이언트로부터 요청을 받은 후, 클라이언트에게 특정 URL로 이동하라고 요청할 수 있다. 이를 리다이렉트라고 한다.
+ 서버에서는 클라이언트에게 응답으로 상태코드를 302와 함께 이동할 URL정보를 Location 헤더(Header)에 담아 전송한다. 클라이언트는 서버로 부터 받은 상태값이 302이면(302인지 확인하고) Locataion헤더값으로 재요청을 보내게 된다. 이때 브라우저의 주소창은 전송받은 URL로 바뀌게 된다.
+ 서블릿이나 jsp는  redirect하기 위해서 HttpServletResponse가 가지고 있는 sendRedirect()메소드를 사용한다.



### 실습

~~~jsp
<!-- redirect01.jsp -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	response.sendRedirect("redirect02.jsp");
%>
~~~

+ url 주소로 http://localhost:8080/firstweb/redirect01.jsp 을 입력해도 리다이렉트 때문에

  http://localhost:8080/firstweb/redirect02.jsp url이 실행되는 것을 확인할 수 있다.



### 브라우저에서 리다이렉트 확인

<img width="1188" alt="스크린샷 2022-03-12 10 57 08" src="https://user-images.githubusercontent.com/88477839/157999370-04c4782d-3ac5-47cd-9464-f6cf80822b84.png">



![2_4_1_redirect__](https://user-images.githubusercontent.com/88477839/157999454-ee476f96-b173-431c-b723-46972c37c5f3.png)

+ 사용자가 링크를 클릭한다거나 URL을 바꿔서 쓴다거나 이런 동작을 하지 않았음에도 이 브라우저가 알아서 302라는 코드가 들어왔음을 확인했기 때문에, Location 헤더 값에 가져온 redirect02.jsp를, 브라우저가 알아서 자동으로 다시 서버한테 요청하게 된다.

+ 리다이렉트를 할때 중요한 점이 클라이언트가 요청을 한번, 두번 보내게 된다는 점이다.
  + 처음 redirect01이 들어왔을 때 생겼던 요청 객체와 응답객체랑 다시 들어와서 redirect02가 요청이 들어갔을 때 생기는 요청 객체와 응답 객체는 다른 객체이다.
  + 이 두 요청은 서로 다른 요청이다.



## forward

> WAS의 서블릿이나 JSP가 요청을 받은 후 그 요청을 처리하다가, 추가적인 처리를 같은 웹 어플리케이션 안에 포함된 다른 서블릿이나 JSP에게 위임하는 경우가 있다.
>
> 이렇게 위임하는 것을 포워드(forward)라고 한다.



### forward 란?

![2_4_2_forward](https://user-images.githubusercontent.com/88477839/158002035-a7b143dd-918f-4d53-ad9e-7b7e4a91ed79.png)

+ 웹 브라우저에서 Servlet1에게 요청을 보냄
+ Servlet 1은 요청을 처리한 후, 그 결과를 HttpServletRequest에 저장
+ Servlet1은 결과가 저장된 HttpServletRequest와 응답을 위한 HttpServletResponse를 같은 웹 어플리케이션 안에 있는 Servlet2에게 전송(forward)
+ Servlet2는 Servlet1으로 부터 받은 HttpServletRequest와 HttpServletResponse를 이용하여 요청을 처리한 후 웹 브라우저에게 결과를 전송

+ redirect와의 차이점
  + 리다이렉트는 서로다른 요청이 2번 실행되고 따라서 URL 주소도 바뀌게 된다. (두 요청에서의 request객체와 response객체가 같지않다.)
  + 반면 포워드는 요청이 1번 실행되고 URL주소도 바뀌지 않는다. ( 클라이언트가 한 번 요청했을 때 만들어졌던 요청, 응답 객체가 그대로 유지된다.)



### 실습

~~~java
//FrontServlet.java

package examples;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FrontServlet
 */
@WebServlet("/front")
public class FrontServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FrontServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int diceValue = (int)(Math.random()*6) +1;
		request.setAttribute("dice", diceValue); // 첫번째 인자와 두번째 인자는 키 밸류 같은 느낌, 그리고 두 번째 인자는 오브젝트 형태 아마 오토박싱 된듯
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/next");
    //포워드할 경로는 같은 웹 어플리케이션 안에서만 가능하고 /로 시작한다.
		requestDispatcher.forward(request, response);
	}

}

~~~

~~~java
//NextServlet.java

package examples;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class NextServlet
 */
@WebServlet("/next")
public class NextServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NextServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head><title>form</title></head>");
		out.println("<body>");
		
    //dice의 밸류가 오브젝트 형태기 때문에 Integer로 형변환함. 그리고 int로 오토언박싱된듯.
		int dice = (Integer)request.getAttribute("dice");
		out.println("dice : " + dice);
		for(int i =0; i < dice; i++) {
			out.print("<br>hello");
		}
		out.println("</body>");
		out.println("</html>");
	}

}
~~~

+ 결과

  <img width="980" alt="스크린샷 2022-03-12 13 03 49" src="https://user-images.githubusercontent.com/88477839/158002986-2e4c58c0-bfa1-4916-a52a-9c836dd76cd3.png">

  + URL 주소가 바뀌지 않은 것을 확인할 수 있음



## Servlet & jsp 연동

> 서블릿과 JSP는 서로 상호 보완적인 관계를 가지고 있다.
>
> 서블릿은 로직을 구현하기에는 알맞지만, HTML을 출력하기엔 불편하다.
>
> JSP는 로직을 구현하는 것은 불편하지만 HTML을 출력하기엔 편리하다.
>
> 이러한 서블릿과 JSP를 좀 더 잘 사용하기 위해서 forward가 사용되는 경우가 많다.



### Servlet과 JSP 연동

+ Servlet은 프로그램 로직이 수행되기에 유리하다. IDE등에서 지원을 좀 더 잘해준다.
+ JSP는 결과를 출력하기에 Servlet보다 유리하다. 필요한 html문을 그냥 입력하면 된다.
+ 프로그램 로직 수행은 Servlet에서, 결과 출력은 JSP에서 하는 것이 유리하다.
+ Servlet과 JSP의 장단점을 해결하기 위해서 Servlet에서 프로그램 로직이 수행되고, 그 결과 JSP에게 포워딩하는 방법이 사용되게 되었다. 이를 Servlet과 JSP연동이라고 한다.



### 실습

~~~java
// LogicServlet.java

package examples;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LogicServlet
 */
@WebServlet("/logic")
public class LogicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogicServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int v1 = (int)(Math.random() * 100) + 1;
		int v2 = (int)(Math.random() * 100) + 1;
		int result = v1+v2;
		
		request.setAttribute("v1", v1);
		request.setAttribute("v2", v2);
		request.setAttribute("result", result);
		
		RequestDispatcher rd = request.getRequestDispatcher("/result.jsp"); // WebContent 바로 밑에 있는 파일이면 파일이름만 적어주면 되는데 만약 폴더 안에 위치한 파일이면 파일 이름 앞에 /폴더명 을 적어준다.
		rd.forward(request, response);
	}

}
~~~

~~~jsp
<!-- result.jsp -->

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
	int v1 = (int)request.getAttribute("v1");
	int v2 = (int)request.getAttribute("v2");
	int result = (int)request.getAttribute("result");
%>

<%=v1 %> + <%=v2 %> = <%=result %>
</body>
</html>
~~~



![2_4_3_servlet_jsp](https://user-images.githubusercontent.com/88477839/158006089-c6c0b69b-5b38-4670-8b30-cac1d9e332ef.png)

