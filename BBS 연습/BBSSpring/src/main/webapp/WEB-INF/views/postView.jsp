<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>BBS Spring 게시판</title>

<!-- 컨트롤러의 @PathVariable과 관련하여 css,js 경로와 충돌이 일어나기 때문에 -->
<!-- href="css/main.css"를 href="/BBSSpring/css/main.css로 바꿔줌 " -->
<link rel="stylesheet" href="/BBSSpring/css/main.css">

<style>
	#postView_content > div {width:70%; margin: 20px auto;}
	#postView_content > div > table{border:solid 1px #eeeeee; text-align: center; width: 100%;}
	#postView_content > div > table > thead > tr > th{background-color: #dddddd;}
	#postView_content > div > table > tbody :last-child :last-child{min-height: 200px; text-align: left;}
</style>

</head>

<body>

	<c:if test="${empty bbsForPostView }">
		<script>
			alert("유효하지 않은 글입니다.");
			history.back();
		</script>
	</c:if>

	<div id="container">
		<header>
			<h1 onclick="javascript:goMain()">BBS Spring 게시판</h1>
			
			<c:if test="${empty sessionScope.userID}">
				<nav>
					<ul>
						<li><a href="/BBSSpring/login">로그인</a></li>
						<li><a href="/BBSSpring/join">회원가입</a></li>
					</ul>
				</nav>
			</c:if>
			
			<c:if test="${not empty sessionScope.userID}">
				<nav>
					<ul>
						<li><a href="#">${sessionScope.userID}님 사용중</a></li>
						<li><a href="/BBSSpring/logout">로그아웃 </a></li>
					</ul>
				</nav>
			</c:if>
			
		</header>
		
		<section id="main_content">
			<nav>
				<ul>
					<li><a href="/BBSSpring/main">메인</a></li>
					<li><a href="/BBSSpring/bbs">게시판</a></li>
				</ul>
			</nav>
			
			<div id="postView_content">
				<div>
					<table>
						<thead>
							<tr>
								<th colspan="3"> 게시글 </th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>작성자</td>
								<td colspan="2">${bbsForPostView.userID}</td>
							</tr>
							<tr>
								<td>제목</td>
								<td colspan="2">${bbsForPostView.bbsTitle}</td>
							</tr>
							<tr>
								<td>작성일</td>
								<td colspan="2">${bbsForPostView.bbsDate}</td>
							</tr>
							<tr>
								<td>내용</td>
								<td colspan="2">${bbsForPostView.bbsContent}</td>
							</tr>
						</tbody>
					</table>
					
					<c:if test="${not empty sessionScope.userID && sessionScope.userID eq bbsForPostView.userID}">
						<a href="/BBSSpring/board/${bbsForPostView.bbsID}">수정</a>
						<a href="javascript:void(0)" onclick="javascript:deletePost(${bbsForPostView.bbsID})">삭제</a>
					</c:if>
					
				</div>
			</div>
		</section>
		
		<footer> 
			<span>푸터입니다.</span>
		</footer>
		
	</div>
	
	<!-- 컨트롤러의 @PathVariable과 관련하여 css,js 경로와 충돌이 일어나기 때문에 -->
	<!-- src="js/main.css"를 src="/BBSSpring/js/main.js로 바꿔줌 " -->
	<script src="/BBSSpring/js/main.js"></script>
	<!-- 만약 스크립트가 동작하지 않을때는 캐시된 파일들을 삭제해본다. -->
	<script src="/BBSSpring/js/postView.js"></script>
	
</body>




</html>