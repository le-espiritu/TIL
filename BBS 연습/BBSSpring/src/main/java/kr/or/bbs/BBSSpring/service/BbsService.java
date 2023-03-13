package kr.or.bbs.BBSSpring.service;

import java.util.List;

import kr.or.bbs.BBSSpring.dto.Bbs;

public interface BbsService {
	public static final Integer LIMIT=10;
	public List<Bbs> getBbs (Integer start);
	public int getCount();
}
