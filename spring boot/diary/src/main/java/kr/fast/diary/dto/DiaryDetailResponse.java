package kr.fast.diary.dto;

import java.time.LocalDate;
import java.util.List;

import kr.fast.diary.entity.EmotionTag;
import kr.fast.diary.entity.Post;

public record DiaryDetailResponse(
	    Long id,
	    LocalDate diaryDate,
	    String title,
	    String content,
	    String imageUrl,
	    boolean isPublic,
	    List<String> emotionTags
	) {
	    public static DiaryDetailResponse from(Post post) {
	        return new DiaryDetailResponse(
	            post.getId(), post.getDiaryDate(), post.getTitle(),
	            post.getContent(), post.getImageUrl(), post.isPublic(),
	            post.getEmotionTags().stream().map(EmotionTag::getName).toList()
	        );
	    }
	}