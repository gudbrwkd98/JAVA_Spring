package com.springmvc.board.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.model2.board.model.BoardDAO;

public class ListController implements Controller{
	//DI �� �ܺο��� ��ü�� �ν��Ͻ��� ���Թ޴� ���� ����̴�.. ������ �ޱ����ؼ� setter �� �����ڸ� �غ��ؾ��Ѵ� 
	private BoardDAO boardDAO; //DI �� �ƴ�!!
	
	public void setBoardDAO(BoardDAO boardDAO) {
		this.boardDAO = boardDAO;
	}
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//3�ܰ� ���� ��ü�� �Ͻ�Ų��
		List list = boardDAO.selectAll();
		

		
		//4�ܰ� ��� ����
		ModelAndView mav = new ModelAndView();
		mav.addObject("boardList",list);
		mav.setViewName("board/list");
		return mav;
	}

}