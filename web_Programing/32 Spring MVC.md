# Spring MVC



## Spring MVC란?



### MVC

+ MVC는 Model-View-Controller의 약자이다.
+ 원래는 제록스 연구소에서 일을 하던 트뤼그베 린즈커그가 처음으로 소개한 개념으로, 데스크톱 어플리케이션용으로 고안되었다.

+ Model : 모델은 뷰가 렌더링하는데 필요한 데이터이다.
  + 예를 들어 사용자가 요청한 상품 목록이나, 주문 내역이 이에 해당한다.
+ View : 웹 애플리케이션에서 뷰(View)는 실제로 보이는 부분이며, 모델을 사용해 렌더링을 한다.
  + 뷰는 JSP, JSF, PDF, XML등으로 결과를 표현한다.
+ Controller : 컨트롤러는 사용자의 액션에 응답하는 컴포넌트다.
  + 컨트롤러는 모델을 업데이트하고, 다른 액션을 수행한다.



### MVC Model 1 아키텍쳐

![1](https://user-images.githubusercontent.com/88477839/161357680-25d4fe68-176d-4f0b-8959-2a58689db17b.png)

+ 브라우저가 요청을 하게 되면 해당 요청을 JSP가 받는다.
  + 따라서 요청만큼 JSP 페이지가 존재한다.
+ 이런 JSP는 Java Bean을 이용해서 데이터베이스를 사용하게 된다.
  + Ex ) RoleDao
+ 그리고 이 결과를 화면에 출력한다.
+ 위 방식으로 진행했을 때 문제점은 JSP 자체에 Java코드랑 HTML 코드들이 섞여있다.
  +  때문에 유지보수가 어려웠다.



### MVC Model 2 아키텍쳐

