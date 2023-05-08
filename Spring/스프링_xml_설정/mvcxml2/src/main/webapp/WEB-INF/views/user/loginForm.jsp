<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta  charset="UTF-8" />
    <title>로그인 페이지</title>
</head>

<h3>아이디와 비밀번호를 입력해주세요.</h3>

<c:url value="/login" var="loginUrl" />
<form action="${loginUrl}" method="POST"> <!-- ${loginUrl} 또는 /mvcxml2/login으로 해줌 -->
    <c:if test="${param.error != null}">
        <p>아이디와 비밀번호가 잘못되었습니다.</p>
    </c:if>
    <c:if test="${param.logout != null}">
        <p>로그아웃 하였습니다.</p>
    </c:if>
    <p>
        <label>아이디</label>
        <input type="text" name="userId" />
    </p>
    <p>
        <label>비밀번호</label>
        <input type="password" name="password"/>
    </p>
    <button type="submit">로그인</button>
</form>

</body>



</html>

<!-- 

	스프링 시큐리티 4 부터는 CSRF 방지 기능이 디폴트로 작동
	form:form 태그를 사용하면 PATCH, POST, PUT, DELETE 요청에 CSRF 토큰이 자동으로 적용되는 듯 하다.
	
	만약 form:form태그를 사용하고 싶지 않다면
	시큐리티 설정파일에서 <csrf disabled="true"/> 를 해주거나
	
	CSRF 토큰을 사용하면서 form:form 태그를 사용하고 싶지 않다면
	<input type="hidden" name="${_csrf.parameterName}" value="${_crsf.token}"/>
	위 코드를 추가해주면 된다.

 -->

