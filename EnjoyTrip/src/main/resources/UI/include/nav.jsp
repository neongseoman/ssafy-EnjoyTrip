<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- jstl 사용하기 위한 코드 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 프로젝트의 context 경로를 편하게 사용하기 위한 코드 --%>
<c:set var="root" value="${pageContext.request.contextPath}" />

<header class="header__layout">
	<div class="header">
		<div class="header__left">
			<nav id="navbar" class="navbar">
				<ul>
					<li><a href="${root}"><img id="logo"
							src="${root}/assets/img/logo.png" alt="logo" /></a></li>
					<li><a class="nav-link scrollto " href="${root}/map/map.jsp">지역별여행지</a></li>
					<li><a class="nav-link scrollto"
						href="${root}/include/notReady.jsp">나의여행계획</a></li>
					<li><a class="nav-link scrollto"
						href="${root}/include/notReady.jsp">핫플자랑하기</a></li>
					<li><a class="nav-link scrollto "
						href="${root}/article?action=list&pgno=1">여행정보공유</a></li>
				</ul>
				<i class="bi bi-list mobile-nav-toggle"></i>
			</nav>
		</div>

		<div class="header__right">
			<c:if test="${empty sessionScope.userinfo}">
				<%-- <c:if test="${empty cookie['ssafy_id'].value}"> --%>
				<a href="#" id="loginButton">로그인</a>
				<a href="#" id="registerButton">회원가입</a>
			</c:if>
			<c:if test="${not empty sessionScope.userinfo}">
				<div id="profile-dropdown" class="hidden">
					<img id="profile" src="${root}/assets/img/profile.png"
						alt="profile" />
					<!-- <div class="profile-menu" id="profile-menu">
						<ul>
							<li><a href="#" id="userInfoLink" class="hidden">회원정보</a></li>
							<li><a href="#" id="logoutButton" class="hidden">로그아웃</a></li>
						</ul>
					</div> -->
				</div>
				<span id="useridnim"><c:out value="${userinfo['userName']}" />님
					환영합니다.</span>
				<a href="${root}/user/mypage.jsp" id="userInfoLink">회원정보</a>
				<form action='${root}/user' method='post'>
					<input type='hidden' name='action' value='logout'> <input
						type='submit' id="logoutButton" value='로그아웃'><br>
				</form>
			</c:if>
		</div>
	</div>
</header>

<div id="join-modal" class="modal-overlay">
	<div class="modal-window" style="height: 460px;">
		<!-- join modal header -->
		<header id="join-modal__modal_header_" class="modal-header">
			<h3 id="join-modal__modal_title_" class="modal-title">회원가입</h3>
			<button type="button" aria-label="Close" class="close-area">X</button>
		</header>

		<!-- join modal body -->

		<div id="join-modal___modal_body_" class="modal-body">
			<form id="form-join" method="POST" action="">
				<input type="hidden" name="action" value="join" />
				<div class="container-fluid">
					<div class="row mb-1 text-center">
						<div class="col-3">이름</div>
						<div class="col">
							<input type="text" placeholder="이름" class="form-control"
								id="username" name="username">
						</div>
					</div>
					<div class="row mb-1 text-center">
						<div class="col-3">아이디</div>
						<div class="col">
							<input type="text" placeholder="아이디" class="form-control"
								id="userid" name="userid">
						</div>
						<div class="col-3">
							<button type="button" class="btn btn-secondary btn-sm"
								id="id-check-btn">중복확인</button>
						</div>
					</div>
					<div class="row mb-1 text-center">
						<div class="col-3">비밀번호</div>
						<div class="col">
							<input type="password" placeholder="비밀번호" class="form-control"
								id="userpwd" name="userpwd" oninput="validatePassword()">
						</div>
						<div class="col-3">
							<button type="button" class="btn btn-secondary btn-sm"
								id="str-pwd-btn">강력암호</button>
						</div>
					</div>
					<!-- 비밀번호 조건 추가 -->
					<div class="row mb-1 text-center">
						<div class="col-3"></div>
						<div class="col">
							<div class="row mb-1">
								<span style="font-size: 12px; text-align: left;"> 최소 10자리
									이상 / 영문, 숫자, 특수기호 중 2가지 필수 </span>
							</div>
							<div class="row mb-1 text-left">
								<div class="col-md-5">
									<p style="font-size: 12px; text-align: left;">
										비밀번호 안전도 | <span id="safetext" style="font-size: 12px;">
											- </span>
									</p>
								</div>

								<div class="col-md-2 p-1">
									<!-- 첫 번째 직사각형 -->
									<div id="box1"
										style="width: 100%; height: 5px; background-color: #6C757D;"></div>
								</div>
								<div class="col-md-2 p-1">
									<!-- 두 번째 직사각형 -->
									<div id="box2"
										style="width: 100%; height: 5px; background-color: #6C757D;"></div>
								</div>
								<div class="col-md-2 p-1">
									<!-- 세 번째 직사각형 -->
									<div id="box3"
										style="width: 100%; height: 5px; background-color: #6C757D;"></div>
								</div>
							</div>
						</div>
					</div>
					<!-- 비밀번호 조건 추가 끝 -->
					<div class="row mb-1 text-center">
						<div class="col-3">비밀번호확인</div>
						<div class="col">
							<input type="password" placeholder="비밀번호확인" class="form-control"
								id="repassword">
						</div>
					</div>
					<div class="row mb-1 text-center">
						<div class="col-3">이메일</div>
						<div class="col-4">
							<input type="text" placeholder="이메일" class="form-control"
								id="useremail" name="useremail">
						</div>
						<div class="col-1">
							<span class="input-group-text">@</span>
						</div>
						<div class="col">
							<select class="custom-select" id="selectdomain"
								name="emaildomain">
								<option value="">도메인 선택</option>
								<option value="ssafy.com">SSAFY</option>
								<option value="google.com">GOOGLE</option>
								<option value="kakao.com">KAKAO</option>
								<option value="naver.com">NAVER</option>
							</select>
						</div>
					</div>
				</div>
			</form>
		</div>

		<!-- join modal footer -->
		<footer id="join-modal__modal_footer_" class="modal-footer">
			<div>
				<button type="button" class="btn btn-danger btn-sm"
					id="register-cancel-button">취소</button>
				<button type="button" class="btn mr-2 btn-primary btn-sm"
					id="register-button">회원가입</button>
			</div>
		</footer>
	</div>
