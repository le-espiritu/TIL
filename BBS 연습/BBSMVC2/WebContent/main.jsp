<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

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
		String userID = null;
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
			
			<section id="main_img">
				<a href="#"><img src="./img/main_img.jpg" alt="main_img"></a>
			</section>
			
			<section id="banners">
				<div>
					<a href="#"><img src="./img/banner2.png" alt="banner"></a>
				</div>
				<div>
					<a href="#"><img src="./img/banner2.png" alt="banner"></a>
				</div>
			</section>
		
		</section>
		
		<footer>
			<span>푸터</span>
		</footer>
	</div>
</body>
</html>