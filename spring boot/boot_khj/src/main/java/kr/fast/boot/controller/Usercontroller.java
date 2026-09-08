package kr.fast.boot.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.fast.boot.dto.SignupDTO;
import kr.fast.boot.service.UserService;
import lombok.AllArgsConstructor;


@Controller //이노테이션이 있어야 컨트롤로가 됨
@AllArgsConstructor //모든 필드와 상수를 매개변수로 하는 생성자를 추가. 의존성 자동 주입
public class Usercontroller {
	
	
	private final UserService userService;
	
	@GetMapping("/user/nickname")
	@ResponseBody
	public String userNickname() {
		// 로그인한 회원 닉네임을 가져왔다 치고
		return "홍길동";
	}
	
	@GetMapping("/user/Signup")
	@ResponseBody 
	public String SignupUser(SignupDTO dto) {
		System.out.println(dto);
		
		return "회원가입이 완료되었습니다.";
	}
	
	@PostMapping("/user/signup")
	@ResponseBody
	public ResponseEntity<String> SignupUserPost(@RequestBody SignupDTO dto) {
		
		try {
			//서비스에게 회원가입 정보를 주면서 가입하라고 시킴
			userService.signup(dto);
			return ResponseEntity.status(HttpStatus.CREATED).body("회원 가입에 성공했습니다.");
		}catch(Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
	}

}
