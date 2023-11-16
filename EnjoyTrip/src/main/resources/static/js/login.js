const login_url = 'http://'+ip+'/EnjoyTrip/user/login'

document.getElementById('login-button').addEventListener('click', function(){
	let userId = document.getElementById('login-id').value;
	let userPassword = document.getElementById('login-password').value;
	let saveid = document.getElementById('saveid').checked;
	if(userId==null||userId==''){
		alert('아이디를 입력해 주세요');
		return;
	}
	if(userPassword==null||userPassword==''){
		alert('비밀번호를 입력해 주세요');
		return;
	}
	if(saveid==true){
		setCookie('saveid', "ok");
	}else{
		deleteCookie('saveid');
	}
	fetch(login_url, {
		method: 'POST',
		headers: { 'Content-Type': 'application/json', },
		body: JSON.stringify({userId, userPassword})
	}).then(function(res){
		return res.json();
	}).then(function(obj){
		const msg = obj['msg'];
		const detail = obj['detail'];
		const name = obj['name'];
		const email_id = obj['email_id'];
		const email_domain = obj['email_domain'];
		if(msg=='OK'){
			closeModal();
			document.getElementById('before-login').style.display='none';
			document.getElementById('after-login').style.display='flex';
			document.getElementById('user-name-nim').innerText = name;
			document.getElementById('login-info-name').value = name;
			document.getElementById('login-info-id').value = userId;
			document.getElementById('login-info-pw').value = userPassword;
			document.getElementById('login-info-email-id').value = email_id;
			document.getElementById('login-info-email-domain').value = email_domain;
			setCookie('id', userId);
			setCookie('pw', userPassword);
		}
		alert(detail);
	});
});
