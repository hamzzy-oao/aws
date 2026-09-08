package kr.fast.boot.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import kr.fast.boot.dto.BoardDTO;
import kr.fast.boot.dto.BoardResponse;
import kr.fast.boot.entity.Board;
import kr.fast.boot.service.BoardService;
import lombok.AllArgsConstructor;

@RestController //@ResponseBody + @Controller
@RequestMapping("/api/admin")
@AllArgsConstructor
public class AdminController {
	
	private final BoardService boardService;
	
	//url : /api/admin/board
	//method : post를 처리하는 메서드를 추가
	//  리턴 : "ok"문자열을 리턴
	
	@PostMapping("/board")
	@ResponseBody
	public ResponseEntity<String> sendBoardPost(@RequestBody BoardDTO dto) {
		//게시판 서비스야 새 게시판 등록해줘 여기 새 게시판명이 있어 등록하고 결과를 알려줘
		try {
			boolean isInsert = boardService.insertBoard(dto);
			return ResponseEntity.ok("게시판을 등록했습니다.");
		}catch(Exception e) {
			return ResponseEntity.ok(e.getMessage());
		}
		
	}
	
	//url : /api/admin/board
	//method : put
	//  리턴 : "ok"문자열을 리턴
	
	@PutMapping("/board")
	public ResponseEntity<String> boardPut(@RequestBody BoardDTO dto) {
		try {
			//서비스야. 게시판 번호와 이름을 줄게. 게시판명을 수정해줘
			boardService.updateBoard(dto);
			return ResponseEntity.ok("게시판을 수정했습니다.");
		}catch(Exception e) {
			return ResponseEntity.ok(e.getMessage());
		}
	}
	
	@GetMapping("/board")
	public ResponseEntity<List<Board>> boardGet() {
		//서비스야. 게시판 목록을 가져와줘
		List<Board> list = boardService.getBoardList();
		return ResponseEntity.ok(list);
	}

	//메서드를 추가해서 화면에 ok가 출력되도록 작업
	
	@DeleteMapping("/board")
	public ResponseEntity<String> boardDelete(@RequestBody BoardDTO dto) {
		try {
			//서비스야. 게시판 번호 줄게 게시판 삭제해줘
			boardService.deleteBoard(dto.id());
			return ResponseEntity.ok("게시판을 삭제하였습니다.");
		}catch(Exception e) {
			return ResponseEntity.ok(e.getMessage());
		}
	}
	
}
