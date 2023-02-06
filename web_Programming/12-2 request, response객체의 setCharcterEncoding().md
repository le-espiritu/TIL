# request, response - setCharcterEncoding 관련

### setCharacterEncoding() 사용 예

- request.setCharacterEncoding("UTF-8");
- response.setCharacterEncoding("UTF-8");
- response.setContentType("text/html;charset=utf-8");



+ 참고 자료 - https://powersmile-it.tistory.com/56



### request.setCharacterEncoding("UTF-8"); 사용 예

+ ~~~java
  package user;
  
  import java.io.IOException;
  
  import javax.servlet.RequestDispatcher;
  import javax.servlet.ServletException;
  import javax.servlet.annotation.WebServlet;
  import javax.servlet.http.HttpServlet;
  import javax.servlet.http.HttpServletRequest;
  import javax.servlet.http.HttpServletResponse;
  
  /**
   * Servlet implementation class JoinActionServlet
   */
  @WebServlet("/joinAction")
  public class JoinActionServlet extends HttpServlet {
  	private static final long serialVersionUID = 1L;
         
      /**
       * @see HttpServlet#HttpServlet()
       */
      public JoinActionServlet() {
          super();
          // TODO Auto-generated constructor stub
      }
  
  	/**
  	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
  	 */
  	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  		request.setCharacterEncoding("UTF-8"); // 이걸 안해주면 db에서 한글 깨짐, post방식일때 사용 
  		
  		User user = new User();
  		user.setUserID(request.getParameter("userID"));
  		user.setUserPassword(request.getParameter("userPassword"));
  		user.setUserName(request.getParameter("userName"));
  		user.setUserGender(request.getParameter("userGender"));
  		user.setUserEmail(request.getParameter("userEmail"));
  		
  		UserDAO userDAO = new UserDAO();
  		int result = userDAO.join(user);
  		
  		request.setAttribute("joinResult", result);
  		RequestDispatcher rd = request.getRequestDispatcher("/joinResult.jsp");
  		rd.forward(request, response);
  	}
  
  }
  ~~~

  + request.setCharacterEncoding("UTF-8"); 코드를 작성하지 않으면 db에서 한글 깨진다. (post방식일때 사용)

