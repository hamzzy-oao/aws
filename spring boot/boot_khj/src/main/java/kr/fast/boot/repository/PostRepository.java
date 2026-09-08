package kr.fast.boot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.fast.boot.entity.Board;
import kr.fast.boot.entity.Post;


public interface PostRepository extends JpaRepository<Post, Integer> {

	List<Post> findAllByIsDeletedOrderByIdDesc(String isDeleted);





}