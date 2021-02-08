package com.koreait.bootapp0208.rest.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.koreait.bootapp0208.model.board.service.BoardService;
import com.koreait.bootapp0208.model.doain.Board;
import com.koreait.bootapp0208.test.Dog;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class BoardController {
	
	@Autowired
	private Dog dog;
	@Autowired
	private BoardService boardService;
	
	
	public ResponseEntity<List<Board>> getTest() {
		log.debug("목록을 원해?");
		List<Board> boardList = new ArrayList<Board>();
		
		Board board = new Board();
		board.setBoard_id(1);
		board.setTitle("아무거나"); 
		board.setWriter(dog.getName());
		board.setContent("내용 무");
		board.setRegdate("20201-02-8");
		board.setHit(25);
		
		boardList.add(board);
		
		//                    200  / data 
		return ResponseEntity.ok().body(boardList);
	}
	
	@GetMapping("/rest/board")
	public ResponseEntity<List<Board>> getList(){
		List<Board> boardList = boardService.selectAll();
		
		return ResponseEntity.ok().body(boardList);
	}

}
