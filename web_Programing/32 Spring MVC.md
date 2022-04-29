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

  + DispatcherServlet은 해당 설정 파일을 읽어들여서 내부적으로 Spring 컨테이너인 ApplicationContext를 생성하게 되는 것이다.




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

+ 프로젝트 우클릭 -> 패키지를 새로 생성한다. (패키지 이름은 kr.or.connect.mvcexam.config)

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

  + addResourceHandlers() 
    + Web.xml에서 모든 요청이 들어 왔을때 서블릿(디스패처서블릿)을 실행하게 해줘요 라고 설정했었다.
    + 그런데 이때 들어오는 모든 요청중에는 컨트롤러의 URL이 매핑되어있는 요청만 들어오는 것이 아니라 CSS라던가, 이미지라던가 자바스크립트같은 요청들도 들어와서 문제가 생긴다.
    + 위 문제를 해결하기 위한 메소드이고 "/css/**" 이렇게 시작하는 URL 요청은 애플리케이션 루트 디렉토리 아래에 있는 "/css/"에서 찾아요 라는 의미임.
  + addViewControllers() 메서드
    + 특정 URL에 대한 처리를 컨트롤러 클래스를 작성하지 않고 매핑할 수 있도록 해준다.
    + "/" 라는 요청이 들어오면 "main"이라고 하는 뷰의 이름으로 보여준다.
      + View name("main")은 ViewResolver라는 객체를 이용해서 찾는다. 
  + getInternalResourceViewResolver() 메서드
    + 이 메서드에서 설정된 형태로 뷰를 사용하게 된다.
    + InternalResourceViewResolver를 생성하고 있고 이 리졸버에게 Prefix, Suffix를 지정하고 있다.

  ### web.xml 파일에 DispatcherServlet을 FrontController로 설정하기

  <img width="1168" alt="스크린샷 2022-04-02 23 38 13" src="https://user-images.githubusercontent.com/88477839/161388290-15dcef00-7c29-47da-b5e8-da2e24a8706e.png">

+ ~~~xml
  <!-- <!DOCTYPE web-app PUBLIC
   "-//Sun Microsystems, Inc.//DTD Web Application 2.3//EN"
   "http://java.sun.com/dtd/web-app_2_3.dtd" > -->
  
  <?xml version="1.0" encoding="UTF-8"?> <!-- 메이븐 강의에서 web.xml에서 바꿔줬던 부분이니 참고 -->
  
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



## Spring MVC를 이용한 웹 페이지 작성 실습3



### Controller 작성 실습

1. 웹 브라우저에서 http://localhost:8080/mvcexam/plusform 이라고 요청을 보내면 서버는 웹 브라우저에게 2개의 값을  입력받을 수 있는 입력 창과 버튼이 있는 화면을 출력한다.
2. 웹 브라우저에 2개의 값을 입력하고 버튼을 클릭하면 http://localhost:8080/mvcexam/plus URL로 2개의 입력값이 POST방식으로 서버에게 전달한다. 서버는 2개의 값을 더한 후, 그 결과 값을 JSP에게 request scope으로 전달하여 출력한다.



### view 만들기

+ <img width="793" alt="스크린샷 2022-04-03 13 56 45" src="https://user-images.githubusercontent.com/88477839/161412200-576eea6b-3d8d-4d59-888b-25d8ddfc20f5.png">
  + Views 폴더 밑에 plusform.jsp 파일을 만든다.
+ <img width="852" alt="스크린샷 2022-04-03 14 01 32" src="https://user-images.githubusercontent.com/88477839/161412350-c4610cca-c65c-4053-8969-620575b22794.png">
  + Views 폴더 밑에  plusResult.jsp 파일을 만든다. - 처리해서 보여주기 위해
  + request scope에 넣어줬던 ${value1},${value2}, ${result}을 꺼내와서 (EL표기법으로) 출력할 수 있는 JSP파일



### Controller 만들기

+ 컨트롤러만 보관하기 위해 kr.or.connect.mvcexam.controller 패키지를 생성한다.

