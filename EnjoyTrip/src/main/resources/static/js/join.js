const join_url = 'http://'+ip+'/EnjoyTrip/user/join';
const id_check_url = 'http://'+ip+'/EnjoyTrip/user/idCheck';
let isValidPassword = false
const arr = ["123","password","qwerty","111111",
	"456","abc","password1","iloveyou","1q2w3e4r",
	"000000","zaq12wsx","dragon","sunshine",
	"princess",	"letmein","321","monkey",
	"27653","1qaz2wsx","superman","asdfghjkl"];

document.getElementById('userpwd').addEventListener('keydown', function(){
	const password = document.getElementById('userpwd').value;
	// 1. 최소 10자리 이상이어야 하고, 영문, 숫자, 특수 기호 중 2가지 이상이 포함되어야 합니다.
	const condition1 = /^(?=.*[a-zA-Z])(?=.*\d|\W).{10,}$/.test(password);
	// 2. 배열 arr에 있는 단어가 비밀번호에 포함되면 안됩니다.
	const condition2 = !arr.some(word => password.includes(word));
	// 3. 연속되는 문자 3개 이상은 허용되지 않습니다.
	const condition3 = !/(.)\1\1/.test(password);
	
	const box1 = document.getElementById("box1");
	const box2 = document.getElementById("box2");
	const box3 = document.getElementById("box3");
	console.log(condition1, condition2, condition3);
	const safetext = document.getElementById("safetext");
	
	if (condition1==true && condition2==true && condition3==true) {
		box1.style.backgroundColor = "green";
		box2.style.backgroundColor = "green";
		box3.style.backgroundColor = "green";
		safetext.innerText = "강";
		safetext.style.color = "green"
		isValidPassword = true
	} else if (condition1==true && condition2==true) {
		box1.style.backgroundColor = "yellow";
		box2.style.backgroundColor = "yellow";
		box3.style.backgroundColor = "#6C757D";
		safetext.innerText = "중";
		safetext.style.color = "yellow"
		isValidPassword = true
	} else if (condition1==true) {
		box1.style.backgroundColor = "red";
		box2.style.backgroundColor = "#6C757D";
		box3.style.backgroundColor = "#6C757D";
		safetext.innerText = "약";
		safetext.style.color = "red"
		isValidPassword = true
	} else {
		box1.style.backgroundColor = "#6C757D";
		box2.style.backgroundColor = "#6C757D";
		box3.style.backgroundColor = "#6C757D";
		safetext.innerText = "-";
		safetext.style.color = "#6C757D"
		isValidPassword = false
	}
});
document.getElementById('join-button').addEventListener('click', function(){
	const userId = document.getElementById('userid').value;
	const userName = document.getElementById('username').value;
	const userPassword = document.getElementById('userpwd').value;
	const passwordCheck = document.getElementById('repassword').value;
	const emailId = document.getElementById('useremail').value;
	const selectDomain = document.getElementById('selectdomain');
	const emailDomain = selectDomain.options[selectDomain.selectedIndex].value;
	const idChecked = document.getElementById('is-id-checked').value;
	if(userName==''||userName==null){
		alert('이름을 작성해 주세요');
		return;
	}
	if(userId==''||userId==null){
		alert('아이디를 작성해 주세요');
		return;
	}
	if(userPassword==''||userPassword==null){
		alert('비밀번호를 작성해 주세요');
		return;
	}
	if(emailId==''||emailId==null||emailDomain==''||emailDomain==null){
		alert('이메일을 작성해 주세요');
		return;
	}
	if(idChecked!='OK'){
		alert('아이디 중복을 확인해 주세요');
		return;
	}
	if(userPassword!=passwordCheck){
		alert('비밀번호 확인이 틀렸습니다.');
		return;
	}
	if(isValidPassword==false){
		alert("비밀번호가 유효하지 않습니다.");
		return;
	}
	fetch(join_url, {
		method: 'POST',
		headers: {'Content-Type': 'application/json', },
		body: JSON.stringify({userId, userPassword, userName, emailId, emailDomain})
	}).then(function(res){
		return res.json();
	}).then(function(obj){
		const msg = obj['msg'];
		const detail = obj['detail'];
		if(msg=='OK'){
			closeModal();
		}
		alert(detail);
	});
});
document.getElementById('id-check-button').addEventListener('click', function(){
	const id = document.getElementById('userid').value;
	if(id==null||id==''){
		alert('아이디를 입력해 주세요');
		return;
	}
	fetch(id_check_url, {
		method: 'POST',
		headers: {'Content-Type': 'application/json'},
		body: JSON.stringify({id}),
	}).then(function(res){
		return res.json();
	}).then(function(obj){
		const msg = obj['msg'];
		const detail = obj['detail'];
		if(msg!='OK'){
			document.getElementById('userid').value = '';
			document.getElementById('userid').style.color='red';
			document.getElementById('is-id-checked').value = 'NO';
		}else{
			document.getElementById('userid').style.color='blue';
			document.getElementById('is-id-checked').value = 'OK';
		}
		alert(detail);
	});
});

function validatePassword() {
	
}