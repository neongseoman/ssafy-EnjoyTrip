<script setup>
import {ref, provide, inject, onMounted} from "vue";
import LoginModal from "@/components/user/loginModal.vue";
import JoinModal from "@/components/user/joinModal.vue";
import InfoEditModal from "@/components/user/infoEditModal.vue";
import { fetch_logout } from "@/assets/js/logout.js";
import {getCookie, deleteCookie} from '@/assets/js/cookie.js'
const isLogin = inject('isLogin');
const showLoginModal = ref(false)
const showJoinModal = ref(false)
const showInfoEditModal = ref(false)
const openLoginModal = () => showLoginModal.value = !showLoginModal.value
const openJoinModal = () => showJoinModal.value = !showJoinModal.value
const openInfoEditModal = () => {
  showInfoEditModal.value = !showInfoEditModal.value
}
const logout =  () => {
  fetch_logout();
  deleteCookie("validUserId")
  deleteCookie("validUserName")
  deleteCookie('sessionId');
  location.reload();
    
}

provide("login", {openLoginModal, showLoginModal})
provide("join", {openJoinModal, showJoinModal})
provide("edit", {openInfoEditModal, showInfoEditModal})

onMounted(() => {
  if (getCookie("sessionId") != null) {
    isLogin.value = true
  } else {
    deleteCookie('validUserId');
    deleteCookie('validUserName');
  }
});
</script>

<template>
  <header class="header__layout">
    <div class="header">
      <div class="header__left">
        <nav id="navbar" class="navbar">
          <ul>
            <li>
              <router-link id="home-link" :to="{name:'home'}">
                <img id="logo" src="../../assets/img/logo.png" alt="logo"/></router-link>
            </li>
            <li>
              <router-link class="nav-link scrollto " :to="{name: 'map'}" id="map-link">지역별여행지</router-link>
            </li>
            <li>
              <router-link class="nav-link scrollto " :to="{name:'board'}" id="board-link">여행정보공유</router-link>
            </li>
            <li>
              <router-link class="nav-link scrollto " :to="{name:'todo'}" id="board-link">여행일정관리</router-link>
            </li>
          </ul>
          <i class="bi bi-list mobile-nav-toggle"></i>
        </nav>
      </div>
      <!--      {{userinfo.userId + "user ID"}}-->
      <!--      {{userinfo.userName + "user Name"}}-->
      <div class="header__right">

        <div id="before-login" v-if="!isLogin">
          <button @click="openLoginModal" id="login-a" class="login-modal-btn">로그인</button>
          <button @click="openJoinModal" id="join-a" class="join-modal-btn">회원가입</button>
        </div>

        <div id="after-login" v-else>
          <img id="profile" src="../../assets/img/profile.png" alt="profile"/>
          <!--          <span id="useridnim">-->
          {{ getCookie("validUserId") }}님 환영합니다.
          <!--          </span>-->
          <button @click="openInfoEditModal" class="infoedit-modal-btn" id="update-link">회원정보</button>
          <button @click=logout id="logout-button" value='로그아웃'>로그아웃</button>
        </div>
      </div>
    </div>
  </header>
  <!--  Modal section-->
  <teleport to="body">
    <login-modal/>
  </teleport>
  <teleport to="body">
    <join-modal/>
  </teleport>
  <teleport to="body">
    <info-edit-modal/>
  </teleport>
</template>

<style scoped>
.container img {
  width: 100%;
  /* 이미지의 너비를 부모 요소의 100%로 설정하여 부모에 맞춤 */
  max-width: 100%;
  /* 이미지가 원본보다 큰 경우에도 부모 요소 너비를 넘지 않도록 설정 */
  height: auto;
  /* 이미지의 원래 비율을 유지 */
}

#after-login {
  flex-flow: row nowrap;
  justify-content: flex-end;
  item-align: left;
}

#after-login > img {
  display: block;
  max-height: 50px;
}

#after-login > * {
  display: block;
  text-align: center;
}

#user-name-nim {
  display: inline-block;
  font-size: 20px;
}
</style>