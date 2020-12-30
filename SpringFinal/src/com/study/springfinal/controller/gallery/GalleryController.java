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
	//ǥ�ø� ���״� ���⿡ �־��ּ���
	@Autowired
	private GalleryDAO galleryDAO;
	
	//�������� ����ġ���ε� ���� �̿��Ѵ�
	@RequestMapping(value="/regist", method = RequestMethod.POST)
	public String regist(Gallery gallery , HttpServletRequest request) {
		
		System.out.println("��� ����?");
		
		System.out.println("title" + gallery.getTitle());
		System.out.println("Writer" + gallery.getWriter());
		System.out.println("Content" + gallery.getContent());
		System.out.println("Filename " + gallery.getPhoto());
		
		MultipartFile photo = gallery.getPhoto();
		System.out.println(photo.getOriginalFilename());
		
		String newName = Long.toString(System.currentTimeMillis());
		String ext = FileManager.getExtend(photo.getOriginalFilename());
		String filename = newName+"."+ext; //���� ���ϸ�..
		gallery.setFilename(filename); //���ο� ���ϸ� vo �� ����
		
		ServletContext context = request.getServletContext();
		String dir = context.getRealPath("/data");
		
		//��Ŭ���� ���� ������ ��� ���� ��οʹ� Ʋ�� ��ο� ����.. ���߽ÿ� �� ��θ� Ȯ���Ϸ��� 
		System.out.println(dir);
		try {
			photo.transferTo(new File(dir+"/"+filename));  //������ ����.. 
			int result = galleryDAO.insert(gallery);
			System.out.println("��ϰ����" + result);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "redirect:/gallery/list";
		
	}
	
	//��� ��������!
	@RequestMapping(value="/list",method = RequestMethod.GET)
	public ModelAndView selectAll() {
		//3�ܰ�
		List gallerylist = galleryDAO.selectAll();
		
		
		//4�ܰ�
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
