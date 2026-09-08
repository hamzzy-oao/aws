package kr.fast.boot.dto;

import lombok.Data;

@Data //getter/setter/toString/equals를 제공
public class ArticleDTO {
	
	private String search;
	private int page;
}