+ plusController 클래스 파일을 생성한다.

  <img width="845" alt="스크린샷 2022-04-03 14 13 16" src="https://user-images.githubusercontent.com/88477839/161412699-a1365f3a-c469-4ca4-a84b-76406f43fdb9.png">

  + 클래스명 위에 @Controller 어노테이션을 표기해준다.
  + @GetMapping 어노테이션으로 RequestMapping을 사용한다.
  + 보여주고자 하는 뷰(jsp)의 이름을 리턴해준다.
  + 저장후 프로젝트 우클릭 - 런 - 런온서버를 클릭해서 웹 어플리케이션을 실행시켜준다.
  + 실행된 웹 어플리케이션 URL에 RequestMapping 경로를 추가하여 실행시켜본다
    + localhost:8080/mvcexam/plusform

  <img width="1010" alt="스크린샷 2022-04-03 14 34 28" src="https://user-images.githubusercontent.com/88477839/161413353-c3f3f520-db04-4dda-bdcc-5fb3166e7017.png">

  + Plus 메서드를 추가한다.

  + 위 메서드의 인자 부분 코드는 form의 input에서 name이 value1으로 넘어오는데 그 부분이 @Requestparam이다. 이것의 이름이 value1인거, 이것을 int형 변수 value1에 넣어달라는 의미임

  + 위 메서드에서 구한 값들을 request scope에 넣어줘야 한다.

  + 이 때 HttpServletRequest 를 선언해서 쓸 수 있지만 그렇게 되면 이것에 종속되는 것이기 때문에 여기서는 쓰지 않고

  + Spring이 제공하고 있는 ModelMap 객체에 넣어줄 것이다.

  + 그렇게 되면 Spring이 알아서 request scope에다가 매핑 시켜준다.

  + 저장하고 실행시켰을때 만약 EL 표기법이 제대로 인식하지 않았다면

    <img width="1048" alt="스크린샷 2022-04-03 14 54 47" src="https://user-images.githubusercontent.com/88477839/161413949-11cac5b5-580a-41fc-a758-bdb8bd2106f5.png">

    + web.xml 파일을 위와 같이 변경해준다.

  + 그리고 변경후 잘 인식하지 못할 수 있기 때문에 servers 패널에서 해당 프로젝트를 삭제하고 다시 시작해준다.

  



### **Spring MVC가 지원하는 Controller메소드 인수 타입**

- javax.servlet.ServletRequest
- **javax.servlet.http.HttpServletRequest**
- org.springframework.web.multipart.MultipartRequest
- org.springframework.web.multipart.MultipartHttpServletRequest
- javax.servlet.ServletResponse
- **javax.servlet.http.HttpServletResponse**
- **javax.servlet.http.HttpSession**
- org.springframework.web.context.request.WebRequest
- org.springframework.web.context.request.NativeWebRequest
- java.util.Locale
- java.io.InputStream
- java.io.Reader
- java.io.OutputStream
- java.io.Writer
- javax.security.Principal
- java.util.Map
- org.springframework.ui.Model
- org.springframework.ui.ModelMap
- **org.springframework.web.multipart.MultipartFile**
- javax.servlet.http.Part
- org.springframework.web.servlet.mvc.support.RedirectAttributes
- org.springframework.validation.Errors
- org.springframework.validation.BindingResult
- org.springframework.web.bind.support.SessionStatus
- org.springframework.web.util.UriComponentsBuilder
- org.springframework.http.HttpEntity<?>
- Command 또는 Form 객체



### **Spring MVC가 지원하는 메소드 인수 애노테이션**

- **@RequestParam**
- **@RequestHeader**
- **@RequestBody**
- @RequestPart
- **@ModelAttribute**
- **@PathVariable**
- @CookieValue



### **@RequestParam**

- Mapping된 메소드의 Argument에 붙일 수 있는 어노테이션
- @RequestParam의 name에는 http parameter의 name과 멥핑
- @RequestParam의 required는 필수인지 아닌지 판단



### **@PathVariable**

+ URL path에다가 '?변수명 =값' 이런식으로 값을 넘겨올 때가 있었는데 이럴 때 path에서 넘겨온 값을 받기 위한 어노테이션 

- @RequestMapping의 path에 변수명을 입력받기 위한 place holder가 필요함
- place holder의 이름과 PathVariable의 name 값과 같으면 mapping 됨
- required 속성은 default true 임



