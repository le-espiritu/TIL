<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	인덱스 메인 입니다.<br><br>
	
	이미지 테스트<br>
	<img alt="bag" src="img/bag.jpg">
	<!-- src="/img/bag.jpg" 로 하고 싶을때는 Context root를 /로 바꿔줘야 한다. -->
	<!-- Context root를 프로젝트명으로 유지하고 싶을때는
		 src="/mvcxml2/img/bag.jpg" 로 해야 한다. -->
	
	<br>

	
	<br>
	
	<sec:authorize access="isAnonymous()"> <!-- 비로그인 상태를 확인 -->
		<p>
			<!-- <a href="/mvcxml2/login/loginForm">로그인</a> -->
			<a href="/mvcxml2/users/loginForm">로그인</a>
		</p>
	</sec:authorize>
	
	<sec:authorize access="isAuthenticated()"> <!-- 로그인 상태를 확인 -->
		<form:form action="${pageContext.request.contextPath}/logout" method="POST">
			<input type="submit" value="로그아웃"/>
			<br>pageContext.request.contextPath: ${pageContext.request.contextPath}
		</form:form>
	</sec:authorize>
	
	<h3>
		<a href="/mvcxml2/admin/adminMain">관리자 홈</a>
	</h3>
	
	<sec:authorize access="isAuthenticated()"> <!-- 로그인 상태를 확인 -->
		<a href="/mvcxml2/users/memberinfo">마이페이지</a>
	</sec:authorize>
	
	
</body>
</html>