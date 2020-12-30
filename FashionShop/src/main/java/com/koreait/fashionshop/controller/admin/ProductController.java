package com.koreait.fashionshop.controller.admin;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

// 관리자 모드에서의 상품에 대한 요청 처리
@Controller
public class ProductController {

	//상위 카테고리 가져오기
	
	//하위 카테고리 가져오기
	
	//상품목록
	@RequestMapping(value="/admin/product/list", method = RequestMethod.GET)
	public ModelAndView getProductList() {
		List list = null;
		ModelAndView mav = new ModelAndView("admin/product/product_list");
		
		
		return mav;
	}
	
	//상품등록 폼
	@RequestMapping(value="/admin/product/registform")
	public String regist() {
		
		return "/admin/product/regist_form";
	}
	
	//상품상세
	
	//상품등록
	
	//상품수정
	
	//상품삭제
		
}
