package kr.fast.diary.dto;

import java.util.List;

public record DiaryStatResponse(
		
	    List<EmotionStatResponse> emotionStats,
	    List<MonthlyCountResponse> monthlyStats
	) {}