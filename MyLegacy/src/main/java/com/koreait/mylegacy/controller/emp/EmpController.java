package com.koreait.mylegacy.controller.emp;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.koreait.mylegacy.model.dao.jdbcDeptDAO;
import com.koreait.mylegacy.model.dao.jdbcEmpDAO;
import com.koreait.mylegacy.model.domain.Dept;
import com.koreait.mylegacy.model.domain.Emp;
import com.koreait.mylegacy.model.service.JdbcEmpService;
import com.koreait.mylegacy.model.service.MybatisEmpService;

//component-scan 대상이 되려면 어노테이션을 지정해야 한다


@Controller
public class EmpController {
	private static final Logger logger= LoggerFactory.getLogger(EmpController.class);
	@Autowired
	private JdbcEmpService empService;
	@Autowired
	private MybatisEmpService mybatisEmpService;
	
	//사원등록 폼요청 method지정하지않으면 default = get 
	@RequestMapping(value="/emp/registform")
	public String registForm() {
		//저장할 것이없고 , 그냥 뷰만 반환하고 싶을때는 String 도 가능 
		return "emp/regist_form";
	}
	
	
	//사원등록 요청을 처리하는 메서드
	@RequestMapping(value="/emp/regist", method = RequestMethod.POST)
	public String regist(Dept dept, Emp emp) {
		//파라미터 받아와 출력해보기!!
		//log4j 라는 라이브러리는 우리가 사용하는 개발시 디버그 목적으로 사용하는 콘솔 출력보다도 훨씬 다양하면서 
		//복잡한 기능을 지원하는 로그 라이브러리 이다.
		//특히 출력 레벨이라는 기능을 둬서 개발자가 원하는 레벨을 선택하여 출력을 제어할수 있도록 지원해준다 
		//All(모든 로깅)<Debug(확인)<info(강조)<warn(경고)<error(오류)<fatal(심각한 오류)<off
//		logger.debug(""+dept.getDeptno());
//		logger.debug(dept.getDname());
//		logger.debug(dept.getLoc());
//
//		logger.debug(""+emp.getEmpno());
//		logger.debug(emp.getEname());
//		logger.debug(""+emp.getSal());
//		logger.debug(""+emp.getDeptno());
		
		//db에 등록
		//System.out.println("jdbcDeptDAO 는" + jdbcDeptDAO);
		//System.out.println("regist 는 : " + jdbcDeptDAO.regist(dept));
		
		//System.out.println("regist2 는 : " + jdbcEmpDAO.regist(emp));
		emp.setDept(dept);
		
		//empService.regist(emp);
		mybatisEmpService.regist(emp);
		
		return "redirect:/emp/list";
	}
	
	//사원목록
	@RequestMapping(value="/emp/list",method = RequestMethod.GET)
	public ModelAndView selectAll() {
		ModelAndView mav = new ModelAndView();
		
		//mav.addObject("empList",empService.selectAll());
		mav.addObject("empList",mybatisEmpService.selectAll());
		mav.setViewName("/emp/list");
		return mav;
		
		
	}
	
	
	
	
}
