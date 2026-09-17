package kr.fast.diary.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import kr.fast.diary.entity.Post;

public interface DiaryRepository extends JpaRepository<Post, Long> {
	
	List<Post> findByUserIdOrderByDiaryDateDesc(Long userId);
	
	Page<Post> findByUserId(Long userId, Pageable pageable);
	
	@Query("SELECT DISTINCT p FROM Post p LEFT JOIN p.emotionTags e " +
		       "WHERE (p.userId = :userId OR p.isPublic = true) " +
		       "AND (:keyword IS NULL OR p.title LIKE %:keyword% OR p.content LIKE %:keyword%) " +
		       "AND (:date IS NULL OR p.diaryDate = :date) " +
		       "AND (:emotionTagId IS NULL OR e.id = :emotionTagId)")
		Page<Post> searchFeed(@Param("userId") Long userId,
		                       @Param("keyword") String keyword,
		                       @Param("date") LocalDate date,
		                       @Param("emotionTagId") Long emotionTagId,
		                       Pageable pageable);
	
	@Query("SELECT e.name, COUNT(p) FROM Post p JOIN p.emotionTags e " +
		       "WHERE p.userId = :userId AND YEAR(p.diaryDate) = :year " +
		       "GROUP BY e.name")
		List<Object[]> countEmotionByYear(@Param("userId") Long userId, @Param("year") int year);

		@Query("SELECT MONTH(p.diaryDate), COUNT(p) FROM Post p " +
		       "WHERE p.userId = :userId AND YEAR(p.diaryDate) = :year " +
		       "GROUP BY MONTH(p.diaryDate) ORDER BY MONTH(p.diaryDate)")
		List<Object[]> countByMonth(@Param("userId") Long userId, @Param("year") int year);
	
	
}


