package kr.fast.diary.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.fast.diary.entity.EmotionTag;
import kr.fast.diary.repository.EmojiRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class DiaryService {
	
	 private final EmojiRepository emojiRepository;
	
	public List<EmotionTag> getEmotionTags() {
		
		
		return emojiRepository.findAllByOrderByDisplayOrderAsc();
	}
	
	


}
