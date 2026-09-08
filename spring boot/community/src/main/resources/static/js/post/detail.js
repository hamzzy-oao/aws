 /*=============================
	DOM 트리 구성이 완료되면 실행
=============================*/

document.addEventListener("DOMContentLoaded",e =>{
	getPost();
	getComment1();
});

/*=============================
	게시글을 화면에 출력하는 함수
=============================*/
async function getPost(){
	
		const urlParams = new URLSearchParams(location.search);
		const postId = urlParams.get("num");
		
	try{
		//서버에 게시글을 요청하여 게시글을 가져와서 콘솔에 게시글을 출력하는 코드까지 작성하세요.
		//url: /api/posts/board
		//method: get
		const response = await fetch(`/api/posts/${postId}`);
		
		
		if(!response.ok){
			const result = await response.text();
			alert(result);
			throw Error("게시글이 없거나 삭제됨");
		}
		const result = await response.json();
		const post = result.post;
		const files = result.files;
		
		insertValue("[name=title]", post.title);
		insertValue("[name=writer]", post.memberId);
		insertValue("[name=boardName]", post.board.name);
		insertValue("[name=view]", post.viewCount);
		insertValue("[name=createdAt]", post.createdAt.slice(0,10));
		insertValue("[name=content]", post.content);
		document.querySelector(".up-count").textContent = post.upCount;
		document.querySelector(".down-count").textContent = post.downCount;
		
		//수정/추가버튼 보여주기/감추기
		visibleButtons(false);
		
		const 첨부파일박스 = document.querySelector("#files");
		//첨부파일 보여주기
		if(!files || files.length == 0){
			첨부파일박스.innerHTML = `<div class="form-control">없음</div>`;
			return;
		}
		let html = '';
		files.forEach(file=>{
			html +=`
				<a class="form-control" href="/api/upload/${file.savedName}" 
				download=">${file.originalName}" >
				${file.originalName}
				</a>
			`;
		});
		
		첨부파일박스.innerHTML = html;
	}catch(e){
		console.error("게시글 가져오기 실패",e)
	}
}
/*=============================
	입력요소의 value를 수정하는 함수
=============================*/

function insertValue(selector, value){
	document.querySelector(selector).value = value;
	
}

/*=======================================
	게시글 수정/삭제 버튼을 보여줄지를 결정하는 함수
=======================================*/

function visibleButtons(visible){
	if(!visible){
		document.querySelector(".btns").innerHTML = '';
	}
}

	const commentData = {page: 0};
function changeCommentPage(page){
	
	commentData.page = page;
	getComment1();
}

async function createComment1(e){
		e.preventDefault();
		
		const formData = new FormData(e.target);
		const data = Object.fromEntries(formData);
		
		if(data.content.trim().length == 0){
			alert("댓글을 입력하세요.")
			return;
		}
		
		const urlParams = new URLSearchParams(location.search);
		const postId = urlParams.get("num");
	//url:/api/posts/${postId}/comment
	//method : post
	try{
		const response = await authFetch(`/api/posts/${postId}/comment`,{
			method : "post",
			headers : {
				"Content-Type": "application/json"
			},
			body : JSON.stringify(data)
		});
		if (!response.ok) {
			const message = await response.text();
			throw new Error(`댓글 등록 실패: ${response.status}, ${message}`);
		}
		
		const restlt = await response.json();
		alert(restlt.message);
		if(restlt.success){
			//댓글 목록 새로고침
			
			//댓글 입력 비우기
			const 댓글입력창 = e.target.querySelector("textarea");
			댓글입력창.value = "";
			
			await getComment1();
		}
		
	}catch(e){
		console.error(e);
	}
}

let firstCommentLoad = true;

