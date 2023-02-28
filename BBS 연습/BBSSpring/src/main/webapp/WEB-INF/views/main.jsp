<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>BBS Spring 게시판</title>

<link rel="stylesheet" href="css/main.css">

</head>

<body>
	<div id="container">
		<header>
			<h1>BBS Spring 게시판</h1>
			<nav>
				<ul>
					<li><a href="login">로그인</a></li>
					<li><a href="join">회원가입</a></li>
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
			<section id="main_img">
				<a href="#"><img src="img/main_img.jpg" alt="main_img"></a>
			</section>
			
			<section id="banners">
				<div>
					<a href="#"><img src="img/banner2.png" alt="banner2"></a>
				</div>
				<div>
					<a href="#"><img src="img/banner2.png" alt="banner2"></a>
					<!-- img src="WEB-INF/img/banner2.png"는 안됨 -->
					<!-- WEB-INF는 접근이 불가능하기때문인듯 -->
					<!-- 아니면 WebMvcContextConfiguration 때문인듯  -->
				</div>
			</section>
		</section>
		
		<footer> 
			<span>푸터입니다.</span>
		</footer>
		
	</div>
</body>
</html>