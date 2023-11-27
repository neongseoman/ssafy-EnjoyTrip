<script setup>
import {passwordCheck,idCheck,join} from "@/assets/js/join";
import {inject} from "vue";

const {openJoinModal,showJoinModal} = inject('join')
const joinProcess = () =>{
  console.log("join process")
  if (join()) {
    console.log("join success")
    openJoinModal()
  }
  else
    console.log("fail!!")
}
</script>

<template>

  <Transition name="modal">
    <div v-if="showJoinModal" class="modal-mask">
      <div class="modal-container">
        <div class="modal-header">
          <header id="join-modal__modal_header_" class="modal-header">
            <h3 id="join-modal__modal_title_" class="modal-title">회원가입</h3>
            </header>
        </div>

        <div class="modal-body">
          <div id="join-modal___modal_body_" class="modal-body">
            <div>
              <div class="container-fluid">
                <div class="row mb-1 text-center">
                  <div class="col-3">이름</div>
                  <div class="col">
                    <input type="text" placeholder="이름" class="form-control"
                           id="username">
                  </div>
                </div>
                <div class="row mb-1 text-center">
                  <div class="col-3">아이디</div>
                  <div class="col">
                    <input type="text" placeholder="아이디" class="form-control"
                           id="userid">
                  </div>
                  <div class="col-3">
                    <button type="button" class="btn btn-secondary btn-sm"
                            id="id-check-button" @click="idCheck">중복확인
                    </button>
                    <input type="hidden" id="is-id-checked" value="NO">
                  </div>
                </div>
                <div class="row mb-1 text-center">
                  <div class="col-3">비밀번호</div>
                  <div class="col">
                    <input type="password" placeholder="비밀번호" class="form-control"
                           id="userpwd" @input="passwordCheck">
                  </div>
                </div>
                <!-- 비밀번호 조건 추가 -->
                <div class="row mb-1 text-center">
                  <div class="col-3"></div>
                  <div class="col">
                    <div class="row mb-1">
									<span style="font-size: 12px; text-align: left;"> 최소
										10자리 이상 / 영문, 숫자, 특수기호 중 2가지 필수 </span>
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
                           id="useremail">
                  </div>
                  <div class="col-1">
                    <span class="input-group-text">@</span>
                  </div>
                  <div class="col">
                    <select class="custom-select" id="selectdomain">
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
          </div>
        </div>

        <div class="modal-footer">
          <footer id="join-modal__modal_footer_" class="modal-footer">
          <div>
            <button type="button" class="btn btn-danger btn-sm close-area"
                    @click=openJoinModal
                    id="register-cancel-button">취소
            </button>
            <button type="button" class="btn mr-2 btn-primary btn-sm"
                    @click="joinProcess"
                    id="join-button">회원가입
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

/*
 * The following styles are auto-applied to elements with
 * transition="modal" when their visibility is toggled
 * by Vue.js.
 *
 * You can easily play with the modal transition by editing
 * these styles.
 */

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