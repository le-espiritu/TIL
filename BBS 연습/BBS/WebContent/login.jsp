<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>로그인 </title>
</head>
<body>
	<nav>
		<div>
			<button type="button">
				<span>하 </span>
				<span>하 </span>
				<span>하 </span>
			</button>
			<a href="main.jsp">JSP 게시판 웹 사이트</a>
		</div>
		<div>
			<ul>
				<li><a href="main.jsp">메인</a></li>
				<li><a href="bbs.jsp">게시판</a></li>
			</ul>
			<ul>
				<li>
					<a href="#">접속하기</a>
					<ul>
						<li><a href="login.jsp">로그인</a></li>
						<li><a href="join.jsp">회원가입</a></li>
					</ul>
				</li>
			</ul>
		</div>
	</nav>



	<div class="container">
		<form method="post" action="loginAction.jsp">
			<h3>로그인 화면 </h3>
			<div class="form-group">
				<input type="text" placeholder="아이디" name="userID" maxlength="20">
			</div>
			<div class="form-group">
				<input type="password" placeholder="비밀번호" name="userPassword" mexlength="20">
			</div>
			<input type="submit" value="로그인">
		</form>
	</div>
</body>
</html>