package kr.or.bbs.BBSSpring.dao;

import java.util.Collections;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import kr.or.bbs.BBSSpring.dto.User;

import static kr.or.bbs.BBSSpring.dao.UserDaoSqls.*;

@Repository // 저장소의 역할을 한다는 스프링 빈(객체) 어노테이션 
public class UserDao {
	//NamedParameterJdbcTemplate는 JdbcTemplate(jdbc 중요한 역할을 다 하는 클래스) 에서 
	//JDBC statement 인자를 ?를 사용하는 대신 파라미터명을 사용하여 작성하는 것을 지원
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<User> rowMapper = BeanPropertyRowMapper.newInstance(User.class);
	//RowMapper : select 한건, 한건의 결과를 DTO에 저장하는 목적으로 사용
	//RowMapper : 데이터베이스의 반환 결과인 ResultSet를 객체로 변환해주는 클래스 
	//BeanPropertyRowMapper : DB컬럼명과 객체의 속성명의 형태가 달라도 이를 자동으로 매핑시켜준다.
	// 예를 들어 DB컬럼명이 user_id(snake_case) 이고, 객체의 속성명이 userID(camelCase) 라면 이 둘을 자동으로 매핑시켜준다.
	//만약 위 경우 처럼 자동변환을 할 수 없다면 RowMapper를 직접 구현한다. (RowMapper는 인터페이스여서 구현해줘야 한다.)
	
	private SimpleJdbcInsert insertAction;
	// insert문을 실행하기 위해서는 SimpleJdbcInsert 객체가 필요하다. 
	
	
	//생성자
	// @Autowired 어노테이션은 생성자가 한 개만 있을 경우 생략 가능 
	public UserDao(DataSource dataSource) { // DataSource는 DBconfig에서 @Bean으로 등록
		this.jdbc= new NamedParameterJdbcTemplate(dataSource);
		this.insertAction = new SimpleJdbcInsert(dataSource).withTableName("user");
		// .withTableName("tableName") - 어떤 테이블에 insert 할 것인지 명시하는 것. 
		System.out.println("연결 완료");
	}
	
	public int login(String userID, String userPassword) {
		try {
			Map<String,?> params = Collections.singletonMap("userID", userID);
			User rs = jdbc.queryForObject(LOGIN, params, rowMapper);
			System.out.println("row Mapper 쿼리 코드 실행됨");
			//if(rs!=null) { EmptyResultDataAccessException 때문에 null 체크가 안됨 
			// queryForObject의 결과가 없다면 바로 EmptyResultDataAccessException으로 넘어감 
			if(rs.getUserPassword().equals(userPassword)) {
				return 1;
			}else {
				return 0; // 비밀번호가 일치 하지 않음 
			}
			
		}catch(EmptyResultDataAccessException e) {
			return -1; // 아이디가 존재하지 않음 
		}
	}
	
	//============= queryForObject 메서드의 리턴 타입을 String으로 하고 싶은 경우 코드 =====================
	public int login2(String userID, String userPassword) {
		
		try {
			Map<String,?> params = Collections.singletonMap("userID", userID);
			String rsPassword = jdbc.queryForObject(LOGIN, params, String.class);
			
			if(rsPassword.equals(userPassword)) {
				return 1;
			}else {
				return 0;
			}
		}catch(EmptyResultDataAccessException e) {
			return -1; // 아이디가 존재하지 않음 
		}
	}
	//==============================================================================================
	
	
	public int join(User user) {
		try {
			SqlParameterSource params = new BeanPropertySqlParameterSource(user); // user 객체에 있는 값을 맵으로 바꿔줌
			return insertAction.execute(params);
		} catch (Exception e) {
			return -1; // 데이터베이스 오류 (아이디 중복)
		}
	}

}
