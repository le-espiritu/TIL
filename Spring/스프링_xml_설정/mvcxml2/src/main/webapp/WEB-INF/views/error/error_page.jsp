<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>500 에러 페이지</h1>
	<h4>${exception.message }</h4>
	<ul>
		<c:forEach items="${exception.stackTrace }" var="stack">
			<li>${stack }</li>
		</c:forEach>
	</ul>

</body>
</html>