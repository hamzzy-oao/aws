package kr.fast.diary.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "diary")
@Getter //필드들의 getter를 추가
@NoArgsConstructor //기본 생성자
@AllArgsConstructor // 모든 필드를 매개변수로 하는 생성자를 추가
@ToString
public class Post {
	
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "diary_id")
	private Long id;
	
	@Column(name = "user_id")
	private Long userId;
	
	@Column(name="diary_date")
	private LocalDate diaryDate;
	
	private String title;
	
	private String content;
	
	@Column(name = "image_url")
	private String imageUrl;
	
	@Column(name = "is_public", nullable = false)
	private boolean isPublic = false;
	
	@Column(name="created_at",insertable = false,updatable = false)
	private LocalDateTime createdAt;
	
	@Column(name="updated_at",insertable = false,updatable = false)
	private LocalDateTime updatedAt;
	
	
	@ManyToMany
    @JoinTable(
        name = "diary_emotion",

        joinColumns = @JoinColumn(
            name = "diary_id"
        ),

        inverseJoinColumns = @JoinColumn(
            name = "emotion_tag_id"
        )
    )
    private Set<EmotionTag> emotionTags =
        new LinkedHashSet<>();
	
	public Post(
			Long userId,
            LocalDate localDate,
            String title,
            String content,
            String imageUrl,
            boolean isPublic
            
    ) {
		this.userId = userId;
        this.diaryDate = localDate;
        this.title = title;
        this.content = content;
        this.imageUrl = imageUrl;
        this.isPublic = isPublic;
    }

    // 선택한 감정 태그를 일기에 추가합니다.
    public void addEmotionTag(EmotionTag emotionTag) {
        this.emotionTags.add(emotionTag);
    }

    public void update(String title, String content, LocalDate diaryDate, Boolean isPublic, String imageUrl) {
        this.title = title;
        this.content = content;
        this.diaryDate = diaryDate;
        this.isPublic = isPublic;
        this.imageUrl = imageUrl;
    }

}
	


