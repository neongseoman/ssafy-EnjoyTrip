const ip = 'localhost';
const session_check_url = 'http://'+ip+'/EnjoyTrip/user/session';

window.onload = function(){
	changePage('home-page');
}
document.getElementById('home-link').addEventListener('click', function(){
	changePage('home-page');
});

document.getElementById('update-link').addEventListener('click', function(){
	changePage('update-page');
	const login_name = document.getElementById('login-info-name').value;
	const login_id = document.getElementById('login-info-id').value;
	const login_email_id = document.getElementById('login-info-email-id').value;
	const login_email_domain = document.getElementById('login-info-email-id').value;
	if(login_id==null||login_id==''){
		alert('로그인을 먼저 해 주세요');
		changePage('home-page');
		document.getElementById('before-login').style.display='flex';
		document.getElementById('after-login').style.display='none';
		return;
	}
	document.getElementById('userinfo-name').value = login_name; 
	document.getElementById('userinfo-id').value = login_id;
	document.getElementById('userinfo-email').value = login_email_id;
	document.querySelectorAll('#userinfo-selectdomain option').forEach((e)=>{
		if(e.value == login_email_domain){
			e.selected = true;
		}
	});
});
document.querySelectorAll('.close-area').forEach(element=>{
	element.addEventListener('click', closeModal);
});
document.getElementById('login-a').addEventListener('click', function(){
	document.getElementById('login-modal').style.display='flex';
	if(getCookie('saveid')!=null&&getCookie('saveid')!=''){
		const id = getCookie('id');
		const pw = getCookie('pw');
		if(id!=null&&pw!=null||id!=''||pw!=''){
			document.getElementById('login-id').value = id;
			document.getElementById('login-password').value = pw;
		}
	}
});
document.getElementById('join-a').addEventListener('click', function(){
	document.getElementById('join-modal').style.display='flex';
});
let userId_session = getCookie('id');
let userPassword_session = getCookie('pw');
if(userId_session==null||userPassword_session==null||userId_session==''||userPassword_session==''){
	document.getElementById('before-login').style.display='flex';
	document.getElementById('after-login').style.display='none';
}else{
	fetch(session_check_url, {
		method: "POST",
		headers: { 'Content-Type': 'application/json', },
		body: JSON.stringify({userId: userId_session, userPassword: userPassword_session}),
	}).then(function(res){
		return res.json();
	}).then(function(obj){
		const msg = obj['msg'];
		const detail = obj['detail'];
		const name = obj['name'];
		const email_id = obj['eamil_id'];
		const email_domain = obj['email_domain'];
		if(msg=="OK"){
			document.getElementById('before-login').style.display='none';
			document.getElementById('after-login').style.display='flex';
			document.getElementById('user-name-nim').innerText = name;
			document.getElementById('login-info-name').value = userId_session;
			document.getElementById('login-info-id').value = userPassword_session;
			document.getElementById('login-info-pw').value = name
			document.getElementById('login-info-email-id').value = email_id;
			document.getElementById('login-info-email-domain').value = email_domain;
		}else{
			document.getElementById('before-login').style.display='flex';
			document.getElementById('after-login').style.display='none';
			alert(detail);
		}
	});
}
function changePage(pageId){
	const target_element = document.getElementById(pageId)
	target_element.style.display='flex';
	document.querySelectorAll('.content').forEach(element=>{
		if(element!=target_element){
			element.style.display='none';
		}
	});
}
function closeModal(){
	document.getElementById('login-modal').style.display='none';
	document.getElementById('join-modal').style.display='none';
}



