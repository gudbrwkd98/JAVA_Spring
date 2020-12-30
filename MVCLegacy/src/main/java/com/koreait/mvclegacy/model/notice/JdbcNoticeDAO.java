package com.koreait.mvclegacy.model.notice;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.koreait.mvclegacy.exception.DMLException;
import com.koreait.mvclegacy.model.domain.Notice;

/*
 * 스프링이 지원하는 DB 연동 기술에는 여러가지가 있다
 * [Mybatis]
 * SQL Mapper: 쿼리문과 자바객체간 매핑
 * 
 * [Hibernate]
 * ORM(Object Relation Mapping)
 * 
 * */
@Repository
public class JdbcNoticeDAO implements NoticeDAO {
	private static final Logger logger = LoggerFactory.getLogger(JdbcTemplate.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate; //쿼리 수행객체
	
	//목록가져오기
	public List selectAll() {
		List list = null;
		String sql = "select * from notice order by notice_id desc";
		
		//매개변수 순서 : 쿼리문, 바인드변수를 배열로 처리, 매핑객체 (ResultSet의 데이터를 담는 객체)
		list = jdbcTemplate.query(sql,new Object[] {},new RowMapper<Notice>() {

			@Override
			public Notice mapRow(ResultSet rs, int rowNum) throws SQLException {
				logger.info("row number 는 : "+rowNum);
				Notice notice = new Notice(); //create empty VO 
				notice.setNotice_id(rs.getInt("notice_id"));
				notice.setTitle(rs.getString("title"));
				notice.setWriter(rs.getString("writer"));
				notice.setContent(rs.getString("content"));
				notice.setRegdate(rs.getString("regdate"));
				notice.setHit(rs.getInt("hit"));
				
				return notice;
			}
			
		});
		return list;
	}

	@Override
	public Notice select(int notice_id) {
		Notice notice = null;
		String sql = "select * from notice where notice_id = ?";
		notice = jdbcTemplate.queryForObject(sql,new RowMapper<Notice>() {

			@Override
			public Notice mapRow(ResultSet rs, int rowNum) throws SQLException {
				Notice notice = new Notice(); //create empty VO 
				notice.setNotice_id(rs.getInt("notice_id"));
				notice.setTitle(rs.getString("title"));
				notice.setWriter(rs.getString("writer"));
				notice.setContent(rs.getString("content"));
				notice.setRegdate(rs.getString("regdate"));
				notice.setHit(rs.getInt("hit"));
				
				return notice;
			}
			
		},notice_id);
		return notice;
	}

	@Override
	public void insert(Notice notice) throws DMLException {
	 String sql = "insert into notice(notice_id,title,writer,content)";
	 sql+=" values(seq_notice.nextval,?,?,?)";
	 
	 int result = jdbcTemplate.update(sql, notice.getTitle(),notice.getWriter(),notice.getContent());

		if(result == 0 ) {// 수정실패
			throw new DMLException("수정작업에 실패하였습니다");
		}
		
	}

	@Override
	public void update(Notice notice)throws DMLException {
		// TODO Auto-generated method stub
		String sql = "update notice set title=?,writer=?,content=? where notice_id = ?";
		
		int result = jdbcTemplate.update(sql,notice.getTitle(),notice.getWriter(),notice.getContent(),notice.getNotice_id());
		
		if(result == 0 ) {// 수정실패
			throw new DMLException("수정작업에 실패하였습니다");
		}
	}

	@Override
	public void delete(int notice_id)throws DMLException {
		// TODO Auto-generated method stub
		String sql = "delete from notice where notice_id = ?";
		
		int result = jdbcTemplate.update(sql,notice_id);
		
		if(result == 0 ) {// 수정실패
			throw new DMLException("수정작업에 실패하였습니다");
		}
	}
	
	
}
