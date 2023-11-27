<script setup>
import {ref} from "vue";
import {login} from "@/api/user.js";

const id = ref('')
const pw = ref('')
const loginHandler = () => {
  console.log(id.value)
  console.log(pw.value)
  const param = {id: id.value, pw: pw.value}
  login(param,
      ({data}) => { //res의 구조분해할당으로 data 변수만 받음.
    //token을 어떻게 브라우저에서 관리할까? session Storage or pinia
    console.log(data.tokens[0]) //
        sessionStorage.setItem("access_token",data.tokens[0])
    console.log(data.tokens[1]) //
        sessionStorage.setItem("refresh_token",data.tokens[1])

      }, (err) => {
    console.log(err)
  })
}
</script>

<template>
  <div>
    ID <input v-model="id">
    PW <input type="text" v-model="pw">
    <button @click="loginHandler">login</button>
  </div>

</template>

<style scoped>

</style>