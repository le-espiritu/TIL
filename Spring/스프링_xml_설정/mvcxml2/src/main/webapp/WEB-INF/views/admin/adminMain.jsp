<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 메인창</title>
</head>
<body>

	<h1>관리자 메인창</h1>
	
	<a href="/mvcxml2/">홈으로 가기</a>
	<br>
	<a href="<c:url value="/"/>">홈으로 가기 (jstl c태그 라이브러리 활용하여 contextPath 처리한 방식)</a>

</body>
</html>