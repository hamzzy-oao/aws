/*=============================
	DOM 트리 구성이 완료되면 실행
=============================*/

document.addEventListener("DOMContentLoaded",e =>{
	getPosts();
});

/*===================================
	게시글 목록을 불러와서 화면에 배치하는 함수
====================================*/

async function getPosts(){
	
	//서버에 게시글 목록을 요청해서 화면에 배치하는 작업
	// 서버에 게시글 목록을 요청
	// url : /api/posts
	// method: get
	
	const queryString ='?'+ new URLSearchParams(data).toString();
	
	
	try{
		const response = await fetch("/api/posts"+queryString);
		//가져온 게시글들을 이용하여 html코드로 구성
		
		if(!response.ok){
			throw Error("서버 상태 이상");
		}
		
		const result = await response.json();
		const{content,hasPrev,hasNext,startPage,endPage,page} = result;
		let str = '';
		content.forEach(post=>{
			str += `
			<tr>
				<td>${post.id}</td>
				<td><a href="/post/detail.html?num=${post.id}">${post.content}</a></td>
				<td>${post.memberId}</td>
				<td>${post.createdAt.slice(0,10)}</td>
				<td>${post.upCount}/${post.downCount}</td>
			</tr>
			`;
		});
		
		//table태그 안 body에 html코드를 덮어쓰기
		const 테이블바디 = document.querySelector(".table tbody");
		
		if(result.length != 0){
			테이블바디.innerHTML = str;
		}
		else{
			테이블바디.innerHTML = `
			<tr>
				<td class="text-center" colspan="5">등록된 게시글이 없습니다.</td>
			</tr>
			`;
		}
		
		const 페이지네이션 = document.querySelector(".pagination");
		
		let 페이지네이션코드 = '';
		
		if(hasPrev){
			페이지네이션코드 +=`
				<li class="page-item">
			    	<a class="page-link" href="javascript:void(0);" onclick="changePage(${startPage -1-1})">이전</a>
				</li>
			`;
		}
		
		if(hasNext){
			페이지네이션코드 +=`
				<li class="page-item">
			    	<a class="page-link" href="javascript:void(0);" onclick="changePage(${endPage +1-1})">다음</a>
				</li>
			`;
		}
		
		for(i = startPage; i <= endPage; i++){
			//현재 페이지에 색상 추가
			const active = i == page ? "active" : "";
			페이지네이션코드 +=`
				<li class="page-item" ${active}>
			    	<a class="page-link" href="javascript:void(0);" onclick="changePage(${i-1})">${i}</a>
				</li>
			`;
		}
		
		페이지네이션.innerHTML = 페이지네이션코드;
	}catch(e){
		console.error("게시글 목록 불러오기 실패",e);
	}
}

const data = {
	type : 'all',
	keyword : '',
	page : 0,
	size : 3,
	sort : 'id,desc'
 	
}

/*==================
	검색 버튼 눌렀을때
===================*/
function sumitSearch(e){
	e.preventDefault();
	getPosts();
	
}

/*======================================
	입력태그(input, seletec, textarea 등)
	제목과 게시판, 내용이 입력되면 입력된 
	값들을 가져와서 객체에 저장하도록 하는 함수
	단, data 객체는 전역으로 선언되어 있어야 함
=======================================*/

function changeinput(e){
	// 객체에 있는 값들을 변수에 쉽게 저장하는 방법
	const{name, value} = e.target;
	// 객체에 있는 속성의 값을 변경
	data[name] = value;
}
/*==================
	게시글 페이지 눌렀을 때
===================*/
function changePage(page){
	data.page = page;
	getPosts();
}