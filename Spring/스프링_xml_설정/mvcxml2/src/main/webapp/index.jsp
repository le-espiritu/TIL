<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	인덱스 메인 입니다.
	
	이미지 테스트
	<img alt="bag" src="img/bag.jpg">
	<!-- src="/img/bag.jpg" 로 하고 싶을때는 Context root를 /로 바꿔줘야 한다. -->
	<!-- Context root를 프로젝트명으로 유지하고 싶을때는
		 src="/mvcxml2/img/bag.jpg" 로 해야 한다. -->
	
	<form:form action="${pageContext.request.contextPath}/logout" method="POST">
		<input type="submit" value="로그아웃"/>
		pageContext.request.contextPath: ${pageContext.request.contextPath}
	</form:form>
	
	
</body>
</html>