</div>

<div id="login-modal" class="modal-overlay">
	<div class="modal-window">
		<!-- login modal header -->
		<header id="login-modal__modal_header_" class="modal-header">
			<h3 id="login-modal__modal_title_" class="modal-title">로그인</h3>
			<button type="button" aria-label="Close" class="close-area">X</button>
		</header>

		<!-- login modal body -->
		<form id="login-modal___modal_body_" class="modal-body" method="post">
			<input type="hidden" name="action" value="login" />
			<div class="container-fluid">
				<div class="row mb-1">
					<div class="col-12 col-md-3 text-md-right">
						<!-- 아이디 라벨 -->
						<label for="login-id">아이디</label>
					</div>
					<div class="col-12 col-md-9">
						<!-- 아이디 입력란 -->
						<input type="text" placeholder="아이디" class="form-control"
							id="login-id" name="loginid">
					</div>
				</div>
				<div class="row mb-1">
					<div class="col-12 col-md-3 text-md-right">
						<!-- 비밀번호 라벨 -->
						<label for="login-password">비밀번호</label>
					</div>
					<div class="col-12 col-md-9">
						<!-- 비밀번호 입력란 -->
						<input type="password" placeholder="비밀번호" class="form-control"
							id="login-password" name="loginpwd">
					</div>
				</div>
				<div class="row">
					<div class="col-12 col-md-9 offset-md-3">
						<!-- 아이디 저장 체크박스 -->
						<div class="form-check text-md-left">
							<label class="form-check-label" for="saveid">아이디 저장</label> <input
								type="checkbox" class="form-check-input" id="saveid"
								name="saveid" value="ok">
						</div>
					</div>
				</div>
			</div>
		</form>

		<!-- login modal footer -->
		<footer id="login-modal__modal_footer_" class="modal-footer">
			<div>
				<button type="button" class="btn btn-danger btn-sm"
					id="cancel-button">취소</button>
				<button type="button" class="btn mr-2 btn-primary btn-sm"
					id="login-button">로그인</button>
			</div>
		</footer>
	</div>
</div>

<script>

/* 회원가입 */
const joinModal = document.getElementById("join-modal")
				const joinbtnModal = document.getElementById("registerButton")
				joinbtnModal.addEventListener("click", e => {
				    joinModal.style.display = "flex"
				})
				const joinCloseBtn = joinModal.querySelector(".close-area")


/* 아이디 중복 확인 */
let isUseId = false;
document.querySelector("#id-check-btn").addEventListener("click", function () {
	const userid = document.getElementById("userid").value
	console.log(userid)
	let url = "${root}/user?action=idcheck&checkid="+userid;
	
	fetch(url)
		.then((response) => response.text())
	    .then((data) => useIdCheck(data));
})

function useIdCheck(data) {
    let val = data.split(",");
    let id = val[0];
    let cnt = val[1];

    if (cnt == 0) {
      isUseId = true;
      alert("사용할 수 있는 아이디입니다 !")
    } else {
      isUseId = false;
      alert("중복된 아이디입니다 !")
    }
  }
  
/* 비밀번호 안전도 체크 */
 
