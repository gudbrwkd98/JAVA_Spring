package com.koreait.mvclegacy.controller.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.koreait.mvclegacy.model.member.MemberService;

@Controller
public class ClientController {
	private static  final Logger logger = LoggerFactory.getLogger(ClientController.class);
	
	@Autowired
	MemberService memberService;

	@RequestMapping(value="/main",method = RequestMethod.GET)
	public String test() {
		logger.debug("client 테스트 완료");
		logger.debug("주소 값" + memberService);
		memberService.regist();
		return null;
	}
}
