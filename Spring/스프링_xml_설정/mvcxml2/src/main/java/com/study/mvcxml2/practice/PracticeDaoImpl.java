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
	public List<PracticeDTO> practiceList() {
		return sqlSession.selectList("com.study.mvcxml2.practice.selectall");
	}

	@Override
	public int insert(PracticeDTO practice) {
		return sqlSession.insert("com.study.mvcxml2.practice.insert", practice);
	}

}
