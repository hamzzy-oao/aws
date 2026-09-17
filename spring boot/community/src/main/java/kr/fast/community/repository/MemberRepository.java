package kr.fast.community.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import kr.fast.community.entity.Member;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, String>{
	
    Optional<Member> findByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByNickname(String nickname);
	
	
	
	
}
