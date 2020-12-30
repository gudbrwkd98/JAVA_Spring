package com.koreait.mylegacy.model.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.koreait.mylegacy.exception.RegistException;
import com.koreait.mylegacy.model.domain.Dept;
import com.koreait.mylegacy.model.domain.Emp;
import com.koreait.mylegacy.mybatis.config.MybatisConfigManager;

@Repository
public class MybatisDeptDAO {
	private SqlSession sqlSession = null;
	
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

 

	//1건 등록
	public int insert(Dept dept) {
		int result = 0 ;
		result = sqlSession.insert("Dept.insert",dept); 
		if(result == 0) {
			//자바에서는 에러를 억지로 발생시켜주는 기능이지원
			throw new RegistException("부서등록에 실패하였습니다");
		}
		return result;
	}
}
