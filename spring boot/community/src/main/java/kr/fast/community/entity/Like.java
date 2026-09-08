package kr.fast.community.entity;

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
@Table(name ="post_like")
@Getter //필드들의 getter를 추가
@NoArgsConstructor //기본 생성자
@AllArgsConstructor // 모든 필드를 매개변수로 하는 생성자를 추가
@ToString
public class Like {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	
	
	int state;
	
	@Column(name="member_id")
	 String memberId;
	
	@Column(name="post_id")
	int postId;
	
	
	public Like(int id, String memberId, Integer state) {
		
		this.postId = id;
		this.memberId = memberId;
		this.state = state;
		
	}


	public void updateState(int i) {
		this.state = i;
	}

}
