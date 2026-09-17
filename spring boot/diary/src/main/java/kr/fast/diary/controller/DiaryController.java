package kr.fast.diary.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import kr.fast.diary.dto.DiaryCreateResponse;
import kr.fast.diary.dto.DiaryDetailResponse;
import kr.fast.diary.dto.DiaryListResponse;
import kr.fast.diary.dto.DiaryRequest;
import kr.fast.diary.dto.DiaryStatResponse;
import kr.fast.diary.entity.EmotionTag;
import kr.fast.diary.security.CustomUserDetails;
import kr.fast.diary.service.DiaryService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/diary")
public class DiaryController {
	
	private final DiaryService diaryService;
	@Value("${file.upload-path}")
	private String uploadFilePath;

	@GetMapping("/emotion")
	public ResponseEntity<List<EmotionTag>>getEmotion(){
		
		 List<EmotionTag> emotionTags =  diaryService.getEmotion();
		 
		 return ResponseEntity.ok(emotionTags);
	}
	
	@PostMapping("/post")
	public ResponseEntity<DiaryCreateResponse>diaryPost(
			@RequestPart("request") DiaryRequest request,
			@AuthenticationPrincipal CustomUserDetails userDetails,
			@RequestPart(value="files", required = false) List<MultipartFile> files
			){
		
		DiaryCreateResponse response = diaryService.createDiary(request,userDetails,files);
		
		
		return ResponseEntity.ok(response);
	}
		
		
	@GetMapping("/my")
	public ResponseEntity<Page<DiaryListResponse>> getMyDiaries(
	        @AuthenticationPrincipal CustomUserDetails userDetails,
	        @PageableDefault(size = 10, sort = "diaryDate", direction = Sort.Direction.DESC) Pageable pageable) {
	    return ResponseEntity.ok(diaryService.getMyDiaries(userDetails, pageable));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<DiaryDetailResponse> getDiary(@PathVariable("id") Long id) {
	    return ResponseEntity.ok(diaryService.getDiary(id));
	}
	
	@GetMapping("/download")
	public ResponseEntity<Resource> downloadFile(@RequestParam("filename") String filename) throws IOException {
	    Path filePath = Paths.get(uploadFilePath).resolve(filename).normalize();
	    Resource resource = new UrlResource(filePath.toUri());

	    if (!resource.exists()) {
	        throw new IllegalArgumentException("파일을 찾을 수 없습니다.");
	    }

	    String encodedFilename = URLEncoder.encode(resource.getFilename(), StandardCharsets.UTF_8);

	    return ResponseEntity.ok()
	            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename*=UTF-8''" + encodedFilename)
	            .body(resource);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<DiaryCreateResponse> updateDiary(
	        @PathVariable("id") Long id,
	        @RequestPart("request") DiaryRequest request,
	        @AuthenticationPrincipal CustomUserDetails userDetails,
	        @RequestPart(value = "files", required = false) List<MultipartFile> files) {
	    diaryService.updateDiary(id, request, userDetails, files);
	    return ResponseEntity.ok(new DiaryCreateResponse(true, "일기가 수정되었습니다.", id));
	}

	    
	
	@GetMapping("/my/all")
	public ResponseEntity<List<DiaryListResponse>> getAllMyDiaries(
	        @AuthenticationPrincipal CustomUserDetails userDetails) {
	    return ResponseEntity.ok(diaryService.getMyDiaries(userDetails));
	}
	
	@GetMapping("/feed")
	public ResponseEntity<Page<DiaryListResponse>> getFeed(
	        @AuthenticationPrincipal CustomUserDetails userDetails,
	        @RequestParam(value = "keyword", required = false) String keyword,
	        @RequestParam(value = "date", required = false) LocalDate date,
	        @RequestParam(value = "emotionTagId", required = false) Long emotionTagId,
	        @PageableDefault(size = 10, sort = "diaryDate", direction = Sort.Direction.DESC) Pageable pageable) {
	    return ResponseEntity.ok(diaryService.getFeed(userDetails, keyword, date, emotionTagId, pageable));
	}
	
	@GetMapping("/stats")
	public ResponseEntity<DiaryStatResponse> getStats(
	        @AuthenticationPrincipal CustomUserDetails userDetails,
	        @RequestParam(value = "year", required = false) Integer year) {
	    int targetYear = (year != null) ? year : java.time.Year.now().getValue();
	    return ResponseEntity.ok(diaryService.getStats(userDetails, targetYear));
	}
}
