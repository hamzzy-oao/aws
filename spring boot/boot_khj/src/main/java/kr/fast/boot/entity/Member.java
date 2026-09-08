package kr.fast.boot.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name ="member")
@Getter //필드들의 getter를 추가
@NoArgsConstructor //기본 생성자
@AllArgsConstructor // 모든 필드를 매개변수로 하는 생성자를 추가
@ToString
public class Member {

	
	@Id
	private String id;
	
	@Column(nullable = false, length = 255)	
	private String password;
	
	@Column(nullable = false, length = 255)	
	private String email;
	
	@Column(nullable = false, length = 10)	
	private String role;
	
	
}
