# 서블릿 - web.xml 방식으로 작성방법(서블릿3.0 미만 에서)



<img width="596" alt="스크린샷 2022-03-04 23 32 17" src="https://user-images.githubusercontent.com/88477839/156782014-b0f26a47-ad24-4aa6-81ba-05d80c2e5c25.png">

+ New - Dynamic Web Project를 클릭해서 새로운 프로젝트를 생성한다.
+ 프로젝트 이름을 작성하고 Dynamic web module version을 2.5로 선택해준다.

<img width="598" alt="스크린샷 2022-03-04 23 32 41" src="https://user-images.githubusercontent.com/88477839/156782321-5ccec9f2-d643-4e63-b0ed-0e0a0017fda5.png">

+ Generate web.xml deployment descriptor가 자동으로 체크된 것을 확인 할 수 있다.

<img width="1134" alt="스크린샷 2022-03-04 23 36 43" src="https://user-images.githubusercontent.com/88477839/156782682-dcf0aa42-b9c6-43c7-8de7-0e20e8d39b87.png">

+ 프로젝트 생성 직후 web.xml 파일의 모습
+ WEB-INF 디렉토리 안에 위치해 있다.

#### \* 서블릿 생성방법은 이전과 동일함

<img width="1135" alt="스크린샷 2022-03-04 23 40 33" src="https://user-images.githubusercontent.com/88477839/156783316-3e8d7750-594f-4f8b-9017-f0215dd9eda8.png">

+ 서블릿을 생성하면 HttpServlet을 상속받고 있는 클래스가 생성됨
+ Class 위쪽에 어노테이션이 없음

<img width="1136" alt="스크린샷 2022-03-04 23 43 15" src="https://user-images.githubusercontent.com/88477839/156783747-677e3b51-e4df-4663-b58d-00d3a90c99b5.png">

+ 서블릿 생성 직후의 web.xml 파일의 모습
+ 서블릿 생성전에는 보이지 않았던 \<servlet> 태그와 \<servlet-mapping> 태그가 생성됨

~~~xml
<servlet>
  <description></description>
  <display-name>TenServlet</display-name> <!-- DD파일의 title. 이클립스에서는 기본적으로 프로젝트명으로 설정되며 수정 가능하다 -->
  <servlet-name>TenServlet</servlet-name> <!-- 동일한 서블릿 네임을 찾음 -->
  <servlet-class>exam.TenServlet</servlet-class> <!-- exam 패키지 안에 있는 실제 클래스인 TenServlet을 실행시켜주세요 -->
</servlet>
<servlet-mapping>
  <servlet-name>TenServlet</servlet-name> <!-- 클라이언트 요청을 받고 해당하는 동일한 서블릿 네임을 <servlet> 태그에서 찾는다.-->
  <url-pattern>/ten</url-pattern> <!-- 클라이언트가 요청할때의 url mapping, 여기서 URL 매핑명을 변경할 수 있고 만약 번경한다면 서버를 리스타트 해야한다.-->
</servlet-mapping>
~~~

<img width="1139" alt="스크린샷 2022-03-04 23 53 13" src="https://user-images.githubusercontent.com/88477839/156785643-dfd479b7-355e-4b33-9689-12cd2b23e643.png">

+ doGet 메서드의 실행코드는 동일함

