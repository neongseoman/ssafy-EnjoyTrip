<script setup>
import { inject } from "vue";
import { login } from "@/assets/js/login";
import {setCookie} from '@/assets/js/cookie.js'
const {openLoginModal,showLoginModal} = inject("login")
const {user_name,user_id} = inject("user_info")
const isLogin = inject('isLogin');
const loginProcess = async ()=>{
  console.log("process")
  const result = await login()
  console.log("login done")
  // console.log(result)
  if (result.msg === "OK") {
    console.log("result : " + result.msg + result.detail)
    alert("성공")
    openLoginModal()
    user_name.value = result.name;
    user_id.value = result.userId;
    setCookie("validUserId", user_id.value)
    setCookie('validUserName', user_name.value);
    isLogin.value = true;
  } else{
    alert(result.detail)
  }
  console.log("user_id : ",user_id.value, "\n user_name : ",user_name.value)
}
</script>

<template>
  <Transition name="modal">
    <div v-if="showLoginModal" class="modal-mask">
      <div class="modal-container">
        <div class="modal-header">
          <header id="login-modal__modal_header_" class="modal-header">
            <h3 id="login-modal__modal_title_" class="modal-title">로그인</h3>
          </header>
        </div>

        <div class="modal-body">
          <div id="login-modal___modal_body_" class="modal-body">
            <div class="container-fluid">
              <div class="row mb-1">
                <div class="col-12 col-md-3 text-md-right">
                  <!-- 아이디 라벨 -->
                  <label for="login-id">아이디</label>
                </div>
                <div class="col-12 col-md-9">
                  <!-- 아이디 입력란 -->
                  <input type="text" placeholder="아이디" class="form-control"
                         id="login-id">
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
                         id="login-password">
                </div>
              </div>
              <div class="row">
                <div class="col-12 col-md-9 offset-md-3">
                  <!-- 아이디 저장 체크박스 -->
                  <div class="form-check text-md-left">
                    <label class="form-check-label" for="saveid">아이디 저장</label>
                    <input
                      type="checkbox" class="form-check-input" id="saveid">
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>

        <div class="modal-footer">
          <footer>
            <div>
<!--              @click="$emit('close')"-->
              <button
                  class="modal-default-button"
                  @click=openLoginModal()
                  id="cancel-button">취소
              </button>
              <button type="button" class="btn mr-2 btn-primary btn-sm"
                      @click="loginProcess"
                      id="login-button">로그인
              </button>
            </div>
          </footer>
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

.modal-enter-from .modal-container,
.modal-leave-to .modal-container {
  -webkit-transform: scale(1.1);
  transform: scale(1.1);
}
</style>