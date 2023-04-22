package com.study.mvcxml2.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class TestInterceptor extends HandlerInterceptorAdapter {

	private static final Logger logger = LoggerFactory.getLogger(TestInterceptor.class);
	
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		logger.info("prehandle = {} 를 호출했습니다.", handler);
		
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		logger.info("postHandle = {}, {}를 view로 호출합니다.",handler,modelAndView);
		
	}
	

}


/* 
 * 인터셉터란? 
 * 컨트롤러의 핸들러를 호출하기 전과 후에 요청과 응답을 참조하거나 가공할 수 있는 일종의 필터이다.
 * 
 * 사용자 요청에 의해 서버에 들어온 Request객체를
 * 핸들러 (요청한 url에 따라 실행되어야 할 컨트롤러 메서드) 로 도달하기전에 
 * 낚아채서 추가적인 작업을 한후 Request객체를 핸들러로 보낼 수 있도록 해주는 것이
 * 인터셉터이다.
 * 
 * 어떤 핸들러에게 인터셉터를 적용할 것인지 특정 url경로를 등록해줌으로써 정하게 된다. (servlet-context.xml에 등록) 
 * 
 * 인터셉터 적용의 유무 기준이 되는 url을 설정해주게 되면,
 * 스프링에서 일괄적으로 해당 url 경로의 핸들러에 인터셉터를 적용해주게 되고, 
 * 
 * 인터셉터 로직의 누락에 대한 위험이 상당히 줄게 된다.
 * 
 * 만약 해당 url 경로내에서 요청메서드나 어노테이션의 구분이 필요하다면 
 * 		1. 인자로 들어온 handler 를 if(handler instanceof HandlerMethod) 체크해주고,
 * 		2. true이다면, handler를 (HandlerMethod)handler로 형변환해준다.
 * 		3. 그리고 .getMethod().getName()) 를 통해 메서드 유형을 확인하거나
 * 			.getMethodAnnotation(RequestMapping.class)) 로 어노테이션 타입을 확인한다.
 * */
 
/* 
 * =인터셉터 메소드 분석=
 * 
 * preHandle
 * 		반환 형태
 * 			boolean : 요청의 통과(continue) 여부를 반환할 수 있다. true 반환 시 통과 , false 반환 시 차단된다.
 * 		매개 변수
 * 			HttpServletRequest : 요청 정보를 가지고 있는 객체이다.
 * 			HttpServletResponse : 응답 정보를 가지고 있는 객체이다.
 * 			Object : 실행될 대상의 정보를 가지고 있는 객체이다.(일반적으로 컨트롤러의 매핑 메소드)
 * 
 * postHandle
 * 		반환 형태
 * 			void : 이 메소드는 어떠한 결과도 전달할 수 없다.
 * 		매개 변수
 * 			HttpServletRequest : 요청 정보를 가지고 있는 객체이다.
 * 			HttpServletResponse : 응답 정보를 가지고 있는 객체이다.
 * 			Object : 실행된 대상의 정보를 가지고 있는 객체이다.(일반적으로 컨트롤러의 매핑 메소드)
 * 			ModelAndView : 처리 완료 후의 Model과 View의 정보를 가진 객체이다.
 *  */
 
/* 
 * 인터셉터 등록 
 * 
 * 인터셉터는 요청에 대한 처리와 연관되어 있으므로 servlet-context.xml에 등록한다.
 * 
 * 인터셉터를 등록하기 위한 코드들에 대한 설명은 다음과 같다.
 * 		<interceptors> : spring-mvc 태그. 인터셉터들을 등록하기 위한 영역
 * 		<interceptor> : spring-mvc 태그. 인터셉터 1개를 등록하기 위한 영역
 * 		<mapping> : spring-mvc 태그. 인터셉터가 간섭할 URL 패턴을 지정(spring-expression으로 설정)
 * 		<exclude-mapping> : spring-mvc 태그. 인터셉터가 간섭하지 말아야할 URL 패턴을 지정(mapping에서 제외)
 * 		<beans:bean> : spring-beans 태그. 인터셉터로 등록할 bean 설정
 * */
