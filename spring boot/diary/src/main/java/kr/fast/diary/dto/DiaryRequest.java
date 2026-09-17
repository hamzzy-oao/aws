package kr.fast.diary.dto;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public record DiaryRequest (
		@JsonFormat(pattern = "yyyy-MM-dd")
		LocalDate diaryDate, 
		String title,
		String content,
		List<Long> emotionTagIds,
		Boolean isPublic){
	


}
