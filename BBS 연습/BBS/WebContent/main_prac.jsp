<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- 
https://dheldh77.tistory.com/entry/%ED%94%84%EB%A1%A0%ED%8A%B8%EC%97%94%EB%93%9C%EB%B2%A0%EC%9D%B4%EC%A7%81-%EB%A9%94%EC%9D%B8-%ED%8E%98%EC%9D%B4%EC%A7%80-%EB%A7%8C%EB%93%A4%EA%B8%B0

https://unikys.tistory.com/333

http://www.gmsgondr.net/lecture/2/28

참고함 
 -->

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>메인 페이지 연습</title>

<link rel="stylesheet" href="./css/main_prac.css">

</head>
<body>
	<div class="container">
		<header>
			<h1>JSP게시판 웹 사이트</h1>
			<nav>
				<ul>
					<li><a href="login.jsp">로그인</a></li>
					<li><a href="join.jsp">회원가입</a></li>
				</ul>
			</nav>
		</header>
		
		<section id="main">
			<nav>
				<ul>
					<li><a href="main.jsp">메인</a></li>
					<li><a href="bbs.jsp">게시판</a></li>
				</ul>
			</nav>
		
			<section id="images">
				<div id="main_img">
					<img src="./img/main_img.jpg" alt="메인 이미지">
				</div>
			
				<div id="banners">
					<div>
						<img src="./img/banner2.png" alt="banner1">
					</div>
					<div>
						<img src="./img/banner2.png" alt="banner2">
					</div>
				</div>
			</section>
		</section>
		
		<footer><span>footer영역</span></footer>
	</div>

</body>
</html>