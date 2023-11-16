const board_list_url = 'http://'+ip+'/EnjoyTrip/board/list'
const board_pagenation_url = 'http://'+ip+'/EnjoyTrip/board/page';
document.getElementById('board-link').addEventListener('click', function(){
	changePage('board-list-page');
	makeBoardList(1, '', '');
});
document.getElementById('board-search-btn').addEventListener('click', function(){
	const word = document.getElementById('board-word').value;
	const key_select = document.getElementById('board-key');
	const key = key_select[key_select.selectedIndex].value;
	if(key==''||key==null){
		alert('검색 조건을 선택해 주세요');
		return;
	}
	if(word==''||word==null){
		alert('검색어를 입력해 주세요');
		return;
	}
	makeBoardList(1, key, word);
});
function makeBoardList(pgno, key, word){
	makePageNation(pgno, key, word);
	const tbody = document.getElementById('board-list-tbody');
	fetch(board_list_url, {
		method: 'POST',
		headers: { 'Content-Type': 'application/json', },
		body: JSON.stringify({pgno, key, word})
	}).then(function(res){
		return res.json();
	}).then(function(obj){
		msg = obj['msg'];
		detail = obj['detail'];
		list = obj['list'];
		if(msg=='OK'){
			console.log(list);
		}else{
			alert(detail);
		}
	});
}

function makePageNation(pngo, key, word){
	const page_div = document.getElementById('pagenation');
	fetch(board_pagenation_url,{
		method: 'POST',
		headers: { 'Content-Type': 'application/json' },
		body: JSON.stringify({key, word})
	}).then(function(res){
		return res.json();
	}).then(function(obj){
		msg = obj['msg'];
		detail = obj['detail'];
		pages = obj['pages'];
		if(msg=='OK'){
			console.log(pages);
		}else{
			alert(detail);
			changePage('home-page');
		}
	});
}