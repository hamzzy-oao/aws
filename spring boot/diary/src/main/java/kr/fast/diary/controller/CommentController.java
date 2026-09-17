package kr.fast.diary.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.fast.diary.dto.CommentRequest;
import kr.fast.diary.dto.CommentResponse;
import kr.fast.diary.security.CustomUserDetails;
import kr.fast.diary.service.CommentService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/diary/")
public class CommentController {
	
	private final CommentService commentService;

	@PostMapping("/{diaryId}/comments")
	public ResponseEntity<CommentResponse> createComment(
	        @PathVariable("diaryId") Long diaryId,
	        @RequestBody CommentRequest request,
	        @AuthenticationPrincipal CustomUserDetails userDetails) {
	    return ResponseEntity.ok(commentService.createComment(diaryId, request, userDetails));
	}

	@GetMapping("/{diaryId}/comments")
	public ResponseEntity<List<CommentResponse>> getComments(@PathVariable("diaryId") Long diaryId) {
	    return ResponseEntity.ok(commentService.getComments(diaryId));
	}

	@DeleteMapping("/comments/{commentId}")
	public ResponseEntity<Void> deleteComment(
	        @PathVariable("commentId") Long commentId,
	        @AuthenticationPrincipal CustomUserDetails userDetails) {
		commentService.deleteComment(commentId, userDetails);
	    return ResponseEntity.ok().build();
	}
	
	@PutMapping("/comments/{commentId}")
	public ResponseEntity<Void> updateComment(
	        @PathVariable("commentId") Long commentId,
	        @RequestBody CommentRequest request,
	        @AuthenticationPrincipal CustomUserDetails userDetails) {
		commentService.updateComment(commentId, request, userDetails);
	    return ResponseEntity.ok().build();
	}
	
	

}
