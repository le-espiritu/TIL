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

	<c:if test="${writeResult==-1 }">
		<script>
			alert("글쓰기에 실패하였습니다..");
			history.back();
		</script>
	</c:if>
	
	<script>
		alert("입력하지 않은 항목이 있습니다.");
		history.back();
	</script>
	

</body>

</html>