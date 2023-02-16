<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Insert title here</title>

<link rel="stylesheet" href="./css/main_prac2.css">

</head>
<body>
	<div id="container">
		<header>
			<h1>JSP 게시판 웹 사이트</h1>
			<nav>
				<ul>
					<li><a href="login.jsp">로그인</a></li>
					<li><a href="join.jsp">회원가입</a></li>
				</ul>
			</nav>
		</header>
		
		<section id="content">
			<nav>
				<ul>
					<li><a href="main.jsp">메인</a></li>
					<li><a href="bbs.jsp">게시판</a></li>
				</ul>
			</nav>
			
			<section id="main_img">
				<div>
					<a href="#"><img src="./img/main_img.jpg" alt="메인이미지"></a>
				</div>
			</section>
			
			<section id="banners">
				<div>
					<a href="#"><img src="./img/banner2.png"></a>
				</div>
				<div>
					<a href="#"><img src="./img/banner2.png"></a>
				</div>
			</section>
		
		</section>
		
		<footer>
			<span>footer입니다.</span>
		</footer>
	</div>
</body>
</html>