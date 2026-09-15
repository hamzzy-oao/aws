package kr.fast.diary.entity;

import java.time.LocalDateTime;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "users")
@Getter //필드들의 getter를 추가
@NoArgsConstructor //기본 생성자
@AllArgsConstructor // 모든 필드를 매개변수로 하는 생성자를 추가
@ToString
public class Member {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long id;
	
	
	@Column(name="email")
	private String email;
	
	
	@Column(name="password")
	private String pw;
	
	private String nickname;
	
	
	@Column(name="created_at",insertable = false,updatable = false)
	private LocalDateTime createdAt;
	
	public Member(
	        String email,
	        String pw,
	        String nickname
	) {
	    this.email = email;
	    this.pw = pw;
	    this.nickname = nickname;
	}

}


