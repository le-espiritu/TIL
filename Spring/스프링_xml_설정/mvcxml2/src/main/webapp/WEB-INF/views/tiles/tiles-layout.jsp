<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"  %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		
		<title> <tiles:insertAttribute name="title"/> </title>
		
		<link rel="stylesheet" href="/mvcxml2/css/layoutcommon.css"> <!-- 전체 레이아웃 css -->
		<link rel="stylesheet" href="/mvcxml2<tiles:getAsString name="body-css"/>"> <!-- body 부분 css -->
		
	</head>
	
	<body>
		<div class='wrap'>
			<!-- header 부분 -->
			<tiles:insertAttribute name="header"/>
			<div class='content'>
				<!-- aside 부분 (aside 있다면) -->
				
				<div class="page_content">
					<!-- body 부분 -->
					<tiles:insertAttribute name="body"/>
				</div>
			</div>
			<!-- footer 부분 -->
			<tiles:insertAttribute name="footer"/>
		</div>
		
		<script></script>
		
	</body>

</html>