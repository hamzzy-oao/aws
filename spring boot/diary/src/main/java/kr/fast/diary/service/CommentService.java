package kr.fast.diary.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.fast.diary.dto.CommentRequest;
import kr.fast.diary.dto.CommentResponse;
import kr.fast.diary.entity.Comment;
import kr.fast.diary.entity.Member;
import kr.fast.diary.entity.Post;
import kr.fast.diary.repository.CommentRepository;
import kr.fast.diary.repository.DiaryRepository;
import kr.fast.diary.repository.MemberRepository;
import kr.fast.diary.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {
	
	private final MemberRepository membereepository;
	private final CommentRepository commentrepository;
	private final DiaryRepository diaryrepository;


	@Transactional
	public CommentResponse createComment(Long diaryId, CommentRequest request, CustomUserDetails userDetails) {
	    Post post = diaryrepository.findById(diaryId)
	            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 일기입니다."));

	    if (!post.isPublic()) {
	        throw new IllegalArgumentException("공개된 일기에만 댓글을 작성할 수 있습니다.");
	    }

	    Comment comment = new Comment(diaryId, userDetails.getUserId(), request.content());
	    Comment saved = commentrepository.save(comment);

	    return new CommentResponse(saved.getId(), userDetails.getUserId(), userDetails.getNickName(),
	            saved.getContent(), saved.getCreatedAt());
	}

	@Transactional(readOnly = true)
	public List<CommentResponse> getComments(Long diaryId) {
	    List<Comment> comments = commentrepository.findByDiaryIdOrderByCreatedAtAsc(diaryId);
	    return comments.stream()
	            .map(c -> {
	                Member member = membereepository.findById(c.getUserId()).orElse(null);
	                String nickname = (member != null) ? member.getNickname() : "알 수 없음";
	                return new CommentResponse(c.getId(), c.getUserId(), nickname, c.getContent(), c.getCreatedAt());
	            })
	            .toList();
	}


	@Transactional
	public void deleteComment(Long commentId, CustomUserDetails userDetails) {
	    Comment comment = commentrepository.findById(commentId)
	            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 댓글입니다."));

	    if (!comment.getUserId().equals(userDetails.getUserId())) {
	        throw new IllegalArgumentException("삭제 권한이 없습니다.");
	    }

	    commentrepository.delete(comment);
	}
	
	@Transactional
	public void updateComment(Long commentId, CommentRequest request, CustomUserDetails userDetails) {
	    Comment comment = commentrepository.findById(commentId)
	            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 댓글입니다."));

	    if (!comment.getUserId().equals(userDetails.getUserId())) {
	        throw new IllegalArgumentException("수정 권한이 없습니다.");
	    }

	    comment.update(request.content());
	}
}
