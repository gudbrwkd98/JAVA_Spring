package com.koreait.restproject.controller.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
//일반적인 요청을 처리하는ㄴ 컨트롤러
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.koreait.restproject.model.domain.Member;
import com.koreait.restproject.model.member.service.MemberService;

import lombok.extern.slf4j.Slf4j;
@Controller
@Slf4j
public class MemberController {
	@Autowired
	private MemberService memberService;
	
	@GetMapping("/member")
	public List<Member> getList() {
		log.debug("일반 테스트 요청");
		List memberList =  memberService.selectAll();
		return memberList;
	}
	
	@PostMapping("/member")
	public String regist(@RequestBody Member member){
		log.info("등록을 원해?");
		log.info("m_id" + member.getM_id());
		log.info("m_name" + member.getM_name());
		log.info("m_pass" + member.getM_pass());
		memberService.regist(member);
		return "regist success"; //Rest 에서는 개발자가 클라이언트에게 도대체 뭘 반환해야 할까? 
	}
	
}
