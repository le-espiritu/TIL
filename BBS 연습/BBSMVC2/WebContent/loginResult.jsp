<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.io.PrintWriter" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		int loginResult=(Integer)request.getAttribute("loginResult");
	
		if(loginResult==1){
			PrintWriter script = response.getWriter();
			session.setAttribute("userID", request.getParameter("userID"));
			//String userID = request.getParameter("userID");
			//System.out.println(userID+"님 환영 합니다.");
			response.sendRedirect("main");
		}
		
		if(loginResult==0){
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('비밀번호가 일치하지 않습니다.')");
			script.println("history.back()");
			script.println("</script>");
		}
		
		if(loginResult==-1){
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('아이디가 존재하지 않습니다.')");
			script.println("history.back()");
			script.println("</script>");
		}
		
		if(loginResult==-2){
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('데이터 베이스 오류입니다.')");
			script.println("history.back()");
			script.println("</script>");
		}
	%>

</body>
</html>