package kr.fast.community.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import kr.fast.community.entity.Member;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, String>{
	
	boolean existsByEmail(String email);
	
	
	
	
}
