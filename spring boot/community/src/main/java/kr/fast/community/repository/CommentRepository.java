package kr.fast.community.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import kr.fast.community.entity.Comment;

public interface CommentRepository extends JpaRepository <Comment, Integer> {

	Page<Comment> findAllByPostId(int id, Pageable pageble);
}
