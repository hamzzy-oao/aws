package kr.fast.boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.fast.boot.entity.Member;

// JpaRepository<엔티티명, 엔티티의 기본키타입>
public interface MemberRepository extends JpaRepository<Member, String> {

	boolean existsByEmail(String email);


}
