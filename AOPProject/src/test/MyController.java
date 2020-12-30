package test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class MyController {
	@RequestMapping("/anno")
	public String test() {
		System.out.println("어노테이션 방식으로 하는중");
		return "";
	}
}
