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
		int joinResult = (Integer)request.getAttribute("joinResult");
	
		if(joinResult==-1){
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('중복된 아이디입니다.')");
			script.println("history.back()");
			script.println("</script>");
		}
		else{
			session.setAttribute("userID", request.getParameter("userID"));
			response.sendRedirect("main");
		}
	%>

</body>
</html>