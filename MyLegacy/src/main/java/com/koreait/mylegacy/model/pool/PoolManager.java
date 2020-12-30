package com.koreait.mylegacy.model.pool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.stereotype.Component;

//이 클래스를 통해서 db 작업을 하고 싶은 객체가 connection 객체를 빌려갈 수 있도록 제공하기 위함 
@Component
public class PoolManager {
	private static PoolManager instance;
	private InitialContext context;
	private DataSource ds;
	private PoolManager() {
		//jndi 를 이용하여 DataSource 가져오기
		try {
			context = new InitialContext();
			ds = (DataSource) context.lookup("java:comp/env/jdbc/oracle");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static PoolManager getInstance() {
		if(instance ==null) {
			instance = new PoolManager();
		}
		return instance;
	}
	
	//커넥션 반환
	public Connection getConnection() {
		Connection con = null;
		try {
			con = ds.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}
	
	public void freeConnection(Connection con,PreparedStatement pstmt) {
		if(pstmt != null) {
			
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
		if(con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public void freeConnection(Connection con,PreparedStatement pstmt, ResultSet rs) {
		if(pstmt != null) {
			
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
		if(rs !=null) {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
