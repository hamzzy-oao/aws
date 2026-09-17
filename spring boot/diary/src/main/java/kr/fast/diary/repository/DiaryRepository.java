package kr.fast.diary.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import kr.fast.diary.entity.Post;

public interface DiaryRepository extends JpaRepository<Post, Long> {
	
	List<Post> findByUserIdOrderByDiaryDateDesc(Long userId);
	
	Page<Post> findByUserId(Long userId, Pageable pageable);
	
}


