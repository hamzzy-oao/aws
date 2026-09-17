package kr.fast.diary.dto;

import java.time.LocalDate;
import java.util.List;

import kr.fast.diary.entity.EmotionTag;
import kr.fast.diary.entity.Post;

public record DiaryListResponse(
	    Long id,
	    LocalDate diaryDate,
	    String title,
	    String imageUrl,
	    boolean isPublic,
	    List<String> emotionTags
	) {
	    public static DiaryListResponse from(Post post) {
	        return new DiaryListResponse(
	            post.getId(), post.getDiaryDate(), post.getTitle(),
	            post.getImageUrl(), post.isPublic(),
	            post.getEmotionTags().stream().map(EmotionTag::getEmoji).toList()
	        );
	    }
	}