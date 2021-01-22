package com.koreait.restproject.model.member.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.koreait.restproject.exception.MemberListException;
import com.koreait.restproject.exception.MemberUpdateException;
import com.koreait.restproject.model.domain.Member;

@Repository
public class MybatisMemberDAO implements MemberDAO{

	@Autowired
	private SqlSessionTemplate sqlSesstionTemplate;
	
	@Override
	public List selectAll() throws MemberListException {

		return sqlSesstionTemplate.selectList("Member.selectAll");
 
		 
	}

	@Override
	public Member select() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(Member member) throws MemberUpdateException{
		int result =  sqlSesstionTemplate.insert("Member.insert",member);
		if(result == 0) {
			throw new MemberUpdateException("등록에러");
		}
	}

	@Override
	public void update(Member member) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int member_id) {
		// TODO Auto-generated method stub
		
	}

}
