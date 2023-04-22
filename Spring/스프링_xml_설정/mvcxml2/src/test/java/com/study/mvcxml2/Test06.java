package com.study.mvcxml2;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.study.mvcxml2.test.TestController;

public class Test06 {
	
	MockMvc mockMvc; // MockMvc 를 이용하면 컨트롤러를 테스트 할 수 있다.
	
	@Before // @Before는 테스트 메소드가 실행되기 전에 실행되는 어노테이션이며, 테스트 메소드가 실행될때마다 실행된다.
	public void prepare() {
		mockMvc = MockMvcBuilders.standaloneSetup(new TestController()).build();
	}
	
	@Test
	public void test() throws Exception{
		mockMvc.perform(get("/test1")).andDo(print())
									.andExpect(status().is2xxSuccessful())
									.andReturn();
	}
}

/* 
 * MockMvc 를 이용하면 컨트롤러를 테스트 할 수 있다.
 * Spring Bean들은 Test05.java에서와 같은 방법으로 모두 테스트 가능하다.
 * 그러나 컨트롤러는 요청이 있어야 하고, 주입할 수 있는 객체(어노테이션)이 아니기 때문에 테스트가 불가하다.
 * 하지만 MockMvc 객체를 이용하면 가상의 요청을 만들고 그에 따른 실행 결과를 테스트 할 수 있다. */

/* MockMvc 명령 정리 
 * .perform() : 요청을 수행하고 추가 조치를 수행할 수 있는 ResultActions 데이터 반환
 * .andDo() : 수행할 일반적인 행동을 설정합니다.
 * .andExpect() : 성공 상황을 설정(단정)합니다.
 * .andReturn() : 결과에 대한 정보들을 MvcResult 형태로 반환*/
