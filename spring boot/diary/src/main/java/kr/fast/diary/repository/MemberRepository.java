package kr.fast.diary.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.fast.diary.entity.Member;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

	Member findByEmail(String email);

	boolean existsByNickname(String nickname);

	boolean existsByEmail(String email);



	



}
