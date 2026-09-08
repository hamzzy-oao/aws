package kr.fast.boot.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.fast.boot.dto.BoardDTO;
import kr.fast.boot.dto.BoardResponse;
import kr.fast.boot.entity.Board;
import kr.fast.boot.repository.BoardRepository;
import lombok.AllArgsConstructor;

@Service
@Transactional(readOnly=true)
@AllArgsConstructor
public class BoardService {
	
	private final BoardRepository boardRepository;
	
	@Transactional
	public boolean insertBoard(BoardDTO dto) {
		//게시판 이름이 null이거나 빈 문자열이면 false를 리턴
		if( dto == null || dto.name() == null || dto.name().trim().length() == 0 ) {
			throw new IllegalArgumentException("게시판명이 잘못됐습니다.");
		}
		
		//레포지토리야. 게시판명이 중복되는지 알려줘.게시판명을 줄테니 
		boolean isExists = boardRepository.existsByName(dto.name());
		if(isExists) {
			throw new IllegalArgumentException("이미 등록된 게시판입니다.");
		}
		
		//게시판 등록
		
		//게시판 등록을 위해 엔티티 객체를 생성
		Board board = new Board(dto.name());
		//생성한 엔티티를 저장해서 DB에 추가
		Board insertedBoard = boardRepository.save(board);
		
		return true;
	}
	
	@Transactional
	public void updateBoard(BoardDTO board) {
		//레포야 id로 게시판 가져와
		Board selectedBoard 
			= boardRepository.findById(board.id())
			.orElseThrow(()-> new IllegalArgumentException("게시판이 존재하지 않습니다."));
		//바꾸려는 게시판명이 이미 있는 경우를 처리
		boolean isExsist = boardRepository.existsByIdNotAndName(board.id(),board.name());
		if(isExsist) {
			throw new IllegalArgumentException("이미 존재하는 게시판입니다.");
		}
		
		//기존 게시판명과 동일한 경우
		if(selectedBoard.getName().equals(board.name())) {
			throw new IllegalArgumentException("수정할 게시판명을 입력하세요.");
		}
		
		selectedBoard.update(board.name());
	}
	
	@Transactional
	public List<Board> getBoardList() {
		//래포야 게시판 목록 가져와
		List<Board> list = boardRepository.findAll();
		
		return list;
	}

	@Transactional
	public void deleteBoard(Integer id) {
		;
		//레포에게 id주면서 게시판 가져오라고 요청
		Board delBoard 
		= boardRepository.findById(id)
			.orElseThrow(()-> new IllegalArgumentException("게시판이 존재하지 않습니다."));
		boardRepository.delete(delBoard);
		
		
	}
}