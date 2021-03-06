package com.springmvc.board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.model2.board.model.BoardDAO;

public class DeleteController implements Controller{
	BoardDAO boardDAO;
	
	
	public void setBoardDAO(BoardDAO boardDAO) {
		this.boardDAO = boardDAO;
	}


	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int result = boardDAO.delete(Integer.parseInt(request.getParameter("board_id")));
		ModelAndView mav = new ModelAndView();
		
		if(result==1) {
		mav.setViewName("redirect:/board/list"); //성공한경우의 url
		}else {
		mav.addObject("msg","삭제실패");
		mav.setViewName("error/result"); //실패한 경우의 url
		}
		return mav;
	}

}
