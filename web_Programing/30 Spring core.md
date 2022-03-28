# Spring Core



## Spring이란?

### Spring Framework란?

+ Framework
  + 이미 중요한 부분, 어려운 부분, 복잡한 부분등이 다 구현 되어 있는, 잘 만들어진 반제품 같은것

+ 엔터프라이즈 급 어플리케이션(굉장히 큰 애플리케이션)을 구축할 수 있는 가벼운 솔루션이자, 원스-스탑-숍(One-Stop-Shop)
  + 원스-스톱-숍은 모든 과정을 한꺼번에 해결하는 상점, 이런 것을 이야기함

+ 원하는 부분만 가져다 사용할 수 있도록 모듈화가 잘 되어 있다.
+ IoC 컨테이너이다.
+ 선언적으로 트랜잭션을 관리할 수 있다.
+ 완전한 기능을 갖춘 MVC Framework를 제공한다.
+ AOP지원
+ 스프링은 도메인 논리코드와 쉽게 분리될 수 있는 구조를 가지고 있다.



### 프레임 워크 모듈

![2_10_1___](https://user-images.githubusercontent.com/88477839/160264423-22aca0d1-f579-4d6c-8983-0c84295914c1.png)

+ 스프링 프레임워크는 약 20개의 모듈로 구성되어 있다.
+ 필요한 모듈만 가져다 사용할 수 있다.



### AOP와 인스트루멘테이션(Instrumentation)

+ spring-AOP : AOP 얼라이언스(Aliance)와 호환되는 방법으로 AOP를 지원한다.
+ spring-aspects : AspectJ와의 통합을 제공한다.
+ spring-instrument : 인스트루멘테이션을 지원하는 클래스와 특정 WAS에서 사용하는 클래스로더 구현체를 제공한다. 참고로 BCI(Byte Code Instrumentations)은 런타임이나 로드(Load)때 클래스의 바이트 코드에 변경을 가하는 방법을 말한다.



### 메시징(Messaging)

+ Spring-messaging : 스프링 프레임워크 4는 메시지 기반 어플리케이션을 작성할 수 있는 Message, MessageChannel, MessageHandler 등을 제공한다. 또한, 해당 모듈에는 메소드에 메시지를 맵핑하기 위한 어노테이션도 포함되어 있으며, Spring MVC 어노테이션과 유사하다.



### 데이터 엑세스(Data Access) / 통합(Integration)

+ 데이터 엑세스/통합 계층은 JDBC, ORM, OXM, JMS 및 트랜잭션 모듈로 구성되어 있다.
+ spring-jdbc: 자바 JDBC프로그래밍을 쉽게 할 수 있도록 기능을 제공한다.
+ spring-tx : 선언적 트랜잭션 관리를 할 수 있는 기능을 제공한다.
+ spring-orm : JPA, JDO및 Hibernate를 포함한 ORM API를 위한 통합 레이어를 제공한다.
+ spring-oxm : JAXB, Castor, XMLBeans, JiBX및 XStream과 같은 Object/XML 매핑을 지원한다.
+ spring-jms : 메시지 생성(producing) 및 사용(consuming)을 위한 기능을 제공. Spring Framework 4.1부터 spring-messaging모듈과의 통합을 제공한다.



### 웹(Web)

+ 웹 계층은 spring-web, spring-webmvc, spring-websocket, spring-webmvc-portlet 모듈로 구성된다.
+ spring-web : 멀티 파트 파일 업로드, 서블릿 리스너 등 웹 지향 통합 기능을 제공한다. HTTP클라이언트와 Spring의 원격 지원을 위한 웹 관련 부분을 제공한다.
+ spring-webmvc : Web-Servlet 모듈이라고도 불리며, Spring MVC및 REST 웹 서비스 구현을 포함한다.
+ spring-websocket: 웹 소켓을 지원한다.
+ spring-webmvc-portlet : 포틀릿 환경에서 사용할 MVC 구현을 제공한다.



## Spring IoC/ DI 컨테이너



### 컨테이너(Container)란?

+ 컨테이너는 인스턴스의 생명주기를 관리한다.
+ 생성된 인스턴스들에게 추가적인 기능을 제공한다.
+ Servlet을 실행시켜주는 톰캣(WAS)는 Servlet 컨테이너를 가지고 있다.
  + 서블릿 클래스를 정의하긴 하지만 서블릿 클래스를 인스턴스화하는 과정을 직접 하지는 않았다. 이러한 과정을 WAS가 대신 해준다.
    + WAS는 웹브라우저로부터 서블릿 URL에 해당하는 요청을 받으면 서블릿을 메모리에 올린 후에 실행하게 된다.
    + 즉 개발자가 서블릿 클래스를 작성했지만 실제로 메모리에 올리고 실행하는 것은 WAS가 가지고 있는 서블릿 컨테이너가 해준다.
    + 서블릿 컨테이너는 동일한 서블릿에 해당하는 요청을 받으면 또 메모리에 올리지 않고 기존에 메모리에 올라간 서블릿을 실행해서 그 결과를 웹브라우저에게 전달한다.



## IoC란?

+ IoC란 Inversion of Control의 약어이다.
  + inversion은 사전적 의미로는 '도치, 역전'이다.
  + 보통 IoC를 제어의 역전이라고 번역한다.
+ 개발자는 프로그램의 흐름을 제어하는 코드를 작성한다. 
  + 그런데, 이 흐름의 제어를 개발자가 하는 것이 아니라 다른 프로그램이 그 흐름을 제어하는 것을 IoC라고 말한다.



### DI 란?

+ DI는 Dependency Injection의 약자로, 의조선 주입이란 뜻을 가지고 있다.
+ DI는 클래스 사이의 의존 관계를 빈(Bean)설정 정보를 바탕으로 컨테이너가 자동으로 연결해주는 것을 말한다.



### DI 예

+ DI가 적용 안된 예

+ 개발자가 직접 인스턴스를 생성한다.

  ~~~java
  class 엔진{
    
  }
  
  class 자동차{
    엔진 v5 = new 엔진();
  }
  ~~~



+ Spring에서 DI가 적용된 예.

+ 엔진type의 v5변수에 아직 인스턴스가 할당되지 않았다.

+ 컨테이너가 v5변수에 인스턴스를 할당해주게 된다.

  ~~~java
  @Component
  class 엔진{
    
  }
  
  @Component
  class 자동차{
    @Autowired
    엔진 v5;
  }
  ~~~

  ![3 7 2-2](https://user-images.githubusercontent.com/88477839/160265809-fe946f4b-caba-41e0-9df3-050c5f83284a.jpg)

  

### Spring에서 제공하는 IoC/DI 컨테이너

+ BeanFactory : IoC/DI에 대한 기본 기능을 가지고 있다.
  + 스프링에서는 객체를 bean이라고 부른다.
+ ApplicationContext : BeanFactory의 모든 기능을 포함하며, 일반적으로 BeanFactory보다 추천된다. 트랜잭션처리, AOP등에 대한 처리를 할 수 있다. BeanPostProcessor, BeanFactoryPostProcessor등을 자동으로 등록하고, 국제화 처리, 어플리케이션 이벤트 등을 처리할 수 있다.



## xml파일을 이용한 설정

> Spring IoC / DI 컨테이너에 대한 동작을 확인하기 위해서 Maven을 이용해 프로젝트를 생성한 후, XML 형식의 설정 파일을 만들어 IoC와 DI가 잘 동작하는직 확인해 보도록 한다.



### 프로젝트 생성

![2_10_3_01 (1)](https://user-images.githubusercontent.com/88477839/160266712-1d397ada-d31b-4726-9f3c-a79777c3eae3.png)

<img width="617" alt="2_10_3_02" src="https://user-images.githubusercontent.com/88477839/160266722-03bf9044-1da2-4d4f-a83a-d9be3ebba0bd.png">



<img width="614" alt="2_10_3_03" src="https://user-images.githubusercontent.com/88477839/160266738-97f90490-3880-4b61-b2a5-edf85f58482a.png">

+ Archetype은 Maven 프로젝트에서 제공해주는 템플릿을 이야기한다.



<img width="612" alt="2_10_3_04" src="https://user-images.githubusercontent.com/88477839/160266753-6a342a90-77dd-4cfd-b5c7-178324069031.png">

+ Artifact Id - 프로젝트 이름



~~~xml
<build>
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
  </build>
~~~

+ Pom.xml에 jdk를 사용하기 위한 플러그인 설정을 추가한다.
+ 그리고 나서 프로젝트 우클릭 - Maven - Update Project 눌러준다.



### Bean class생성

<img width="771" alt="스크린샷 2022-03-27 13 53 44" src="https://user-images.githubusercontent.com/88477839/160267235-44bbf27f-a160-43f4-abb9-4b03d2a36e47.png">

+ Bean class란? - 일반적인 java클래스를 bean 클래스라고 보통 말한다.
+ Bean 클래스의 특징
  + 기본생성자를 가지고 있다.
  + 필드는 private하게 선언한다.
  + getter, setter 메소드를 가진다.
  + getName() setName() 메소드를 name 프로퍼티(property)라고 한다.



~~~java
// UserBean.java

package kr.or.connect.diexam01;

public class UserBean {
	
//	기본생성자를 가지고 있다.
//	필드는 private하게 선언한다.
//	getter, setter 메소드를 가진다.
//	getName() setName() 메소드를 name 프로퍼티(property)라고 한다.

	private String name;
	private int age;
	private boolean male;
	
	public UserBean() {}
	public UserBean(String name, int age, boolean male) {
		this.name = name;
		this.age = age;
		this.male = male;
	}
	
  // setter, getter메소드는 프로퍼티라고 한다.
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public boolean isMale() {
		return male;
	}
	public void setMale(boolean male) {
		this.male = male;
	}

}
~~~

+ 필드에 접근해서 일을 해야하는데 필드들이 모두 private 하다.
  + 객체지향 프로그래밍에서 이 객체의 속성에 직접 접근하는 것은 별로 바람직하지 않다.
  + 그래서 항상 메서드를 통해서 접근을 하는게 좋다. 



### Spring 라이브러리 추가

~~~xml
<spring.version> 4.3.14.RELEASE</spring.version>
~~~

+ pom.xml의 porperties에 위 설정을 추가함
+ porperties에 들어있는 것들은 상수처럼 사용할 수 있는 것들이다.
+ dependency나 pom.xml안에서 해당 값(4.3.14.RELEASE)들이 필요한 경우가 있다.
+ 그랬을 때 값 자체(4.3.14.RELEASE)를 그냥 사용하는게 아니라 \<spring.version>으로 상수로 사용할 수 있도록 도와준다.

~~~xml
<!-- Spring -->
	<dependency>
		<groupId>org.springframework</groupId>
		<artifactId>spring-context</artifactId>
		<version>${spring.version}</version> 
	</dependency>
~~~

+ ${spring.version} 는 \<spring.version> 을 의미함
  + 이렇게 사용하는 이유는 스프링 버전을 바꾸고 싶을때 버전이 명시된 부분마다 일일이 찾아가면서 버전을 바꾸지 않고, <spring.version> 4.3.14.RELEASE</spring.version> 이 한 부분만 수정해주면 되기 때문이다.

<img width="288" alt="2_10_3_12" src="https://user-images.githubusercontent.com/88477839/160267639-8bfa346a-80e9-4f63-8f74-46b7da7fafd2.png">

+ 저장 후 Maven Dependencies를 확인하면 스프링과 관련된 라이브러리들이 추가된 것을 확인 할 수 있다.



### Spring Bean Factory를 이용하여 Bean객체 이용하기



<img width="604" alt="2_10_3_13" src="https://user-images.githubusercontent.com/88477839/160267766-869f718a-5802-4124-ad55-1c5d3876cefb.png">

<img width="845" alt="스크린샷 2022-03-27 14 14 29" src="https://user-images.githubusercontent.com/88477839/160267782-dab4b809-c719-4d03-bab6-a053b5c7a999.png">

+ 설정 파일 관리를 위해 resources 폴더를 하나 만든다.

<img width="780" alt="스크린샷 2022-03-27 14 19 48" src="https://user-images.githubusercontent.com/88477839/160267911-1caa79f9-c176-467b-a192-801299b61e2e.png">

+ resources 폴더에 new file을 선택해서 applicationContext.xml 파일을 만든다.

~~~xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

  <bean id="userBean" class="kr.or.connect.diexam01.UserBean"></bean>
  
</beans>
~~~

+ applicationContext.xml 파일에 위 코드를 추가한다.

  + \<?xml version="1.0" encoding="UTF-8"?>
    + 나 xml이고 버전은 뭐고, 캐릭터 셋은 뭘쓸거야 하는 정보를 알려주는 부분. 반드시 있어야함

  + \<beans>\</beans>
    + 루트 element는 beans라는 element임
    + xml파일로 스프링 설정파일을 만들게 되면 가장 바깥쪽 태그를 루트 element라고 하는데 이것이 반드시 beans로 되어 있어야 함



+ 스프링 컨테이너에게 내가 사용할 객체를 대신 생성하게 하고 싶은 경우에는 스프링 컨테이너에게 정보를 줘야 한다.

  + 이를 위해 사용되는 엘리먼트가 \<bean> 이라는 엘리먼트다.

  + ~~~xml
    <bean id="userBean" class="kr.or.connect.diexam01.UserBean"></bean>
    ~~~

  + 위 코드는 kr.or.connect.diexam01.UserBean userBean = new kr.or.connect.diexam01.UserBean(); 와 같은 의미다.

  + 스프링 컨테이너는 이런 객체를 하나만 생성해서 가지고 있다.

    + 이렇게 객체를 딱 하나만 가지고 있는 것을 싱글턴 패턴이라고 한다.



<img width="800" alt="스크린샷 2022-03-27 14 37 44" src="https://user-images.githubusercontent.com/88477839/160268327-42903e46-c850-4376-835b-465b75514159.png">

+ 작성한 Spring 설정 파일을 읽어들이는 객체도 하나 생성하기 위해 클래스를 하나 생성한다.



<img width="1104" alt="스크린샷 2022-03-27 15 08 52" src="https://user-images.githubusercontent.com/88477839/160269070-e6134695-9ddd-4b6f-b2d1-2d84f1e9902c.png">

~~~java
package kr.or.connect.diexam01;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationContextExam01 {

	public static void main(String[] args) {
		
		// V 스프링이 가진 공장을 만드는 코드 
		// 빈정보가 담긴 패스를 알려줘서 => applicationContext.xml // 이 정보를 읽어서 공장을 세워요 하고 알려주는 것 
    // ApplicationContext는 인터페이스다. => ApplicationContext를 구현하는 다양한 컨테이너가 존재할것이다.
		ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		System.out.println("초기화 완료 ");
		
		// 공장의 getBean()메서드를 이용해서 객체를 얻어온다.
		// getBea()의 리턴타입은 오브젝트이기 때문에 (UserBean)으로 형변환 해준다. 
    // 리턴타입이 오브젝트인 이유는 스프링이 제공하는 공장이 만들어 내는 객체는 다양할 것이기 때문이다.
		UserBean userBean = (UserBean)ac.getBean("userBean");
		userBean.setName("kang");
		
		System.out.println(userBean.getName());
    
    UserBean userBean2 = (UserBean)ac.getBean("userBean");
		
		if(userBean == userBean2) {
			System.out.println("같은 인스턴스입니다.");
		}
		
	}

}

~~~

+ 위 코드를 작성후 저장한다음 실행시켜준다.
+ 만약 실행시켰을때 filenotfoundexception 에러가 생겼다면 초반에 만들었던 resources 폴더를 인식하지 못했기 때문이다.
+ 프로젝트 우클릭 - 프로퍼티즈 - 자바 빌드 패스 - source 탭으로 이동한다. 여기서 만약 resources 폴더가 보이지 않는다면 우측의 Add Folder를 눌러 resources 폴더를 추가하고 어플라이 해준다.
  + 참고 - https://developer-joe.tistory.com/225
+ 위 코드를 실행시켰을때 같은 인스턴스라고 출력이 된다
  + 사용자가 계속 getBean()을 요청한다고 하더라도 그 객체를 계속 만들어내는 게 아니라 하나 만든 bean을 이용하기 때문
  +  Spring ApplicationContext가 객체를 생성하는데 싱글턴 패턴을 이용한다는 말이 이 의미이다.

+ 이렇게 객체를 대신 생성해주고 싱글턴으로 관리해주는 기능등을 IoC 제어의 역전이라고 한다.





## DI  확인하기



~~~java
//Engine.java

package kr.or.connect.diexam01;

public class Engine {
	public Engine() {
		System.out.println("Engine 생성");
	}
	
	public void exec() {
		System.out.println("엔진이 동작합니다.");
	}

}
~~~

~~~java
// Car.java

package kr.or.connect.diexam01;

public class Car {
	private Engine v8;
	
	public Car() {
		System.out.println("Car 생성");
	}
	
	public void setEngine(Engine e) {
		this.v8 = e;
	}
	
	public void run() {
		System.out.println("엔진을 이용하여 달립니다.");
		v8.exec();
	}
	
//	public static void main(String[] args) {
//		Engine e = new Engine(); // 이 부분을 IoC로 구현해야 함 
//		Car c = new Car(); // 이 부분을 IoC로 구현해야 함 
//		c.setEngine(e);
//		c.run();
//	}
}
~~~



+ 위 main 메서드 과정을 Spring IoC 컨테이너가 만들게 해야한다.

  ~~~xml
  <!--applicationContext.xml-->
  
  <bean id="e" class="kr.or.connect.diexam01.Engine"/>
  <bean id="c" class="kr.or.connect.diexam01.Car"/>
  
  <!-- Engine e = new Engine(); 와 동일--> 
  <!-- Car c = new Car(); 와 동일-->
  ~~~

  ~~~xml
  <bean id="c" class="kr.or.connect.diexam01.Car">
  	<property name="engine" ref="e"></property>
  </bean>
  
  <!-- c.setEngine(e); 와 동일 -->
  ~~~

  + property는 getter(), setter()를 의미함

  + property name="engine" : 프로퍼티 네임은 "engine"으로 설정
  + ref="e" : 생성된 bean인 e를 사용(참고)하겠다.
    + 인스턴스 Engine e를 setEngine() 메서드에 파라미터로 전달하겠다 라는 의미를 가짐
  + 즉, name이 engine인 property는 setEngine 또는 getEngine 메서드를 의미한다.
    + 근데 bean태그 안에서는 모두 값을 설정하는 것이다.
    + 그렇기 때문에 setEngine()이라는 메서드를 의미한다.

  ~~~xml
  <!--applicationContext.xml-->
  
  <?xml version="1.0" encoding="UTF-8"?>
  <beans xmlns="http://www.springframework.org/schema/beans"
  	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
  
  	<bean id="userBean" class="kr.or.connect.diexam01.UserBean"></bean>
  	<bean id="e" class="kr.or.connect.diexam01.Engine"/>
  	<bean id="c" class="kr.or.connect.diexam01.Car">
  		<property name="engine" ref="e"></property>
  	</bean>
  
  </beans>
  ~~~

  

+ 위의 설정 파일을 읽어들여 실행하는 ApplicationContextExam02.java를 작성

  ~~~java
  //ApplicationContextExam02.java
  
  package kr.or.connect.diexam01;
  
  import org.springframework.context.ApplicationContext;
  import org.springframework.context.support.ClassPathXmlApplicationContext;
  
  public class ApplicationContextExam02 {
  	public static void main(String[] args) {
  		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
  		
  		Car car = (Car) ac.getBean("c");
  		car.run();
  	}
  }
  ~~~



+ 어떤 객체에게 객체를 주입하는 것을 DI라고 한다.



## Java config를 이용한 설정

> Java Config와 어노테이션을 이용해 스프링에서 사용하는 빈을 정의하고 DI하는 방법에 대해 알아보자



### 어노테이션

+ @를 붙여서 시작한다.
+ 어노테이션은 jdk 5부터 지원이 되기 시작했다.( 이보다 낮은 버전에서는 사용할 수 없다.)
+ 사전적인 의미는 주석이지만 자바 어노테이션은 특수한 의미를 부여하는 역할을 수행한다.
  + 이런 특수한 의미는 컴파일 시에, 혹은 런타임 시에 해석 될 수 있다.
+ 스프링은 설정을 위해서 다양한 어노테이션을 제공한다.



### Java config를 이용한 설정을 위한 어노테이션

~~~java
// ApplicationConfig.java

package kr.or.connect.diexam01;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {
	@Bean
	public Car car(Engine e) {
		Car c = new Car();
		c.setEngine(e);
		return c;
	}
	
	@Bean
	public Engine engine() {
		return new Engine();
	}
}

~~~

+ @Configuration
  + 스프링 설정 클래스를 선언하는 어노테이션
  + 클래스 위에 위치해야 한다.
  + 나는 config 파일이에요 하고 알려주는 일을 한다.
+ Bean
  + bean을 정의하는 어노테이션



~~~java
// ApplicationContextExam03.java

package kr.or.connect.diexam01;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextExam03 {
	public static void main(String[] args) {
		ApplicationContext ac = new AnnotationConfigApplicationContext(ApplicationConfig.class);
		
		Car car = (Car) ac.getBean("car");
		car.run();
	}

}
~~~

+ 위 설정 파일을 읽어서 실행시키는  클래스 작성

~~~java
package kr.or.connect.diexam01;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextExam03 {
	public static void main(String[] args) {
		ApplicationContext ac = new AnnotationConfigApplicationContext(ApplicationConfig.class);
		
		Car car = (Car) ac.getBean(Car.class); // 클래스 타입을 파라미터로 넣어줌. 이 경우 bean 이름이 오타가 났어도 오타와 상관없이 bean을 불러 올 수 있다.
		car.run();
	}

}
~~~

+ 이렇게 해도 동일한 결과가 나옴



### 위 코드들을 수정

~~~java
// ApplicationConfig2.java

package kr.or.connect.diexam01;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("kr.or.connect.diexam01")
public class ApplicationConfig2 {

}
~~~

+ @ComponentScan

  + @Controller,@Service,@Repository,@Component 어노테이션이 붙은 클래스를 찾아 컨테이너에 등록	

  + 알아서 여기 있는 것들을 읽어들여서 지정해. 사용자가 일일이 하나하나 알려주지 않아도 어노테이션 붙어있는 것들을 잘 찾아내서 등록해. 이런 의미
  + @ComponentScan 뒤에는 패키지명을 적어준다.
    + 굉장히 광범위하기 때문에 @ComponentScan을 할 정확한 패키지를 알려주는 것임
    + 이 패키지 안에 있는 것들 중에 스캔해와 이런 의미



~~~java
//Car.java

package kr.or.connect.diexam01;

import org.springframework.stereotype.Component;

@Component
public class Car {
	@Autowired
	private Engine v8;
	
	public Car() {
		System.out.println("Car 생성");
	}
	
	public void run() {
		System.out.println("엔진을 이용하여 달립니다.");
		v8.exec();
	}
	
//	public static void main(String[] args) {
//		Engine e = new Engine(); // 이 부분을 IoC로 구현해야 함 
//		Car c = new Car(); // 이 부분을 IcO로 구현해야 함 
//		c.setEngine(e);
//		c.run();
//	}
}
~~~

+ 기존에 있던 Car 클래스에 @Component 어노테이션을 달아준다.

+ @Component

  + 컴포넌트 스캔의 대상이 되는 어노테이션 중 하나로써 주로 유틸, 기타 지원 클래스에 붙이는 어노테이션이다.

+ Car가 갖고있는 setEngine()이라는 메서드를 없앤다.

  + ~~~java
    public void setEngine(Engine e) {
    		this.v8 = e;
    	}
    ~~~

+ 대신 필드인 private Engine v8 위에 @Autowired을 붙인다.

  + ~~~java
    @Autowired
    private Engine v8;
    ~~~

+ @Autowired

  + 주입 대상이 되는 bean을 컨테이너에 찾아 주입하는 어노테이션
  + 이런 객체 (Engine 타입의 객체)가 있으면 알아서 여기다가 주입해 줘 이런 의미.
  + @Autowired가 알아서 해주기 때문에 굳이 setter메서드는 없어도 괜찮다.

~~~java
//Engine.java

package kr.or.connect.diexam01;

import org.springframework.stereotype.Component;

@Component
public class Engine {
	public Engine() {
		System.out.println("Engine 생성");
	}
	
	public void exec() {
		System.out.println("엔진이 동작합니다.");
	}

}
~~~

+ 기존에 있던 Engine 클래스에 @Component 어노테이션을 달아준다.



~~~java
package kr.or.connect.diexam01;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextExam04 {
	public static void main(String[] args) {
		ApplicationContext ac = new AnnotationConfigApplicationContext(ApplicationConfig2.class);
		
		Car car = (Car) ac.getBean(Car.class);
		car.run();
	}

}
~~~

+ 위 어노테이션으로 수정된 코드들을 읽어서 실행시켜주는 객체 생성.
