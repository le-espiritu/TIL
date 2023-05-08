package com.study.mvcxml2.user;

import java.sql.Connection;

import javax.sql.DataSource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"file:src/main/webapp/WEB-INF/spring/root-context.xml",
})  //locations = {"classpath:WEB-INF/spring/appServlet/root-context.xml"} 처럼 classPath:도 됨
public class UserDaoTest {

	@Autowired
	DataSource dataSource;
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	UserRoleDao userRoleDao;
	
	@Test
	public void configTest() throws Exception{
		// 아무 작업도 하지 않는다. 실행이 잘된다는 것은 Spring 설정이 잘 되어 있다는 것을 의미한다.
	}
	
	@Test
	public void connectionTest() throws Exception{
		Connection connection = dataSource.getConnection();
		Assert.assertNotNull(connection);
	}
	
	@Test
	public void getUser() throws Exception{
		UserDTO userDTO = userDao.getUserByEmail("carami@example.com");
		Assert.assertNotNull(userDTO);
		Assert.assertEquals("강경미", userDTO.getName());
	}
}
