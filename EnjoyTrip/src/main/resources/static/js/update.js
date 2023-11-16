const update_url = 'http://'+ip+'/EnjoyTrip/user/update';
document.getElementById('edit-button').addEventListener('click', function(){
	const userName = document.getElementById('userinfo-name').value;
	const userId = document.getElementById('userinfo-id').value;
	const userPassword = document.getElementById('login-info-pw').value;
	const userPasswordCheck = document.getElementById('userinfo-password').value;
	const newPassword = document.getElementById('new-password').value;
	const newPasswordCheck = document.getElementById('new-repassword').value;
	const emailId = document.getElementById('userinfo-email').value;
	const selectDomain = document.getElementById('userinfo-selectdomain');
	const emailDomain = selectDomain[selectDomain.selectedIndex].value;
	if(userName==null||userName==''){
		alert('이름을 입력해 주세요');
		return;
	}
	if(userPasswordCheck==null||newPasswordCheck==''){
		alert('정보를 업데이트 하기 위하여 기존 비밀 번호를 입력해 주세요');
		return;
	}
	if(emailId==null||emailId==''){
		alert('이메일 아이디를 입력해 주세요');
		return;
	}
	if(emailDomain==null||emailDomain==''){
		alert('이메일 도메인을 선택해 주세요');
		return;
	}
	if(userPassword!=userPasswordCheck){
		alert('비밀 번호가 틀립니다.');
		return;
	}
	if(newPassword!=''&&newPassword!=null){
		if(newPassword!=newPasswordCheck){
			alert('비밀 번호 확인이 틀립니다.');
			return;
		}
	}else{
		newPassword = userPassword;
	}
	fetch(update_url, {
		method: 'POST',
		headers: {'Content-Type': 'application/json'},
		body: JSON.stringify({userName, userId, userPassword, newPassword, emailId, emailDomain})
	}).then(function(res){
		return res.json();
	}).then(function(obj){
		const msg = obj['msg'];
		const detail = obj['detail'];
		if(msg=='OK'){
			changePage('home-page');
			document.getElementById('login-info-name').value = userName;
			document.getElementById('login-info-pw').value = newPassword;
			document.getElementById('login-info-email-id').value = emailId;
			document.getElementById('login-info-email-domain').value = emailDomain;
		}
		alert(detail);
	})
	
});
document.getElementById('reset-button').addEventListener('click', function(){
	const login_name = document.getElementById('login-info-name').value;
	const login_id = document.getElementById('login-info-id').value;
	const login_email_id = document.getElementById('login-info-email-id').value;
	const login_email_domain = document.getElementById('login-info-email-id').value;
	document.getElementById('userinfo-name').value = login_name; 
	document.getElementById('userinfo-id').value = login_id;
	document.getElementById('userinfo-email').value = login_email_id;
	document.querySelectorAll('#userinfo-selectdomain option').forEach((e)=>{
		if(e.value == login_email_domain){
			e.selected = true;
		}
	});
});
document.getElementById('withdraw-button').addEventListener('click', function(){
	pageChane('home-page');
});