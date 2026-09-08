/*=============================
	DOM 트리 구성이 완료되면 실행
=============================*/

document.addEventListener("DOMContentLoaded",e =>{
	getBoardsAndDisplay();
});

const post = {
	title : '',
	boardId : '',
	content : '' 
}

/*==============================
	게시판 목록을 가져와서 화면에 배치
===============================*/

async function getBoardsAndDisplay(){
	try{
		//게시판 목록 가져오기
		const response = await fetch(`/api/boards`,{
			method : "post",
			headers : {
						"Content-Type": "application/json"
			},
			body: JSON.stringify(post)
		});
		
		const restlt = await response.json();
			
		//게시판 목록 배치하기
		let html = '<option value="10">게시판을 선택하세요.</option>';
		restlt.forEach(board => {
			html += `
			<option value=${board.id}>${board.name}</option>
			`
		});
		document.querySelector("[name=boardId]").innerHTML = html;
		
	}catch(e){
		console.error(e);
	}
}

async function insertPost(e){
	e.preventDefault();
	
	//입력값(제목, 내용, 게시판) 체크
	if(data.title.trim().length == 0){
		alert("제목을 입력하세요.");
		return;
	}
	
	if(data.boardId.trim().length == 0){
		alert("게시판을 선택하세요.");
		return;
	}

	if(data.content.trim().length == 0){
		alert("내용을 입력하세요.");
		return;
	}
	
	//게시글과 첨부파일을 하나로 합침
	const formData = new FormData();
	// JSON 데이터를 Blob 데이터로 변환
	const jsonBlob = new Blob([JSON.stringify(data)],{type:"application/json"});
	formData.append("post",jsonBlob);
	
	const 첨부파일요소들 = document.querySelectorAll("[name=files]");
	첨부파일요소들.forEach(첨부파일요소=>{
		const 첨부파일들 = 첨부파일요소.files;
		for(let i = 0; i < 첨부파일들.length; i++){
			formData.append("files",첨부파일들[i]);
		}
	});
	
	
	try{
		//게시판 목록 가져오기
		const response = await authFetch(`/api/posts`,{
			method : "post",
			body: formData
		});
			
		const result = await response.json();
		alert(result.message);
		if(result.success){
			location.href ="/post/list.html";
		}
				
			
	}catch(e){
		console.error(e);
	}
}

const data = {
	title : '',
	content : '',
	boardId : ''
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