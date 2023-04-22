package com.study.mvcxml2.practice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PracticeServiceImpl implements PracticeService {
	
	private PracticeDao dao;
	
	@Autowired
	public PracticeServiceImpl(PracticeDao dao) {
		this.dao=dao;
	}

	@Override
	public List<Practice> practiceList() {
		return dao.practiceList();
	}

	@Override
	public int insert(Practice practice) {
		return dao.insert(practice);
	}

}
