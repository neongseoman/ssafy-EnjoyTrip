<script setup>
import { ip } from '@/util/ip.js';
import { ref, onMounted, inject, watch } from 'vue';
import { useBoardStore } from "@/store/board.js";
import { getCookie, deleteCookie } from "@/assets/js/cookie";
const isLogin = inject('isLogin');
const boardStore = useBoardStore(); 
const comments = ref([]);
const board = ref({});
const content = ref('');
const showBtn = ref(false);
const article_no = inject('article_no');
const user_info = inject('user_info');
const user_id = user_info.user_id;
const user_name = user_info.user_name;
const positions = ref([]);
function getDetail() {
    fetch(ip + '/YZ97gY92/KPVnFhFX',{
        method: 'POST',
        headers: { 'Content-Type': 'application/json', },
        body: JSON.stringify({articleNo: article_no.value, sessionId: getCookie('sessionId')})
	}).then(function (res) {
		if (res.statusCode == 999) {
			throw new Error("x");
		}
		return res.json();
	}).then(function(obj){
		const msg = obj['msg'];
        if (msg == 'OK') {
			board.value = obj['board'];
			positions.value = obj['positions'];
			if (user_id.value == board.value.userId) {
				showBtn.value = true;
			}
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
function getComments() {
    fetch(ip + '/B1Fc0O5K/qSg1WWvf',{
        method: 'POST',
        headers: { 'Content-Type': 'application/json', },
        body: JSON.stringify({articleNo: article_no.value, sessionId: getCookie('sessionId')})
	}).then(function (res) {
		if (res.statusCode == 999) {
			throw new Error("x");
		}
		return res.json();
	}).then(function(obj){
		const msg = obj['msg'];
        const list = obj['list'];
        if (msg == 'OK') {
            comments.value = list;
        }
	}).catch(err => { 
		console.log(err);
		isLogin.value = false;
		location.reload();
	})
}
function addComment() {
    fetch(ip + '/B1Fc0O5K/ndKw8G69',{
        method: 'POST',
        headers: { 'Content-Type': 'application/json', },
        body: JSON.stringify({articleNo: article_no.value, content: content.value, sessionId: getCookie('sessionId') })
	}).then(function (res) {
		if (res.statusCode == 999) {
			throw new Error("x");
		}
		return res.json();
	}).then(function(obj){
		const msg = obj['msg'];
        if (msg == 'NO') {
            alert('뎃글을 다는데 실패했습니다.');
		} else {
			content.value = '';
			getComments();
		}
	}).catch(err => { 
		console.log(err);
		isLogin.value = false;
		location.reload();
	})
    
}
function deleteBoard(){
	fetch(ip + '/YZ97gY92/S1BLjFsA',{
        method: 'POST',
        headers: { 'Content-Type': 'application/json', },
        body: JSON.stringify({articleNo: article_no.value, sessionId: getCookie('sessionId')})
	}).then(function (res) {
		if (res.statusCode == 999) {
			throw new Error("x");
		}
		return res.json();
	}).then(function (obj) {
		boardStore.updateBoard('OK');
	}).catch(err => { 
		console.log(err);
		isLogin.value = false;
		location.reload();
	})
}
function commentDelete(idx) {
	fetch(ip + '/B1Fc0O5K/cBHoXtQ5', {
		method: 'POST',
		headers: { 'Content-Type': 'application/json' },
		body: JSON.stringify({ idx, sessionId: getCookie('sessionId')})
	}).then(function (res) {
		return res.json();
	}).then(function (obj) {
		if (obj['msg'] == 'OK') {
			getComments();
		} else {
			alert(obj['detail']);
		}
	}).catch(err => { 
		console.log(err);
		isLogin.value = false;
		location.reload();
	})
}
watch(positions, function () {
	const mapDiv = document.getElementById('map');
	if (positions.value.length > 0) {
		mapDiv.style.display = 'block';
		const mapPos = [];
		for (let i = 0; i < positions.value.length; i++) {
			const pos = positions.value[i];
			const position = new kakao.maps.LatLng(pos['latitude'], pos['longitude']);
			mapPos.push(position);
		}
		makeMarkers(mapPos)

	} else {
		mapDiv.style.display = 'none';
	}
});
function makeMarkers(positions){
	const mapContainer = document.getElementById('map');
	const mapOption = {
			center: positions[0], // 지도의 중심좌표
	        level: 3 
	}
	const map = new kakao.maps.Map(mapContainer, mapOption);
	const bounds = new kakao.maps.LatLngBounds();    
	for(let i=0;i<positions.length;i++){
		const pos = positions[i];
		const marker = new kakao.maps.Marker({
		    position: pos
		});
		marker.setMap(map);
		bounds.extend(pos);
	}
	map.setBounds(bounds);
}
onMounted(() => { 
    getDetail();
	getComments();
})
</script>

<template>
    <div class="row justify-content-center">
		<div class="col-lg-8 col-md-10 col-sm-12">
			<div class="row my-2">
				<h2 class="text-secondary px-5">{{ board.articleNo }}.
					{{ board.subject }}</h2>
			</div>
			<div class="row">
				<div class="col-md-2">
					<div class="clearfix align-content-center">
						<img class="avatar me-2 float-md-start bg-light p-2"
							src="https://raw.githubusercontent.com/twbs/icons/main/icons/person-fill.svg" />
						<div>
							<div class="fw-bold">{{ board.userId }}</div>
                            <div class="text-secondary fw-light"> 조회 : {{ board.hit }} 회</div>
						</div>
					</div>
				</div>
				<div class="divider mb-3"></div>
				<div class="text-secondary">{{ board.content }}</div>
				<div id="map" style="width: 100%; height: 400px; display: none;"></div>
				<div class="divider mt-3 mb-3"></div>
				<div class="d-flex justify-content-end">
					<router-link :to="{name: 'board-list'}">
						<button type="button" id="btn-list"
						class="btn btn-outline-primary mb-3">글목록</button>
					</router-link>
					<router-link v-if="showBtn" :to="{name: 'board-modify'}">
						<button type="button" id="btn-mv-modify" 
							class="btn btn-outline-success mb-3 ms-1">글수정</button>
					</router-link>
					<router-link v-if="showBtn" :to="{name: 'board'}" >
						<button type="button" id="btn-delete"
							class="btn btn-outline-danger mb-3 ms-1" @click="deleteBoard" >글삭제</button>
					</router-link>
				</div>
			</div>
		</div>
		<div class="col-lg-8 col-md-10 col-sm-12" id="comment-container">
			<div id="write-comment-div" class="row my-2">
				<form action="article" method="post">
					<input type="text" id="comment" placeholder="댓글을 추가해 보세요!" v-model="content"
						style="width: 90%;"> 
                    <input type="button" value="add" style="width: 8%;" @click="addComment">
				</form>
			</div>
			<div class="row my-2" id="comment-list-div">
				<table id="comment-list" class="table table-hover col">
					<thead>
						<tr class="text-center">
							<th scope="col" class="col-md-2">댓글 번호</th>
							<th scope="col" class="col-md-8">내용</th>
							<th scope="col" class="col-md-2">작성자</th>
							<th scope="col"></th>
						</tr>
					</thead>
					<tbody id="comment-list-body">
						<tr v-for="comment in comments" class="text-center" >
							<td>{{ comment.idx }}</td>
							<td>{{ comment.content }}</td>
							<td>{{ comment.userName }}</td>
							<td v-if="user_id==comment.userId">
								<button class="btn btn-danger comment-delete" @click="commentDelete(comment.idx)">X</button>
							</td>
							<td v-else></td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</template>

<style scoped>
.comment-delete{
	padding: 0px 5px;
}
</style>