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
	<div id="container">
		<header>
			<h1>bbs게시판 mvc2모델</h1>
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
		
			<section id="login_content">
				<form method="post" action="loginAction">
					<h2>로그인 화면</h2>
					<div>
						<input type="text" placeholder="아이디" name="userID" maxlength="20">
					</div>
					<div>
						<input type="password" placeholder="비밀번호" name="userPassword" maxlength="20">
					</div>
					<input type="submit" value="로그인">
				</form>
			</section>
			
		
		</section>
		
		<footer>
			<span>푸터</span>
		</footer>
	</div>
</body>
</html>