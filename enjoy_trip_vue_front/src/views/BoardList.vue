<script setup>
import { ip } from '@/util/ip.js';
import {ref, onMounted, watch, inject} from 'vue';
import { useBoardStore } from "@/store/board.js";
import { getCookie, deleteCookie } from "@/assets/js/cookie";
const isLogin = inject('isLogin');
const boardStore = useBoardStore();
const isupdated = boardStore.watchBoard;
const boards = ref([]);
const key = ref('');
const word = ref('');
function makeBoardList1(pgno) {
    fetch(ip + '/YZ97gY92/Ct6X83dL', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json', },
        body: JSON.stringify({ pgno, sessionId: getCookie('sessionId') })
    }).then(function (res) {
        console.log(res.statusCode);
        if (res.statusCode == 999) {
            throw new Error("x");
        }
        console.log(res);
        return res.json();
    }).then(function (obj) {
        const msg = obj['msg'];
        const list = obj['list'];
        if (msg == 'OK') {
            boards.value = list;
        }
    }).catch(err => {
        console.log(err);
        isLogin.value = false;
        //deleteCookie('sessionId');
        //location.reload();
    })
}
function makeBoardList(pgno) {
    fetch(ip + '/YZ97gY92/Ct6X83dL', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json', },
        body: JSON.stringify({ pgno, key: key.value, word: word.value, sessionId: getCookie('sessionId') })
    }).then(function (res) {
        if (res.statusCode === 999) {
            throw new Error("x");
        }
        return res.json();
    }).then(function (obj) {
        const msg = obj['msg'];
        const list = obj['list'];
        if (msg == 'OK') {
            boards.value = list;
        }
    }).catch(err => {
        console.log(err);
        isLogin.value = false;
        //deleteCookie('sessionId');
        //location.reload();
    })
}
watch(isupdated, function () {
    if (isupdated.value == 'OK') {
        makeBoardList1(1)
        boardStore.updateBoard('NO');
    }
})
function updateKey() {
    const select_key = document.getElementById('key');
    key.value = select_key[select_key.selectedIndex].value;
}
onMounted(() => {
    makeBoardList1(1);
});

</script>
<template>
    <div>
        <div class="d-flex col-md-10 col justify-content-center">
            <div class="d-flex col-md-10 offset-3">
                <select name="key" id="key" class="form-select form-select-sm ms-5 me-1 w-50" aria-label="검색조건"
                    @change="updateKey">
                    <option value="" selected>검색조건</option>
                    <option value="content">내용</option>
                    <option value="subject">제목</option>
                    <option value="user_id">작성자</option>
                </select>
                <div class="input-group input-group-sm">
                    <input type="text" name="word" id="word" class="form-control" placeholder="검색어..." v-model="word" />
                    <button id="btn-search" class="btn btn-dark" type="button" @click="makeBoardList(1)">검색</button>
                </div>
            </div>
            <div class="col-md-1"></div>
            <div class="col-md-2">
                <router-link :to="{ name: 'board-write' }" class="col-lg-1 col-md-3 col-sm-12" >
                    <button class="btn btn-outline-primary">글쓰기</button>
                </router-link>
            </div>
        </div>
        <table class="table table-hover">
            <thead>
                <tr class="text-center">
                    <th scope="col">글번호</th>
                    <th scope="col">제목</th>
                    <th scope="col">작성자</th>
                    <th scope="col">조회수</th>
                    <th scope="col">작성일</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="board in boards" :key="board.article_no"  class="text-center">
                    <th>{{ board.articleNo }}</th>
                    <th><router-link :to="{ name: 'board-detail' }" @click="$emit('articleNo', board.articleNo);" replace>{{
                        board.subject }}</router-link></th>
                    <th>{{ board.userId }}</th>
                    <th>{{ board.hit }}</th>
                    <th>{{ board.registerTime }}</th>
                </tr>
            </tbody>
        </table>
    </div>
</template>

<style scoped></style>