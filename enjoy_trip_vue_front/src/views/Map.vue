<script setup>
import { ref, onMounted, provide,inject } from 'vue';
import { ip } from '@/util/ip.js'
import DetailModal from "@/components/map/DetailModal.vue";
import { getCookie, deleteCookie } from "@/assets/js/cookie";
const isLogin = inject('isLogin');
const sidos = ref([]);
const guguns = ref([]);
const showDetail = ref(false);
const target = ref({});
function openDetailModal() {
	showDetail.value = true;
}
function closeDetailModal() {
	showDetail.value = false;
}
function getDetail(row) {
	target.value = row;
	openDetailModal();
}
const map_row = ref([]);
function changeGugun() {
    const select = document.getElementById('search-area');
    const sido_code = select[select.selectedIndex].value;
	document.querySelectorAll('#search-sigungu option').forEach(function(e){
		if(e.getAttribute('class')==`sido-${sido_code}`){
            e.style.display = 'block';
		}else{
			e.style.display = 'none';
		}
	});
	document.getElementById('search-sigungu').selectedIndex = 0;
}
provide('open_modal', showDetail);
provide('close_modal_function', closeDetailModal);
provide('map_info', target);
function changeOption() {
    const select_gugun = document.getElementById('search-sigungu');
	const gugun_code = select_gugun[select_gugun.selectedIndex].value;
	let myStyle = 'block';
	if(gugun_code==''||gugun_code==null){
		myStyle = 'none';
	}
	document.querySelectorAll('#search-content-id option').forEach(function(option){
		option.style.display = myStyle;
	});

	document.getElementById('search-content-id').selectedIndex = 0;
}
function onload() {
    fetch(ip+'/MytfEaEE/vQ1gaZml', {
		method: 'POST',
		headers: { 'Content-Type': 'application/json' },
		body: JSON.stringify({ sessionId: getCookie('sessionId') })
	}).then(function (res) {
		if (res.statusCode == 999) {
			throw new Error("x");
		}
		return res.json();
	}).then(function(obj){
		const msg = obj['msg'];
		const detail = obj['detail'];
		const list = obj['list'];
		if(msg=='OK'){
            sidos.value = list;
        } else {
            alert(detail);
        }
	}).catch(err => {
        console.log(err);
		isLogin.value = false;
		deleteCookie('sessionId');
        location.reload();
    })
	fetch(ip+'/MytfEaEE/j4nwRBe9', {
		method: 'POST',
		headers: { 'Content-Type': 'application/json' },
		body: JSON.stringify({ sessionId: getCookie('sessionId') })
	}).then(function (res) {
		if (res.statusCode == 999) {
			throw new Error("x");
		}
		return res.json();
	}).then(function(obj){
		const msg = obj['msg'];
		const detail = obj['detail'];
		const list = obj['list'];
		if(msg=='OK'){
            guguns.value = list;
		}else{
			alert(detail);
		}
	}).catch(err => {
        console.log(err);
        isLogin.value = false;
		deleteCookie('sessionId');
        location.reload();
    })
    let position = new kakao.maps.LatLng(37.501265, 127.039610);
	let map = new kakao.maps.Map(document.getElementById('map'), {
		center: position,
		level: 3,
		mapTypeId: kakao.maps.MapTypeId.ROADMAP
	})
}
function getMapInfo() {
    const select_sido = document.getElementById('search-area');
	const sido_code = select_sido[select_sido.selectedIndex].value;
	const select_gugun = document.getElementById('search-sigungu');
	const gugun_code = select_gugun[select_gugun.selectedIndex].value;
	const select_type = document.getElementById('search-content-id');
	const content_type_id = select_type[select_type.selectedIndex].value;
	if(content_type_id==''){
		return;
	}
	fetch(ip+'/MytfEaEE/hqJImwm8', {
		method: 'POST',
		headers: {'Content-Type': 'application/json'},
		body: JSON.stringify({sido_code, gugun_code, content_type_id, sessionId: getCookie('sessionId')})
	}).then(function (res) {
		if (res.statusCode == 999) {
			throw new Error("x");
		}
		return res.json();
	}).then(function(obj){
		const msg = obj['msg'];
        const list = obj['list'];
        let positions = [];
		let trs = [];
		const table = document.getElementById('map-table');
		if(msg=='OK'&&list.length>0){
			table.style.display = 'block';
			map_row.value = list;
			for(let i=0;i<list.length;i++){
				const pos = list[i];
				const position = new kakao.maps.LatLng(pos['latitude'], pos['longitude']);
				positions.push(position);
			}
			makeMarkers(positions);
		}else{
            positions.push(new kakao.maps.LatLng(37.501265, 127.039610));
			table.style.display = 'none';
		}
		
	}).catch(err => {
        console.log(err);
        isLogin.value = false;
		deleteCookie('sessionId');
        location.reload();
    })
}
onMounted(() => {
    onload();
	changeGugun();
	changeOption();
});
function makeSidoCode(sido_code) {
    return `sido-${sido_code}`;
}
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
</script>
<script>

</script>

<template>
    <div class="content" id="map-page">
        <div class="col-md-8 text-center mx-auto">
            <h1>지역별 관광정보</h1>
            <p>
                <span class="placeholder col-4"></span>
            </p>
            <!-- 관광지 검색 start -->
            <div class="d-flex my-3">
                <select id="search-area" class="form-select me-2" @change="changeGugun()">
                    <option value="" selected>검색 할 지역 선택</option>
                    <option v-for="sido in sidos" :key="sido.sido_code" :value="sido.sido_code">{{ sido.sido_name }}
                    </option>
                </select> 
                <select id="search-sigungu" class="form-select me-2" @change="changeOption()">
                    <option value="" selected>시군구 선택</option>
                    <option v-for="gugun in guguns" :key="gugun.gugun_name" :value="gugun.gugun_code" :class="makeSidoCode(gugun.sido_code)" v-show="false">{{ gugun.gugun_name }}</option>
                </select> 
                <select id="search-content-id" class="form-select me-2" @change="getMapInfo">
                    <option value="" selected id="no-choice">컨텐츠 선택</option>
                    <option value="12">관광지</option>
                    <option value="14">문화시설</option>
                    <option value="15">행사/공연/축제</option>
                    <option value="25">여행코스</option>
                    <option value="28">레포츠</option>
                    <option value="32">숙박</option>
                    <option value="38">쇼핑</option>
                    <option value="39">음식점</option>
                </select>
            </div>
            <!-- kakao map start -->
            <div id="map" class="mt-3" style="width: 100%; height: 500px"></div>
            <!-- kakao map end -->
            <div class="mt-3">
                <table class="table table-striped" style="display: none;" id="map-table">
                    <thead>
                        <tr>
                            <th  scope="col">유적지 이름</th>
                            <th  scope="col">전화번호</th>
                            <th  scope="col">주소</th>
                        </tr>
                    </thead>
                    <tbody id="trip-list" >
						<tr v-for="row in map_row" :key="row.content_id" @click="getDetail(row)">
							<td >{{ row.title }}</td>
							<td>{{ row.tel }}</td>
							<td>{{ row.addr1 }}</td>
						</tr>
					</tbody>
                </table>
            </div>
            <!-- 관광지 검색 end -->
        </div>
    </div>
	<teleport to="body">
    	<DetailModal/>
  	</teleport>
</template>

<style scoped>
</style>
