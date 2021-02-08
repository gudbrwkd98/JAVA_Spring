package com.koreait.bootapp0208.model.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koreait.bootapp0208.exception.BoardUpdateException;
import com.koreait.bootapp0208.model.board.repository.BoardDAO;
import com.koreait.bootapp0208.model.doain.Board;
 

@Service
public class BoardServiceImpl implements BoardService{

	@Autowired
	private BoardDAO boardDAO;
	
	@Override
	public List selectAll() {
		// TODO Auto-generated method stub
		return boardDAO.selectAll();
	}

	@Override
	public Board select(int board_id) {
		// TODO Auto-generated method stub
		return boardDAO.select(board_id);
	}

	@Override
	public void regist(Board board) throws BoardUpdateException {
		// TODO Auto-generated method stub
		boardDAO.insert(board);
	}

	@Override
	public void update(Board board)throws BoardUpdateException {
		// TODO Auto-generated method stub
		boardDAO.update(board);
	}

	@Override
	public void delete(int board_id) throws BoardUpdateException {
		// TODO Auto-generated method stub
		boardDAO.delete(board_id);
	}

}
