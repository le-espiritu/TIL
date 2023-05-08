package com.study.mvcxml2.user;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {
	
	private SqlSession sqlSession;
	
	@Autowired
	public UserDaoImpl(SqlSession sqlSession) {
		this.sqlSession=sqlSession;
	}

	@Override
	public UserDTO getUserByEmail(String email) {
		return sqlSession.selectOne("com.study.mvcxml2.user.selectbyemail", email);
	}

	@Override
	public int addUser(UserDTO userDTO) {
		try {
			return sqlSession.insert("com.study.mvcxml2.user.insert", userDTO);
		} catch (Exception e) {
			return -1;
		}
	}

}
