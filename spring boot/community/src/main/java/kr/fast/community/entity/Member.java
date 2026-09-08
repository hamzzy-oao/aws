package kr.fast.community.entity;

import org.hibernate.annotations.Audited.Table;
import org.jspecify.annotations.Nullable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "members")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Member {
	


	@Id
	private String id;
	

	@Column(name="password")
	private String pw;
	
	@Column(name="email")
	private String email;
	
	@Column(name="role")
	private String role = "USER";
	
	public Member(String id, String pw, String email) {
		this.id = id;
		this.pw = pw;
		this.email = email;
	}

	public String getPw() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
