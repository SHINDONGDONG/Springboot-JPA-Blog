package com.cos.blog.model;
import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.*;
//ORM ->JAVA (다른언어들 object 를 테이블로 맵핑해주는것)

@Data //데이터가 오고가는
@NoArgsConstructor 
@AllArgsConstructor
@Builder //빌더 패턴
//@DynamicInsert //insert 시에 null인 필드는제외시켜준다.
@Entity//User클래스가 Mysql에 테이블이 생성됨
public class User {

	@Id//primarykey
	@GeneratedValue(strategy = GenerationType.IDENTITY)//프로젝트에서 연결된 DB의 넘버링 전략에 따라간다
	private int id;//시퀀스,auto_incremnet
	
	
	@Column(nullable = false,length = 30)//컬럼의 nullable을 false로 줘서 notnull 효과 / 글씨30자이내
	private String username; //아이디
	
	@Column(nullable = false,length = 100) //123456 => 해쉬(비밀번호 암호화)
	private String password;
	
	@Column(nullable = false,length = 50)
	private String email;
	
	//@ColumnDefault("'user'") // " ' ' "  <<<<<<
	//data베이스는 RoleType이라는게 없다
	@Enumerated(EnumType.STRING)
	private RoleType role; //Enum 을 쓰는게 좋다. //도메인을 지정해서 값을 넣을수있음
	//admin , user, manager 권한을주는 
	
	@CreationTimestamp //시간이 자동으로 입력이됨.
	private Timestamp createDate; //작성시간
	
}
