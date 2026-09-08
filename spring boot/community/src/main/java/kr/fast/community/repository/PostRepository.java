package kr.fast.community.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import kr.fast.community.entity.Post;

public interface PostRepository extends JpaRepository<Post, Integer>{


	Page<Post> findAllByIsDeletedContaining(String isDeleted, Pageable pageble);

	Page<Post> findAllByIsDeletedAndTitleContaining(String isDeleted, String keyword, Pageable pageble);

	Page<Post> findAllByIsDeletedAndMemberIdContaining(String isDeleted, String keyword, Pageable pageble);

	Post findByIdAndIsDeleted(int id, String isDeleted);

	@Modifying
	@Query("update Post p set"
	         + "   p.upCount = (select count(*) from Like pl where pl.postId = :postId and pl.state = 1),"
	         + " p.downCount = (select count(*) from Like pl where pl.postId = :postId and pl.state = -1)"
	         + " where p.id = :postId")
	void updateLikeAndDislikeCount(@Param("postId") int id);

}
