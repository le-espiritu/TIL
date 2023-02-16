package user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginActionServlet
 */
@WebServlet("/loginAction")
public class LoginActionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginActionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userID = request.getParameter("userID");
		String userPassword = request.getParameter("userPassword");
		
		UserDAO userDAO = new UserDAO();
		int result = userDAO.login(userID, userPassword);
		
		request.setAttribute("loginResult", result);
		
		RequestDispatcher rd = request.getRequestDispatcher("/loginResult.jsp");
		rd.forward(request, response);
	}

}
