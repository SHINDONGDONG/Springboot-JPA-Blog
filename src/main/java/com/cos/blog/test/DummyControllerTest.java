package com.cos.blog.test;

import java.util.List;
import java.util.function.Supplier;

import org.hibernate.annotations.SortType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

//html이 아니라 data를 리턴해주는 컨트롤러
@RestController
public class DummyControllerTest {

	@Autowired //의존성 주입 DI
	private UserRepository userRepository;
	//http://localhost:8000/blog/dummy/join(요청)
	//http:의 body에 username,password,email 데이터를 가지고 요청
	
	@DeleteMapping("/dummy/user/{id}")
	public String delete(@PathVariable int id) {
		try {
			userRepository.deleteById(id);
		}catch(EmptyResultDataAccessException e) {
			return "삭제에 실패하여습니다 해당하는 데이터가 없습니다";
		}
		
		return "삭제 되었습니다" + id ;
	}
	
	
	//save함수는 아이디를 전달하지 않으면 insert해주고
	//save함수는 아이디를 전달하면  해다id에 대한 데이터가 없으면 insert해주고
	//save함수는 아이디를 전달하면  해다id에 대한 데이터가 있으면 update해주고
	
	//localhost:8000/blog/dummy/user/3
	//email,password
	@Transactional
	@PutMapping("/dummy/user/{id}")
	public User updateUser(@PathVariable int id,@RequestBody User requestUser) {
		System.out.println("id : " +id);
		System.out.println("password : " +requestUser.getPassword());
		System.out.println("email : " +requestUser.getEmail());
		
		User user = userRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("수정에 실패.");
		});
		user.setPassword(requestUser.getPassword());
		user.setEmail(requestUser.getEmail());
		
		
		//더티체킹
//		userRepository.save(user);
		return user;
	}
	
	
	
	@GetMapping("/dummy/users")
	public List<User> list (){
		return userRepository.findAll();
	}

	//한페이지당 2건의 데이터를 받아봄
	@GetMapping("/dummy/user")
	public List<User>pagieList(@PageableDefault(size = 2,sort = "id",direction = Sort.Direction.DESC) Pageable pageable){
		Page<User> pagingUser = userRepository.findAll(pageable);  //.getContent()로 하면 콘텐츠만 리턴해준다 List<User>
//		
//		if(pagingUser.isFirst()) {
//			//isLast 등등 함수가 있다.
//		}
		List<User> users = pagingUser.getContent();
		return users;
	}
	//{id}주소로 파라메타를 전달 받을 수 있습니다.
	//http://localhost:8000/blog/dummy/user/3
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) {

		//람다식 
//		User user = userRepository.findById(id).orElseThrow(()->{
//			return IllegalArgumentException("해당 id를 찾을 수 없다");
//		});
		
		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
			@Override
			public IllegalArgumentException get() {
				return new IllegalArgumentException("해당 유저는 없습니다. id : " + id);
			}
		});
		//user객체는 = 자바 오브젝트 
		//요청 : 웹브라우저 
		//변환 웹브라우저가 이해할 수 있는 데이터) json
		//스프링 부트는  =message convert 응답시에 자동작동
		//만약에 자바 obj를 리턴하게되면 메세지 컨버터가 jackson라이브러리를 호출
		//user 오브젝트를 json으로 변환하여 브라우저에 던져줌..
		return user;
		//user/4번을 찾으면  내가 데이터베이스에서 못찾아오게되면 user가 null이됨
		//그럼 ? return할때 null이 리턴됨. 그럼 프로그램에 문제있을 가능성이있다
		//optional로 너의 User객체를 감싸서 가져올테니 null인지 판단해서 return해
		
//		User user =userRepository.findById(id).orElseGet(new Supplier<User>() {
//			@Override
//			public User get() {
//				return new User();
//			}
//		});
//		return user;
	}
		
		
	
	
	@PostMapping("/dummy/join")
	public String join(User user) { //key=value(약속된 규칙)
		System.out.println("id : " + user.getId());
		System.out.println("username  : " + user.getUsername());
		System.out.println("password  : " + user.getPassword());
		System.out.println("password  : " + user.getEmail());
		System.out.println("role : " + user.getRole());
		System.out.println("createDate : " + user.getCreateDate());
		
		user.setRole(RoleType.USER);
		userRepository.save(user); 
		
		return "회원가입이 완료 되었습니다";
	}
	
	
}
