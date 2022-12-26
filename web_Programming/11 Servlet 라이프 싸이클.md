# Servlet 라이프 싸이클

> 라이프 사이클이란 어떤 객체의 생성부터 소멸까지의 과정을 말한다.



#### HttpServelt의 3가지 메소드를 오버라이딩

+ Init()
+ service(request, response)
+ Destroy()

<img width="599" alt="스크린샷 2022-03-05 11 52 14" src="https://user-images.githubusercontent.com/88477839/156865336-c7e6a809-2d22-4f5b-b59c-fb439ff38c9f.png">



### Servlet 생명 주기

![1_5_3_ServletLifcycle](https://user-images.githubusercontent.com/88477839/156865480-d82468a6-e242-4a20-bda6-d6a7d68f06f2.png)

+ WAS는 서블릿의 요청을 받으면 해당 서블릿이 메모리에 있는지 없는지를 확인함
+ 만약 메모리에 없으면
  + 해당 서블릿 클래스를 메모리에 올리는 작업을 한다. (서블릿 클래스 생성자 메서드 실행)
  + Init() 메소드를 실행
+ service()메소드 실행
  + 두번째 요청이 들어왔을 때는 생성자나 init() 메서드는 호출이 되지 않지만 service() 메서드는 호출되어 실행된다.
  + 때문에 요청이들어왔을때 응답해야 하는 모든 내용은 service() 메서드에 구현해야한다.
+ WAS가 종료되거나, 웹 어플리케이션이 새롭게 갱신(서블릿 수정 등)될 경우 destroy() 메소드가 실행된다.
  + 서블릿 수정등이 되면 그 이전의 서블릿을 사용할 수 없기 때문에 destroy()메소드가 호출됨
  + Destroy()메소드가 실행되고 다시 요청을 보내면 메모리에 서블릿이 없는 상태이기 때문에 메모리에 서블릿 클래스를 올리는 작업과 init()메소드가 실행된다.



### service(request, response)메소드

WAS는 init() 메서드를 호출하고 service()라는 메서드를 호출하는 예만 수행하는데, 이전에 만들었던 HelloServlet 이라는 클래스에는  service()메서드를 정의 하지 않았고 doGet()메서드만 정의해서 호출했었다. 어떻게 실행된 것일까?

+ service()메서드는 실제 HttpServlet의 메서드로 이미 구현이 되어 있는 상태다.

+ 내가 만든 서블릿에 service()메서드를 오버라이드 하지 않았어도 상속때문에 내가 만든 서블릿의 부모인 HttpServlet의 service()메서드가 실행된다.

+ HttpServlet의 service메소드는 템플릿 메소드 패턴으로 구현 되어 있다.

  + 클라이언트의 요청이 GET일 경우에는 자신이 가지고 있는 doGet(request, response)메소드를 호출
  + 클라이언트의 요청이 Post일 경우에는 자신이 가지고 있는 doPost(request, response)를 호출

  ~~~java
  @Override
  	protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
  		response.setContentType("text/html");
  		PrintWriter out = response.getWriter();
  		out.println("<html>");
  		out.println("<head><title>form</title></head>");
  		out.println("<body>");
  		out.println("<form method='post' action='/firstweb/LifecycleServlet'>");
  		out.println("name : <input type='text' name='paramname'><br>");
  		out.println("<input type = 'submit' value='ok'><br>");
  		out.println("</form>");
  		out.println("</body>");
  		out.println("</html>");
  		out.close();
  	}
  
  	@Override
  	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  		response.setContentType("text/html");
  		PrintWriter out = response.getWriter();
  		String name = request.getParameter("paramname");
  		out.println("<h1> hello " + name + "</h1>");
  		out.close();
  	}
  ~~~

  
  
  ~~~html
  <html>
    <head>
      <title>form</title>
    </head>
    <body>
      <form method = "post" action = "/firstweb/LifecycleServlet">
        name : <input type="text" name = "paramname"><br>
        <input type = "submit" value="ok"><br>
      </form>
    </body>
  </html>
  
  <!-- 
  submit이라는 버튼이 눌렸을때 action 뒤에 있는 /firstweb/LifecycleServlet 이 주소로 요청하고 메소드는 post로 하라는 의미
  -->
  ~~~
  
  + 이렇게 같은 URL주소이지만 GET방식이냐 POST 방식이냐에 따라서 다른 메서드가 호출된다.

