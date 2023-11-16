function getCookie(cookie_name){
	cookie_value = null;
	let cookies = document.cookie.split(';');
	for(let i=0;i<cookies.length;i++){
		let cookie= cookies[i].trim().split('=');
		if(cookie_name==cookie[0]){
			cookie_value = cookie[1];
			break;
		}
	}
	return cookie_value;
}
function setCookie(cookie_name, cookie_value){
	document.cookie = `${cookie_name}=${cookie_value};`;
}
function deleteCookie(cookie_name){
	setCookie(cookie_name, '');
}