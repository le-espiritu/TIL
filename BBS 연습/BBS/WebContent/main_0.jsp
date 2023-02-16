<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!-- https://lcw126.tistory.com/156 

https://dheldh77.tistory.com/entry/%ED%94%84%EB%A1%A0%ED%8A%B8%EC%97%94%EB%93%9C%EB%B2%A0%EC%9D%B4%EC%A7%81-%EB%A9%94%EC%9D%B8-%ED%8E%98%EC%9D%B4%EC%A7%80-%EB%A7%8C%EB%93%A4%EA%B8%B0
https://studiomeal.com/archives/197

참고하여 html및 css 구성함 -->
    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>Insert title here</title>
		
		<link rel="stylesheet" href="./css/main.css">
	</head>
<body>

	<div id="page">
	
		<header>
			<div id="logo">
				<a href="main.jsp">jsp 게시판 웹 사이트</a>
			</div>
			
			<div id="top_menu">
				<a href="main.jsp">HOME</a>
				<a href="login.jsp">LOGIN</a>
				<a href="join.jsp">JOIN</a>
			</div>
			
			<nav>
				<ul>
					<li><a href="main.jsp">메인</a></li>
					<li><a href="bbs.jsp">게시판</a></li>
				</ul>
			</nav>
		</header>
		
		<article id="content">
			<section id="main">
				<img src="./img/main_img.jpg" alt="main img">
			</section>
			<section>
				<ul id="banner">
					<li><a href="#"><img src="./img/banner2.png" alt="banner1" ></a></li>
					<li><a href="#"><img src="./img/banner2.png" alt="banner2" ></a></li>
				</ul>
			</section>
		</article>
		
		<footer>
			<a href="#">footer@footer</a>
		</footer>
	
	</div>

</body>
</html>