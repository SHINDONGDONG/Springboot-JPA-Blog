package com.cos.blog.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TempControllerTest {

	//http://localhost:8000/blog/temp/home
	@GetMapping("/temp/home")
	public String tempHome() {
		return "/home.html";
		
		//파일 기본경로 src/main/resource/static
		//return명을 /home.html
	}
	@GetMapping("/temp/img")
	public String tempImg() {
		return "/a.png";
	}
	
	@GetMapping("/temp/jsp")
	public String tempJsp() {
		//prefix : /WEB-INF/views/
		//suffix : .jsp
		return "test";
	}
}
