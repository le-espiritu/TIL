# Web API



### Web API

+ REST API의 모든 스타일을 구현하지 못할 경우에는 REST API라고 부르기 보다는 Web API나 HTTP API라고 부른다.



### Web API 디자인 가이드

+ URI는 정보의 자원을 표현해야 한다.
+ 자원에 대한 행위는 HTTP Method(GET,POST,PUT,DELETE)로 표현한다.



### 자원에 대한 행위는 HTTP Method로 표현

![2_11_2_webapi](https://user-images.githubusercontent.com/88477839/158934941-174091c5-e041-40c0-bc88-e264427d4df0.png)



### URI는 정보의 자원을 표현해야 한다.

+ GET / members

  + 위의 표현은 멤버의 모든 정보를 달라는 요청이다.

  + 명사는 보통 집합을 나타내기 때문에 복수형으로 사용하는 것이 좋다.(members)

    

+ GET / members/delete/1

  + GET은 정보를 요청할 때 사용한다. 위와 같이 동사로 삭제를 표현하면 안된다.

  + 이렇게 표현하면 안되고 아래와 같은 표현방법을 쓴다.

    

+ DELETE /members/1

  + HTTP Method중의 하나인 DELETE를 이용하여 삭제를 표현해야 한다.
  + members중에 1에 해당하는 것을 삭제해주세요 라는 의미

​	

### **자원에 대한 행위는 HTTP Method로 표현**

+ 조희, 입력, 삭제, 수정과 관련된 것들이 동사로 표현되면 안된다.

  - GET /members/1                  (o)

  - GET /members/get/1           (x)

  - GET /members/add             (x)

  - POST /members                   (o)

  - GET /members/update/1    (x)

  - PUT /members/1                  (o)

  - GET /members/del/1           (x)

  - DELETE /members/1            (o)



### 슬래시 구분자(/)는 계층을 나타낼때 사용

http://domain/houses/apartments : 집들 중에서 아파트를 요청하고 있음

http://domain/departments/1/employees : 모든 부서들 중에서 1번 부서, 그  1번 부서의 사원에 대해서 요청하고 있음

- URI 마지막 문자로 슬래시 구분자(/)를 포함하지 않습니다.
- 하이픈(-)은 URI가독성을 높일 때 사용합니다.
- 언더바(_)는 사용하지 않습니다.
- URI경로는 소문자만 사용합니다.
- RFC 3986(URI 문법 형식)은 URI스키마와 호스트를 제외하고는 대소문자를 구별합니다.
- 파일 확장자는 URI에 포함하지 않습니다.
- Accept Header를 사용합니다.



### 상태 코드(성공)

![2_11_1_1](https://user-images.githubusercontent.com/88477839/158936122-aa7d67f2-039f-40f4-bd95-e6272e04757c.png)

+ 상태 코드가 200번 대는 대부분 성공을 의미함
+ 200번은 성공일때 대부분 클라이언트가 받는 상태 값
+ 201번은 POST 방식으로 등록을 성공했을 때 받는 상태 값



### 상태 코드 (클라이언트로 인한 오류)

![2_11_1_2](https://user-images.githubusercontent.com/88477839/158936365-37243fba-376a-44bc-baf3-19f30da0f464.png)

+ 400번대의 상태 코드는 모두 클라이언트가 뭔가 잘못 요청했을 때 받는 상태 값



### 상태 코드 (서버로 인한 오류)

![2_11_1_3](https://user-images.githubusercontent.com/88477839/158936571-8ebff571-5b78-4e2c-a9c6-5c8a11ff7ea2.png)

+ 500번대 예 - 중간에 서버가 프로그램 돌다가 NullPointException이 발생한 경우등



## Web API 실습



### Maven 프로젝트 생성

<img width="933" alt="스크린샷 2022-03-18 17 55 54" src="https://user-images.githubusercontent.com/88477839/158971879-e994925b-5073-43a4-8ea7-b768c0000f25.png">

+ archetype을 webapp로 선택

<img width="935" alt="스크린샷 2022-03-18 17 56 37" src="https://user-images.githubusercontent.com/88477839/158972040-5a420411-24ed-454d-bd96-61d2aed45ab2.png">

+ artifact id는 webapiexam

<img width="1081" alt="스크린샷 2022-03-18 18 01 38" src="https://user-images.githubusercontent.com/88477839/158972557-f8f5e010-86d7-4a64-a27f-40eae7bab8a7.png">

+ pom.xml에 아래 코드 추가한다.

  ~~~xml
  <plugins>
  		<plugin>
  			<groupId>org.apache.maven.plugins</groupId>
  			<artifactId>maven-compiler-plugin</artifactId>
  			<version>3.6.1</version>
  			<configuration>
  				<source>1.8</source>
  				<target>1.8</target>
  			</configuration>
  		</plugin>
  	</plugins>
  ~~~



<img width="1128" alt="스크린샷 2022-03-18 18 10 04" src="https://user-images.githubusercontent.com/88477839/158973927-ada12939-144b-4fed-a696-909630982b08.png">

+ pom.xaml dependencies에 jdbc, Json라이브러리, 서블릿 api, JSTL dependency 추가한다.

  ~~~xml
  <!-- jdbc -->
    	<dependency>
  	    <groupId>mysql</groupId>
  	    <artifactId>mysql-connector-java</artifactId>
  	    <version>8.0.28</version>
  	</dependency>
    
    	<!-- Json 라이브러리 -->
  	<dependency>
  		<groupId>com.fasterxml.jackson.core</groupId>
  		<artifactId>jackson-databind</artifactId>
  		<version>2.9.4</version>
  	</dependency>
  
  	<!-- 서블릿 api -->
  	<dependency>
  		<groupId>javax.servlet</groupId>
  		<artifactId>javax.servlet-api</artifactId>
  		<version>3.1.0</version>
  		<scope>provided</scope>
  	</dependency>
  
  	<!-- JSTL -->
  	<dependency>
  		<groupId>javax.servlet</groupId>
  		<artifactId>jstl</artifactId>
  		<version>1.2</version>
  	</dependency>
  ~~~

+ 저장 누름

+ 프로젝트 우클릭 -> Maven -> Update Project 누름

<img width="1155" alt="스크린샷 2022-03-18 18 17 01" src="https://user-images.githubusercontent.com/88477839/158975027-c2ce9e93-bece-4153-b176-7818e0d25891.png">

+ Navigator 탭 -> 프로젝트 -> .settings -> org.eclipse.wst.common.project.facet.core.xml 오픈
+ jst.web의 버전이 2.3인것을 확인할 수 있다.(서블릿 버전과 관련 있음)

<img width="1159" alt="스크린샷 2022-03-18 18 20 45" src="https://user-images.githubusercontent.com/88477839/158975674-846d707e-9468-41d3-adb8-e8fded14b9ea.png">

+ jst.web 버전을 3.1로 바꿔줌 (서블릿 버전 3.1 사용을 위해)
+ 저장 후 이클립스 재시작해야함.

<img width="959" alt="스크린샷 2022-03-18 18 25 41" src="https://user-images.githubusercontent.com/88477839/158976816-b7963a91-7d44-4f8f-9d7c-5331fe025f85.png">

+ 이클립스 재시작후 프로젝트 우클릭 -> properties 선택

+ Project Facets로 가면 Dynamic Web Module 버전이 3.1로 바뀐것을 확인할 수 있음

<img width="971" alt="스크린샷 2022-03-18 18 29 43" src="https://user-images.githubusercontent.com/88477839/158977273-03ffe7ba-d9ad-4fb7-88bb-f66622ded477.png">

+ 프로젝트 -> src -> main -> webapp -> WEB-INF -> Web.xml 파일 삭제
  + 해당 프로젝트에서는 Annotation을 이용해서 서블릿을 설정할 것이기 때문

<img width="953" alt="스크린샷 2022-03-18 18 35 46" src="https://user-images.githubusercontent.com/88477839/158978206-46316986-14c3-42e0-a210-e8fc99de19bf.png">

+ pom.xml \<properties>에 \<failOnMissingWebXml> 엘리먼트를 추가해주어야 오류가 발생하지 않는다.
  + 기본적으로 web.xml을 찾으려고 할 것이기 때문에 web.xm.을 찾지 말라고 설정하는 것임

<img width="942" alt="스크린샷 2022-03-18 18 42 30" src="https://user-images.githubusercontent.com/88477839/158979285-71b61530-b5f9-45fd-9cbe-1d2d04201799.png">

+ src -> main 에 java 라는 폴더를 만든다. (Navigator 탭에서 - 하지만 크게 상관은 없을 듯함)
+ 이 java라는 폴더에는 자바 패키지와 클래스등이 저장될 것임
+ Project Explorer탭으로 돌아간다.

<img width="901" alt="스크린샷 2022-03-18 18 48 33" src="https://user-images.githubusercontent.com/88477839/158982235-cca4fde1-4f31-4e9e-826e-14a979ea7a5c.png">

+ Java Resources - src/main/java에 패키지를 하나 생성한다.
  + 패키지 이름은 kr.or.connect.webapiexam.api

<img width="801" alt="스크린샷 2022-03-18 19 57 08" src="https://user-images.githubusercontent.com/88477839/158991079-b6626b2f-bb2c-48c6-bf07-720289d4ff44.png">

<img width="1000" alt="스크린샷 2022-03-18 19 57 49" src="https://user-images.githubusercontent.com/88477839/158991142-1fd8541f-beeb-41a5-86d8-95ba1ad13e37.png">

+ Jdbcexam프로젝트의 kr.or.connect.jdbcexam.dao패키지와 kr.or.connect.jdbcexam.dto 패키지를 복사한다.
+ webapiexam 프로젝트에 붙여넣는다.



### GET /roles Web api 작성하기



<img width="851" alt="스크린샷 2022-03-18 22 44 07" src="https://user-images.githubusercontent.com/88477839/159018570-4885927d-4e59-4a89-9b57-bc35a8f4cbd1.png">

+ 서블릿을 생성한다.

<img width="607" alt="스크린샷 2022-03-18 22 44 27" src="https://user-images.githubusercontent.com/88477839/159018675-cf881652-0083-4c6a-bbac-721b8376d80d.png">

+ url매핑명을 변경한다.

<img width="603" alt="스크린샷 2022-03-18 22 44 50" src="https://user-images.githubusercontent.com/88477839/159018772-b831baaa-8bc8-4be7-adf6-265305935947.png">

+ doGet을 오버라이딩 한다.

~~~java
// RolesServlet.java


package kr.or.connect.webapiexam.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.connect.jdbcexam.dao.RoleDao;
import kr.or.connect.jdbcexam.dto.Role;


@WebServlet("/roles")
public class RolesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RolesServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		
		RoleDao dao = new RoleDao();
		
		List<Role> list = dao.getRoles();
		
		// V JSON사용하기 위해 pom.xml에 추가했던 라이브러리가 제공해주는 객체
		// JSON 문자열로 바꿔주거나 JSON 문자열을 객체로 바꾸는 역할을 수행해주는 객체 
		ObjectMapper objectMapper = new ObjectMapper();
		// writeValueAsString메서드에 파라미터로 list를 넣어주면 해당 list가 JSON 문자로 바뀌어서 리턴을 해주게됨 
		String json = objectMapper.writeValueAsString(list);
		
		PrintWriter out = response.getWriter();
		out.println(json);
		out.close();
		
	}

}
~~~

+ 코드를 작성하고 실행한다.
  + 실행 브라우저 설정하기
  + <img width="632" alt="스크린샷 2022-03-18 23 00 24" src="https://user-images.githubusercontent.com/88477839/159019211-e1df4e48-091a-40cc-8e46-e8b97ca4d27b.png">
  + new 클릭
  + <img width="702" alt="스크린샷 2022-03-18 23 01 44" src="https://user-images.githubusercontent.com/88477839/159019286-4cc6e46d-d9a5-4b63-bd72-e12b15fda606.png">
  + 이름적어주고 Browse 클릭해서 google chrome 앱 찾아서 선택해줌
  + <img width="639" alt="스크린샷 2022-03-18 23 02 07" src="https://user-images.githubusercontent.com/88477839/159019456-c5af1e9d-e232-40ff-91c8-748ba5e50336.png">
  + 추가된 Chrome 선택하고 Use external web browser 선택
  + <img width="558" alt="스크린샷 2022-03-18 23 02 20" src="https://user-images.githubusercontent.com/88477839/159019722-5280f10c-6c48-4e2c-aaed-512f2470cf79.png">
  + 브라우저가 바뀐것을 확인할 수 있음



<img width="656" alt="스크린샷 2022-03-18 23 03 50" src="https://user-images.githubusercontent.com/88477839/159019844-e4e2877c-4cba-4c75-929f-364968b30e00.png">

+ 실행 결과 - list가 JSON으로 바뀌어서 출력되고 있는 것을 확인 할 수 있음



### JSON

+ JSON은 JavaScript Object Notation의 약어로 아주 가벼운 형태의 메시지 교환 형식이다.
+ 자바스크립트에서는 객체로 만들 때 사용하는 표현식
+ 대괄호([])는 배열, 리스트를 의미함
+ 중괄호({})는 객체 한 것을 의미함
+ "속성명":값 



### Maven이 라이브러리를 제대로 인식못해서 import가 잘 안 된 경우 대처법

<img width="392" alt="스크린샷 2022-03-18 23 25 44" src="https://user-images.githubusercontent.com/88477839/159021122-3b73782d-1174-4f1f-aa50-c68c2af0fbe6.png">

+ Maven Dependencies에 라이브러리들이 분명히 다 있음에도 실행이 제대로 안되는 경우가 있음

<img width="547" alt="스크린샷 2022-03-18 23 27 23" src="https://user-images.githubusercontent.com/88477839/159021770-b9422243-0ea7-4cd6-81bd-45110fb3f9bc.png">

+ 파일 탐색기 - c드라이브 -user 폴더에 .m2 디렉토리가 있음

<img width="548" alt="스크린샷 2022-03-18 23 27 46" src="https://user-images.githubusercontent.com/88477839/159022102-ff3661f2-b033-4226-87f4-af220d625318.png">

<img width="546" alt="스크린샷 2022-03-18 23 28 06" src="https://user-images.githubusercontent.com/88477839/159022198-1908933d-016f-4914-b7b3-f34258d75a09.png">

+ repository 안에 사용하고 있는 라이브러리들이 다 여기에 위치한것을 확인 할 수 있다.
+ 이 부분을 제대로 읽어오지 못했다던가 하는 문제들이 발생했을때 import가 제대로 되지 않는 문제들이 발생한다.
+ 이런 경우에는 이클립스를 종료하고 user 폴더의 .m2 폴더를 삭제해준다.
+ 이클립스를 재실행하고 프로젝트 우클릭 - properties -  maven - update project를 눌러서 실행하면 된다.



### 아이디 한 건에 대해서 role 정보 읽어오는 서블릿

+ 서블릿을 하나 생성하는데 이때 url매핑명을 /roles/*로 해준다.
  + path가 /roles로 시작하지만 어떤 문자든지 올 수 있다는 의미.(*)

~~~java
// RoleByIdServlet.java

package kr.or.connect.webapiexam.api;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.connect.jdbcexam.dao.RoleDao;
import kr.or.connect.jdbcexam.dto.Role;

@WebServlet("/roles/*") // path가 /roles로 시작하지만 어떤 문자든지 올 수 있다.(*)
public class RoleByIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RoleByIdServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		
		//V path 정보를 읽어오는 코드 
		String pathInfo = request.getPathInfo(); // /roles/{roleId}
		String[] pathParts = pathInfo.split("/"); // 읽어온 path 정보를 /를 기준으로 잘라서 배열을 구함
		String idStr = pathParts[1]; // roles는 인덱스가 0번, {roleId}는 인덱스가 1
		int id = Integer.parseInt(idStr);
		
		RoleDao dao = new RoleDao();
		
		Role role = dao.getRole(id);
		
		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(role);
		
		PrintWriter out = response.getWriter();
		out.println(json);
		out.close();
	}

}
~~~



+ 실행결과

  <img width="835" alt="스크린샷 2022-03-19 00 57 19" src="https://user-images.githubusercontent.com/88477839/159038660-aa06b3f4-ca25-4b23-8b7e-84717300b7ef.png">

  + Url을 /roles/100으로 해줬을 때의 결과

  <img width="835" alt="스크린샷 2022-03-19 00 57 30" src="https://user-images.githubusercontent.com/88477839/159038955-b7033e03-f793-4417-971d-856b500f83e0.png">

  + url을 /roles/101으로 해줬을 때의 결과

  <img width="838" alt="스크린샷 2022-03-19 00 57 41" src="https://user-images.githubusercontent.com/88477839/159039101-d7dd6a70-4ad1-4c3f-aaf5-c9fc11072c8c.png">

  + url을 /roles/102로 해줬을 때의 결과

  

  

  





