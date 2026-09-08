package kr.fast.boot.service;

import org.glassfish.jaxb.core.v2.runtime.IllegalAnnotationException;
import org.springframework.http.ResponseCookie;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.fast.boot.dto.LoginDTO;
import kr.fast.boot.dto.TokenDTO;
import kr.fast.boot.entity.Member;
import kr.fast.boot.repository.MemberRepository;
import kr.fast.boot.security.JwtProvider;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Transactional(readOnly = true)
@AllArgsConstructor
@Log4j2
public class AuthService {
	
	private final MemberRepository memberRepository;
	
	private final BCryptPasswordEncoder passwordEncoder;
	
	private final JwtProvider jwtProvider;
	
	@Transactional
	public TokenDTO login(LoginDTO dto) {
		//회원 정보를 가져옴
		Member user = memberRepository.findById(dto.username())
				.orElseThrow(
					()-> new IllegalArgumentException("아이디나 비번이 일치하지 않습니다."));
		//회원의 비번과 화면에서 입력한 비번을 비교해서 다르면 예외 발생
		//matches(원래 비번, 암호화된비번)
		if(!passwordEncoder.matches(dto.password(), user.getPassword())) {
			throw new IllegalArgumentException("아이디나 비번이 일치하지 않습니다.");
		}
		//같으면 토큰을 생성
		String accessToken = jwtProvider.createToken(user.getId(), user.getRole());
		
		String refreshToken = jwtProvider.createRefreshToken(user.getId());
		ResponseCookie refreshCookie = 
				createCookie(
						"refreshToken",
						refreshToken,
						7 * 24 * 60 * 60, 
						"/api/auth/refresh"); 
		return new TokenDTO(accessToken, refreshCookie);
	}

	public String createNewAccessToken(String refreshToken) {
		
		//리프레쉬 토큰이 맞는지
		if(!jwtProvider.isRefreshToken(refreshToken)) {
			throw new IllegalArgumentException("리프레쉬 토큰이 아닙니다.");
		}
		//유효한 리프레쉬 토큰인지 확인
		if(!jwtProvider.validateToken(refreshToken)) {
			throw new IllegalArgumentException("만료된 리프레쉬 토큰입니다.");
		}
		
		String username = jwtProvider.getUsername(refreshToken);
		Member user = memberRepository.findById(username)
				.orElseThrow(()-> new IllegalArgumentException("아이디나 비번이 일치하지 않습니다."));
		String role =user.getRole();
		
		//새 어세스 토큰을 생성
		String accessToken = jwtProvider.createToken(username, role);
		//생성한 어세스 토큰을 리턴
		return accessToken;
	}

	public ResponseCookie createCookie(String cookieName, String value, int maxAge, String path) {
		
		return ResponseCookie.from(cookieName,value)
				.httpOnly(true) //js 접근 불가
				.secure(false) //http에서도 전송되도록
				.sameSite("Lax") //다른 사이트에서 요청 시 전송 제한
				.path(path) //해당 url 요청시에만 쿠키전송
				.maxAge(maxAge) //쿠키 유효기간 7일
				.build();
	}
	
}