package kr.fast.community.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import kr.fast.community.dto.CommentRequest;
import kr.fast.community.dto.LikeRequest;
import kr.fast.community.dto.MessageResponse;
import kr.fast.community.dto.PageResponse;
import kr.fast.community.dto.PostRequest;
import kr.fast.community.entity.Comment;
import kr.fast.community.entity.File;
import kr.fast.community.entity.Post;
import kr.fast.community.security.CustomUserDetails;
import kr.fast.community.service.BoardService;
import kr.fast.community.service.PostService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/posts")
@AllArgsConstructor
public class PostController {
	
	private final PostService postService;
	private final BoardService boardService;
	
	@Operation(summary = "게시글 목록 조회", description = "검색어, 타입, 페이지를 이용하여 게시글 목록을 조회")
	@GetMapping("")
	public ResponseEntity<Object> get(
			@Parameter(description = "검색 타입",
					schema = @Schema(type ="String", allowableValues = {"all","title","writer"}))
			@RequestParam(required = false, defaultValue = "all", name ="type" )String type,
			@Parameter(description = "검색어")
			@RequestParam(required = false, defaultValue = "", name ="keyword" )String keyword,
			@Parameter(description = "정렬 방법")
			@PageableDefault(size=10, sort="id", direction = Sort.Direction.DESC)
				Pageable pageble){
		PageResponse<Post> pageResponse = postService.getPosts(type, keyword, pageble);
		return ResponseEntity.ok(pageResponse);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> idGet(@PathVariable("id")int id){
		try {
			Post post = postService.getPost(id);
			List<File> files = postService.getFiles(id);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("post", post);
			map.put("files", files);
			return ResponseEntity.ok(map);
			
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	@PostMapping("")
	public ResponseEntity<Object> post(
			@RequestPart("post") PostRequest request, //화면에서 보낸 게시글 정보
			@RequestPart(value="files", required = false) List<MultipartFile> files,
			@AuthenticationPrincipal CustomUserDetails userDetails //로그인한 회원 정보
			){
		
		MessageResponse ms;
		try {
			ms = postService.insertPost(request,userDetails,files);
		}catch(Exception e) {
			ms = new MessageResponse(false,e.getMessage());
		}
		return ResponseEntity.ok(ms);
	}
	
	@PostMapping("/{id}/comment")
	public ResponseEntity<Object> createComment1(
			@PathVariable("id")int id,
			@RequestBody CommentRequest request,
			@AuthenticationPrincipal CustomUserDetails userDetails
			){
		MessageResponse ms= postService.insertComment(id, request, userDetails);
		return ResponseEntity.ok(ms);
	}
	
	@GetMapping("/{id}/comment")
	public ResponseEntity<Object> getComment(@PathVariable("id")int id,
			@PageableDefault(size=3, sort="originId", direction = Sort.Direction.ASC)
			Pageable pageble){
		//서비스한테 게시글 아이디주고 댓글 목록 가져오라고 시키기
		PageResponse<Comment> pageResponse = postService.getPosts(id, pageble);
		return ResponseEntity.ok(pageResponse);
	}
	
	
	@PostMapping("/{id}/likes")
	public ResponseEntity<Object> likesGet(
			@PathVariable("id")int id,
			@AuthenticationPrincipal CustomUserDetails userDetails,
			@RequestBody LikeRequest request){
		//서비스한테 게시글 아이디주고 댓글 목록 가져오라고 시키기
		MessageResponse ms;
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			int state = postService.like(id,userDetails,request);
			String msg;
			switch(state) {
			case 1: msg = "좋아요를 눌렀습니다."; break;
			case -1: msg = "싫어요를 눌렀습니다."; break;
			default:
				msg = request.state() == 1 ? "좋아요를 취소했습니다." : "싫어요를 취소했습니다.";
			}
			map.put("state", state);
			
			ms = new MessageResponse (true, msg);
			
		}catch(Exception e) {
			ms = new MessageResponse(false, e.getMessage());
		}
		map.put("ms", ms);
		return ResponseEntity.ok(map);
	}
}
