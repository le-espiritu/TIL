<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>BBS Spring 게시판</title>

<link rel="stylesheet" href="/BBSSpring/css/main.css">

<style>
	#board_content {width:50%; margin: 20px auto; text-align: right;}
	#board_content table{border:1px solid #ddd; text-align:center; width:100%;}
	
	#board_content table input,textarea{width:100%;}
	#board_content table textarea{height:350px;}
	
	#board_content th{background-color:#eee}
	
</style>

</head>

<body>

	<c:if test="${empty sessionScope.userID || sessionScope.userID ne bbsForUpdate.userID}">
		<script>
			alert("권한이 없습니다.");
			history.back();
		</script>
	</c:if>


	<div id="container">
		<header>
			<h1 onclick="javascript:goMain()">BBS Spring 게시판</h1>
			
			<c:if test="${not empty sessionScope.userID}">
				<nav>
					<ul>
						<li><a href="#">${sessionScope.userID}님 사용중</a></li>
						<li><a href="javascript:void()" onclick="javascript:logout()">로그아웃 </a></li>
					</ul>
				</nav>
			</c:if>
			
		</header>
		
		<section id="main_content">
			<nav>
				<ul>
					<li><a href="/BBSSpring/main">메인</a></li>
					<li><a href="/BBSSprint/bbs">게시판</a></li>
				</ul>
			</nav>
			
			<div id="board_content">
				<form method="post" action="/BBSSpring/posts/${bbsForUpdate.bbsID}">
					<input type="hidden" name="_method" value="patch">
				
					<table>
						<thead>
							<tr>
								<th colspan="2"> 게시글 수정 양식 </th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td><input type="text" name="bbsTitle" maxlength="20" value="${bbsForUpdate.bbsTitle}"></td>
							</tr>
							<tr>
								<td><textarea name="bbsContent" maxlength="2048">${bbsForUpdate.bbsContent}</textarea></td>
							</tr>
						</tbody>
					</table>
					<input type="submit" value="수정하기">
				</form>
			</div>
		</section>
		
		<footer> 
			<span>푸터입니다.</span>
		</footer>
		
	</div>
	
	<script src="/BBSSpring/js/main.js"></script>
	<!-- 만약 스크립트가 동작하지 않을때는 캐시된 파일들을 삭제해본다. -->
	
</body>




</html>