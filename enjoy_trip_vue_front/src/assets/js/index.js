const ip = 'localhost';
const session_check_url = 'http://'+ip+'/EnjoyTrip/user/session';
const sido_url = 'http://'+ip+'/EnjoyTrip/map/sido';
const gugun_url = 'http://'+ip+'/EnjoyTrip/map/gugun';
const search_map_url = 'http://'+ip+'/EnjoyTrip/map/search';

window.onload = function(){
	changePage('home-page');
}

document.getElementById('home-link').addEventListener('click', function(){
	changePage('home-page');
});

document.getElementById('map-link').addEventListener('click', function(){
	changePage('map-page');
	fetch(sido_url, {
		method:'GET'
	}).then(function(res){
		return res.json();
	}).then(function(obj){
		const msg = obj['msg'];
		const detail = obj['detail'];
		const list = obj['list'];
		let select = document.getElementById('search-area');
		select.addEventListener('change', changeGugun);
		if(msg=='OK'){
			list.forEach(function(item){
				let option = document.createElement('option');
				option.value = item['sido_code'];
				option.innerText = item['sido_name'];
				select.appendChild(option);
			});
		}else{
			alert(detail);
			changePage('home-page');
		}
	});
	fetch(gugun_url, {
		method:'GET'
	}).then(function(res){
		return res.json();
	}).then(function(obj){
		const msg = obj['msg'];
		const detail = obj['detail'];
		const list = obj['list'];
		let select = document.getElementById('search-sigungu');
		select.addEventListener('change', showOption);
		if(msg=='OK'){
			list.forEach(function(item){
				let option = document.createElement('option');
				let sido_code = item['sido_code'];
				option.value = item['gugun_code'];
				option.setAttribute('class', `${sido_code}`);
				option.innerText = item['gugun_name'];
				select.appendChild(option);
			});
			changeGugun();
			showOption();
		}else{
			alert(detail);
			changePage('home-page');
		}
	});
	let position = new kakao.maps.LatLng(37.501265, 127.039610);
	let map = new kakao.maps.Map(document.getElementById('map'), {
		center: position,
		level: 3,
		mapTypeId: kakao.maps.MapTypeId.ROADMAP
	});
	
	
	document.getElementById('search-content-id').addEventListener('change', mapChange);
});
document.getElementById('board-link').addEventListener('click', function(){
	changePage('board-page');
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
function changeGugun(){
	const select = document.getElementById('search-area');
	const sido = select[select.selectedIndex].value;
	document.querySelectorAll('#search-sigungu option').forEach(function(e){
		if(e.getAttribute('class')==sido){
			e.style.display = 'block';
		}else{
			e.style.display = 'none';
		}
	});
	document.getElementById('search-sigungu').selectedIndex = 0;
}
function showOption(){
	const select_gugun = document.getElementById('search-sigungu');
	const gugun_code = select_gugun[select_gugun.selectedIndex].value;
	let myStyle = 'block';
	if(gugun_code==''||gugun_code==null){
		myStyle = 'none';
	}
	document.querySelectorAll('#search-content-id option').forEach(function(option){
		option.style.display = myStyle;
	});

	document.getElementById('search-content-id').selectedIndex = 0;
}

function mapChange(){
	const select_sido = document.getElementById('search-area');
	const sido_code = select_sido[select_sido.selectedIndex].value;
	const select_gugun = document.getElementById('search-sigungu');
	const gugun_code = select_gugun[select_gugun.selectedIndex].value;
	const select_type = document.getElementById('search-content-id');
	const content_type_id = select_type[select_type.selectedIndex].value;
	if(content_type_id==''){
		return;
	}
	fetch(search_map_url, {
		method: 'POST',
		headers: {'Content-Type': 'application/json'},
		body: JSON.stringify({sido_code, gugun_code, content_type_id})
	}).then(function(res){
		return res.json();
	}).then(function(obj){
		const msg = obj['msg'];
		const detail = obj['detail'];
		let positions = [];
		const list = obj['list'];
		if(msg=='OK'){
			let trs = [];
			for(let i=0;i<list.length;i++){
				const pos = list[i];
				console.log(pos);
				const tr = document.createElement('tr');
				tr.appendChild(makeTd(pos['title']))
			}
			appendToMap(trs);
		}else{
			alert(detail);
		}
		
	});
}
function appendToMap(trs){
	const tbody = document.getElementById('trip-list');
	document.querySelectorAll('#trip-list tr').forEach(function(e){
		tbody.removeChilde(e);
	})
	trs.forEach(function(e){
		tbody.appendChilde(e);
	})
}
function makeTd(content){
	const td = document.createElement('td');
	td.innerText = content;
	return td;
}