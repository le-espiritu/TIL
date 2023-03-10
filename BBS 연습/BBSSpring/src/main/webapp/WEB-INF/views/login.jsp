<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width:device-width, initial-scale=1">
<title>BBS Spring 게시판</title>

<link rel="stylesheet" href="css/main.css">

</head>

<body>
	<div id="container">
		<header>
			<h1 onclick="location.href='main'">BBS Spring 게시판</h1>
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
			
			<section id="login_view">
				<form action="login" method="post">
				<h2>로그인 화면</h2>
				<div>
					<input type="text" placeholder="아이디" name="userID" maxlength="20">
				</div>
				<div>
					<input type="password" placeholder="패스워드" name="userPassword" maxlength="20">
				</div>
				
				<input type="submit" value="로그인">
			</form>
			</section>
			
		</section>
		
		<footer>
			<span>푸터입니다.</span>
		</footer>
	
	</div>
</body>

</html>