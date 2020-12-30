package com.study.springfinal.controller.gallery;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.study.springfinal.common.FileManager;
import com.study.springfinal.domain.Gallery;
import com.study.springfinal.model.dao.GalleryDAO;

@Controller
@RequestMapping(value="/gallery")
public class GalleryController {
	//표시를 할테니 여기에 넣어주세요
	@Autowired
	private GalleryDAO galleryDAO;
	
	//스프링은 아파치업로드 툴을 이용한다
	@RequestMapping(value="/regist", method = RequestMethod.POST)
	public String regist(Gallery gallery , HttpServletRequest request) {
		
		System.out.println("등록 원해?");
		
		System.out.println("title" + gallery.getTitle());
		System.out.println("Writer" + gallery.getWriter());
		System.out.println("Content" + gallery.getContent());
		System.out.println("Filename " + gallery.getPhoto());
		
		MultipartFile photo = gallery.getPhoto();
		System.out.println(photo.getOriginalFilename());
		
		String newName = Long.toString(System.currentTimeMillis());
		String ext = FileManager.getExtend(photo.getOriginalFilename());
		String filename = newName+"."+ext; //최종 파일명..
		gallery.setFilename(filename); //새로운 파일명 vo 에 저장
		
		ServletContext context = request.getServletContext();
		String dir = context.getRealPath("/data");
		
		//이클립스 냅부 톰켓인 경우 실제 경로와는 틀린 경로에 저장.. 개발시엔 그 경로를 확인하려면 
		System.out.println(dir);
		try {
			photo.transferTo(new File(dir+"/"+filename));  //물리적 저장.. 
			int result = galleryDAO.insert(gallery);
			System.out.println("등록결과는" + result);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "redirect:/gallery/list";
		
	}
	
	//목록 가져오기!
	@RequestMapping(value="/list",method = RequestMethod.GET)
	public ModelAndView selectAll() {
		//3단계
		List gallerylist = galleryDAO.selectAll();
		
		
		//4단계
		ModelAndView mav = new ModelAndView();
		mav.addObject("galleryList",gallerylist);
		mav.setViewName("gallery/list");
		
		return mav;
	}
	
	@RequestMapping(value="/detail", method = RequestMethod.GET)
	public ModelAndView select(int gallery_id) {
		Gallery gallery = galleryDAO.select(gallery_id);
		ModelAndView mav = new ModelAndView();
		mav.addObject("gallery",gallery);
		mav.setViewName("gallery/detail");
		return mav;
	}
	
	@RequestMapping(value="/edit",method = RequestMethod.POST)
	public String edit(Gallery gallery) {
		galleryDAO.update(gallery);
		
		return "redirect:/gallery/detail?gallery_id="+gallery.getGallery_id();
	}
	
	@RequestMapping(value="/delete", method = RequestMethod.POST)
	public String delete(int gallery_id) {
		galleryDAO.delete(gallery_id);
		
		return "redirect:/gallery/list";
	}
	
	
	
}
