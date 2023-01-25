<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.io.PrintWriter" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>JSP 게시판 웹 사이트</title>
</head>
<body>

	<%
		String userID=null;
		if(session.getAttribute("userID")!=null){
			userID=(String)session.getAttribute("userID");
		}
		
	%>

	<nav>
		<div>
			<button type="button">
				<span></span>
				<span></span>
				<span></span>
			</button>
			<a href="main.jsp">JSP 게시판 웹 사이트</a>
		</div>
		<div>
			<ul>
				<li><a href="main.jsp">메인</a></li>
				<li><a href="bbs.jsp">게시판</a></li>
			</ul>
			
			<%
				if(userID == null){
			%>
				<ul>
					<li>
						<a href="#">접속하기</a>
						<ul>
							<li><a href="login.jsp">로그인</a></li>
							<li><a href="join.jsp">회원가입</a></li>
						</ul>
					</li>
				</ul>
			
			<%		
				}else{
			%>
			
				<ul>
					<li>
						<a href="#">회원관리</a>
						<ul>
							<li><a href="logoutAction.jsp">로그아웃 </a></li>
						</ul>
					</li>
				</ul>
			
			<%
				}
			%>
			
		</div>
	</nav>


</body>
</html>