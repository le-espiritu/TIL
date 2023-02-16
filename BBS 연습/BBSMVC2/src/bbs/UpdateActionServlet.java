package bbs;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UpdateActionServlet
 */
@WebServlet("/updateAction")
public class UpdateActionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateActionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		int bbsID = Integer.parseInt(request.getParameter("bbsID"));
		String bbsTitle = request.getParameter("bbsTitle");
		String bbsContent = request.getParameter("bbsContent");
		
		BbsDAO bbsDAO = new BbsDAO();
		int result = bbsDAO.update(bbsID, bbsTitle, bbsContent);
		
		if(result==-1) {
			PrintWriter script = response.getWriter();
			script.println("<html>");
			script.println("<head><title>bbs게시판 mvc2모델</title></head>");
			script.println("<body>");
			script.println("<script>");
			script.println("alert('데이터베이스 오류입니다.')");
			script.println("history.back()");
			script.println("</script>");
			script.println("</body>");
			script.println("</html>");
		}
		else {
			response.sendRedirect("bbs");
		}
	}

}
