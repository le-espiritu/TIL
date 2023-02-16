<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.io.PrintWriter" %>

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
			script.println("alert('로그인 해주십시오')");
			script.println("location.href='login'");
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
		
			<section id="write_content">
				<form method="post" action="writeAction">
				
					<table>
						<thead>
							<tr>
								<th colspan="2" style="background-color:#eee; text-align:center">게시판 글 작성 양식</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>
									<input type="text" placeholder="제목" name="bbsTitle" maxlength="20">
								</td>
							</tr>
							<tr>
								<td>
									<textarea placeholder="글 내용" name="bbsContent" maxlength="2048" style="height:350px"></textarea>
								</td>
							</tr>
						</tbody>
					</table>
					<input type="submit" value="작성하기">
				</form>
			</section>
			
		
		</section>
		
		<footer>
			<span>푸터</span>
		</footer>
	</div>
</body>
</html>