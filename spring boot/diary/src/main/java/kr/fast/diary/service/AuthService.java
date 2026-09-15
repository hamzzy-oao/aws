package kr.fast.diary.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.fast.diary.security.JwtProvider;
import kr.fast.diary.dto.MessageResponse;
import kr.fast.diary.dto.SignupRequest;
import kr.fast.diary.entity.Member;
import kr.fast.diary.repository.MemberRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class AuthService {

	private final BCryptPasswordEncoder encoder;
	private final MemberRepository memberRepository;
	
	@Transactional
	public MessageResponse signup(SignupRequest request) {
		
		String nickname = request.getNickname();
        String email = request.getEmail();
        String pw = request.getPw();
        String pw2 = request.getPw2();
		
		
		
		//닉네임이 없는 경우 
		if(nickname == null || nickname.length() == 0 ) {
			throw new IllegalArgumentException("닉네임은 필수 항목입니다.");
		}
		
		if(email == null|| email.length() == 0) {
			throw new IllegalArgumentException("이메일은 필수 항목입니다.");
		}
		
		boolean existsByNickname = memberRepository.existsByNickname(request.getNickname());
		if(existsByNickname) {
			throw new IllegalArgumentException("이미 사용중인 닉네임입니다.");
		}
		
		//이메일 중복 검사
		boolean existsByEmail = memberRepository.existsByEmail(request.getEmail());
		if(existsByEmail) {
			throw new IllegalArgumentException("이미 사용중인 이메일입니다.");
		}
		
		if(!pw.equals(pw2)) {
			throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
		}
		
		//비번 암호화
		String encodePw = encoder.encode(request.getPw());
		
		//회원가입(Member 엔티티객체 필요)
		Member member = new Member(email, encodePw, nickname);
		
		
		Member savedMember = memberRepository.save(member);
		
		
		return new MessageResponse(true, "회원가입이 완료되었습니다.", savedMember.getId());
	}
	

	

}
