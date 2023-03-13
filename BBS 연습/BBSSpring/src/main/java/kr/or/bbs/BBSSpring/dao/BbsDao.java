package kr.or.bbs.BBSSpring.dao;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import kr.or.bbs.BBSSpring.dto.Bbs;

import static kr.or.bbs.BBSSpring.dao.BbsDaoSqls.*;

@Repository // 저장소의 역할을 한다는 스프링 빈(객체) 어노테이션 
public class BbsDao {
	private NamedParameterJdbcTemplate jdbc; // JDBC statement 인자를 ?를 사용하는 대신 파라미터명을 사용하는 JdbcTemplate 
	private SimpleJdbcInsert insertAction; // insert문을 실행하기 위한 객체 
	private RowMapper<Bbs> rowMapper = BeanPropertyRowMapper.newInstance(Bbs.class);
	//RowMapper : 데이터베이스의 반환 결과인 ResultSet를 객체로 변환해주는 클래스 
	//BeanPropertyRowMapper : DB컬럼명과 객체의 속성명의 형태가 달라도 이를 자동으로 매핑시켜준다. 
	
	//생성자
	// @Autowired 어노테이션은 생성자가 한 개만 있을 경우 생략 가능 
	public BbsDao(DataSource dataSource) {
		this.jdbc= new NamedParameterJdbcTemplate(dataSource);
		this.insertAction = new SimpleJdbcInsert(dataSource).withTableName("bbs");
	}
	
	public List<Bbs> selectAll(Integer start, Integer limit){
		Map<String, Integer> params = new HashMap<>();
		params.put("start", start);
		params.put("limit", limit);
		return jdbc.query(SELECT_PAGING, params, rowMapper);
	}
	
	public int selectCount() {
		return jdbc.queryForObject(SELECT_COUNT, Collections.emptyMap(), Integer.class);
	}
}
