package com.study.mvcxml2.practice;

import java.util.List;

public interface PracticeService {
	List<PracticeDTO> practiceList();
	int insert(PracticeDTO practice);

}
