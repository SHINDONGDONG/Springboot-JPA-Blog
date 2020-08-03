package com.cos.blog.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController//spring이 com.cos.blog패키지  이하를 스캔해서 모든 파일을 메모리에 new 하는것은 아니고
					//특정 어노테이션이 붙어있는 클래스파일들을 new 해서 스프링 컨테이너에 넣어줌
public class BolgConrollerTest {

	//localhost:8080/test/hello
	@GetMapping("/test/hello")
	public String hello() {
		return "<h1>Hello Spring boot</h1>";
	}
	
}
