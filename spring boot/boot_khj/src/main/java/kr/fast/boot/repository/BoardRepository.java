package kr.fast.boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.fast.boot.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Integer> {

	boolean existsByName(String board);

	boolean existsByIdNotAndName(Integer id, String name);



}
