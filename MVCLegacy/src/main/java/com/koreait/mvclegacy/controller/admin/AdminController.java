package com.koreait.mvclegacy.controller.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.koreait.mvclegacy.model.member.MemberService;


@Controller
public class AdminController {
	private static  final Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	@Autowired
	MemberService memberService;
	
	@RequestMapping(value="/main",method = RequestMethod.GET)
	public String test() {
		logger.debug("admin 테스트 완료");
		logger.debug("주소 값" + memberService);
		memberService.regist();
		return null;
	}
}
