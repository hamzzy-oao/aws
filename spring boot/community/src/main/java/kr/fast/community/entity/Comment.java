package kr.fast.community.entity;

import java.time.LocalDateTime;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PostPersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name ="comment")
@Getter //필드들의 getter를 추가
@NoArgsConstructor //기본 생성자
@AllArgsConstructor // 모든 필드를 매개변수로 하는 생성자를 추가
@ToString
public class Comment {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	
	String content; 
	@Column(name="created_at")
	LocalDateTime createdAt;
	
	@Column(name="origin_id")
	Integer originId;
	
	@Column(name="is_deleted")
	String isDeleted;
	
	@Column(name="member_id")
	String memberId;
	
	@Column(name="post_id")
	int postId;
	
	//대댓인 경우
	public Comment(String content, int postId, String memberId) {
		this.content = content;
		this.postId = postId;
		this.memberId = memberId;
	}
	
	//댓글인 경우
	public Comment(String content, int postId, String memberId, Integer originId) {
		this.content = content;
		this.postId = postId;
		this.memberId = memberId;
		this.originId = originId;
		this.createdAt = LocalDateTime.now();
		this.isDeleted = "N";
	}
	
	@PostPersist //저장 후 실행되는 메서드(JPA 콜백)
	public void initOriginId() {
		//댓글인 경우(originId가 null) originId를 id로 수정
		if(this.originId == null) {
			this.originId = this.id;
		}
	}

}
