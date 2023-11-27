<script setup>
import { ip } from '@/util/ip.js';
import { ref, onMounted, inject } from 'vue';
import { useBoardStore } from "@/store/board.js";
import { getCookie } from "@/assets/js/cookie";
const isLogin = inject('isLogin');
const boardStore = useBoardStore();
const user_info = inject('user_info');
const user_id = user_info.user_id;
const user_name = user_info.user_name;
const subject = ref('');
const content = ref('');
const positions = ref([]);
function writeBoard() {
    fetch(ip + '/YZ97gY92/ePQowXNQ', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({
            board: {
                subject: subject.value,
                content: content.value
             },
            positions: positions.value,
            sessionId: getCookie('sessionId')
        })
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
		deleteCookie('sessionId');
        location.reload();
    })
}
function reset() {
    subject.value = '';
    content.value = '';
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
            const latlng = mouseEvent.latLng;
            positions.value = positions.value.filter(e=>e.latitude!=latlng.Ma&&e.longitude!=latlng.La);
        })
    });
}

onMounted(() => {
    reset();
    makeMap();
});
</script>

<template>
    <div class="row justify-content-center" >
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
                <div class="mb-3">
                    <label for="map" class="form-label">위치 : </label>
                    <div id="map" style="width: 100%; height: 400px;"></div>
                </div>
                <div class="d-flex justify-content-center">
                    <div class="col-auto text-center btn-div">
                        <router-link :to="{name:'board-list'}" replace>
                            <button type="button" id="btn-register" class="btn btn-outline-primary mb-3" @click="writeBoard">
                                글작성
                            </button>
                        </router-link>
                    </div>
                    <div class="col-auto text-center btn-div">
                        <button type="reset" class="btn btn-outline-danger mb-3" @click="reset">초기화</button>
                    </div>
                    <div class="col-auto text-center btn-div">
                        <router-link :to="{ name: 'board-list' }" style="display: block;">
                            <button type="button" class="btn btn-outline-success">글목록</button>
                        </router-link>
                    </div>
                </div>

            </div>
        </div>
    </div>
</template>

<style scoped>
.btn-div {
    display: inline-flex;
}
</style>