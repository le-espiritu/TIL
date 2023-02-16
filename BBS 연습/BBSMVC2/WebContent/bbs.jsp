<%@page import="java.util.Collections"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.ArrayList" %>
<%@ page import="bbs.Bbs" %>
<%@ page import="bbs.BbsDAO" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>bbs게시판 mvc2모델</title>

<link rel="stylesheet" href="./css/main.css">

</head>
<body>
	<%
		String userID=null;
		if(session.getAttribute("userID")!=null){
			userID=(String)session.getAttribute("userID");
		}
		
		int pageNumber = (Integer)request.getAttribute("pageNumber");
	%>

	<div id="container">
		<header>
			<h1>bbs게시판 MVC2모델</h1>
			
			<nav>
				<%
				if(userID==null){
				%>
				<ul>
					<li><a href="login">로그인</a></li>
					<li><a href="join">회원가입</a></li>
				</ul>
				<%
				}else{
				%>
				<ul>
					<li><a href="#"><%=userID%> 님 사용중</a></li>
					<li><a href="logout">로그아웃</a></li>
				</ul>
				<%
				}
				%>
			</nav>
		</header>
		
		<section id="main_content">
			<nav>
				<ul>
					<li><a href="main">메인</a></li>
					<li><a href="bbs">게시판</a></li>
				</ul>
			</nav>
			
			<section id="bbs_content">
				<div>
					<table style="border:1px solid #ddd; text-align:center;">
						<thead>
							<tr>
								<th style="background-color:#eee">글 번호</th>
								<th style="background-color:#eee">제목</th>
								<th style="background-color:#eee">작성자</th>
								<th style="background-color:#eee">날짜</th>
							</tr>
						</thead>
						<tbody>
							<%
							ArrayList<Bbs> bbsList = (ArrayList<Bbs>)request.getAttribute("bbsList");
							for(int i=0; i<bbsList.size(); i++){
							%>
							<tr>
								<td><%=bbsList.get(i).getBbsID()%></td>
								<td><a href="view?bbsID=<%=bbsList.get(i).getBbsID()%>"><%=bbsList.get(i).getBbsTitle().replaceAll(" ","&nbsp;").replaceAll("<","&lt").replaceAll(">","&gt").replaceAll("\n","<br>")%></a></td>
								<td><%=bbsList.get(i).getUserID()%></td>
								<td><%=bbsList.get(i).getBbsDate().substring(0, 11)+bbsList.get(i).getBbsDate().substring(11, 13)+"시"+bbsList.get(i).getBbsDate().substring(14, 16)+"분"%></td>
							</tr>
							<%
							}
							%>
						</tbody>
					</table>
					<%
						if(pageNumber!=1){
					%>
						<a href="bbs?pageNumber=<%=pageNumber-1%>">이전</a>
					<%
						}
						if(new BbsDAO().nextPage(pageNumber+1)){
					%>
						<a href="bbs?pageNumber=<%=pageNumber+1%>">다음</a>
					<%
						}
					%>
					<a href="write">작성하기</a>
				</div>
			</section>
		
		</section>
		
		<footer>
			<span>footer</span>
		</footer>
	
	</div>

</body>
</html>