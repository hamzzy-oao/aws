/*========================
	입력한 회원정보를 저장하는 객체
======================== */

document.addEventListener("DOMContentLoaded", async e=>{
	const info = await getMyInfo();
	let str = "반갑습니다."
	
	if(info.username){
		str = info.username + "님 " + str;
	}
	document.querySelector("#box").innerHTML = str;
});