<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.io.PrintWriter" %>
<%@ page import="bbs.Bbs" %>

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
		String userID =null;
		if(session.getAttribute("userID")!=null){
			userID=(String)session.getAttribute("userID");
		}
		
	%>

	<div id="container">
		<header>
			<h1>bbs게시판 mvc2모델</h1>
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
		
			<section id="view_content">
				<div style="width:70%; margin:0 auto;">
					<table style="border:1px solid #ddd; text-align:center; width:100%;">
						<thead>
							<tr>
								<th colspan="3" style="background-color:#eee; text-align:center">게시글 보기</th>
							</tr>
						</thead>
						
						<%
							Bbs bbs = (Bbs)request.getAttribute("bbs");
						%>
						
						<tbody>
							
							<tr>
								<td>작성자</td>
								<td colspan="2"><%=bbs.getUserID()%></td>
							</tr>
							<tr>
								<td>제목</td>
								<td colspan="2"><%=bbs.getBbsTitle().replaceAll(" ", "&nbsp;").replaceAll("<","&lt;").replaceAll(">","&gt;").replaceAll("\n","<br>")%></td>
							</tr>
							<tr>
								<td>내용</td>
								<td colspan="2" style="height:350px; min-height:200px; text-align:left;"><%=bbs.getBbsContent().replaceAll(" ", "&nbsp;").replaceAll("<","&lt;").replaceAll(">","&gt;").replaceAll("\n","<br>")%></td>
							</tr>
							<tr>
								<td>날짜</td>
								<td colspan="2"><%=bbs.getBbsDate().substring(0, 11)+bbs.getBbsDate().substring(11,13)+"시"+bbs.getBbsDate().substring(14,16)+"분"%></td>
							</tr>
							
						</tbody>
					</table>
					
					<a href="bbs">목록</a>
					<%
						if(userID.equals(bbs.getUserID())){
					%>
					<a href="update?bbsID=<%=bbs.getBbsID()%>">수정하기</a>
					<a onclick="return confirm('정말로 삭제하시겠습니까?')" href="delete?bbsID=<%=bbs.getBbsID()%>">삭제하기</a>
					<%
						}
					%>
				</div>
			</section>
			
		
		</section>
		
		<footer>
			<span>푸터</span>
		</footer>
	</div>
</body>
</html>