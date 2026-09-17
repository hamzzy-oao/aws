package kr.fast.diary.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.fast.diary.entity.EmotionTag;

public interface EmojiRepository extends JpaRepository<EmotionTag, String> {

	List<EmotionTag> findAllByOrderByDisplayOrderAsc();

	Optional<EmotionTag> findById(Long tagId);

	List<EmotionTag> findAllByIdIn(List<Long> emotionTagIds);

}
