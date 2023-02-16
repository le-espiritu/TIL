package bbs;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class WriteActionServlet
 */
@WebServlet("/writeAction")
public class WriteActionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WriteActionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		String userID = (String)request.getSession().getAttribute("userID");
		String bbsTitle = request.getParameter("bbsTitle");
		String bbsContent = request.getParameter("bbsContent");
		
		
		if(bbsTitle==null || bbsContent==null || bbsTitle.equals("") || bbsContent.equals("")) {
			//System.out.println("입력오류 ");
			PrintWriter script = response.getWriter();
			script.println("<html>");
			script.println("<head><title>bbs게시판 mvc2모델</title></head>");
			script.println("<body>");
			script.println("<script>");
			script.println("alert('입력하지 않은 항목이 있습니다.')");
			script.println("history.back()");
			script.println("</script>");
			script.println("</body>");
			script.println("</html>");
		}
		
		else {
			BbsDAO bbsDAO = new BbsDAO();
			int result = bbsDAO.write(userID, bbsTitle, bbsContent);
			
			if(result==-1) {
				PrintWriter script = response.getWriter();
				script.println("<html>");
				script.println("<head><title>bbs게시판 mvc2모델</title></head>");
				script.println("<body>");
				script.println("<script>");
				script.println("alert('글쓰기에 실패하였습니다.')");
				script.println("history.back()");
				script.println("</script>");
				script.println("</body>");
				script.println("</html>");
			}
			
			response.sendRedirect("bbs");
		}
		
	}

}
