package com.springmvc.test.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;


/* 이 클래스는 요청을 실제적으로 처리하는 컨틀롤러 임을 명시 */
//@Controller
public class TestController implements Controller{
	
	
	//어떤한 url 요청을 처리할지를 메서드 차원에서 작성하는 것임..
	//@RequestMapping(value="/hello")


	@Override
	public ModelAndView handleRequest(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		//3단계 알맞는 로직 객체에 일을 시킨다
		String msg = "안녕";
		
		//4단계 저장할것이있다면 request 객체에 저장!
		ModelAndView mav = new ModelAndView();
		mav.addObject("msg",msg);
		//형님 컨트롤러가 어떤 페이지를 보여줘야 할지에 대한정보는 여전히
		mav.setViewName("test/result");
		//System.out.println(msg);
		return mav;
	}
}
