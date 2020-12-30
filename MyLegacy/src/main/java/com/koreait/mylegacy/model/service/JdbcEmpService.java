package com.koreait.mylegacy.model.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koreait.mylegacy.model.dao.jdbcDeptDAO;
import com.koreait.mylegacy.model.dao.jdbcEmpDAO;
import com.koreait.mylegacy.model.domain.Dept;
import com.koreait.mylegacy.model.domain.Emp;
import com.koreait.mylegacy.model.pool.PoolManager;

/*
 * MVC 에서 Model 영역의 서비스를 정의한다.
 * 서비스는 직접 로직을 수행하지는 않으며 모델 영역의 각업무를 수행하는 객체를
 * 제어하는 역할 만일 Service 존재가 없다면 컨트롤러가 모델 영역의 복잡한 처리를
 * 직접해야 하므로 어플리케이션 설계상 영역 구분이 약해지게 된다..
 * 
 * */
@Service
public class JdbcEmpService {
	@Autowired
	private PoolManager poolManager;
	@Autowired
	private jdbcDeptDAO jdbcDeptDAO;
	@Autowired
	private jdbcEmpDAO jdbcEmpDAO;
	
	
	public List selectAll() {
		List list= null;
		Connection con = poolManager.getConnection();
		jdbcEmpDAO.setCon(con);
		
 
		list = jdbcEmpDAO.selectAll();
 
		
		
		return list;
	}
	
	//한건 가져오기 (부서 사원)
	public Dept selectDept() {
		Dept dept = null;
		return dept;
	}
	
	public Emp selectEmp() {
		Emp emp = null;
		return emp;
	}
	
	//등록 (emp,dept 의 트랜잭션 관계!!)
	public int regist(Emp emp) {
		int result = 0;
		Connection con = poolManager.getConnection();
		//DAO들에게 동일한 Connection을 배분!!
		jdbcDeptDAO.setCon(con);
		jdbcEmpDAO.setCon(con);
		
		try {
			con.setAutoCommit(false);//자동 커밋방지
			jdbcDeptDAO.regist(emp.getDept());
			jdbcEmpDAO.regist(emp);
			con.commit(); //위업무 수행중 에러가 안났다면?
			result=1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}finally {
			try {
				con.setAutoCommit(true);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
		poolManager.freeConnection(con, null);
		return result;
	}
	

	
	//수정
	public int updateDept() {
		int result = 0;
		
		
		return result;
	}
	
	public int updateEmp() {
		int result = 0 ;
		 
		
		return result;
	}
	
	//삭제
	public int deleteDept() {
		int result = 0;
		
		
		return result;
	}
	
	public int deleteEmp() {
		int result = 0 ;
		 
		
		return result;
	}
}
