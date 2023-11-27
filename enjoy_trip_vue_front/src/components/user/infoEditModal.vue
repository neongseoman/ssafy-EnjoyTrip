<script setup>
import { inject, ref } from "vue";
import { getCookie, deleteCookie } from "@/assets/js/cookie";

const url = import.meta.env.VITE_ENV_URL
const update_url = url + '/reHfPyFw/yztR0IHo'

const {openInfoEditModal, showInfoEditModal} = inject("edit")
const { user_id, user_name } = inject('user_info');
const isLogin = inject('isLogin');
const userName = user_name;
const userPassword = ref('');
const userId = user_id;
const newPassword = ref('');
const checkPassowrd = ref('');
const emailId = ref('');
const emailDomain = ref('');
function edit() {
	if (newPassword.value == '') {
		alert('새 비밀번호를 입력해 주세요');
		return;
	}
	if (newPassword.value != checkPassowrd.value) {
		alert('비밀번호 확인이 틀립니다.');
		return;
	}
	if (emailId.value == '') {
		alert('이메일을 입력헤 주세요');
		return;
	}
	if (emailDomain.value == '') {
		alert('이메일 도메인을 선택해 주세요');
		return;
	}
  const userInfo = {
    userPassword: userPassword.value, newPassword: newPassword.value, emailId: emailId.value, emailDomain: emailDomain.value,
    sessionId: getCookie('sessionId')
  }
  console.log(userInfo);
  fetch(update_url, {
		method: 'POST',
		headers: {'Content-Type': 'application/json'},
		body: JSON.stringify(userInfo)
  }).then(function (res) {
    if (res.statusCode == 999) {
      throw new Error("x");
    }
		return res.json();
	}).then(function(obj){
		const msg = obj['msg'];
		const detail = obj['detail'];
		if(msg==='OK'){
      console.log("ok")
      alert('회원 정보가 정상적으로 수정 되었습니다.');
      openInfoEditModal();
		}
		alert(detail);
  }).catch(err => {
    console.log(err);
    isLogin.value = false;
    location.reload();
  })
	
};

function reset(){
	//빈칸으로 만들기
  userPassword.value = '';
  newPassword.value = '';
  checkPassowrd.value = '';
  emailId.value = '';
  emailDomain.value = '';
};

function withdraw() {
  fetch(url + '/reHfPyFw/0fokQBK6', {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ sessionId: getCookie('sessionId')})
  }).then(function (res) {
    if (res.statusCode == 999) {
      throw new Error("x");
    }
    return res.json();
  }).then(function (result) {
    if (result['msg'] == 'OK') {
      console.log("msg=OK");
      deleteCookie('validUserId');
      deleteCookie('validUserName');
      deleteCookie('sessionId');
      openInfoEditModal();
      Location.reload();
    }
    alert(result['detail']);
  }).catch(err => {
    console.log(err);
    isLogin.value = false;
    location.reload();
  })
};
</script>

<template>
  <Transition name="modal">
    <div v-if="showInfoEditModal" class="modal-mask">
      <div class="modal-container">
        <div class="modal-header">
          <div class="row mb-4 text-center">
            <h3>회원정보 수정</h3>
          </div>
        </div>

        <div class="modal-body">
          <div class="container-fluid">
            <div class="row mb-2 text-center">
              <div class="col-3">이름</div>
              <div class="col">
                <input type="text" class="form-control" id="userinfo-name" disabled :value="user_name">
              </div>
            </div>
            <div class="row mb-2 text-center">
              <div class="col-3">아이디</div>
              <div class="col">
                <input type="text" class="form-control" disabled :value="user_id"
                       id="userinfo-id">
              </div>
            </div>
            <div class="row mb-2 text-center">
              <div class="col-3">비밀번호</div>
              <div class="col">
                <input type="password" placeholder="비밀번호" class="form-control" v-model="userPassword"
                       id="userinfo-password" name="password">
              </div>
            </div>
            <div class="row mb-2 text-center">
              <div class="col-3">신규비밀번호</div>
              <div class="col">
                <input type="password" placeholder="신규비밀번호" class="form-control" v-model="newPassword"
                       id="new-password" name="newPassword">
              </div>
            </div>
            <div class="row mb-2 text-center">
              <div class="col-3">비밀번호확인</div>
              <div class="col">
                <input type="password" placeholder="비밀번호확인" class="form-control" v-model="checkPassowrd"
                       id="new-repassword">
              </div>
            </div>
            <div class="row mb-2 text-center">
              <div class="col-3">이메일</div>
              <div class="col-4">
                <input type="text" class="form-control" id="userinfo-email" v-model="emailId">
              </div>
              <div class="col-2">
                <span class="input-group-text">@</span>
              </div>
              <div class="col-3">
                <select class="custom-select" id="userinfo-selectdomain" v-model="emailDomain"
                        name="domain">
                  <option value="">도메인 선택</option>
                  <option value="ssafy.com">SSAFY</option>
                  <option value="google.com">GOOGLE</option>
                  <option value="kakao.com">KAKAO</option>
                  <option value="naver.com">NAVER</option>
                </select>
              </div>
            </div>


          </div>
        </div>

        <div class="modal-footer">
          <div id="edit-footer"
               class="container-fluid d-flex justify-content-end">
            <button type="button" class="btn mr-2 btn-primary btn-sm"
                    id="edit-button" @click="edit">수정</button>
            <button type="button" class="btn btn-secondary btn-sm"
                    id="reset-button" @click="reset">초기화</button>
            <button type="button" class="btn btn-danger btn-sm"
                    id="withdraw-button" @click="withdraw" >회원탈퇴</button>
          </div>
            <button
                class="modal-default-button"
                @click="openInfoEditModal"
            >취소</button>
        </div>
      </div>
    </div>
  </Transition>
</template>


<style scoped>

.modal-mask {
  position: fixed;
  z-index: 9998;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5);
  display: flex;
  transition: opacity 0.3s ease;
}

.modal-container {
  width: 500px;
  margin: auto;
  padding: 20px 30px;
  background-color: #fff;
  border-radius: 2px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.33);
  transition: all 0.3s ease;
}

.modal-header h3 {
  margin-top: 0;
  color: #42b983;
}

.modal-body {
  margin: 20px 0;
}

.modal-default-button {
  float: right;
}

.modal-enter-from {
  opacity: 0;
}

.modal-leave-to {
  opacity: 0;
}

.modal-enter-from .modal-container,
.modal-leave-to .modal-container {
  -webkit-transform: scale(1.1);
  transform: scale(1.1);
}
</style>