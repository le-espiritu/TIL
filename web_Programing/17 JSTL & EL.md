# JSTL & EL



## EL(Expression Language)

### 표현 언어란

표현언어(Expression Language)는 값을 표현하는 데 사용되는 스크립트 언어로서 JSP의 기본 문법을 보완하는 역할을 한다.



#### [표현 언어가 제공하는 기능]

+ JSP의 스코프(scope)에 맞는 속성 사용
+ 집합 객체에 대한 접근 방법 제공
+ 수치 연산, 관계 연산, 논리 연산자 제공
+ 자바 클래스 메소드 호출 기능 제공
+ 표현언어만의 기본 객체 제공



### 표현 언어의 표현 방법

![2_6_1__](https://user-images.githubusercontent.com/88477839/158014748-781ff668-8a46-4260-9d6f-42e35c2dcca8.png)



### 표현 언어의 기본 객체

![2_6_1__ (1)](https://user-images.githubusercontent.com/88477839/158014871-ad936e9c-b02b-4aa0-960b-82cf6f195636.png)



### 표현 언어의 기본 객체 사용 예

![2_6_1____](https://user-images.githubusercontent.com/88477839/158014934-5aac6c8d-1444-40dd-8127-bff01d03d4ce.png)



### 표현 언어의 데이터 타입

+ 불리언 타입 - true와 false

+ 정수타입 - 0~9로 이루어진 정수 값 음수의 경우 '-'가 붙음

+ 실수타입 - 0~9로 이루어져 있으며, 소수점('.')을 사용할 수 있고,

  3.24e3과 같이 지수형으로 표현 가능하다.

+ 문자열 타입 - 따옴표( ' 또는 " ) 로 둘러싼 문자열. 만약 작은 따옴표(')를 사용해서 표현할 경우 값에 포함된 작은 따옴표는 \\'와 같이 \\ 기호와 함께 사용해야 한다. \\ 기호 자체는 \\\로 표시한다.

+ 널 타입 - null



### 객체 접근 규칙

![2_6_1_](https://user-images.githubusercontent.com/88477839/158015119-a58e0e42-3575-499b-8c8d-651234c2104f.png)

- 표현 1이나 표현 2가 null이면 null을 반환한다.
- 표현1이 Map일 경우 표현2를 key로한 값을 반환한다.
- 표현1이 List나 배열이면 표현2가 정수일 경우 해당 정수 번째 index에 해당하는 값을 반환한다.
- 만약 정수가 아닐 경우에는 오류가 발생한다.
- 표현1이 객체일 경우는 표현2에 해당하는 getter메소드에 해당하는 메소드를 호출한 결과를 반환한다.



### 표현 언어의 수치 연산자

- \+ : 덧셈
- \- : 뺄셈
- \* : 곱셈
- / 또는 div : 나눗셈
- % 또는 mod : 나머지
- 숫자가 아닌 객체와 수치 연산자를 사용할 경우 객체를 숫자 값으로 변환 후 연산자를 수행 : ${"10"+1} → ${10+1}
- 숫자로 변환할 수 없는 객체와 수치 연산자를 함께 사용하면 에러를 발생 : ${"열"+1} → 에러
- 수치 연산자에서 사용되는 객체가 null이면 0으로 처리 : ${null + 1} → ${0+1}



### 비교 연산자

- == 또는 eq
- != 또는 ne
- < 또는 lt
- \> 또는 gt
- <= 또는 le
- \>= 또는 ge
- 문자열 비교: ${str == '값'} str.compareTo("값") == 0 과 동일



### 논리연산자

- && 또는 and
- || 또는 or
- ! 또는 not



### empty 연산자, 비교선택 연산자

![2_6_1_empty_,__](https://user-images.githubusercontent.com/88477839/158015273-42eaa3ee-706f-4793-980a-7df56bbdaf73.png)



### 연산자 우선순위

1. [] .
2. ()
3. \- (단일) not ! empty
4. \* / div % mod
5. \+ -
6. < > <= >= lt gt le ge
7. == != eq ne
8. && and
9. || or
10. ? :



### 표현 언어 비활성화 : JSP에 명시하기

+ 문법

  ~~~
  <%@ page isELIgnored = "true" %>
  ~~~

![2_6_1___](https://user-images.githubusercontent.com/88477839/158015378-464f5c46-87c7-4c7d-9503-26d1a1c4d982.png)



### EL 실습

~~~jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
	pageContext.setAttribute("p1", "page scope value");
	request.setAttribute("r1", "request scope value");
	session.setAttribute("s1", "session scope value");
	application.setAttribute("a1","application scope value");
%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
 pageContext.getAttribute("p1") : <%=pageContext.getAttribute("p1") %><br>
 pageContext.getAttribute("p1") el 방식 : ${pageScope.p1} <br>
 request.getAttribute("r1") : ${requestScope.r1 } <br>
 session.getAttribute("s1") : ${sessionScope.s1 } <br>
 application.getAttribute("a1") : ${applicationScope.a1 }<br>
 
 pageContext.getAttribute("p1") el 방식 : ${p1} <br>
 request.getAttribute("r1") : ${r1 } <br>
 session.getAttribute("s1") : ${s1 } <br>
 application.getAttribute("a1") : ${a1 }<br>
 <!-- 위 방식은 키값들이 서로 겹치치 않을때 사용 가능함 -->
  
</body>
</html>
~~~

+ 사칙연산자, 비교연산자 EL 실습

  ~~~jsp
  <%@ page language="java" contentType="text/html; charset=UTF-8"
      pageEncoding="UTF-8"%>
  
  <%@ page isElIgnored = "true" %>
  <!--위에는 EL표기법을 무시하고 싶을때 사용 --> 
  
  <% 
  request.setAttribute("k", 10); 
  request.setAttribute("m", true); 
  %>
  <!DOCTYPE html>
  <html>
  <head>
  <meta charset="UTF-8">
  <title>Insert title here</title>
  </head>
  <body>
  k : ${k } <br>
  k + 5 : ${k+5 } <br>
  k - 5 : ${k-5 } <br>
  k * 5 : ${k*5 } <br>
  k / 5 : ${k div 5 } <br>
  
  k : ${k } <br>
  m : ${m } <br>
  
  k > 5 : ${k > 5 } <br>
  k < 5 : ${k < 5 } <br>
  k <= 10 : ${k <= 10 } <br>
  k >= 10 : ${k >= 10 } <br>
  m : ${m } <br>
  !m : ${!m } <br>
  
  </body>
  </html>
  ~~~

  

## JSTL(JSP Standard Tag Library)

>프론트 개발자가 JSP를 수정하는데, JSP안에 자바코드와 HTML코드가 섞여 있다면 수정할 때 굉장히 어려움을 느끼게 될 가능성이 크다.
>
>이런 문제를 해결하기 위해서 등장한 것이 JSTL이다.



### JSTL이란?

+ JSTL(JSP Standard Tag Library)은 JSP페이지에서 조건문 처리, 반복문 처리 등을 html tag형태로 작성할 수 있게 도와준다.

![2_6_2_jstl](https://user-images.githubusercontent.com/88477839/158021960-6b0893a0-87be-4ac9-806e-60e9b68f3b95.png)



### JSTL을 사용하려면?

- http://tomcat.apache.org/download-taglibs.cgi
- 위의 사이트에서 3가지 jar파일을 다운로드 한 후 WEB-INF/lib/ 폴더에 복사를 한다.

![2_6_2_jstl_](https://user-images.githubusercontent.com/88477839/158022040-dec1470c-846c-461f-8dc4-63d3a1e4c128.png)

### JSTL이 제공하는 태그의 종류

![2_6_2_jstl___](https://user-images.githubusercontent.com/88477839/158022176-cdf7f78a-8ee9-4ad3-a0de-cb3d434353b0.png)

###  코어 태그

![2_6_2_jstl_ (1)](https://user-images.githubusercontent.com/88477839/158022202-ae9a11eb-de65-4606-b067-fd257328add3.png)



### 코어태그 : 변수 지원 태그 - set, remove

![1](https://user-images.githubusercontent.com/88477839/158022293-e59d56db-7b94-4ddc-9420-dddcecf51146.png)



### 실습

~~~jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="value1" scope="request" value="kang"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
성 : ${value1} <br>
<c:remove var="value1" scope="request"/> 
성 : ${value1} <br>
</body>
</html>
~~~



### 코어태그 : 변수 지원 태그 - 프로퍼티, 맵의 처리

+ 자바 언어를 공부할때 객체에 property라는 용어가 나오면 이 객체의 값을 변경하거나 값을 읽어들이기 위한 getter, setter 메서드를 생각하면 된다.