![2](https://user-images.githubusercontent.com/88477839/161357872-85c845a3-d199-4419-b5d7-0f5a577005a0.png)

+ MVC Model 2 아키텍쳐는 MVC Model 1 아키텍쳐의 문제점을 해결하기 위해 등장했다.
+ 모델2 아키텍쳐는 요청 자체를 서블릿이 받게 한다.
  + 여기서 서블릿은 요청과 데이터를 처리하는 컨트롤러의 역할을 수행한다.
+ 서블릿이 Java bean을 이용해 DB에서 데이터를 꺼내온다.
+ 꺼내온 결과들을 JSP를 통해서 화면에 보여준다.
  + JSP는 모델의 결과를 보여주게 하는 View의 역할을 한다.
+ 이렇게 함으로써 로직(컨트롤러,서블릿)과 view(JSP)를 분리할 수 있게 되는 것이다.



### MVC Model2 발전형태

![3](https://user-images.githubusercontent.com/88477839/161358063-948b7d5a-afb3-4785-8e26-4d99aa7925ef.png)

+  클라이언트가 보내는 모든 요청을 프론트 컨트롤러라고 하는 서블릿 클래스가 다 받는다.
  + 이 프론트 컨트롤러 서블릿은 딱 하나만 존재한다.
  + 이 프론트 컨트롤러는 요청만 받고 실제 일을 처리하지 않는다.
  + 실제 일을 그 다음에 나오는 컨트롤러 클래스(핸들러 클래스)에게 위임한다.
+ 서블릿은 관련된 요청을 처리하기에 조금 불편한 구조를 가지고 있다.
+ 이런 단점을 해결하기 위해서 사용자의 모든 요청은 서블릿이 받고
+ 그 요청의 실제 처리는 컨트롤러 혹은 핸들러라고 불리는 클래스에게 위임함으로써 관련된 URL을 하나의 클래스에서 다 처리할 수 있도록 하게 된다.

+ 이런 컨트롤러는 Java bean등을 이용해서 결과를 만들어낸다.
+ 만들어진 결과를 모델에다 담고 프론트 컨트롤러에게 보낸다.
+ 프론트 컨트롤러는 알맞은 뷰에게 모델을 전달해서 그 결과를 출력하게 된다.



### Spring Web Module

![4](https://user-images.githubusercontent.com/88477839/161358322-3cdd0291-1797-439f-adaa-e2d6d7427fef.png)

+ Model2 MVC 패턴을 지원하는 Spring Module
+ 앞에서 본 모델2의 발전된 형태가 스프링 프레임워크 모듈 중에 하나인 Web 모듈에 구현이 되어 있다.
  + 이러한 Web 모듈을 보통 Spring MVC라고 한다.



## Spring MVC구성요소 1



### Spring MVC 기본 동작 흐름

![1](https://user-images.githubusercontent.com/88477839/161359665-ebbf5492-df53-4346-8303-f3943978aa02.png)

+ Spring MVC는 모델2 아키텍쳐로 구성이 되어 있다.
+ 위 그림에서 Database를 제외하고 파란색 부분은 Spring MVC가 제공해주는 것들이다.
+ 보라색 부분은 개발자가 만들어야 하는 부분이다.
+ 녹색으로 되어있는 view는 Spring이 제공하는 부분도 있고 개발자가 만들어야 하는 부분도 같이 존재한다.
+ 동작 흐름
  + 클라이언트가 요청을 보내면 보낸 모든 요청을 Dispatcher Servlet이라고 하는 서블릿 클래스가 받는다.
  + Dispatcher Servlet은 요청을 처리해줄 컨트롤러와 메서드가 무엇인지 Handler Mapping에게 물어본다.
    + 개발자가 어떤 요청에 어떤 컨트롤러가 동작할지를 XML파일이나 java 파일에 어노테이션으로 설정한다.
    + 이런 정보들을 Spring으로 만들어진 웹 어플리케이션이 실행할 때 Handler Mapping 객체들이 생성이 되면서 관리를 하게 된다.
  + Dispatcher Serlvet은 그렇게 Handler Mapping으로부터 지금 들어온 요청에 알맞은 컨트롤러가 무엇인지 해당되는 메서드는 무엇인지에 대한 정보를 알아내게 될 것이다.
  + 이렇게 정보를 알아냈다면 Handler Adapter에게 실행을 요청한다.
  + 그때 결정된 컨트롤러와 해당 메서드가 실행이 된다.
  + 그 결과를 Model에 받아서 Dispatcher Servlet에게 전달을 하게 된다.
  + 이때 Dispatcher Servlet은 컨트롤러가 리턴한 view name을 알아오게 된다.
  + 컨트롤러가 리턴한 view name을 가지고 적절한 View Resolver을 통해서 뷰를 출력하게 된다.
+ Spring MVC를 이해한다는 것은 DispatcherServlet이 어떻게 동작하는지 이해하는 것이다.



## Spring MVC 구성요소2



### DispatcherServlet

+ 프론트 컨트롤러 (Front Controller)
+ 클라이언트의 모든 요청을 받은 후 이를 처리할 핸들러에게 넘기고 핸들러가 처리한 결과를 받아 사용자에게 응답 결과를 보여준다.
+ DispatcherServlet은 여러 컴포넌트를 이용해 작업을 처리한다.



### DispatcherServlet 내부 동작흐름

![2](https://user-images.githubusercontent.com/88477839/161366319-9cae5a86-9341-46ac-a91b-3c69cb7d6058.png)



### DispatcherServlet 내부 동작흐름 상세 - 요청 선처리 작업

![3](https://user-images.githubusercontent.com/88477839/161366387-31af8b89-c4a7-4452-a537-a557b3ebdff6.png)

+ Spring MVC는 지역화라는 것을 제공한다.
  + 지역화는 우리가 똑같은 사이트에 들어갔음에도 불구하고 어떤 사용자한테는 영어로 된 화면이 보여지고 어떤 사용자한테는 한국어로 된 화면이 보여지고 어떤 사용자한테는 독일어로 된 화면이 보여지게 할 수 있다는 의미이다.
  + 브라우저가 보내는 헤더 정보에서 언어 설정의 값들을 이용해서 Locale을 결정 할 수 있다.
+ RequestContextHolder - 스레드 로컬 객체 
  + 요청을 받아서 응답할 때까지 HttpServletRequest, HttpServletResponse 등을 Spring이 관리하는 객체 안에서 사용할 수 있도록 해주는 것들을 이야기 한다. 
  + 예를 들어 Spring MVC를 사용하다가 컨트롤러가 가진 메서드 안에서 Request 객체가 필요한 경우 인자에다가 HttpServletRequest request 라고 선언하면 된다. 그럼 그 메서드 안에서 request를 사용할 수 있다. 
+ FlashMap
  + Spring 3에서 추가된 기능
  + redirect로 값을 전달할 때 사용되는 것이다.
  + redirect로 값을 전달할때 우리가 ?, 파라미터 이런 것들을 이용한다. 근데 이렇게 하다 보면 URL이 굉장히 복잡해진다. 
  + 이런 부분때문에 FlashMap을 지원한다.
  + 이런 FlashMap을 사용하면 redirect 될 때 딱 한 번 값을 유지시킬 수 있게 해준다.
  + FlashMap을 복원한다는 것은 현재 실행이 redirect 되었을 때만 실행이 되는 부분들을 말한다.



### 요청 선처리 작업시 사용된 컴포넌트

+ **org.springframework.web.servlet.LocaleResolver**

  - 지역 정보를 결정해주는 전략 오브젝트이다.

  - 디폴트인 AcceptHeaderLocalResolver는 HTTP 헤더의 정보를 보고 지역정보를 설정해준다.

+ **org.springframework.web.servlet.FlashMapManager**

  - FlashMap객체를 조회(retrieve) & 저장을 위한 인터페이스

  - RedirectAttributes의 addFlashAttribute메소드를 이용해서 저장한다.

  - 리다이렉트 후 조회를 하면 바로 정보는 삭제된다.

+ **org.springframework.web.context.request.RequestContextHolder**

  - 일반 빈에서 HttpServletRequest, HttpServletResponse, HttpSession 등을 사용할 수 있도록 한다.

  - 해당 객체를 일반 빈에서 사용하게 되면, Web에 종속적이 될 수 있다.

+ **org.springframework.web.multipart.MultipartResolver**
  - 멀티파트 파일 업로드를 처리하는 전략



### DispatcherServlet 내부 동작흐름 상세 - 요청 전달

![4](https://user-images.githubusercontent.com/88477839/161367029-075faf0d-594a-4267-8ecd-96de1dae821b.png)



### 요청 전달시 사용된 컴포넌트

+ org.springframework.web.servlet.HandlerMapping

  - HandlerMapping구현체는 어떤 핸들러가 요청을 처리할지에 대한 정보를 알고 있다.

  - 디폴트로 설정되는 있는 핸들러매핑은 BeanNameHandlerMapping과 DefaultAnnotationHandlerMapping 2가지가 설정되어 있다.

+ org.springframework.web.servlet.HandlerExecutionChain

  - HandlerExecutionChain구현체는 실제로 호출된 핸들러에 대한 참조를 가지고 있다.

  - 즉, 무엇이 실행되어야 될지 알고 있는 객체라고 말할 수 있으며, 핸들러 실행전과 실행후에 수행될 HandlerInterceptor도 참조하고 있다.

+ org.springframework.web.servlet.HandlerAdapter

  - 실제 핸들러를 실행하는 역할을 담당한다.

  - 핸들러 어댑터는 선택된 핸들러를 실행하는 방법과 응답을 ModelAndView로 변화하는 방법에 대해 알고 있다.

  - 디폴트로 설정되어 있는 핸들러어댑터는 HttpRequestHandlerAdapter, SimpleControllerHandlerAdapter, AnnotationMethodHanlderAdapter 3가지이다.

  - @RequestMapping과 @Controller 애노테이션을 통해 정의되는 컨트롤러의 경우 DefaultAnnotationHandlerMapping에 의해 핸들러가 결정되고, 그에 대응되는 AnnotationMethodHandlerAdapter에 의해 호출이 일어난다.



### DispatcherServlet 내부 동작흐름 상세 - 요청 처리

![5](https://user-images.githubusercontent.com/88477839/161367176-9ee1c273-422d-4786-8d5a-4854e6c6ce56.png)

+ 인터셉터는 일종의 필터다. - 뭔가 처리하기 전에 한번 거쳐서 지나가게 하는 거



### **요청 처리시 사용된 컴포넌트**

+ **org.springframework.web.servlet.ModelAndView**
  - ModelAndView는 Controller의 처리 결과를 보여줄 view와 view에서 사용할 값을 전달하는 클래스이다.

+ **org.springframework.web.servlet.RequestToViewNameTranslator**
  - 컨트롤러에서 뷰 이름이나 뷰 오브젝트를 제공해주지 않았을 경우 URL과 같은 요청정보를 참고해서 자동으로 뷰 이름을 생성해주는 전략 오브젝트이다. 디폴트는 DefaultRequestToViewNameTranslator이다.



### **DispatcherServlet 내부 동작흐름 상세 - 예외처리**

![6](https://user-images.githubusercontent.com/88477839/161367339-cc846687-e28f-479a-a2d5-b8814db8aa4f.png)



### **예외 처리시 사용된 컴포넌트**

+ **org.springframework.web.servlet.handlerexceptionresolver**

  - 기본적으로 DispatcherServlet이 DefaultHandlerExceptionResolver를 등록한다.

  - HandlerExceptionResolver는 예외가 던져졌을 때 어떤 핸들러를 실행할 것인지에 대한 정보를 제공한다.



### **DispatcherServlet 내부 동작흐름 상세 - 뷰 렌더링 과정**

![7](https://user-images.githubusercontent.com/88477839/161367400-b8fc4e0b-d9b5-4418-913e-f6047af33e3e.png)



### **뷰 렌더링 과정시 사용된 컴포넌트**

+ **org.springframework.web.servlet.ViewResolver**

  - 컨트롤러가 리턴한 뷰 이름을 참고해서 적절한 뷰 오브젝트를 찾아주는 로직을 가진 전략 오프젝트이다.

  - 뷰의 종류에 따라 적절한 뷰 리졸버를 추가로 설정해줄 수 있다.



### DispatcherServlet 내부 동작흐름 상세 - 요청 처리 종료

![8](https://user-images.githubusercontent.com/88477839/161367463-047631fc-3cf3-430a-8f15-45ae3a082c2c.png)



## Spring MVC를 이용한 웹 페이지 작성 실습 1



### Controller 작성 실습

1. 웹 브라우저에서 http://localhost:8080/mvcexam/plusform 이라고 요청을 보내면 서버는 웹 브라우저에게 2개의 값을  입력받을 수 있는 입력 창과 버튼이 있는 화면을 출력한다.
2. 웹 브라우저에 2개의 값을 입력하고 버튼을 클릭하면 http://localhost:8080/mvcexam/plus URL로 2개의 입력값이 POST방식으로 서버에게 전달한다. 서버는 2개의 값을 더한 후, 그 결과 값을 JSP에게 request scope으로 전달하여 출력한다.



### 프로젝트 생성

+ 메이븐 프로젝트를 하나 생성한다 
  + archetype은 webapp 1.0버전으로
  + Artifact Id는 mvcexam

+ 프로젝트를 생성했으면 naviator 탭으로 가서 mvcexam 프로젝트 src -> main 폴더 안에 java 폴더를 하나 생성한다.

+ 다시 Project Explore로 이동해서 pom.xml에 필요한 부분들 추가

  + ~~~xml
    <build>
        <finalName>mvcexam</finalName>
        <plugins>
        	<plugin>
        		<groupId>org.apache.maven.plugins</groupId>
        		<artifactId>maven-compiler-plugin</artifactId>
        		<version>3.6.1</version>
        		<configuration>
        			<source>1.8</source>
        			<source>1.8</source>
        		</configuration>
        	</plugin>
        </plugins>
      </build>
    ~~~

  + ~~~xml
    <dependency>
      <groupId>javax.servlet</groupId>
      <artifactId>javax.servlet-api</artifactId>
      <version>3.1.0</version>
      <scope>provided</scope>
    </dependency>
    
    <dependency>
      <groupId>javax.servlet.jsp</groupId>
      <artifactId>javax.servlet.jsp-api</artifactId>
      <version>2.3.1</version>
      <scope>provided</scope>
    </dependency>
    
    <dependency>
      <groupId>jstl</groupId>
      <artifactId>jstl</artifactId>
      <version>1.2</version>
    </dependency>
    ~~~

    + jstl, jsp, servlet 라이브러리 추가

  + ~~~xml
    <!-- Spring -->
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-context</artifactId>
      <version>${spring.version}</version>
    </dependency>
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-webmvc</artifactId>
      <version>${spring.version}</version>
    </dependency>
    ~~~

    + spring-context , webmvc 추가

  + ~~~xml
    <properties>
    	<project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    	<spring.version> 4.3.5.RELEASE</spring.version>
    </properties>
    ~~~

    + 스프링 버전 통일하기 위해서 properties에 Spring 버전 넣어줌

+ 저장후 프로젝트 우클릭 -> 메이븐 -> 업데이트 프로젝트 클릭



+ Navigator로 이동해서 해당 프로젝트 클릭 -> .settings -> org.eclipse.wst.common.project.facet.core.xml로 이동한다.

  + ~~~xml
    <?xml version="1.0" encoding="UTF-8"?>
    <faceted-project>
      <fixed facet="wst.jsdt.web"/>
      <installed facet="jst.web" version="3.1"/>
      <installed facet="wst.jsdt.web" version="1.0"/>
      <installed facet="java" version="1.8"/>
    </faceted-project>
    
    ~~~

    + servlet 버전을 3.1로 바꿔준다.

+ 이후 이클립스 재시작한다.

+ 프로젝트 우클릭 - 프로퍼티즈 - project facets - dynamic web module이 3.1로 잘 바뀌었는지 확인한다.



## Spring MVC를 이용한 웹 페이지 작성 실습2



### DispatcherServlet을 FrontController로 설정하기

+ web.xml 파일에 설정 (가장 많이 사용)
+ javax.servlet.ServletContainerInitializer 사용
  + 서블릿 3.0 스펙 이상에서 web.xml파일을 대신해서 사용할 수 있다.
+ org.springframework.web.WebApplicationInitializer 인터페이스를 구현해서 사용 (많이 사용)



### web.xml파일에서 DispatcherServlet 설정하기

+ Xml spring 설정 읽어들이도록 DispatcherServlet 설정

  ![1](https://user-images.githubusercontent.com/88477839/161372151-d33c128e-fdbc-4823-a854-bf32f2e45c84.png)

  + Servlet-class가 실제로 내가 동작시킬 클래스를 의미함
    + 반드시 패키지 명을 포함해서 Spring이 제공하고 있는 클래스 명을 제대로 넣어주어야 함
  + DispatcherServlet 같은경우는 Spring이 제공하고 있기 때문에 실제로 내가 무슨 일을 하고 싶은지에 대한 내용까지는 알 수가 없다. 그런 것에 대한 설정을 하고 있는 것이 Init-param이 지정하고 있는 부분이다.
    + WebMVCContextConfig.xml 파일에 실제로 어떤 일들을 해야 될 건지를 알려준다.



+ Java config spring 설정 읽어들이도록 DispathcerServlet설정

  ![2](https://user-images.githubusercontent.com/88477839/161372613-98b53e66-af3f-4a1c-bebf-21cf642a3aa4.png)

  + Init-param 부분에 xml파일이 아니라 자바 클래스 이름을 넣고 있는것을 볼 수 있다.
    + 자바 config파일을 읽어오고 있는 것을 의미함
  + \<url-pattern> 이 / 로 설정되어 있는 것을 볼 수 있다.
    + 이는 어떤 특정한 하나의 요청만 받아들이는 것이 아니라 모든 요청을 받는다는 것을 의미함



### WebApplicationInitializer를 구현해서 설정하기

+ Spring MVC는 ServletContainerInitializer를 구현하고 있는 SpringServletContainerInitializer를 제공한다.
+ SpringServletContainerInitializer는 WebApplicationInitializer 구현체를 찾아 인스턴스를 만들고 해당 인스턴스의 onStartup메소드를 호출하여 초기화 한다.

![3](https://user-images.githubusercontent.com/88477839/161385310-32812766-c052-460c-94cf-c1da3da65a0a.png)



### Spring MVC 설정

+ kr.or.connect.webmvc.config.WebMvcContextConfiguration

  ![4](https://user-images.githubusercontent.com/88477839/161385407-ba4dd98a-e300-4255-a2ec-a0e47b562187.png)

+ DispatcherServlet에 대한 설정은 web.xml에서 하고 

+ DispatcherServlet이 읽어들여야 될 설정은 별도로 하게 된다. - 자바 config로 한다.



### **@Configuration**

- org.springframework.context.annotation의 Configuration 애노테이션과 Bean 애노테이션 코드를 이용하여 스프링 컨테이너에 새 로운 빈 객체를 제공할 수 있다.
- 자바 config 파일임을 알려준다.



### **@EnableWebMvc**

- DispatcherServlet의 RequestMappingHandlerMapping, RequestMappingHandlerAdapter, ExceptionHandlerExceptionResolver, MessageConverter 등 Web에 필요한 빈들을 대부분 자동으로 설정해준다.
- xml로 설정의 <mvc:annotation-driven/> 와 동일하다.
- 기본 설정 이외의 설정이 필요하다면 WebMvcConfigurerAdapter 를 상속받도록 Java config class를 작성한 후, 필요한 메소드를 오버라이딩 하도록 한다.

![5](https://user-images.githubusercontent.com/88477839/161385688-f5424228-5362-4645-b767-63141415647e.png)

**WebMvcConfigurationSupport**

- https://github.com/spring-projects/spring-framework/blob/master/spring-webmvc/src/main/java/org/springframework/web/servlet/config/annotation/WebMvcConfigurationSupport.java



### **@ComponentScan**

- ComponentScan애노테이션을 이용하면 Controller, Service, Repository, Component애노테이션이 붙은 클래스를 찾아 스프링 컨테이너가 관리하게 된다.
- DefaultAnnotationHandlerMapping과 RequestMappingHandlerMapping구현체는 다른 핸드러 매핑보다 훨씬 더 정교한 작업을 수행한다. 이 두 개의 구현체는 애노테이션을 사용해 매핑 관계를 찾는 매우 강력한 기능을 가지고 있다. 이들 구현체는 스프링 컨테이너 즉 애플리케이션 컨텍스트에 있는 요청 처리 빈에서 RequestMapping애노테이션을 클래스나 메소드에서 찾아 HandlerMapping객체를 생성하게 된다.
- HandlerMapping은 서버로 들어온 요청을 어느 핸들러로 전달할지 결정하는 역할을 수행한다.
- DefaultAnnotationHandlerMapping은 DispatcherServlet이 기본으로 등록하는 기본 핸들러 맵핑 객체이고, RequestMappingHandlerMapping은 더 강력하고 유연하지만 사용하려면 명시적으로 설정해야 한다.



### **WebMvcConfigurerAdapter**

- org.springframework.web.servlet.config.annotation. WebMvcConfigurerAdapter
- @EnableWebMvc 를 이용하면 기본적인 설정이 모두 자동으로 되지만, 기본 설정 이외의 설정이 필요할 경우 해당 클래스를 상속 받은 후, 메소드를 오버라이딩 하여 구현한다.



### **Controller(Handler) 클래스 작성하기**

- @Controller 애노테이션을 클래스 위에 붙인다.
- 맵핑을 위해 @RequestMapping 애노테이션을 클래스나 메소드에서 사용한다.



### **@RequestMapping**

- Http 요청과 이를 다루기 위한 Controller 의 메소드를 연결하는 어노테이션
- Http Method 와 연결하는 방법
   \- @RequestMapping(value="/users", method=RequestMethod.POST)
   \- Spring 4.3 version  부터
  - @GetMapping
  - @PostMapping
  - @PutMapping
  - @DeleteMapping
  - @PatchMapping
- Http 특정 해더와 연결하는 방법
   \- @RequestMapping(method = RequestMethod.GET, headers = "content-type=application/json")
- Http Parameter 와 연결하는 방법
   \- @RequestMapping(method = RequestMethod.GET, params = "type=raw")
- Content-Type Header 와 연결하는 방법
   \- @RequestMapping(method = RequestMethod.GET, consumes = "application/json")
- Accept Header 와 연결하는 방법
   \- @RequestMapping(method = RequestMethod.GET, produces = "application/json")



### 실습 - WebMvcContextConfiguration.java

+ 해당 클래스는 DispatcherServlet이 실행될 때 읽어들이는 설정 파일이다.

+ 프로젝트 우클릭 -> 패키지를 새로 생성한다. (패키지 이름은 kr.or.connect.mvcexam)

+ 해당 패키지에 클래스를 작성한다.

  <img width="601" alt="스크린샷 2022-04-02 23 20 27" src="https://user-images.githubusercontent.com/88477839/161387481-7b7296d1-7d90-4b47-b405-223417c0e0d7.png">

  + Browse를 눌러서 WebMvcConfigurerAdapter를 상속받도록 한다.

  

+ 어노테이션들을 추가함

  ~~~java
  // WebMvcContextConfiguration.java
  
  package kr.or.connect.mvcexam.config;
  
  import org.springframework.context.annotation.ComponentScan;
  import org.springframework.context.annotation.Configuration;
  import org.springframework.web.servlet.config.annotation.EnableWebMvc;
  import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
  
  @Configuration
  @EnableWebMvc
  @ComponentScan(basePackages = {"kr.or.connect.mvcexam.controller"})
  public class WebMvcContextConfiguration extends WebMvcConfigurerAdapter {
  
  }
  ~~~

+ 코드 추가

  ~~~java
  package kr.or.connect.mvcexam.config;
  
  import org.springframework.context.annotation.Bean;
  import org.springframework.context.annotation.ComponentScan;
  import org.springframework.context.annotation.Configuration;
  import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
  import org.springframework.web.servlet.config.annotation.EnableWebMvc;
  import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
  import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
  import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
  import org.springframework.web.servlet.view.InternalResourceViewResolver;
  
  @Configuration
  @EnableWebMvc // 기본적인 설정들은 여기에 해주세요
  @ComponentScan(basePackages = {"kr.or.connect.mvcexam.controller"})
  public class WebMvcContextConfiguration extends WebMvcConfigurerAdapter {
  	@Override
      public void addResourceHandlers(ResourceHandlerRegistry registry) {
          registry.addResourceHandler("/assets/**").addResourceLocations("classpath:/META-INF/resources/webjars/").setCachePeriod(31556926);
          registry.addResourceHandler("/css/**").addResourceLocations("/css/").setCachePeriod(31556926);
          registry.addResourceHandler("/img/**").addResourceLocations("/img/").setCachePeriod(31556926);
          registry.addResourceHandler("/js/**").addResourceLocations("/js/").setCachePeriod(31556926);
      }
   
      // default servlet handler를 사용하게 합니다.
      @Override
      public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
          configurer.enable();
      }
     
      @Override
      public void addViewControllers(final ViewControllerRegistry registry) {
      		System.out.println("addViewControllers가 호출됩니다. ");
          registry.addViewController("/").setViewName("main");
      }
      
      @Bean
      public InternalResourceViewResolver getInternalResourceViewResolver() {
          InternalResourceViewResolver resolver = new InternalResourceViewResolver();
          resolver.setPrefix("/WEB-INF/views/");
          resolver.setSuffix(".jsp");
          return resolver;
      }
  }
  ~~~

  

  ### web.xml 파일에 DispatcherServlet을 FrontController로 설정하기

  <img width="1168" alt="스크린샷 2022-04-02 23 38 13" src="https://user-images.githubusercontent.com/88477839/161388290-15dcef00-7c29-47da-b5e8-da2e24a8706e.png">

+ ~~~xml
  <!DOCTYPE web-app PUBLIC
   "-//Sun Microsystems, Inc.//DTD Web Application 2.3//EN"
   "http://java.sun.com/dtd/web-app_2_3.dtd" >
  
  <web-app>
    <display-name>Archetype Created Web Application</display-name>
    
    <servlet>
      <servlet-name>mvc</servlet-name>
      <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
      <init-param>
        <param-name>contextClass</param-name>
        <param-value>org.springframework.web.context.support.AnnotationConfigWebApplicationContext</param-value>
      </init-param>
      <init-param>
        <param-name>contextConfigLocation</param-name>
        <param-value>kr.or.connect.mvcexam.config.WebMvcContextConfiguration</param-value>
      </init-param>
      <load-on-startup>1</load-on-startup>
    </servlet>
    <servlet-mapping>
      <servlet-name>mvc</servlet-name>
      <url-pattern>/</url-pattern>
    </servlet-mapping>
    
  </web-app>
  ~~~



### view(jsp) 파일 만들기

+ <img width="866" alt="스크린샷 2022-04-02 23 57 33" src="https://user-images.githubusercontent.com/88477839/161389036-5ad2e7e3-ef07-4376-b73e-f3c132c4928e.png">
  + src - main - webapp - WEB-INF 밑에 views라는 폴더를 만들고 그 밑에 main.jsp 파일을 생성한다.
  + 저장후 프로젝트 우클릭 - run as run on server를 눌러준다.
  + 만약 난데 없이 Hello world라는 텍스트가 담긴 브라우저 화면이 뜬다면 WEB-INF 안에 있는 indext.jsp 파일을 삭제 시켜준다.
  + 그 이후 정상적으로 main page!! 라는 텍스트가 출력 되는 것을 확인할 수 있다.

