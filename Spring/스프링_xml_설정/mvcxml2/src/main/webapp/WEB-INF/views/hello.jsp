<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	할룽할룽 
	<img alt="bag" src="img/bag.jpg"> <!-- 또는 "/mvcxml2/img/bag.jpg" -->
	<!-- src="/img/bag.jpg" 로 하고 싶을때는 Context root를 /로 바꿔줘야 한다. -->
	<!-- Context root를 프로젝트명으로 유지하고 싶을때는
		 src="/mvcxml2/img/bag.jpg" 로 해야 한다. -->
		 
	<br><br>
	
	<form action="/mvcxml2/practice" method="post">
		<h3>시험 성적을 등록하세요</h3>
		이름:
		<input type="text" placeholder="이름" name="name"/>
		점수:
		<input type="number" name = "score"/>
		<input type="submit" value="제출하기">
	</form>
	
	
</body>
</html>