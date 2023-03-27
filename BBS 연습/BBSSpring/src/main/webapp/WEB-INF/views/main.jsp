<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
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
			<h1 onclick="javascript:goMain()">BBS Spring 게시판</h1>
			<c:if test="${empty sessionScope.userID}">
				<nav>
					<ul>
						<li><a href="user">로그인</a></li>
						<li><a href="member">회원가입</a></li>
					</ul>
				</nav>
			</c:if>
			<c:if test="${not empty sessionScope.userID}">
				<nav>
					<ul>
						<li><a href="#">${sessionScope.userID}님 사용중</a></li>
						<li><a href="javascript:void(0)" onclick="javscript:logout()">로그아웃 </a></li>
					</ul>
				</nav>
			</c:if>
			
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
	
	<script src="js/main.js"></script>
	<!-- 만약 스크립트가 동작하지 않을때는 캐시된 파일들을 삭제해본다. -->

	
</body>




</html>