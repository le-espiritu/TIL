package com.study.mvcxml2.user;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class UserRoleDaoImpl implements UserRoleDao {
	
	private SqlSession sqlSession;
	
	public UserRoleDaoImpl(SqlSession sqlSession) {
		this.sqlSession=sqlSession;
	}

	@Override
	public List<UserRoleDTO> getRolesByEmail(String email) {
		return sqlSession.selectList("com.study.mvcxml2.userRole.selectroles", email);
	}

	@Override
	public int addUserRole(Long userId) {
		return sqlSession.insert("com.study.mvcxml2.userRole.insertuser", userId);
	}

}
