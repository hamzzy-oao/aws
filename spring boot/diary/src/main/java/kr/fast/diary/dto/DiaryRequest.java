package kr.fast.diary.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

public record DiaryRequest (
		@JsonFormat(pattern = "yyyy-MM-dd")
		LocalDate diaryDate, 
		String title,
		String content,
		Long emotionTagId,
		Boolean isPublic){
	


}
