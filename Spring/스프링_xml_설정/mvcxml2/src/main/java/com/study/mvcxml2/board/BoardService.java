package com.study.mvcxml2.board;

import java.util.ArrayList;

public interface BoardService {
	
	public int insert(BoardDTO boardDTO, ArrayList<String> fileNameList);

}
