//로그인한 회원 정보를 가져오는 함수
async function getMyInfo(){
	//토큰으로 회원 정보 가져오기
	const token = localStorage.getItem("accessToken");
	
	if(!token){
		console.log("로그인이 필요합니다.");
		return null;
	}
	//토큰을 서버에 전송하여 회원 정보 가져옴
	try{
		const response = await fetch("/api/auth/me", {
			method : "get",
			headers : {
				"Authorization" : `Bearer ${token}`
			}
		});
		
		//토큰이 만료되서 인증에 실패한 경우
		if(response.status === 401 || response.status === 403){
			console.log("인증이 만료되거나 권한이 없습니다.");
			localStorage.removeItem("accessToken");
			return null;
		}
		
		const result = await response.json();
		return result;
	}catch(e){
		console.error(e);
		return null;
	}
}

//로그인 인증이 필요한 요청을 처리하는 함수
async function authFetch(url,options={}){
	//fetch로 보낼 headers 정보
	
	const headers = {
		
		...options.headers
	}
	
	//토큰 가져옴. 사원증을 가져옴
	const accessToken = localStorage.getItem("accessToken");
	
	//토큰이 있으면 headers에 토큰 정보를 추가
	if(accessToken){ //사원증이 있으면
		headers["Authorization"] ="Bearer " + accessToken; //요청할 때 사원증 보여주기
	}
	
	const config = {
		...options,
		headers
	}
	
	const response = await fetch(url, config);
	//토큰 만료되면 리프레쉬 토큰으로 재발급 후 다시 전송
	if(response.status === 401 || response.status === 403){
		console.log(1);
		//기존 access토큰을 제거
		localStorage.removeItem("accessToken")
		
		//리프레쉬토큰으로 새 access토큰 발급받음
		const newAccessToken = await refreshAccessToken();
		
		//새 access토큰을 발급 받으면
			if(newAccessToken){
				alert(newAccessToken)
			//새 access토큰을 로컬스토리지에 저장
				localStorage.setItem("accessToken",newAccessToken);
			//응답 헤더에 새 access토큰을 추가해서 
				config.headers["Authorization"] = "Bearer " + newAccessToken;
			//요청을 다시함
				return await fetch(url, config);
			}
			else{
				location.href = "/login.html";
				throw new Error("인증이 만료되었습니다.");
			}
		
		}
		return response;
	}


async function refreshAccessToken(){
	console.log("토큰 발급 중")
	try{
		const response = await fetch("/api/auth/refresh",{
			method : "post",
			credentials : "include" // HttpOnly 쿠키를 서버에 자동으로 전송
		});
		
		if(!response.ok){
			return null;
		}
		const result = await response.json();
		return result.accessToken;
	}catch(e){
		console.error("토큰 재발급 실패",e);
		return null;
	}
}