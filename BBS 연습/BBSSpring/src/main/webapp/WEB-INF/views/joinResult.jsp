<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width:device-width, initial-scale=1">
<title>BBS Spring 게시판</title>

<link rel="stylesheet" href="css/main.css">

</head>

<body>

	<c:if test="${joinResult==-1}">
		<script>
			alert("아이디가 이미 존재합니다.");
			history.back();
		</script>
	</c:if>

</body>

</html>