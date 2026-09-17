package kr.fast.diary.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import kr.fast.diary.dto.DiaryCreateResponse;
import kr.fast.diary.dto.DiaryDetailResponse;
import kr.fast.diary.dto.DiaryListResponse;
import kr.fast.diary.dto.DiaryRequest;
import kr.fast.diary.entity.EmotionTag;
import kr.fast.diary.entity.Post;
import kr.fast.diary.repository.DiaryRepository;
import kr.fast.diary.repository.EmojiRepository;
import kr.fast.diary.security.CustomUserDetails;
import kr.fast.diary.utils.FileUtils;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DiaryService {
	
	 private final EmojiRepository emojiRepository;
	 private final DiaryRepository diaryRepository;
	 @Value("${file.upload-path}")
	 private String uploadFilePath;
	 
	
	@Transactional
	public List<EmotionTag> getEmotionTags() {
		
		
		return emojiRepository.findAllByOrderByDisplayOrderAsc();
	}

	@Transactional
	public List<EmotionTag> getEmotion() {
		
		return emojiRepository.findAllByOrderByDisplayOrderAsc();
	}

	@Transactional
	public DiaryCreateResponse createDiary(DiaryRequest request, CustomUserDetails userDetails,
			List<MultipartFile> files) {
		
		if (request.diaryDate() == null) {
            	throw new IllegalArgumentException("날짜를 입력해주세요.");
        }
		
		if (request.title() == null || request.title().trim().length() == 0 ) {
        	throw new IllegalArgumentException("제목을 입력해주세요.");
		}
		
		if (request.content() == null || request.content().trim().length() == 0 ) {
        	throw new IllegalArgumentException("본문을 입력해주세요.");
		}
		
		if (request.emotionTagId() == null) {
			throw new IllegalArgumentException("감정태그를 선택해주세요.");
		}
		
		String imageUrl = null;
		
		if (files != null && !files.isEmpty()) {
		
			if (files.size() > 1) {
				throw new IllegalArgumentException("이미지는 한 장만 첨부할 수 있습니다.");
			}
			MultipartFile file = files.get(0);
			if (!file.isEmpty()) {
				imageUrl = FileUtils.saveFile(uploadFilePath,file);
			}
		}
		
		
			
		
		Post diary = new Post(
		        userDetails.getUserId(),
		        request.diaryDate(),
		        request.title(),
		        request.content(),
		        imageUrl,
		        Boolean.TRUE.equals(request.isPublic())
		);
		
		EmotionTag emotionTag = emojiRepository.findById(request.emotionTagId())
		        .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 감정태그입니다."));

		diary.addEmotionTag(emotionTag);
		
		
		 Post savedDiary = diaryRepository.save(diary);
		
		
		
		return new DiaryCreateResponse(true,"일기가 저장되었습니다.",savedDiary.getId()
		);
		
	}

	@Transactional
	public List<DiaryListResponse> getMyDiaries(CustomUserDetails userDetails) {
	    List<Post> posts = diaryRepository.findByUserIdOrderByDiaryDateDesc(userDetails.getUserId());
	    return posts.stream().map(DiaryListResponse::from).toList();
	}
	
	@Transactional
	public Page<DiaryListResponse> getMyDiaries(CustomUserDetails userDetails, Pageable pageable) {
	    return diaryRepository.findByUserId(userDetails.getUserId(), pageable)
	            .map(DiaryListResponse::from);
	}

	@Transactional
	public DiaryDetailResponse getDiary(Long id) {
	    Post post = diaryRepository.findById(id)
	            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 일기입니다."));

	    return DiaryDetailResponse.from(post);
	}

	@Transactional
	public void updateDiary(Long id, DiaryRequest request, CustomUserDetails userDetails, List<MultipartFile> files) {
	    Post post = diaryRepository.findById(id)
	            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 일기입니다."));

	    if (!post.getUserId().equals(userDetails.getUserId())) {
	        throw new IllegalArgumentException("수정 권한이 없습니다.");
	    }

	    String imageUrl = post.getImageUrl();
	    if (files != null && !files.isEmpty()) {
	        imageUrl = FileUtils.saveFile(uploadFilePath, files.get(0));
	    }

	    post.update(request.title(), request.content(), request.diaryDate(),
	            Boolean.TRUE.equals(request.isPublic()), imageUrl);

	    if (request.emotionTagId() != null) {
	        EmotionTag emotionTag = emojiRepository.findById(request.emotionTagId())
	                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 감정태그입니다."));
	        post.getEmotionTags().clear();
	        post.addEmotionTag(emotionTag);
	    }
	}

	@Transactional
	public void deleteDiary(Long id, CustomUserDetails userDetails) {
	    Post post = diaryRepository.findById(id)
	            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 일기입니다."));

	    if (!post.getUserId().equals(userDetails.getUserId())) {
	        throw new IllegalArgumentException("삭제 권한이 없습니다.");
	    }

	    diaryRepository.delete(post);
	}



}