### **@RequestHeader**

- 요청 정보의 헤더 정보를 읽어들 일 때 사용
- @RequestHeader(name="헤더명") String 변수명



### **Spring MVC가 지원하는 메소드 리턴 값**

- **org.springframework.web.servlet.ModelAndView**
- org.springframework.ui.Model
- java.util.Map
- org.springframework.ui.ModelMap
- org.springframework.web.servlet.View
- **java.lang.String**
- java.lang.Void
- org.springframework.http.HttpEntity<?>
- org.springframework.http.ResponseEntity<?>
- **기타 리턴 타입**



## Spring MVC를 이용한 웹 페이지 작성 실습 4



### Controller 작성 실습2

1. http://localhost:8080/mvcexam/userform 으로 요청을 보내면 이름, email, 나이를 물어보는 폼이 보여진다.
2. 폼에서 값을 입력하고 확인을 누르면 post방식으로 http://localhost:8080/mvcexam/regist에 정보를 전달하게 된다.
3. regist에서는 입력받은 결과를 콘솔 화면에 출력한다.



+ userform.jsp 뷰 작성

  <img width="956" alt="스크린샷 2022-04-04 01 57 33" src="https://user-images.githubusercontent.com/88477839/161439211-c2bf7bd1-36aa-4dff-a8b9-c4c03b448942.png">



+ UserController 작성

  <img width="861" alt="스크린샷 2022-04-04 02 03 33" src="https://user-images.githubusercontent.com/88477839/161439366-d3d93648-5489-4e73-81c8-3b7edd7dd18b.png">

  

  + @Controller 어노테이션을 붙여준다.

  + 여기서는 @GetMapping 어노테이션 대신에 @RequestMapping 어노테이션을 붙여줬다.

+ regitst 메서드 생성

  <img width="1006" alt="스크린샷 2022-04-04 02 12 18" src="https://user-images.githubusercontent.com/88477839/161439677-d0af1689-3330-4fc7-82ad-336ce60ecd48.png">

  + regist()메서드의 인자로 값을 하나하나 받아올 것이 아니라 DTO로 한꺼번에 받아온다.
  + Spring MVC가 알아서 userform.jsp 에서 일치하는 name에 있는 값을 user 객체안에 넣어준다. (객체 생성해서)

+ DTO 생성

  <img width="952" alt="스크린샷 2022-04-04 02 14 57" src="https://user-images.githubusercontent.com/88477839/161439767-4acc3aab-2e63-4e45-823b-af049a06a148.png">

  

  + kr.or.connect.mvcexam.dto 패키지를 만들어준다.
  + 그 아래 User.java DTO를 만들어 준다.
  + 스프링이 알아서 값들을 채줘줄건데 이때 스프링은 setter 메서드를 사용한다.

+ regsit.jsp 뷰 생성

  <img width="926" alt="스크린샷 2022-04-04 02 17 53" src="https://user-images.githubusercontent.com/88477839/161439881-733ba3f2-e75f-4790-aa9b-c5ea2f65797f.png">

  



### Controller 작성 실습2

1. http://localhost:8080/mvcexam/goods/{id} 으로 요청을 보낸다.
   + {id} - 이렇게 넘기는 값을 path variable 이라고 한다.
2. 서버는 id를 콘솔에 출력하고, 사용자의 브라우저 정보를 콘솔에 출력한다.
3. 서버는 HttpServletRequest를 이용해서 사용자가 요청한 PATH정보를 콘솔에 출련한다.



+ goodsById.jsp 작성

  <img width="890" alt="스크린샷 2022-04-06 12 41 33" src="https://user-images.githubusercontent.com/88477839/161891675-85accc93-2ff2-46b2-b1f2-a72cc471cf14.png">

+ GoodsController.java 작성

  <img width="1174" alt="스크린샷 2022-04-06 12 41 46" src="https://user-images.githubusercontent.com/88477839/161891753-5c2d708d-bb9e-4839-971c-08916f1dc939.png">

  

+ 실행결과

  <img width="888" alt="스크린샷 2022-04-06 12 47 51" src="https://user-images.githubusercontent.com/88477839/161891965-87d634d2-9c6e-46ac-b81a-eac08e4f6591.png">

  

  
