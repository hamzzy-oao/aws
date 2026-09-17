package kr.fast.diary.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "comment")
@Getter
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @Column(name = "diary_id")
    private Long diaryId;

    @Column(name = "user_id")
    private Long userId;

    private String content;

    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDateTime createdAt;

    public Comment(Long diaryId, Long userId, String content) {
        this.diaryId = diaryId;
        this.userId = userId;
        this.content = content;
    }
    
    public void update(String content) {
        this.content = content;
    }
}
