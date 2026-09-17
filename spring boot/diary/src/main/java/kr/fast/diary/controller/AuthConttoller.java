package kr.fast.diary.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import kr.fast.diary.dto.LoginRequest;
import kr.fast.diary.dto.LoginResponse;
import kr.fast.diary.dto.MessageResponse;
import kr.fast.diary.dto.SignupRequest;
import kr.fast.diary.entity.EmotionTag;
import kr.fast.diary.security.CustomUserDetails;
import kr.fast.diary.service.AuthService;
import kr.fast.diary.service.DiaryService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/auth")
public class AuthConttoller {
	
	private final DiaryService diaryService;
	private final AuthService authService;


	@GetMapping("/test")
	@ResponseBody
	public ResponseEntity<List<EmotionTag>> test() {
		
		List<EmotionTag> list = diaryService.getEmotionTags();
		
		return ResponseEntity.ok(list);
	}
	
	@PostMapping("/signup")
	@ResponseBody
	public ResponseEntity<MessageResponse> signup(@RequestBody SignupRequest request ){
		
		try {
			MessageResponse messgeresponse = authService.signup(request);
			return ResponseEntity.ok(messgeresponse);
			
		}catch(Exception e) {
			return ResponseEntity.ok(new MessageResponse(false, e.getMessage(), null));
		}
		
	}
	
	@PostMapping("/login")
	@ResponseBody
	public ResponseEntity<LoginResponse> Login(@RequestBody LoginRequest request ){
		
		try {
			LoginResponse loginResponse = authService.login(request);
			return ResponseEntity.ok(loginResponse);
		}catch(Exception e){
			return ResponseEntity.ok(new LoginResponse(false, null, null, null));
		}
		
	}
	
	@GetMapping("/me")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> me(@AuthenticationPrincipal CustomUserDetails userDetails){
		Map<String, Object> map = new HashMap<String, Object>();
		if(userDetails != null) {
			map.put("email", userDetails.getEmail());
			map.put("nickname", userDetails.getNickName());
			map.put("UserId", userDetails.getUserId());
			List<String> list = new ArrayList<String>();
			for(GrantedAuthority tmp : userDetails.getAuthorities()) {
				list.add(tmp.getAuthority());
			}
			map.put("role", list);
		}
		return ResponseEntity.ok(map);
	}
}
