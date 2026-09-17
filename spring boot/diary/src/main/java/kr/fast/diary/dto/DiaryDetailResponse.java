package kr.fast.diary.dto;

import java.time.LocalDate;
import java.util.List;

import kr.fast.diary.entity.EmotionTag;
import kr.fast.diary.entity.Post;

public record DiaryDetailResponse(
	    Long id,
	    Long userId,
	    LocalDate diaryDate,
	    String title,
	    String content,
	    String imageUrl,
	    boolean isPublic,
	    List<Long> emotionTagIds,
	    List<String> emotionTags
	) {
	public static DiaryDetailResponse from(Post post) {
        List<Long> tagIds = post.getEmotionTags().stream()
                .map(EmotionTag::getId)
                .toList();

        return new DiaryDetailResponse(
            post.getId(), post.getUserId(), post.getDiaryDate(), post.getTitle(),
            post.getContent(), post.getImageUrl(), post.isPublic(), tagIds,
            post.getEmotionTags().stream().map(EmotionTag::getName).toList()
        );
    }

}
	