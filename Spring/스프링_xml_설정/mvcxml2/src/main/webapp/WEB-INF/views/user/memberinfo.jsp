<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta  charset="UTF-8" />
    <title>회원정보 페이지</title>
</head>

<body>
	<h3>회원정보 페이지</h3>

	<div>
       <label>id</label>
       <p>${user.id}</p>
     </div>
     <div>
       <label>이름</label>
       <p>${user.name}</p>
     </div>
     <div>
       <label>암호</label>
       <p>${user.password}</p>
     </div>
     <div>
       <label>등록일</label>
       <p>${user.createDate}</p>
     </div>
     <div>
       <label>수정일</label>
       <p>${user.modifyDate}</p>
     </div>


</body>

</html>



