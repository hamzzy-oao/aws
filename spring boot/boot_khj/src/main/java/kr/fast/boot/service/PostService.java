package kr.fast.boot.service;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.annotation.PostConstruct;
import kr.fast.boot.dto.PostDTO;
import kr.fast.boot.entity.Post;
import kr.fast.boot.repository.BoardRepository;
import kr.fast.boot.repository.MemberRepository;
import kr.fast.boot.repository.PostRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly=true)
public class PostService {
	
	private final BoardRepository boardRepository;
	private final PostRepository postRepository;
	private final MemberRepository memberRepository;
	
	@Value("${file.path}")
	private String uploadFilePath;
	
	@PostConstruct
	public void init() {
		File dir = new File(uploadFilePath);
		if(!dir.exists()) {
			dir.mkdirs();
		}
		System.out.println(uploadFilePath);
	}
	


	@Transactional
	public int insertPost(PostDTO dto, String username) {
		// 입력값 예외처리
		if(dto == null || !dto.checkTitleValid()){
			throw new IllegalArgumentException("제목을 입력하세요.");
		}
		if(dto == null || !dto.checkContentValid()){
			throw new IllegalArgumentException("내용을 입력하세요.");
		}
		//게시판 번호 체크
		if(!boardRepository.existsById(dto.boardId())){
			throw new IllegalArgumentException("잘못된 게시판입니다.");
		}
		
		if(username == null || username.equals("anonymousUser")) {
			throw new IllegalArgumentException("로그인이 필요합니다.");
		}
		try{
			//post엔티티 생성
			Post post = new Post(dto.title(),dto.content(),username,dto.boardId());
			Post savedPost = postRepository.save(post);
			return savedPost.getId();
			
		}catch(Exception e) {
			e.printStackTrace();
			throw new IllegalArgumentException("쿼리 실행 중 이상이 생겼습니다.");
		}
	}

	@Transactional
	public List<Post> getPostList() {
		// List<Post> list = postRepository.findAll(); // 최신 게시글이 제일 마지막
		List<Post> list = postRepository.findAllByIsDeletedOrderByIdDesc("N"); // 최신 게시글이 제일 위
		
		return list;
	}
	
	@Transactional
	public Post getPost(int id) {
		
		Post post
		= postRepository.findById(id)
		.orElseThrow(()-> new IllegalArgumentException("게시글이 존재하지 않습니다."));
		return post;
	}

	@Transactional
	public void updateView(int id) {
		Post post = getPost(id);
		post.updateView();
	}

	@Transactional
	public void deletePost(int id, String username) {
		//레포야 게시글 가져와 id줄게 단, 없으면 예외 발생 시켜
		Post post = postRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("게시글이 존재하지 않습니다."));
		
		if(post.getIsDeleted().equals ("Y")) {
			throw new IllegalArgumentException("이미 삭제된 게시글 입니다.");
		}
		
		if(!post.getMemberId().equals(username)) {
			throw new IllegalArgumentException("작성자가 아닙니다.");
		}
		
		//레포야 게시글 삭제해줘. 게시글 줄게
		//소프트 삭제 : 실제 데이터 안지움
		post.delete();
		
		//직접 데이터 삭제
		//postRepository.delete(post);
	}

	@Transactional
	public void updatePost(int id, PostDTO dto, String username) {
		//id와 일치하는 게시글을 가져옴
		Post post = postRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("게시글이 존재하지 않습니다."));
		
		if(!post.getMemberId().equals(username)) {
			throw new IllegalArgumentException("작성자가 아닙니다.");
		}
		
		//수정할 제목과 내용체크
		if(dto == null || !dto.checkTitleValid()) {
			throw new IllegalArgumentException("제목을 입력하세요.");
		}
		if(dto == null || !dto.checkContentValid()) {
			throw new IllegalArgumentException("제목을 입력하세요.");
		}
		//게시글의 제목과 내용을 수정
		post.update(dto.title(),dto.content());
		
	}

		

}
