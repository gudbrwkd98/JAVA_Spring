package com.springmvc.board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.model2.board.model.BoardDAO;
import com.model2.domain.Board;

public class DetailController implements Controller{
	BoardDAO boardDAO;
	
	public void setBoardDAO(BoardDAO boardDAO) {
		this.boardDAO = boardDAO;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Board board = new Board();
		board = boardDAO.select(Integer.parseInt(request.getParameter("board_id")));
		//setViewName 을 생성자에서 넣을시 자동으로 설정
		ModelAndView mav = new ModelAndView("board/detail");
		mav.addObject("board",board);
		//mav.setViewName("board/detail");
		return mav;
	}

}
