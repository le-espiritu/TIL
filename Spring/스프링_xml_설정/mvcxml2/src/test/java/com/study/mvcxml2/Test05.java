package com.study.mvcxml2;

import org.junit.Test;

// 본 클래스는 스프링 mvc 프로젝트에서 테스트케이스를 작성할 때 
// 스프링 빈 컨테이너에서 관리하는 빈 객체를 테스트하기 위해
// 기존에 만들어진 모듈과 연동이 필요할 경우 테스트 클래스에 추가적인 설정을 해야하는 것을 연습한 클래스이다.

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.study.mvcxml2.test.TestComponent;

// 스프링 빈 컨테이너에서 관리하는 빈 객체를 테스트하는 방법 


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {
		"file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"
})  //locations = {"classpath:WEB-INF/spring/appServlet/root-context.xml"} 처럼 classPath:도 됨
public class Test05 {

		@Autowired
		private TestComponent component;
		
		@Test
		public void test() {
			component.run();
		}
		
		@Test
		public void test2() {
			component.run2();
		}
		
		@Test
		public void test3() {
			component.run3();
		}
}

/*	Spring MVC Project에서 테스트케이스를 작성할 때 
 * 	기존에 만들어진 모듈(빈 객체)과 연동이 필요할 경우 테스트 클래스에 추가적인 설정을 해야한다.
 * 
 * 	기존에는 테스트할 객체를 @Before가 붙은 메소드에서 초기화 하였다.
 * 	그러나 스프링 빈 컨테이너를 사용할 때는 개발자가 직접 인스턴스를 생성하면 안된다.
 * 
 * 	스프링 빈 컨테이너가 빈을 생성하고 관리하도록 한다음에 그 빈을 테스트 해야 한다.	
 *	본 테스트 클래스는 빈 테스트와 관련한 설정 코드가 적혀있다.
 * 
 * @RunWith은 JUnit 프레임워크에 테스트 실행기를 추가할 때 사용하는 애노테이션이다
 * 
 * 	JUnit은 확장기능을 가지는데, 스프링에서는 JUnit을 확장하도록 SpringJUnit4ClassRunner.class를 제공합니다.
 * 	해당 코드는 JUnit이 테스트 코드를 실행할 때 스프링 빈 컨테이너가 내부적으로 생성되도록 합니다.
 * 
 *  @WebAppConfiguration는 실제 프로젝트의 web.xml이 아닌 가상의 web.xml을 사용하기 위한 설정이다.
 *  DispatcherServlet을 가상의 web.xml에 등록하여 사용할 수 있도록 구성하기 때문에 mvc관련된 설정들이 정상적으로 작동한다.
 *  (이 예제에서는 MVC나 컨트롤러에 관한 테스트가 아니기 때문에 딱히 필요 없을 듯 하다.) 
 *  
 *  @ContextConfiguration( )은 
 *  내부적으로 생성된 스프링 빈 컨테이너가 사용할 설정파일을 지정할 때 사용합니다
 *  
 *  @RunWith(SpringJUnit4ClassRunner.class),
 *  @ContextConfiguration 이 두 어노테이션이 테스트 클래스 위에 있으면,
 *  테스트 클래스 자체가 빈 객체가 되어 스프링에서 관리되게 된다.
 *  
 *  그리고 @Autowired를 통해 스프링 빈 컨테이너가
 *  테스트할 빈 클래스를 주입하게 된다.
 *   */
