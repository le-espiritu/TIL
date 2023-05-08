package com.study.mvcxml2.board;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BoardServiceImpl implements BoardService {
	
	private BoardDao boardDao;
	
	@Autowired
	public BoardServiceImpl(BoardDao boardDao) {
		this.boardDao=boardDao;
	}

	@Transactional
	@Override
	public int insert(BoardDTO boardDTO, ArrayList<String> fileNameList) {
		boardDao.insert(boardDTO);
		boardDao.fileInsert(fileNameList);
		return 0;
	}

}
