<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
<%@ include file="/include/head.jsp"%>
</head>

<body>
	<%@ include file="/include/nav.jsp"%>
	<div class="edit-information">
		<form id="form-edit" method="POST" action="">
			<input type="hidden" name="action" value="edit" /> <input
				type="hidden" name="userid" value="${userinfo['userId']}" />
			<div class="container-fluid">
				<div class="row mb-4 text-center">
					<h3>회원정보 수정</h3>
				</div>
				<div class="row mb-2 text-center">
					<div class="col-3">이름</div>
					<div class="col">
						<input type="text" class="form-control" id="userinfo-name"
							name="name" value="${userinfo['userName']}">
					</div>
				</div>
				<div class="row mb-2 text-center">
					<div class="col-3">아이디</div>
					<div class="col">
						<input type="text" class="form-control"
							value="${userinfo['userId']}" disabled>
					</div>
				</div>
				<div class="row mb-2 text-center">
					<div class="col-3">비밀번호</div>
					<div class="col">
						<input type="password" placeholder="비밀번호" class="form-control"
							id="userinfo-password" name="password">
					</div>
				</div>
				<div class="row mb-2 text-center">
					<div class="col-3">신규비밀번호</div>
					<div class="col">
						<input type="password" placeholder="신규비밀번호" class="form-control"
							id="new-password" name="newPassword">
					</div>
				</div>
				<div class="row mb-2 text-center">
					<div class="col-3">비밀번호확인</div>
					<div class="col">
						<input type="password" placeholder="비밀번호확인" class="form-control"
							id="new-repassword">
					</div>
				</div>
				<div class="row mb-2 text-center">
					<div class="col-3">이메일</div>
					<div class="col-4">
						<input type="text" class="form-control" id="userinfo-email"
							name="email" value="${userinfo['emailId']}">
					</div>
					<div class="col-2">
						<span class="input-group-text">@</span>
					</div>
					<div class="col-3">
						<select class="custom-select" id="userinfo-selectdomain"
							name="domain">
							<option value="">도메인 선택</option>
							<option value="ssafy.com">SSAFY</option>
							<option value="google.com">GOOGLE</option>
							<option value="kakao.com">KAKAO</option>
							<option value="naver.com">NAVER</option>
						</select>
					</div>
				</div>

				<div id="edit-footer"
					class="container-fluid d-flex justify-content-end">
					<button type="button" class="btn mr-2 btn-primary btn-sm"
						id="edit-button">수정</button>
					<button type="button" class="btn btn-secondary btn-sm"
						id="reset-button">초기화</button>
					<button type="button" class="btn btn-danger btn-sm"
						id="withdraw-button">회원탈퇴</button>
				</div>
			</div>
		</form>
	</div>
</body>

<script>
	/* 회원가입 버튼 */
	document
			.querySelector("#edit-button")
			.addEventListener(
					"click",
					function() {
						if (!document.querySelector("#userinfo-name").value) {
							alert("이름을 입력해주세요 :)");
							return;
						} else if (document.querySelector("#new-password").value != document
								.querySelector("#new-repassword").value) {
							alert("신규비밀번호를 다시 확인해주세요 :)");
							return;
						} else {
							let form = document.querySelector("#form-edit");
							form.setAttribute("action", "${root}/user");
							form.submit();
						}
					});
</script>
</html>