//화면에 댓글 배치하는 함수
async function getComment1(){
	
	// url : /api/posts/${postId}/comment
	// method : get
	const queryString = "?" + new URLSearchParams(commentData).toString();

	const urlParams = new URLSearchParams(location.search);
	const postId = urlParams.get("num");
	
	try{
		const response = await fetch(`/api/posts/${postId}/comment${queryString}`);
		
		if(!response.ok){
			throw new Error(`댓글 조회 실패: ${response.status}`);
		}
		
		
		const result = await response.json();
		
		if (firstCommentLoad && result.totalPages > 0) {
		    firstCommentLoad = false;

		    const lastPage = result.totalPages - 1;

		    if (commentData.page !== lastPage) {
		        commentData.page = lastPage;
		        return getComment1();
		    }
		}
		
		const {content, page, startPage, endPage, hasNext, hasPrev, totalContentSize} = result;
		
		displayCommentPaging(page,startPage,endPage,hasNext, hasPrev,totalContentSize);
		
		const commentlist = document.querySelector("#comment-list");
		
		let html = '';
		
		content.forEach(comment => {
			
			const isReply = comment.id != comment.originId;
			
			if(comment.id == comment.originId){
				html += `<div class="card mb-3">`;
			}else{
				html += `						
					<div class="d-flex align-items-start ms-4 mb-3">
					<span class="text-secondary fs-4 me-2">ㄴ</span>
					
					<div class="card bg-light flex-grow-1">
				`;
			}
			html += `
			        <div class="card-body">
			            <div class="d-flex justify-content-between">
			                <strong>${comment.memberId}</strong>

			                <small class="text-secondary">
			                    ${comment.createdAt.replace("T", " ").slice(0, 16)}
			                </small>
			            </div>

			            <p class="mt-3 mb-3">
			                ${comment.content}
			            </p>

			            <div class="d-flex justify-content-end gap-2">
			                <button
			                    type="button"
			                    class="btn btn-sm btn-outline-warning"
			                    onclick="updateComment(${comment.id})"
			                >
			                    수정
			                </button>

			                <button
			                    type="button"
			                    class="btn btn-sm btn-outline-danger"
			                    onclick="deleteComment(${comment.id})"
			                >
			                    삭제
			                </button>
			            </div>
			        </div>
			    </div>
			    `;

		if(isReply){
			html += `</div>`;
		}
		});
		//댓글이 없을 경우 등록된 댓글이 없습니다. 
		if(totalContentSize === 0){
			html +=`
				<div class="text-secondary text-center py-4">
			    	등록된 댓글이 없습니다.
			    </div>
			`;
		}
		commentlist.innerHTML = html;
		
		
	}catch(e){
		console.error(e);
	}
}

function displayCommentPaging(
    page,
    startPage,
    endPage,
    hasNext,
    hasPrev
) {
    const pagination =
            document.querySelector("#comment-pagination");

    let html = "";

    if (hasPrev) {
        html += `
            <li class="page-item">
                <button
                    type="button"
                    class="page-link"
                    onclick="changeCommentPage(${startPage - 1})"
                >
                    이전
                </button>
            </li>
        `;
    }

    for (let i = startPage; i <= endPage; i++) {
        html += `
            <li class="page-item ${page === i ? "active" : ""}">
                <button
                    type="button"
                    class="page-link"
                    onclick="changeCommentPage(${i-1})"
                >
                    ${i}
                </button>
            </li>
        `;
    }

    if (hasNext) {
        html += `
            <li class="page-item">
                <button
                    type="button"
                    class="page-link"
                    onclick="changeCommentPage(${endPage + 1})"
                >
                    다음
                </button>
            </li>
        `;
    }

    pagination.innerHTML = html;
}

async function clickLike(state){
	
	const urlParams = new URLSearchParams(location.search);
	const postId = urlParams.get("num");
	
	//좋아요 싫어요 정보 전송
	try{
		const response = await authFetch(`/api/posts/${postId}/likes`,{
			method : "post",
			headers : {
				"Content-Type": "application/json"
			},
			body : JSON.stringify({state})
		});
		const result = await response.json();
		alert(result.ms.message);
		
			
		
	}catch(e){
		console.error(e);
	}
	
	
}
