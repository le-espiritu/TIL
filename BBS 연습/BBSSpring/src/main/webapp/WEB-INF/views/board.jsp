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
	#board_content {width:50%; margin: 0 auto; text-align: right;}
	#board_content table{border:1px solid #ddd; text-align:center; width:100%;}
	
	#board_content table input,textarea{width:100%;}
	#board_content table textarea{height:350px;}
	
	#board_content th{background-color:#eee}
	
</style>

</head>

<body>

	<c:if test="${empty sessionScope.userID }">
		<script>
			alert("로그인 해 주십시오");
			location.href="login";
			//history.back();
		</script>
	</c:if>


	<div id="container">
		<header>
			<h1 onclick="javascript:goMain()">BBS Spring 게시판</h1>
			
			<c:if test="${not empty sessionScope.userID}">
				<nav>
					<ul>
						<li><a href="#">${sessionScope.userID}님 사용중</a></li>
						<li><a href="javascript:void(0)" onclick="javascript:logout()">로그아웃 </a></li>
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
			
			<div id="board_content">
				<form method="post" action="board">
					<table>
						<thead>
							<tr>
								<th colspan="2"> 게시판 글 작성 양식 </th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td><input type="text" placeholder="글 제목" name="bbsTitle" maxlength="20";></td>
							</tr>
							<tr>
								<td><textarea placeholder="글 내용" name="bbsContent" maxlength="2048"></textarea></td>
							</tr>
						</tbody>
					</table>
					<input type="submit" value="작성하기">
				</form>
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