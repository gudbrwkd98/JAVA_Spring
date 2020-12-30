package com.koreait.mvclegacy.model.notice;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.koreait.mvclegacy.exception.DMLException;
import com.koreait.mvclegacy.model.domain.Notice;
@Repository
public class MybatisNoticeDAO implements NoticeDAO {
	//스프링이 지원하는 DB기술을 이용하기전에는 트랜잭션때문에 sqlSession을 서비스로 부터
	//주입받았으나 더이상 트랜잭션 때문에 서비스가에서 주입받을 필요없다 스프링이 알아서 트랜잭션 처리를 해준다
	@Autowired
	private SqlSessionTemplate sessionTemplate;
	
	public List selectAll() {
		List list = null;
		list = sessionTemplate.selectList("Notice.selectAll");
		return list;
	}
	
	public Notice select(int notice_id) {
		Notice notice = null;
		notice = sessionTemplate.selectOne("Notice.select",notice_id);
		return notice;
	}
	
	public void insert(Notice notice)throws DMLException {
		int result =  sessionTemplate.insert("Notice.insert",notice);
			if(result == 0 ) {// 수정실패
				throw new DMLException("수정작업에 실패하였습니다");
			}
	}
	
	public void update(Notice notice)throws DMLException {
		int result = sessionTemplate.update("Notice.update",notice);
		if(result == 0 ) {// 수정실패
			throw new DMLException("수정작업에 실패하였습니다");
		}
	}
	
	public void delete(int notice_id)throws DMLException {
		int result =  sessionTemplate.delete("Notice.delete",notice_id);
			if(result == 0 ) {// 수정실패
				throw new DMLException("수정작업에 실패하였습니다");
			}
	}
}
