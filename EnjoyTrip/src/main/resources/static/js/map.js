const sido_url = 'http://'+ip+'/EnjoyTrip/map/sido';
const gugun_url = 'http://'+ip+'/EnjoyTrip/map/gugun';
const search_map_url = 'http://'+ip+'/EnjoyTrip/map/search';
document.getElementById('map-link').addEventListener('click', function(){
	changePage('map-page');
	fetch(sido_url, {
		method:'GET'
	}).then(function(res){
		return res.json();
	}).then(function(obj){
		const msg = obj['msg'];
		const detail = obj['detail'];
		const list = obj['list'];
		let select = document.getElementById('search-area');
		select.addEventListener('change', changeGugun);
		if(msg=='OK'){
			list.forEach(function(item){
				let option = document.createElement('option');
				option.value = item['sido_code'];
				option.innerText = item['sido_name'];
				select.appendChild(option);
			});
		}else{
			alert(detail);
			changePage('home-page');
		}
	});
	fetch(gugun_url, {
		method:'GET'
	}).then(function(res){
		return res.json();
	}).then(function(obj){
		const msg = obj['msg'];
		const detail = obj['detail'];
		const list = obj['list'];
		let select = document.getElementById('search-sigungu');
		select.addEventListener('change', showOption);
		if(msg=='OK'){
			list.forEach(function(item){
				let option = document.createElement('option');
				let sido_code = item['sido_code'];
				option.value = item['gugun_code'];
				option.setAttribute('class', `${sido_code}`);
				option.innerText = item['gugun_name'];
				select.appendChild(option);
			});
			changeGugun();
			showOption();
		}else{
			alert(detail);
			changePage('home-page');
		}
	});
	let position = new kakao.maps.LatLng(37.501265, 127.039610);
	let map = new kakao.maps.Map(document.getElementById('map'), {
		center: position,
		level: 3,
		mapTypeId: kakao.maps.MapTypeId.ROADMAP
	});
	
	document.getElementById('search-content-id').addEventListener('change', mapChange);
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
function mapChange(){
	const select_sido = document.getElementById('search-area');
	const sido_code = select_sido[select_sido.selectedIndex].value;
	const select_gugun = document.getElementById('search-sigungu');
	const gugun_code = select_gugun[select_gugun.selectedIndex].value;
	const select_type = document.getElementById('search-content-id');
	const content_type_id = select_type[select_type.selectedIndex].value;
	if(content_type_id==''){
		return;
	}
	fetch(search_map_url, {
		method: 'POST',
		headers: {'Content-Type': 'application/json'},
		body: JSON.stringify({sido_code, gugun_code, content_type_id})
	}).then(function(res){
		return res.json();
	}).then(function(obj){
		const msg = obj['msg'];
		const detail = obj['detail'];
		let positions = [];
		const list = obj['list'];
		removeTbody();
		const table = document.getElementById('map-table');
		if(msg=='OK'){
			table.style.display = 'block';
			let trs = [];
			let positions = [];
			for(let i=0;i<list.length;i++){
				const pos = list[i];
				const tr = document.createElement('tr');
				tr.appendChild(makeTd(pos['title']));
				tr.appendChild(makeTd(pos['tel']));
				tr.appendChild(makeTd(pos['addr1']));
				trs.push(tr);
				const position = new kakao.maps.LatLng(pos['latitude'], pos['longitude']);
				positions.push(position);
			}
			appendTbody(trs);
			makeMarkers(positions);
		}else{
			table.style.display = 'none';
			alert(detail);
		}
		
	});
}
function removeTbody(){
	const tbody = document.getElementById('trip-list');
	document.querySelectorAll('#trip-list tr').forEach(function(e){
		tbody.removeChild(e);
	});
}
function appendTbody(trs){
	const tbody = document.getElementById('trip-list');
	trs.forEach(function(tr){
		tbody.appendChild(tr);
	});
}
function makeTd(content){
	const td = document.createElement('td');
	td.innerText = content;
	if(content==''){
		td.innerText='-';
	}
	return td;
}
function changeGugun(){
	const select = document.getElementById('search-area');
	const sido = select[select.selectedIndex].value;
	document.querySelectorAll('#search-sigungu option').forEach(function(e){
		if(e.getAttribute('class')==sido){
			e.style.display = 'block';
		}else{
			e.style.display = 'none';
		}
	});
	document.getElementById('search-sigungu').selectedIndex = 0;
}
function showOption(){
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