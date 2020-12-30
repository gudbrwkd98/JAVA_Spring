package com.koreait.mylegacy.model.service;

import java.sql.SQLException;
import java.util.List;import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koreait.mylegacy.exception.RegistException;
import com.koreait.mylegacy.model.dao.MybatisDeptDAO;
import com.koreait.mylegacy.model.dao.MybatisEmpDAO;
import com.koreait.mylegacy.model.domain.Emp;
import com.koreait.mylegacy.mybatis.config.MybatisConfigManager;

@Service
public class MybatisEmpService {
	@Autowired
	private MybatisConfigManager manager;
	@Autowired
	private MybatisEmpDAO mybatisEmpDAO;
	@Autowired
	private MybatisDeptDAO mybatisDeptDAO;
	
	//모든 사원 가져오기
	public List selectAll() {
		List list = null;
		SqlSession sqlSession = manager.getSqlSession();
		mybatisEmpDAO.setSqlSession(sqlSession);
		list = mybatisEmpDAO.selectAll();
		manager.close(sqlSession);
		return list;
	}
	
	public int regist(Emp emp) {
		int result = 0;
		SqlSession sqlSession = manager.getSqlSession(); //default false
		
		mybatisEmpDAO.setSqlSession(sqlSession);
		mybatisDeptDAO.setSqlSession(sqlSession);
		
	 
		try {
			mybatisDeptDAO.insert(emp.getDept());
			mybatisEmpDAO.insert(emp);
			sqlSession.commit();
			result = 1;
		} catch (RegistException e) {
			sqlSession.rollback();
			e.printStackTrace();
		}
	 
		
		
		manager.close(sqlSession);
		return result;
	}
}
