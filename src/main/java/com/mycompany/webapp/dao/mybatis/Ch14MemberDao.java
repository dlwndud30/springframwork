package com.mycompany.webapp.dao.mybatis;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mycompany.webapp.dto.Ch14Member;

@Repository
public class Ch14MemberDao {
	
	@Autowired private SqlSessionTemplate sqlSessionTemplate;
	
	public int insert(Ch14Member member) {
		int rows =sqlSessionTemplate.insert("com.mycompany.webapp.dao.mybatis.Ch14MemberDao.insert", member);
		return rows;
	}
	
	public Ch14Member selectByMid(String mid) {
		Ch14Member ch14M = sqlSessionTemplate.selectOne("com.mycompany.webapp.dao.mybatis.Ch14MemberDao.selectByMid", mid);
		return ch14M;
	}
}
