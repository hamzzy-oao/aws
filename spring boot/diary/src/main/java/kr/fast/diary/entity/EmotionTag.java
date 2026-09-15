package kr.fast.diary.entity;

import java.util.Date;

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
@Table(name = "emotion_tag")
@Getter //필드들의 getter를 추가
@NoArgsConstructor //기본 생성자
@AllArgsConstructor // 모든 필드를 매개변수로 하는 생성자를 추가
@ToString
public class EmotionTag {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "emotion_tag_id")
	private String id;
	
	
	private String name;
	
	
	private String emoji;
	
	@Column(name = "display_order")
	private Integer displayOrder;

}
