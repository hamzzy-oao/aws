package kr.fast.diary.dto;

import java.time.LocalDateTime;

public record CommentResponse(Long id, Long userId, String nickname, String content, LocalDateTime createdAt) {}
