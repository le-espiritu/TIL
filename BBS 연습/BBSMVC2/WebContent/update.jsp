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
		
		if(userID==null){
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('권한이 없습니다.')");
			script.println("location.href='bbs'");
			script.println("</script>");
		}
	%>

	<div id="container">
		<header>
			<h1>bbs게시판 mvc2모델</h1>
			<nav>
			
				<ul>
					<li><a href="#"><%=userID%> 님 사용중</a></li>
					<li><a href="logout">로그아웃</a></li>
				</ul>
				
			</nav>
		</header>
		
		<section id="main_content">
			<nav>
				<ul>
					<li><a href="main">메인</a></li>
					<li><a href="bbs">게시판</a></li>
				</ul>
			</nav>
		
			<section id="update_content">
				<div style="width:70%; margin:0 auto;">
					<form method="post" action="updateAction">
						<table style="border:1px solid #ddd; text-align:center; width:100%;">
							<thead>
								<tr>
									<th colspan="2" style="background-color:#eee; text-align:center">게시글 수정 양식</th>
								</tr>
							</thead>
							
							<%
								Bbs bbs = (Bbs)request.getAttribute("bbs");
							%>
							
							<input type="hidden" name="bbsID" value="<%=bbs.getBbsID()%>">
							
							<tbody>
								<tr>
									<td>
										<input type="text" placeholder="제목" name="bbsTitle" maxlength="20" value="<%=bbs.getBbsTitle()%>">
									</td>
								</tr>
								<tr>
									<td>
										<textarea name="bbsContent" placeholder="본문" maxlength="2048"><%=bbs.getBbsContent()%></textarea>
									</td>
								</tr>
							</tbody>
						</table>
						<input type="submit" value="수정하기">
					</form>
				</div>
			</section>
			
		
		</section>
		
		<footer>
			<span>푸터</span>
		</footer>
	</div>
</body>
</html>