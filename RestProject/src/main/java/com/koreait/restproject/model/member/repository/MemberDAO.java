package com.koreait.restproject.model.member.repository;

import java.util.List;

import com.koreait.restproject.model.domain.Member;

public interface MemberDAO {
	public List selectAll();
	public Member select();
	public void insert(Member member);
	public void update(Member member);
	public void delete(int member_id);
}
