package com.springmvc.board.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.model2.board.model.BoardDAO;

public class ListController implements Controller{
	//DI 란 외부에서 객체의 인스턴스를 주입받는 개발 방법이다.. 주입을 받기위해선 setter 나 생성자를 준비해야한다 
	private BoardDAO boardDAO; //DI 가 아님!!
	
	public void setBoardDAO(BoardDAO boardDAO) {
		this.boardDAO = boardDAO;
	}
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//3단계 로직 객체에 일시킨다
		List list = boardDAO.selectAll();
		

		
		//4단계 결과 저장
		ModelAndView mav = new ModelAndView();
		mav.addObject("boardList",list);
		mav.setViewName("board/list");
		return mav;
	}

}
