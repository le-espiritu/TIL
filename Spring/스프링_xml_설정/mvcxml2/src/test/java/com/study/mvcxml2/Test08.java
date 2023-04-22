package com.study.mvcxml2;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {
		"file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"
})
public class Test08 {
	
	@Autowired
	WebApplicationContext context;
	
	MockMvc mockMvc;
	
	@Before
	public void prepare() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}
	
	@Test
	public void test() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.get("/test2")) // 여기서는 import static 안함 (Test06.java에서는 함)
														.andDo(MockMvcResultHandlers.print())
														.andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
														.andReturn(); 
	}

}

/* MockMvc를 이용한 WebApplication 테스트 
 * 
 * Standalone 방식의 테스트로는 
 * ViewResolver 등 연관된 기능들을 포함한 테스트를 구현하기 어렵다.
 * 
 * ViewResolver등 을 사용하기 위해서는 Spring의 환경과 연동한 테스트를 수행해야 한다.
 * 
 * MockMvc에 환경정보를 설정하기 위해 WebApplicationContext 객체를 연결 생성한다.
 * */
