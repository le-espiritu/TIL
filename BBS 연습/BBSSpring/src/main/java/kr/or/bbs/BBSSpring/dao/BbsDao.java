package kr.or.bbs.BBSSpring.dao;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
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
	
	public Bbs getPostView(int id) {
		try {
			Map<String,?> params = Collections.singletonMap("bbsID", id);
			return jdbc.queryForObject(SELECT_BY_BBSID, params, rowMapper); //1건만 셀렉트 할때는 queryForObject 메서드를 사용한다. 
		} catch (Exception e) {
			return null;
		}
		
	}
	
	public int getNextBbsID() {
		try {
			List<Bbs> list = jdbc.query(SELECT_BBSID, Collections.emptyMap(), rowMapper);
			int bbsID = list.get(0).getBbsID();
			return bbsID+1;
		} catch (Exception e) { // 첫번째 게시물인 경우
			return 1;
		}
	}
	
	public int insert(Bbs bbs) {
		try {
			SqlParameterSource params = new BeanPropertySqlParameterSource(bbs);
			return insertAction.execute(params);
		} catch (Exception e) {
			return -1; // 데이터베이스 오류 
		}
	}
	
	public int selectCount() {
		return jdbc.queryForObject(SELECT_COUNT, Collections.emptyMap(), Integer.class);
	}
}
