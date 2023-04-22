package com.study.mvcxml2.practice;

import java.util.List;

public interface PracticeDao {
	List<Practice> practiceList();
	int insert(Practice practice);

}
