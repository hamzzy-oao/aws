package kr.fast.boot.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.fast.boot.dto.ArticleDTO;
import lombok.extern.log4j.Log4j2;


@Controller
@Log4j2

public class ArticleController {
	
	@ResponseBody // 리턴값을 그대로 전달 (뷰리졸버 거치지 말고)
	
	@GetMapping("/aritcle/{num}")
	public String article(@PathVariable("num")int articleNum) {
		
		return articleNum +"번 기사 입니다.";
	}
	
	@GetMapping("/article1")
	@ResponseBody
	public String articles1(
			@RequestParam("search")String search,
			@RequestParam(value="page",required = false, defaultValue = "1") int page){
		log.info("방법1");
		log.info("검색어 : "+ search);
		log.info("페이지 : "+ page);
			
		return "검색 결과입니다.";
	}
	
	@GetMapping("/article2")
	@ResponseBody
	public String articles2(@RequestParam Map<String, Object>map) {
		log.info("방법2");
		log.info("검색어 : "+ map.get("search"));
		log.info("페이지 : "+ map.get("page"));
		return "검색 결과입니다.";
	}
	
	@GetMapping("/article3")
	@ResponseBody
	public String articles3(ArticleDTO dto) {
		log.info("방법3");
		log.info("검색어 : " + dto.getSearch());
		log.info("페이지 : " + dto.getPage());
		return "검색 결과입니다.";
	}
	
	@ResponseBody // 리턴값을 그대로 전달 (뷰리졸버 거치지 말고)
	@GetMapping("/aritcle/list1")
	public Map<String, Object>articleList1() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("weiter", "홍길동");
		map.put("article", "기사내용입니다.");
		return map;
	}
	
	@ResponseBody // 리턴값을 그대로 전달 (뷰리졸버 거치지 말고)
	@GetMapping("/aritcle/list2")
	public ArrayList<String> articleList2() {
		ArrayList<String> articles = new ArrayList<String>();
		articles.add("기사1");
		articles.add("기사2");
		articles.add("기사3");
		return articles;
	}
	
	@ResponseBody // 리턴값을 그대로 전달 (뷰리졸버 거치지 말고)
	@GetMapping("/aritcle/list3")
	public ResponseEntity<Object> articleList3() {
		ArticleDTO dto = new ArticleDTO();
		dto.setSearch("기사");
		return ResponseEntity.ok(dto);
	}
		
}
