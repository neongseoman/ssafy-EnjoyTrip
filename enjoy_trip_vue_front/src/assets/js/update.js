import {getCookie} from "@/assets/js/cookie";
const url = import.meta.env.VITE_ENV_URL
const update_url = url + '/user/update'

export function edit() {
	const userName = document.getElementsById('userInfo-name').value;
	const userPassword = document.getElementById('userInfo-password').value;
	const userId = document.getElementById('userinfo-id').value;
	const newPassword = document.getElementById('new-password').value;
	const checkPassowrd = document.getElementById('new-repassword').value;
	const emailId = document.getElementById('userinfo-email').value;
	const select = document.getElementsById('userinfo-selectdomain');
	const emailDomain = select[select.selectedIndex].value;
	if (newPassword == '') {
		alert('새 비밀번호를 입력해 주세요');
		return;
	}
	if (newPassword != checkPassowrd) {
		alert('비밀번호 확인이 틀립니다.');
		return;
	}
	if (emailId == '') {
		alert('이메일을 입력헤 주세요');
		return;
	}
	if (emailDomain == '') {
		alert('이메일 도메인을 선택해 주세요');
		return;
	}
	const userInfo = {userName, userId, userPassword, newPassword, emailId, emailDomain, sessionId: getCookie('sessionId')}
	return fetch(update_url, {
		method: 'POST',
		headers: {'Content-Type': 'application/json'},
		body: JSON.stringify(userInfo)
	}).then(function(res){
		return res.json();
	}).then(function(obj){
		const msg = obj['msg'];
		const detail = obj['detail'];
		if(msg==='OK'){
			console.log("ok")
		}
		alert(detail);
	})
	
};

export function reset(){
	//빈칸으로 만들기
	document.getElementById('userInfo-password').value = '';
	document.getElementById('new-password').value  = '';
	document.getElementById('new-repassword').value = '';
	document.getElementById('userinfo-email').value = '';
	const select = document.getElementsById('userinfo-selectdomain');
	select.selectedIndex = 0;
};

export function withdraw(){

	console.log(" 회원 탈퇴?")
	
};