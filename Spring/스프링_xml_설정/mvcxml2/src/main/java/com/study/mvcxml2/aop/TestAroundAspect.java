package com.study.mvcxml2.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TestAroundAspect {
	
	private static final Logger logger = LoggerFactory.getLogger(TestAroundAspect.class);
	
	@Around("execution(public void com.study.mvcxml2.test.TestComponent.run3())")
	public void aroundAdvice(ProceedingJoinPoint pjp) throws Throwable {
		
		logger.info("================around before=======================");
		
			
		pjp.proceed();
		
		// 비즈니스가 리턴하는 객체가 있을 수 있으므로 Obejct로 받아준다. (리턴타입도 Object로 변경해야함)
		//Object result = pjp.proceed();
			
		
		logger.info("========================around after============================");
	}

}

/* @Aspect 애노테이션을 이용하여 이 클래스는 간섭을 위한 것임을 명시한다.
 * @Component 애노테이션을 이용하여 클래스를 스프링 컨테이너에 등록한다(@Service 등으로 등록해도 무방)
 */

/* 간섭시점 
 * @Before : 간섭 대상 실행 전
 * @After : 간섭 대상 실행 후
 * @AfterReturning : 간섭 대상 실행 성공시
 * @AfterThrowing : 간섭 대상 실행 오류시
 * @Around : 간섭 대상의 실행 전체상황
 * 
 */

//AOP 용어 정리
/* aspect : 구현하고자 하는 보조 기능(부속 기능)을 의미한다. 
 * advice : aspect의 실제 구현체를 의미한다.
 * target : advice가 적용되는 클래스를 의미한다.
 * weaving : advice를 주기능에 적용하는 것을 의미한다.
 * joinpoint란? : advice를 적용하는 지점을 뜻한다.(클라이언트가 호출하는 모든 비즈니스 메소드)
 * PointCut이란? : JoinPoint 중 실제로 advice가 적용되는 지점 및 대상을 정의하는 것 . */

/* 예를 들어, MemberService의 hello()라는 메소드 실행 전,후에 hello랑 bye를 출력하는 일을 한다고 가정해보자. 
 * 이때 MemberService 빈이 타겟, "hello() 메소드 실행 전,후"가 포인트컷, 
 * "메소드 실행 전,후"라는게 조인포인트, "hello랑 bye를 출력하는 일"이 Advice다. 
 * 포인트컷과 조인포인트가 많이 햇갈릴텐데 
 * 조인포인트가 메타적인 정보라고 생각하면 되고 
 * 포인트컷이 좀 더 구체적인 적용 지점이라고 생각하면 된다.*/


/* Pointcut 정의 표현식 종류 
 * 
 * within : 특정 패키지 or 클래스의 모든 메소드를 지정
 * target : 특정 인터페이스와 그의 자식 클래스의 메소드를 지정
 * args : 특정 매개변수 형태를 갖는 모든 메소드를 지정
 * this : 특정 인터페이스를 구현하는 프록시 객체를 지정
 * execution : 표현식으로 형태를 지정하여 간섭
 * 
 */

/* execution 표현식 
 * 
 * 		execution([접근제한]  반환형  패키지경로.클래스명.메소드명(매개변수형태)
 * 
 * execution(public int aaa.bbb.Test.hello())
 * 		public 접근제한을 가지고 int 반환형을 갖는 aaa.bbb패키지의 Test클래스의 매개변수가 없는 hello()를 지정
 * execution(public int aaa.bbb.Test.hello(*))
 * 		public 접근제한을 가지고 int 반환형을 갖는 aaa.bbb패키지의 Test클래스의 매개변수가 1개 이상인 hello()를 지정
 * execution(public int aaa.bbb.Test.hello(..))
 * 		public 접근제한을 가지고 int 반환형을 갖는 aaa.bbb패키지의 Test클래스의 매개변수가 0개 이상인 hello()를 지정
 * execution(public int aaa.bbb.Test.hello(int))
 * 		public 접근제한을 가지고 int 반환형을 갖는 aaa.bbb패키지의 Test클래스의 매개변수가 int 1개인 hello()를 지정
 * execution(public int aaa.bbb.Test.h*(..))
 * 		public 접근제한을 가지고 int 반환형을 갖는 aaa.bbb패키지의 Test클래스의 매개변수가 0개 이상인 h로 시작하는 메소드 지정
 * execution(public int aaa.bbb.Test.*(..))
 * 		public 접근제한을 가지고 int 반환형을 갖는 aaa.bbb패키지의 Test클래스의 모든 메소드 지정
 * execution(public int aaa.bbb.*.*(..))
 * 		public 접근제한을 가지고 int 반환형을 갖는 aaa.bbb 패키지의 모든 클래스의 모든 메소드 지정
 * execution(public int aaa.*.*(..))
 * 		public 접근제한을 가지고 int 반환형을 갖는 aaa 패키지의 모든 클래스의 모든 메소드 지정
 * execution(public int aaa..*.*(..))
 * 		public 접근제한을 가지고 int 반환형을 갖는 aaa 패키지와 그 하위 패키지의 모든 클래스의 모든 메소드 지정
 * execution(public int *.*.*(..))
 * 		public 접근제한을 가지고 int 반환형을 갖는 모든 메소드 지정
 * execution(public * *.*.*(..))
 * 		반환형까지 무관
 * execution(* *.*.*(..))
 * 		접근제한은 안써도 public
 */
