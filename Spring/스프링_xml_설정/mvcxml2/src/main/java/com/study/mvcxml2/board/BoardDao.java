package com.study.mvcxml2.board;

import java.util.ArrayList;

public interface BoardDao {
	
	public int insert(BoardDTO boardDTO);
	public int fileInsert(ArrayList<String> fileNameList);

}
