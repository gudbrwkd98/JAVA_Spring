package com.koreait.restproject.client.aop;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.koreait.restproject.exception.MemberListException;

import jdk.internal.org.jline.utils.Log;

//일반요청에 대한 글로벌 예외처리
@ControllerAdvice
public class GlobalExceptionHandler {
	//회원목록을 가져올때 발생하는 예외처리
	@ExceptionHandler(MemberListException.class)
	public String handle(MemberListException e) {
		Log.info("일반 test");
		return null;
	}
}
