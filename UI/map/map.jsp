<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.sql.*, java.util.*, com.ssafy.map.model.MapDto, com.ssafy.util.DBUtil"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/include/head.jsp"%>
</head>
<body>
	<%@ include file="/include/nav.jsp"%>

	<%
		// db에서 정보 얻어와 테이블에 출력하기
	DBUtil dbUtil = DBUtil.getInstance();
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	%>
	<!-- 중앙 컨텐츠 시작 -->
	<div class="col-md-8 text-center mx-auto">
		<h1>지역별 관광정보</h1>
		<p>
			<span class="placeholder col-4"></span>
		</p>
		<!-- 관광지 검색 start -->
		<form class="d-flex my-3" onsubmit="return false;" role="search">
			<select id="search-area" class="form-select me-2"
				onchange="makeGugunOption(), hideMarkers()">
				<option value="0" selected>검색 할 지역 선택</option>
				<%
					try {
					conn = dbUtil.getConnection();
					String sql = "select sido_code, sido_name from sido";
					pstmt = conn.prepareStatement(sql.toString());
					rs = pstmt.executeQuery();
					while (rs.next()) {
						int sido_code = rs.getInt("sido_code");
						String sido_name = rs.getString("sido_name");
				%>
				<option value=<%=sido_code%>><%=sido_name%>
				</option>
				<%
					}
				} catch (SQLException se) {
					System.out.println(se.getMessage());
				} finally {
					dbUtil.close(rs, pstmt, conn);
				}
				%>
			</select> <select id="search-sigungu" class="form-select me-2"
				onchange="hideMarkers()">
				<option value="0" selected>시군구 선택</option>
				<%
					try {
					conn = dbUtil.getConnection();
					String sql = "select gugun_code, gugun_name, sido_code from gugun";
					pstmt = conn.prepareStatement(sql.toString());
					rs = pstmt.executeQuery();
					while (rs.next()) {
						int gugun_code = rs.getInt("gugun_code");
						String gugun_name = rs.getString("gugun_name");
						int sido_code = rs.getInt("sido_code");
				%>
				<option value=<%=gugun_code%> class=<%=sido_code%>
					style='display: none;'><%=gugun_name%>
				</option>
				<%
					}
				} catch (SQLException se) {
					System.out.println(se.getMessage());
				} finally {
					dbUtil.close(rs, pstmt, conn);
				}
				%>
			</select> <select id="search-content-id" class="form-select me-2"
				onchange="hideMarkers()">
				<option value="0" selected>컨텐츠 선택</option>
				<option value="12">관광지</option>
				<option value="14">문화시설</option>
				<option value="15">행사/공연/축제</option>
				<option value="25">여행코스</option>
				<option value="28">레포츠</option>
				<option value="32">숙박</option>
				<option value="38">쇼핑</option>
				<option value="39">음식점</option>
			</select>
		</form>
		<!-- kakao map start -->
		<div id="map" class="mt-3" style="width: 100%; height: 500px"></div>
		<!-- kakao map end -->
		<div class="row">
			<table class="table table-striped" style="display: none">
				<tbody id="trip-list"></tbody>
			</table>
		</div>
		<!-- 관광지 검색 end -->
	</div>
	<!-- 중앙 content end -->

	<script>
		var blockList = [];
		function makeGugunOption() {
			for (var i = 0; i < blockList.length; i++) {
				blockList[i].style.display = "none";
			}
			const sido_code = document.getElementById("search-area").value;
			var sido = document.getElementsByClassName(sido_code);
			for (var j = 0; j < sido.length; j++) {
				sido[j].style.display = "block";
				blockList.push(sido[j]);
			}
		}
	</script>

	<script type="text/javascript"
		src="//dapi.kakao.com/v2/maps/sdk.js?appkey=0d15b933750818d836154d4224b2d03e&libraries=services,clusterer,drawing"></script>
	<script type="module" src="${root}/assets/js/main.js"></script>

</body>
</html>