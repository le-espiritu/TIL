package com.study.mvcxml2.board;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BoardDaoImpl implements BoardDao {
	
	private SqlSession sqlSession;
	
	@Autowired
	public BoardDaoImpl(SqlSession sqlSession) {
		this.sqlSession=sqlSession;
	}

	@Override
	public int insert(BoardDTO boardDTO) {
		return sqlSession.insert("board.insert", boardDTO);
	}

	@Override
	public int fileInsert(ArrayList<String> fileNameList) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("fileNameList", fileNameList);
		return sqlSession.insert("board.fileinsert", paramMap);
	}

}
