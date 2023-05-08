<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>접근 거부</title>
</head>
<body>

	<h1>권한이 없습니다.</h1>
	<h2>접근이 거부되었습니다.</h2>
	
	<h3><a href="/mvcxml2/">홈으로 가기</a></h3>
	<h3><a href="<c:url value="/" />">홈으로 가기 (c:url 태그 사용하여 contextPath 처리)</a></h3>

</body>
</html>