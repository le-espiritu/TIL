package com.study.mvcxml2.practice;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PracticeDaoImpl implements PracticeDao {
	
	private SqlSession sqlSession;
	
	@Autowired
	public PracticeDaoImpl(SqlSession sqlSession) {
		this.sqlSession=sqlSession;
	}
	
	@Override
	public List<Practice> practiceList() {
		return sqlSession.selectList("com.study.mvcxml2.practice.selectall");
	}

	@Override
	public int insert(Practice practice) {
		return 0;
	}

}
