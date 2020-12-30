package com.koreait.mvclegacy.model.member;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


import com.koreait.mvclegacy.controller.admin.AdminController;

@Service
public class MemberService {
	private static  final Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	public void regist() {
		logger.debug("회원등록");
	}
}
