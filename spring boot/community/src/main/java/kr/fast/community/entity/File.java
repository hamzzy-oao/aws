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
@Table(name ="file")
@Getter //필드들의 getter를 추가
@NoArgsConstructor //기본 생성자
@AllArgsConstructor // 모든 필드를 매개변수로 하는 생성자를 추가
@ToString
public class File {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	
	@Column(name="original_name")
	String originalName;
	
	@Column(name="saved_name")
	String savedName;
	
	@Column(name="post_id")
	int postId;
	
	
	public File(String savedName, String originalName, int postId) {
		this.savedName = savedName;
		this.originalName = originalName;
		this.postId = postId;
	}

}
