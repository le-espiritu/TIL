<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.io.PrintWriter" %>
<%@ page import="bbs.BbsDAO" %>
<%@ page import="bbs.Bbs" %>
<%@ page import="java.util.ArrayList" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>JSP 게시판 웹 사이트</title>

<style>
	a,a:hover{
		color:#000000;
		text-decoration: none;
	}
</style>

</head>
<body>
	<%
		String userID=null;
		if(session.getAttribute("userID")!=null){
			userID=(String)session.getAttribute("userID");
		}
		int pageNumber = 1; // 기본 페이지 초기화 
		if(request.getParameter("pageNumber")!=null){
			pageNumber=Integer.parseInt(request.getParameter("pageNumber"));
		}
	%>
	
	<nav>
		<div>
			<button>
				<span></span>
				<span></span>
				<span></span>
			</button>
			<a href='main.jsp'>JSP 게시판 웹 사이트</a>
		</div>
		<div>
			<ul>
				<li><a href='mian.jsp'>메인</a></li>
				<li><a href='bbs.jsp'>게시판</a></li>
			</ul>
			
			<%
				if(userID==null){
			%>
				<ul>
					<li>
						<a href='#'>접속하기</a>
						<ul>
							<li><a href='login.jsp'>로그인</a></li>
							<li><a href='join.jsp'>회원가입</a></li>
						</ul>
					</li>
				</ul>
			<%	
				}else{
			%>
				<ul>
					<li>
						<a href='#'>회원관리</a>
						<ul>
							<li><a href='logoutAction.jsp'>로그아웃</a></li>
						</ul>
					</li>
				</ul>
			<%
				}
			%>
		</div>
	</nav>
	
	<div class="container">
		<div>
			<table style="text-align; border:1px solid #dddddd">
				<thead>
					<tr>
						<th style="background-color:#eeeeee; text-align:center">번호</th>
						<th style="background-color:#eeeeee; text-align:center">제목</th>
						<th style="background-color:#eeeeee; text-align:center">작성자</th>
						<th style="background-color:#eeeeee; text-align:center">날짜</th>
					</tr>
				</thead>
				<tbody>
					<%
						BbsDAO bbsDAO = new BbsDAO();
						ArrayList<Bbs> list = bbsDAO.getList(pageNumber);
						for(int i=0; i<list.size(); i++){
					%>
					<tr>
						<td><%=list.get(i).getBbsID() %></td>
						<td><a href="view.jsp?bbsID=<%=list.get(i).getBbsID()%>"><%=list.get(i).getBbsTitle().replaceAll(" ","&nbsp;").replaceAll("<","&lt;").replaceAll(">","&gt;").replaceAll("\n","<br>") %></a></td>
						<td><%=list.get(i).getUserID() %></td>
						<td><%=list.get(i).getBbsDate().substring(0, 11) + list.get(i).getBbsDate().substring(11,13)+"시"+list.get(i).getBbsDate().substring(14,16)+"분"%></td>
					</tr>
					<%
						}
					%>
				</tbody>
			</table>
			<%
				if(pageNumber!=1){
			%>
				<a href="bbs.jsp?pageNumber=<%=pageNumber-1 %>"> 이전 </a>
			<%
				}
				if(bbsDAO.nextPage(pageNumber+1)){
			%>
				<a href="bbs.jsp?pageNumber=<%=pageNumber+1 %>"> 다음 </a>
			<%
				}
			%>
			<a href='write.jsp'>작성하기</a>
		</div>
	</div>
	
	
</body>
</html>