<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="com.ssafy.board.model.*, java.util.* " pageEncoding="UTF-8"%>
<%@ include file="/include/head.jsp"%>
<html>
<c:if test="${article eq null}">
	<script>
		alert("글이 삭제되었거나 부적절한 URL 접근입니다.");
		location.href = "${root}/article?action=list";
	</script>
</c:if>
<body>
	<%@ include file="/include/nav.jsp"%>
	<div class="row justify-content-center">
		<div class="col-lg-8 col-md-10 col-sm-12">
			<h2 class="my-3 py-3 shadow-sm bg-light text-center">
				<mark class="sky">글보기</mark>
			</h2>
		</div>
		<div class="col-lg-8 col-md-10 col-sm-12">
			<div class="row my-2">
				<h2 class="text-secondary px-5">${article.articleNo}.
					${article.subject}</h2>
			</div>
			<div class="row">
				<div class="col-md-8">
					<div class="clearfix align-content-center">
						<img class="avatar me-2 float-md-start bg-light p-2"
							src="https://raw.githubusercontent.com/twbs/icons/main/icons/person-fill.svg" />
						<p>
							<span class="fw-bold">${article.userId}</span> <br /> <span
								class="text-secondary fw-light"> ${article.registerTime}
								조회 : ${article.hit} </span>
						</p>
					</div>
				</div>
				<div class="col-md-4 align-self-center text-end">댓글 : 17</div>
				<div class="divider mb-3"></div>
				<div class="text-secondary">${article.content}</div>
				<div class="divider mt-3 mb-3"></div>
				<div class="d-flex justify-content-end">
					<button type="button" id="btn-list"
						class="btn btn-outline-primary mb-3">글목록</button>
					<c:if test="${userinfo.userId eq article.userId}">
						<button type="button" id="btn-mv-modify"
							class="btn btn-outline-success mb-3 ms-1">글수정</button>
						<button type="button" id="btn-delete"
							class="btn btn-outline-danger mb-3 ms-1">글삭제</button>
					</c:if>
				</div>
			</div>
		</div>
		<div class="col-lg-8 col-md-10 col-sm-12" id="comment-container">
			<div id="write-comment-div" class="row my-2">
				<form action="article" method="post">
					<input type="text" id="comment" placeholder="댓글을 추가해 보세요!"
						style="width: 90%;"> <input type="button" value="add"
						onClick="addComment()" style="width: 8%;">
				</form>
			</div>
			<div class="row my-2" id="comment-list-div">
				<table id="comment-list" class="table table-hover col">
					<thead>
						<tr class="text-center">
							<th scope="col" class="col-md-2">댓글 번호</th>
							<th scope="col" class="col-md-8">내용</th>
							<th scope="col" class="col-md-2">작성자</th>
						</tr>
					</thead>
					<tbody id="comment-list-body">
						<%
							for (CommentDto c : (List<CommentDto>) request.getSession().getAttribute("comments")) {
						%>
						<tr>
							<td><%=c.idx%></td>
							<td><%=c.comment%></td>
							<td><%=c.userName%></td>
						</tr>
						<%
							}
						%>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</body>
<script>
 function addComment() {
	let obj = new Object();
	obj['comment'] = document.getElementById("comment").value;
	obj['userName'] =  `${userinfo['userName']}`;
	// 코멘트 내용물...	
	fetch("${root}/article?action=commentadd&articleNo="+${article.articleNo}, {
		method: "POST", // *GET, POST, PUT, DELETE 등
	    headers: {
	      "Content-Type": "application/json",
	    },
	    body: JSON.stringify(obj), // body의 데이터 유형은 반드시 "Content-Type" 헤더와 일치해야 함
	  } ).then((res)=>{
		  console.log(res);
		  return res.json();
	  }).then((json)=>{
		  console.log(json);
		  let list = json['list'];
		  let text = ``;
		  for(let i=0;i<list.length;i++){
			  let l = list[i];  
			  text += `<tr>`;
			  text += `<td>`+l['idx']+`</td>`;
			  text += `<td>`+l['comment']+`</td>`;
			  text += `<td>`+l['userName']+`</td>`;
			  text += `</tr>`;
		  }
		  document.getElementById("comment-list-body").innerHTML = text;
	  });
}
	document.querySelector("#btn-list").addEventListener("click", function() {
		location.href = "${root}/article?action=list";
	});
	document
			.querySelector("#btn-mv-modify")
			.addEventListener(
					"click",
					function() {
						alert("글수정하자!!!");
						location.href = "${root}/article?action=mvmodify&articleno=${article.articleNo}";
					});
	document
			.querySelector("#btn-delete")
			.addEventListener(
					"click",
					function() {
						alert("글삭제하자!!!");
						location.href = "${root}/article?action=delete&articleno=${article.articleNo}";
					});
	
</script>
</html>