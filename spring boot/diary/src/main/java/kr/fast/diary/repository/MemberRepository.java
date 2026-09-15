package kr.fast.diary.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.fast.diary.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

	boolean existsByNickname(Object nickname);
	
	boolean existsByEmail(String email);

}
