package kr.fast.boot.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletResponse;
import kr.fast.boot.dto.LoginDTO;
import kr.fast.boot.dto.TokenDTO;
import kr.fast.boot.service.AuthService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/api/auth")
@Log4j2
@AllArgsConstructor
public class AuthController {

	private final AuthService authService;
	
	@PostMapping("/login")
	public ResponseEntity<Object> loginPost(
			@RequestBody LoginDTO dto,
			HttpServletResponse response){
		log.info("로그인 중입니다.");
		try {
			TokenDTO tokenDto = authService.login(dto);
			response.addHeader(HttpHeaders.SET_COOKIE,tokenDto.refreshCookie().toString());
			return ResponseEntity.ok(tokenDto);
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
		}
	}
	@GetMapping("/me")
	public ResponseEntity<Object> meGet(@AuthenticationPrincipal String username){
		log.info("회원 정보 가져오는 중입니다.");
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("username", username);
			return ResponseEntity.ok(map);
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
		}
	}
	
	@PostMapping("/refresh")
	public ResponseEntity<Object> refreshPost(
			@CookieValue(name="refreshToken", required = false)String refreshToken,
			HttpServletResponse response){
		log.info("토큰 재발급중입니다.");
		try {
			String accessToken =authService.createNewAccessToken(refreshToken);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("accessToken", accessToken);
			return ResponseEntity.ok(map);
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
		}
	}
	
	@PostMapping("/logout")
	public ResponseEntity<Object> refreshPost(HttpServletResponse response){
		ResponseCookie cookie =
				authService.createCookie("refreshToken","",0,"api/auth/refresh");
		response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
		return ResponseEntity.ok().build();
	}
}