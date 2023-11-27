<script setup>
import {ip} from '@/util/ip.js'
import {ref, onMounted, inject, watch} from 'vue';
import { useBoardStore } from "@/store/board.js";
import { getCookie, deleteCookie } from "@/assets/js/cookie";

const isLogin = inject('isLogin');
const boardStore = useBoardStore();
const user_info = inject('user_info');
const user_id = user_info.user_id;
const article_no = inject('article_no');
const subject = ref('');
const content = ref('');
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
        const board = obj['board'];
        if (msg == 'OK') {
            subject.value =  board.subject;
            content.value = board.content;
            positions.value = obj['positions'];
            console.log(positions.value);
        }
	}).catch(err => {
        console.log(err);
        isLogin.value = false;
        deleteCookie('sessionId');
        location.reload();
    })
}
function updateBoard() {
    fetch(ip+'/YZ97gY92/7QJMgsU7', {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify({
            board: {articleNo: article_no.value, subject: subject.value, content: content.value},
            positions: positions.value,
            sessionId: getCookie('sessionId')
        })
    }).then(function (res) {
        if (res.statusCode == 999) {
      throw new Error("x");
    }
        return res.json();
    }).then(function (obj) {
        if (obj['msg'] == 'OK') {
            boardStore.updateBoard('OK');
        }
        alert(obj['detail']);
    }).catch(err => {
        console.log(err);
        isLogin.value = false;
        deleteCookie('sessionId');
        location.reload();
    })
}
function makeMap() {
    const mapContainer = document.getElementById('map');
    const mapOption = {
		center: new kakao.maps.LatLng(37.501265, 127.039610), // 지도의 중심좌표
        level: 3,
        mapTypeId: kakao.maps.MapTypeId.ROADMAP  
	}
    const map = new kakao.maps.Map(mapContainer, mapOption);
    kakao.maps.event.addListener(map, 'click', function(mouseEvent) {        
        var marker = new kakao.maps.Marker({ 
        // 지도 중심좌표에 마커를 생성합니다 
            position: map.getCenter() ,
            clickable: true
        }); 
        // 클릭한 위도, 경도 정보를 가져옵니다 
        const latlng = mouseEvent.latLng; 
        console.log(latlng);
        positions.value.push({ latitude: latlng.Ma, longitude: latlng.La });
        // 마커 위치를 클릭한 위치로 옮깁니다
        marker.setPosition(latlng);
        marker.setMap(map);
        kakao.maps.event.addListener(marker,"click", function(){
            marker.setMap(null);
            const lng = marker.longitude;
            const lat = marker.latitude;
            positions.value = positions.value.filter(e=>e.latitude!=lat&&e.longitude!=lng);
        })
    });
}
watch(positions, function () {
	if (positions.value.length > 0) {
		const mapPos = [];
		for (let i = 0; i < positions.value.length; i++) {
			const pos = positions.value[i];
			const position = new kakao.maps.LatLng(pos['latitude'], pos['longitude']);
			mapPos.push(position);
		}
		makeMarkers(mapPos)
	}else {
        makeMap();
    }
});
function makeMarkers(posi){
	const mapContainer = document.getElementById('map');
	const mapOption = {
			center: posi[0], // 지도의 중심좌표
	        level: 3 
	}
	const map = new kakao.maps.Map(mapContainer, mapOption);
	const bounds = new kakao.maps.LatLngBounds();    
	for(let i=0;i<posi.length;i++){
		const pos = posi[i];
		const marker = new kakao.maps.Marker({
		    position: pos
		});
		marker.setMap(map);
		bounds.extend(pos);

        kakao.maps.event.addListener(marker,"click", function(){
            const lng = pos.La;
            const lat = pos.Ma;
            positions.value = positions.value.filter(e=>e.latitude!=lat&&e.longitude!=lng);
        });
	}
	map.setBounds(bounds);
    kakao.maps.event.addListener(map, 'click', function(mouseEvent) {        
        var marker = new kakao.maps.Marker({ 
        // 지도 중심좌표에 마커를 생성합니다 
            position: map.getCenter() ,
            clickable: true
        }); 
        // 클릭한 위도, 경도 정보를 가져옵니다 
        const latlng = mouseEvent.latLng; 
        positions.value.push({ latitude: latlng.Ma, longitude: latlng.La });
        // 마커 위치를 클릭한 위치로 옮깁니다
        marker.setPosition(latlng);
        marker.setMap(map);
        kakao.maps.event.addListener(marker,"click", function(){
            const lng = mouseEvent.latLng.La;
            const lat = mouseEvent.latLng.Ma;
            positions.value = positions.value.filter(e=>e.latitude!=lat&&e.longitude!=lng);
        });
    });
}
onMounted(()=>{
    getDetail();
    makeMap();
});
</script>

<template>
    <div class="row justify-content-center">
        <div class="col-lg-8 col-md-10 col-sm-12">
            <div id="form-register">
                <div class="mb-3">
                    <label for="subject" class="form-label">제목 : </label>
                    <input type="text" v-model="subject" class="form-control" id="subject" name="subject"
                        placeholder="제목..." />
                </div>
                <div class="mb-3">
                    <label for="content" class="form-label">내용 : </label>
                    <textarea class="form-control" v-model="content" id="content" name="content" rows="7"></textarea>
                </div>
                <div class="mb-3" style="height: 400px;" id="map"></div>
                <div class="d-flex justify-content-center">
                    <div class="col-auto text-center btn-div">
                        <router-link :to="{name:'board-list'}" >
                            <button type="button" id="btn-register" class="btn btn-outline-primary mb-3" @click="updateBoard">
                                글수정
                            </button>
                        </router-link>
                    </div>
                    <div class="col-auto text-center btn-div">
                        <button type="reset" class="btn btn-outline-danger mb-3" @click="reset">초기화</button>
                    </div>
                    <div class="col-auto text-center btn-div">
                        <router-link :to="{ name: 'board-list' }" style="display: block;" onClick="alert('글 수정이 최소 되었습니다.');">
                            <button type="button" class="btn btn-outline-success">글목록</button>
                        </router-link>
                    </div>
                </div>

            </div>
        </div>
    </div>
</template>

<style scoped>

</style>