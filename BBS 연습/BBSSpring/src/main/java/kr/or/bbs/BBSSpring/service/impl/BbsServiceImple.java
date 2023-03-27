package kr.or.bbs.BBSSpring.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.bbs.BBSSpring.dao.BbsDao;
import kr.or.bbs.BBSSpring.dto.Bbs;
import kr.or.bbs.BBSSpring.service.BbsService;

@Service
public class BbsServiceImple implements BbsService {
	
	BbsDao bbsDao;
	
	@Autowired
	public BbsServiceImple(BbsDao bbsDao) {
		this.bbsDao=bbsDao;
	}
	
	@Override
	@Transactional //Transactional이라는 어노테이션이 붙은 메서드는 코드 실행이 트랙잭셔널하게 처리된다.
	public List<Bbs> getBbs(Integer start) {
		List<Bbs> list = bbsDao.selectAll(start, BbsService.LIMIT);
		return list;
	}

	@Override
	public int getCount() {
		return bbsDao.selectCount();
	}

	@Override
	@Transactional(readOnly=false)
	public int write(Bbs bbs) {
		int nextBbsID = bbsDao.getNextBbsID();
		bbs.setBbsID(nextBbsID);
		
		return bbsDao.insert(bbs);
	}

	@Override
	@Transactional
	public Bbs getPostView(int id) {
		return bbsDao.getPostView(id);
	}

	@Override
	@Transactional(readOnly=false)
	public int update(int bbsID, String bbsTitle, String bbsContent) {
		return bbsDao.update(bbsID, bbsTitle, bbsContent);
	}

	@Override
	@Transactional(readOnly=false)
	public int deletePost(int bbsID) {
		return bbsDao.delete(bbsID);
	}

}
