<script setup>
import {ip} from '@/util/ip.js'
import { ref, onMounted, inject } from "vue";
import { getCookie } from "@/assets/js/cookie";
const isLogin = inject('isLogin');
const today = ref(new Date());
const user_info = inject('user_info');
const user_id = user_info.user_id;
const user_name = user_info.user_name;
const title = ref('');
const time = ref(`${today.value.getHours()>9?today.value.getHours():'0'+today.value.getHours()}:${today.value.getMinutes() >9 ? today.value.getMinutes():'0'+today.value.getMinutes()}:${today.value.getSeconds()>9?today.value.getSeconds():'0'+today.value.getSeconds()}`);
const date = ref(`${today.value.getFullYear()}-${today.value.getMonth()>9?today.value.getMonth():'0'+today.value.getMonth()}-${today.value.getDate()>9?today.value.getDate():'0'+today.value.getDate()}`);
function addTodo() {
    addTodos(title.value, date.value, time.value, user_id.value, false);
    today.value = new Date();
    title.value = '';
    time.value = `${today.value.getHours()>9?today.value.getHours():'0'+today.value.getHours()}:${today.value.getMinutes()>9?today.value.getMinutes():'0'+today.value.getMinutes()}:${today.value.getSeconds()>9?today.value.getSeconds():'0'+today.value.getSeconds()}`;
    date.value = `${today.value.getFullYear()}-${today.value.getMonth() > 9 ? today.value.getMonth() : '0' + today.value.getMonth()}-${today.value.getDate() > 9 ? today.value.getDate() : '0' + today.value.getDate()}`;
    console.log(time.value, date.value);
}
function upload() {
    fetch(ip + '/SVIhWrLV/PmtI1NmN', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ todos: todos.value,  sessionId: getCookie('sessionId') })
    }).then(function (res) {
        if (res.statusCode == 999) {
      throw new Error("x");
    }
        return res.json();
    }).then(function (obj) { 
        alert(obj['detail']);
    }).catch(err => {
    console.log(err);
    isLogin.value = false;
        deleteCookie('sessionId');
    location.reload();
  })
}
function download() {
    fetch(ip + '/SVIhWrLV/Crh17XqZ', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ sessionId: getCookie('sessionId') })
    }).then(function (res) {
        if (res.statusCode == 999) {
      throw new Error("x");
    }
        return res.json();
    }).then(function (obj) {
        if (obj['msg'] == 'OK') {
            const list = obj['list'];
            list.forEach(item => {
                addTodos(item.title, item.date, item.time, item.user_id, item.completed)
            });
        } else {
            alert(obj['detail']);
        }
    }).catch(err => {
    console.log(err);
        isLogin.value = false;
    
        deleteCookie('sessionId');
    location.reload();
  })
}
onMounted(() => { 
    today.value = new Date();
    time.value = `${today.value.getHours()>9?today.value.getHours():'0'+today.value.getHours()}:${today.value.getMinutes()>9?today.value.getMinutes():'0'+today.value.getMinutes()}:${today.value.getSeconds()>9?today.value.getSeconds():'0'+today.value.getSeconds()}`;
    date.value = `${today.value.getFullYear()}-${today.value.getMonth()>9?today.value.getMonth():'0'+today.value.getMonth()}-${today.value.getDate()>9?today.value.getDate():'0'+today.value.getDate()}`;
    for (todo in todos.value) {
        removeTodo(todo.id);
    }
    download();
});
const showAll = ref(true);


let id = ref(0);
    let todos = ref([]);

    const addTodos = (title, date, time, user_id, completed) => {
      todos.value.push({ id: id.value++, title, time, date, completed, user_id});
      todos.value.sort(function (a, b) {
        const adate = a.date.split('-');
        const bdate = b.date.split('-');
        const atime = a.time.split(':');
        const btime = b.time.split(':');
        if (adate[0] == bdate[0]) {
          if (adate[1] == bdate[1]) {
            if (adate[2] == bdate[2]) {
              if (atime[0] == btime[0]) {
                if (atime[1] == btime[1]) {
                  return atime[2] - btime[2];
                }
                return atime[1] - btime[1];
              }
              return atime[0] - btime[0];
            }
            return adate[2] - bdate[2];
          }
          return adate[1] - bdate[1];
        }
        return adate[0] - bdate[0];
      });
};
const removeTodo = (id, date, time) => {
    todos.value = todos.value.filter((todo) => todo.id !== id);
};
</script>

<template>
    <div class="row justify-content-center">
        <div class="col-lg-8 col-md-10 col-sm-12">
            <h2 class="my-3 py-3 shadow-sm bg-light text-center">
                <mark class="sky">여행일정관리</mark>
            </h2>
        </div>
        <div class="col-lg-8 col-md-10 col-sm-12">
            <div class="d-flex col-md-10 offset-1">
                <input type="date" name="date" id="date" class="form-control" v-model="date" />
                <input type="time" name="time" id="time" class="form-control" v-model="time"/>
                <input type="text" name="title" id="title" class="form-control" placeholder="할일..." v-model="title" />
                <button id="btn-search" class="btn btn-dark" type="button" @click="addTodo">추가</button>
            </div>
        </div>
        <div class="col-lg-8 col-md-10 col-sm-12">
            <table class="table table-hover">
                <thead>
                    <tr class="text-center">
                        <th scope="col">#</th>
                        <th scope="col">날짜</th>
                        <th scope="col">시간</th>
                        <th scope="col">내용</th>
                        <th scope="col">삭제</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="todo in todos" v-show="showAll==true||todo.completed==false"  class="text-center">
                        <td>
                            <input type="checkbox" @click="todo.completed=!todo.completed" :checked="todo.completed">
                        </td>
                        <td :class="{crossline:todo.completed}">
                            {{ todo.date }}
                        </td>
                        <td :class="{crossline:todo.completed}">
                            {{ todo.time }}
                        </td>
                        <td :class="{crossline:todo.completed}">
                            {{ todo.title }}
                        </td>
                        <td>
                            <button class="btn btn-outline-danger cancle" @click="removeTodo(todo.id, todo.date, todo.time)">X</button>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
        <div class="d-flex justify-content-center">
            <div class="col-auto text-center btn-div">
                <button v-if="showAll" @click="showAll=!showAll" class="btn btn-outline-danger">아직 안한 일정 보기</button>
                <button v-else @click="showAll=!showAll" class="btn btn-outline-success">모든 일정 보기</button>
            </div>
            <div class="col-auto text-center btn-div">
                <button @click="upload" class="btn btn-outline-primary">저장하기</button>
            </div>
        </div>
    </div>
</template>

<style scoped>
.crossline{
    text-decoration: line-through;
}
.cancle{
    padding: 0px 5px;
}
</style>
