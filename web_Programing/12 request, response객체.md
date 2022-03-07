# Request, Response 객체 이해하기

> HttpServletRequest - 클라이언트가 서버에게 보낸 요청을 추상화한 객체
>
> HttpServletResponse - 서버가 클라이언트에게 응답하기 위한 정보를 추상화한 객체



### 요청과 응답

![1_5_4_request_response](https://user-images.githubusercontent.com/88477839/156956813-66a7c937-61ad-4e0f-8541-66182f79c110.png)

+ WAS는 웹 브라우저로부터 Servlet요청을 받으면,
  + 요청할 때 가지고 있는 정보를 HttpServletRequest객체를 생성하여 저장
  + 웹 브라우저에게 응답을 보낼 때 사용하기 위하여 HttpServletResponse객체를 생성
  + 생성된 HttpServletRequest, HttpServletResponse 객체를 서블릿에게 전달
    + 이렇게 전달한 객체는 service(), doGet(), doPost() 같은 메서드에 파라미터로 전달돼서 사용하게 된다.



### HttpServletRequest

+ http프로토콜의 request정보를 서블릿에게 전달하기 위한 목적으로 사용
+ 헤더정보, 파라미터, 쿠키, URI, URL 등의 정보를 읽어 들이는 메소드를 가지고 있다.
+ Body의 Stream을 읽어 들이는 메소드를 가지고 있다.



### HttpServletResponse

+ WAS는 어떤 클라이언트가 요청을 보냈는지 알고 있고, 해당 클라이언트에게 응답을 보내기 위한 HttpServletResponse객체를 생성하여 서블릿에게 전달
+ 서블릿은 해당 객체를 이용하여 content type, 응답코드, 응답 메시지등을 전송



### 파라미터 읽어 들이기

+ URL주소의 파라미터 정보를 읽어들이기
+ http://localhost:8080/firstweb/param?name=kim&age=5
  + 위 주소에서 ?를 기준으로 뒤에 있는 것들을 파라미터라고한다.
  + =을 기준으로 앞에 있는 것은 파라미터 이 뒤에 있는 것은 파라미터 값이다.

~~~java
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head><title>form</title></head>");
		out.println("<body>");
		
		String name = request.getParameter("name");
		String age = request.getParameter("age");
		
		out.println("name : " + name + "<br>");
		out.println("age : " + age + "<br>");
		
		out.println("</body>");
		out.println("</html>");
	}
~~~

+ 이런 파라미터들은 반드시 URL을 통해서만 넘어오는 것은 아니다.
+ HTML form이라는 태그 안에는 input이라는 태그를 사용할 수 있는데 input 태그 안에 들어있는 값들도 똑같이 이 파라미터로 넘어오기 때문에 똑같은 방법으로 꺼내서 사용하면 된다.(LIfecycleServlet 참조)



### URI와 URL 

+ URI - 도메인과 포트 이하의 있는 값 (예- /firstweb/info )
+ URL - 요청주소 전체 (예 - http://localhost:8080/firstweb/info )

