<script setup>
import Navbar from "@/components/commons/navbar.vue";
import {provide, ref} from "vue";
import {getCookie} from "@/assets/js/cookie";
const isLogin = ref(false);
const user_id = ref(getCookie('validUserId'));
const user_name = ref(getCookie('validUserName'));

provide("user_info", {user_id,user_name})
provide("isLogin", isLogin);
function setUserName(name) {
  user_name.value = name;
}
function setUserId(id) {
  user_id.value = id;
}
</script>

<template>
  <Navbar @user_name="setUserName" @user_id="setUserId"></Navbar>
  <div class="container" id="contents">
    <router-view></router-view> 
  </div>
<!--  User info check-->
<!--  <button @click="console.log(user_id)">{{user_name}}</button>-->
</template>

<style>
.container img {
  width: 100%;
  /* 이미지의 너비를 부모 요소의 100%로 설정하여 부모에 맞춤 */
  max-width: 100%;
  /* 이미지가 원본보다 큰 경우에도 부모 요소 너비를 넘지 않도록 설정 */
  height: auto;
  /* 이미지의 원래 비율을 유지 */
}
</style>