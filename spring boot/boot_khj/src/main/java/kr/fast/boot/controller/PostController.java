package kr.fast.boot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.fast.boot.dto.PostDTO;
import kr.fast.boot.entity.Board;
import kr.fast.boot.entity.Post;
import kr.fast.boot.service.BoardService;
import kr.fast.boot.service.PostService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/post")
@AllArgsConstructor
public class PostController {
	
	
	private final BoardService boardService;
	private final PostService postService;
	
	@GetMapping("/board")
	public ResponseEntity<List<Board>> boardGet(){
		
		List<Board> list = boardService.getBoardList();
		
		return ResponseEntity.ok(list);
		
	}
	
	@PostMapping("")
	public ResponseEntity<Object> boardPost(
			@RequestBody PostDTO dto,
			@AuthenticationPrincipal String username){
		System.out.println(username);
		try {
			int postId = postService.insertPost(dto,username);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("postId",postId);
			map.put("msg", "게시글을 등록했습니다.");
			
			return ResponseEntity.ok(map);
		}catch(Exception e) {
			return ResponseEntity.ok(e.getMessage());
		}
	}
	
	@GetMapping("")
	public ResponseEntity<List<Post>> postGet() {
		List<Post> list = postService.getPostList();
		return ResponseEntity.ok(list);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> idget(@PathVariable("id")int id) {
		try {
			//서비스야 조회수 증가 시켜줘
			postService.updateView(id);
			Post post = postService.getPost(id);
			return ResponseEntity.ok(post);
		}catch(Exception e) {
			return ResponseEntity.ok("ok");
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> idDelete(
			@PathVariable("id")int id,
			@AuthenticationPrincipal String username){
		try {
			postService.deletePost(id,username);
			return ResponseEntity.ok("게시글을 삭제했습니다.");
		}catch(Exception e) {
			return ResponseEntity.ok(e.getMessage());
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> idPut(
			@PathVariable("id")int id,
			@RequestBody PostDTO dto,
			@AuthenticationPrincipal String username){
		try {
			postService.updatePost(id, dto, username);
			return ResponseEntity.ok("게시글을 수정했습니다.");
		}catch(Exception e) {
			return ResponseEntity.ok(e.getMessage());
		}
	}
}
