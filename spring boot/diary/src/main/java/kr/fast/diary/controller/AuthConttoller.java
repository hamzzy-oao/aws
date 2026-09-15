package kr.fast.diary.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import kr.fast.diary.dto.MessageResponse;
import kr.fast.diary.dto.SignupRequest;
import kr.fast.diary.entity.EmotionTag;
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
	public ResponseEntity<> Login(){
		
	}
}
