package com.koreait.restproject.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.koreait.restproject.exception.BoardUpdateException;
import com.koreait.restproject.message.Message;
import com.koreait.restproject.model.board.service.BoardService;
import com.koreait.restproject.model.domain.Board;

import jdk.internal.org.jline.utils.Log;
import lombok.extern.slf4j.Slf4j;

@RestController //restful url 을 이애한다 또한 @ResponseBody 가 이미 처리 되어있다 
public class BoardController {
	@Autowired
	private BoardService boardService;
	
	//목록가져오기 요청
	@GetMapping("/board")//이미 responseBody 가 적용된 상태이므로 컨버터만 등록해놓았다면 List 는 자동으로 Json 으로 변환되어 클라이언트의 응답 정보고 사용됨..
	public List<Board> getList() {
		List boardList = boardService.selectAll();
		return boardList;
	}
	//한건가져오기 요청
	@GetMapping("/board/{board_id}")
	public Board getDetail(@PathVariable int board_id) {
		Board board = boardService.select(board_id);
		return board;
	}
	
	//등록 요청 
	@PostMapping("/board")
	//@RequestBody 클라이언트가 전송한 JSON 데이터를 자바의 객체로 변환 (JSON --> JAVA OBJ로 변환)
	public ResponseEntity regist(@RequestBody Board board) {
		boardService.regist(board);
		return ResponseEntity.ok().body(board);//board_id 가 탑재된 vo 를 보낸다 
	}
	//수정요청
	@PutMapping("/board")
	public ResponseEntity update(@RequestBody Board board) {
		System.out.println(board.getContent());
		System.out.println(board.getContent());
		System.out.println(board.getBoard_id());
		
		boardService.update(board);
		return ResponseEntity.ok().body(board);
	}
	
	//삭제요청
	@DeleteMapping("/board/{board_id}")
	public ResponseEntity<Message> del(@PathVariable int board_id) {
		boardService.delete(board_id);
			Message message = new Message();
			message.setMsg("게시물 삭제 성공");
			
		return ResponseEntity.ok().body(message);
	}
	
	

}
