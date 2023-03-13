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

<style>
	#bbs_content >table{border:1px solid #ddd; text-align:center;}
	#bbs_content th{background-color:#eee}
</style>

</head>

<body>
	<div id="container">
		<header>
			<h1 onclick="javascript:goMain()">BBS Spring 게시판</h1>
			<c:if test="${empty sessionScope.userID}">
				<nav>
					<ul>
						<li><a href="login">로그인</a></li>
						<li><a href="join">회원가입</a></li>
					</ul>
				</nav>
			</c:if>
			<c:if test="${not empty sessionScope.userID}">
				<nav>
					<ul>
						<li><a href="#">${sessionScope.userID}님 사용중</a></li>
						<li><a href="logout">로그아웃 </a></li>
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
			
			<div id="bbs_content">
				<table>
					<thead>
						<th>글 번호</th>
						<th>제목</th>
						<th>작성자</th>
						<th>날짜</th>
					</thead>
					<tbody>
						<c:forEach	items="${list}" var="bbsPost">
							<tr>
								<td>${bbsPost.bbsID}</td>
								<td>${bbsPost.bbsTitle}</td>
								<td>${bbsPost.userID}</td>
								<td>${bbsPost.bbsDate}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</section>
		
		<footer> 
			<span>푸터입니다.</span>
		</footer>
		
	</div>
	
	<script src="js/main.js"></script>
	<!-- 만약 스크립트가 동작하지 않을때는 캐시된 파일들을 삭제해본다. -->

	
</body>




</html>