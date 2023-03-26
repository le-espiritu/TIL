package kr.or.bbs.BBSSpring.service;

import java.util.List;

import kr.or.bbs.BBSSpring.dto.Bbs;

public interface BbsService {
	public static final Integer LIMIT=10;
	public List<Bbs> getBbs (Integer start);
	public int write(Bbs bbs);
	public Bbs getPostView(int id);
	public int update(int bbsID, String bbsTitle, String bbsContent);
	public int deletePost(int bbsID);
	public int getCount();
}
