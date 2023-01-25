<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>jsp 게시판 웹 사이트</title>
</head>
<body>

	<nav>
		<div>
			<button type="button">
				<span></span>
				<span></span>
				<span></span>
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
		<form method="post" action="joinAction.jsp">
			<h3>회원가입 화면</h3>
			<div>
				<input type="text" placeholder="아이디" name="userID" maxlength="20"/>
			</div>
			<div>
				<input type="password" placeholder="비밀번호" name="userPassword" maxlength="20"/>
			</div>
			<div>
				<input type="text" placeholder="이름" name="userName" maxlength="20"/> 
			</div>
			<div>
				<div>
					<label>
						<input type="radio" name="userGender" autocomplete="off" value="남자" checked> 남자
					</label>
					<label>
						<input type="radio" name="userGender" autocomplete="off" value="여자" checked>여자
					</label>
					
				</div>
			
			</div>
			<div>
				<input type="email" placeholder="이메일" name="userEmail" maxlength="50"/>
			</div>
			<input type="submit" value="회원가입하기" />
		
		</form>
	</div>

</body>
</html>