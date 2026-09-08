package kr.fast.boot.dto;

import kr.fast.boot.entity.Board;

public record BoardResponse(Integer id, String name) {
	
	public static BoardResponse from(Board board) {
		return new BoardResponse(board.getId(), board.getName());
	}
}
