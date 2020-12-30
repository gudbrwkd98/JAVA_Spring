package com.koreait.mvclegacy.controller.client;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.koreait.mvclegacy.exception.DMLException;
import com.koreait.mvclegacy.model.domain.Notice;
import com.koreait.mvclegacy.model.notice.MybatisNoticeDAO;
import com.koreait.mvclegacy.model.notice.NoticeService;

@Controller
public class NoticeController {
	private static final Logger logger = LoggerFactory.getLogger(NoticeController.class);
	
	@Autowired
	private NoticeService noticeService;
	
	//모든 글가져오기 요청
	@RequestMapping(value="/notice/list", method = RequestMethod.GET)
	public ModelAndView selectAll() {
		logger.info("성공");
		
		ModelAndView mav= new ModelAndView("notice/list");
		List list = noticeService.selectAll();
		mav.addObject("noticeList",list);
		mav.setViewName("notice/list");
		return mav;
	}
	
	@RequestMapping(value="/notice/registform")
	public String getRegistForm() {
		return "notice/regist_form";	
		
	}
	@RequestMapping(value="/notice/regist",method = RequestMethod.POST)
	public String regist(Notice notice) { //스프링에서 
		//자동으로 데이터값이 vo 에 채워진다
		noticeService.insert(notice);
		return "redirect:/client/notice/list";
	}
	@RequestMapping(value="/notice/detail", method = RequestMethod.GET)
	public ModelAndView detail(int notice_id) {
		Notice notice = noticeService.select(notice_id);
		ModelAndView mav = new ModelAndView();
		mav.addObject("notice",notice);
		mav.setViewName("/notice/detail");
		
		return mav;
	}
	@RequestMapping(value="/notice/edit",method = RequestMethod.POST)
	public ModelAndView update(Notice notice) {
		String resultView ="";
		ModelAndView mav = new ModelAndView();

			noticeService.update(notice);
			mav.addObject("msg","등록성공");
			mav.setViewName("redirect:/client/notice/detail?notice_id="+notice.getNotice_id());
 
		return mav;
		
	}
	
	@RequestMapping(value="/notice/delete",method = RequestMethod.GET)
	public String delete(int notice_id) {
		noticeService.delete(notice_id);
		return "redirect:/client/notice/list";
	}
	
	//스프링의 컨트롤러의 요청 처리 메서드중 어느 하나라도 예외가 발생하면 그 예외를 처리할수 있는 
		//별도의 메서드가 지원된다 어노테이션에 명시한 예외만 감지하여 처리
		@ExceptionHandler(DMLException.class)
		public ModelAndView handleException(DMLException e) {
			ModelAndView mav = new ModelAndView();
			
			//어떤 내용을 담을지?? 에러메시지
			mav.addObject("msg",e.getMessage());
			//어느페이지를 보여줄지
			mav.setViewName("/message/result");
			return mav;
		}
	
	
}
