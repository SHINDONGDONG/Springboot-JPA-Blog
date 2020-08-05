package com.cos.blog.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.test.Member.MemberBuilder;
import com.fasterxml.jackson.databind.introspect.MemberKey;

//사용자가 요청 ->응답(HTML파일)
//@Controller

@RestController
//사용자가 요청 -> 응답(data)
public class HttpControllerTest {

	private static final String TAG = "HttpControllerTest : ";
	@GetMapping("/http/lombok")
	public String lombokTest() {
		Member m = Member.builder().username("sallls").password("123123").email("email@nate.com").build();
		System.out.println(TAG+"getter : "+m.getUsername());
		m.setUsername("cos");
		System.out.println(TAG+"getter : "+m.getUsername());
		return "lombok test  ok";
	}
	//localhost:8080/http/get(select)
	@GetMapping("/http/get")
	public String getTest(Member m) {

	return null;
		//System.out.println(TAG+"getter : "+m.getId());
	}
	
	//localhost:8080/http/post(insert)
	@GetMapping("/http/post")	
	public String postTest(Member m) {
		
		return "post 요청 " +m.getId()+","+m.getUsername()+","+m.getPassword()+","+m.getEmail()	;
	}
	//http://localhost:8080/http/put(update)
	@GetMapping("/http/put")
	public String putTest() {
		return "put 요청 ";
	}
	//localhost:8080/http/delete(delete)
	@GetMapping("/http/delete")
	public String deleteTest() {
		return "delete 요청 ";
	}
	
	
}
