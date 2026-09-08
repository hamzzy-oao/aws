package kr.fast.boot.dto;

public record PostDTO(
		String title, 
		String content, 
		Integer boardId, 
		String writer) {
	
	//유효한 제목인지 체크
	public boolean checkTitleValid() {
		if(title == null || title.trim().length() == 0) {
			return false;
		}
		return true;
	}
	
	//유효한 내용인지 체크
	public boolean checkContentValid() {
		if(content == null || content.trim().length() == 0) {
			return false;
		}
		return true;
	}
}