let isValidPassword = false
function validatePassword() {
	const password = document.getElementById("userpwd").value;
	const arr = ["123","password","qwerty","111111",
		"456","abc","password1","iloveyou","1q2w3e4r",
		"000000","zaq12wsx","dragon","sunshine",
		"princess",	"letmein","321","monkey",
		"27653","1qaz2wsx","superman","asdfghjkl"];
	
	// 1. 최소 10자리 이상이어야 하고, 영문, 숫자, 특수 기호 중 2가지 이상이 포함되어야 합니다.
	const condition1 = /^(?=.*[a-zA-Z])(?=.*\d|\W).{10,}$/.test(password);
	// 2. 배열 arr에 있는 단어가 비밀번호에 포함되면 안됩니다.
	const condition2 = !arr.some(word => password.includes(word));
	// 3. 연속되는 문자 3개 이상은 허용되지 않습니다.
	const condition3 = !/(.)\1\1/.test(password);
	
	const box1 = document.getElementById("box1");
	const box2 = document.getElementById("box2");
	const box3 = document.getElementById("box3");
	
	const safetext = document.getElementById("safetext");
	
	if (condition1 && condition2 && condition3) {
		box1.style.backgroundColor = "green";
		box2.style.backgroundColor = "green";
		box3.style.backgroundColor = "green";
		safetext.textContent = "강";
		safetext.style.color = "green"
		isValidPassword = true
	} else if (condition1 && condition2) {
		box1.style.backgroundColor = "yellow";
		box2.style.backgroundColor = "yellow";
		box3.style.backgroundColor = "#6C757D";
		safetext.textContent = "중";
		safetext.style.color = "yellow"
		isValidPassword = true
	} else if (condition1) {
		box1.style.backgroundColor = "red";
		box2.style.backgroundColor = "#6C757D";
		box3.style.backgroundColor = "#6C757D";
		safetext.textContent = "약";
		safetext.style.color = "red"
		isValidPassword = true
	} else {
		box1.style.backgroundColor = "#6C757D";
		box2.style.backgroundColor = "#6C757D";
		box3.style.backgroundColor = "#6C757D";
		safetext.textContent = "-";
		safetext.style.color = "#6C757D"
		isValidPassword = false
	}
}

/* 강력한 암호 생성 */
function generateStrongPassword() {
    const length = 16; // 비밀번호 길이 (최소 16자 이상)
    const charset = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%^&*()_+"; // 사용 가능한 문자 집합
    let password = "";

    for (let i = 0; i < length; i++) {
        const randomIndex = Math.floor(Math.random() * charset.length);
        password += charset[randomIndex];
    }

    return password;
}

const generatePasswordButton = document.getElementById("str-pwd-btn");
const generatedPasswordElement = document.getElementById("userpwd");

generatePasswordButton.addEventListener("click", function () {
    const password = generateStrongPassword();
    generatedPasswordElement.value = password;
    validatePassword();
});

/* 회원가입 창닫기 */

joinCloseBtn.addEventListener("click", e => {
    joinModal.style.display = "none"
})
joinModal.addEventListener("click", e => {
        const evTarget = e.target
        if (evTarget.classList.contains("modal-overlay")) {
            joinModal.style.display = "none"
        }
    })
    
/* 회원가입 버튼 */
document.querySelector("#register-button").addEventListener("click", function () {
        if (!document.querySelector("#username").value) {
          alert("이름을 입력해주세요 :)");
          return;
        } else if (!document.querySelector("#userid").value) {
          alert("아이디를 입력해주세요 :)");
          return;
        } else if (!isUseId) {
          alert("아이디 중복을 확인해주세요 :)");
          return;
        } else if (!document.querySelector("#userpwd").value) {
          alert("비밀번호를 입력해주세요 :)");
          return;
        } else if (document.querySelector("#userpwd").value != document.querySelector("#repassword").value) {
          alert("비밀번호를 다시 확인해주세요 :)");
          return;
        } else if (!isValidPassword) {
        	alert("비밀번호의 조건을 다시 확인해주세요 :)");
            return;
        } else {
          let form = document.querySelector("#form-join");
          alert("회원가입이 완료되었습니다 !");
          form.setAttribute("action", "${root}/user");
          form.submit();
        }
      });
      
/* 로그인 */
const loginModal = document.getElementById("login-modal")
    const loginbtnModal = document.getElementById("loginButton")
    loginbtnModal.addEventListener("click", e => {
        loginModal.style.display = "flex";
})
    
/* 로그인 창 닫기 */
const loginCloseBtn = loginModal.querySelector(".close-area")
loginCloseBtn.addEventListener("click", e => {
    loginModal.style.display = "none";
})
    
loginModal.addEventListener("click", e => {
        const evTarget = e.target
        if (evTarget.classList.contains("modal-overlay")) {
            loginModal.style.display = "none";
        }
})
 
/* 로그인 버튼 */ 
document.querySelector("#login-button").addEventListener("click", function () {
        if (!document.querySelector("#login-id").value) {
          alert("아이디 입력!!");
          return;
        } else if (!document.querySelector("#login-password").value) {
          alert("비밀번호 입력!!");
          return;
        } else {
          let form = document.querySelector("#login-modal___modal_body_");
          form.setAttribute("action", "${root}/user");
          form.submit();
        }
      });

/* 로그아웃 버튼 */
document.querySelector("#logoutButton").addEventListener("click", function() {
console.log("logout");
});

</script>
