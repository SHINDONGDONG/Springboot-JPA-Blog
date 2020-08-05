package com.cos.blog.model;
import java.sql.Timestamp;
import javax.annotation.Generated;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import javax.*;

@Entity//User클래스가 Mysql에 테이블이 생성됨
public class User {

	@Id//primarykey
	@GeneratedValue(strategy = GenerationType.IDENTITY)//프로젝트에서 연결된 DB의 넘버링 전략에 따라간다
	private int id;//시퀀스,auto_incremnet
	
	private String username; //아이디
	
	private String password;
	
	private String email;
	
	private Timestamp createDate; //작성시간
	
}
