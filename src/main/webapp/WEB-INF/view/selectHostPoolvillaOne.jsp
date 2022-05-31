<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Orange-poolvilla:insertPoolvillaOne</title>
<meta name="description" content="Free Bootstrap Theme by uicookies.com">
<meta name="keywords"
	content="free website templates, free bootstrap themes, free template, free bootstrap, free website template">

<link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400"
	rel="stylesheet">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/template/css/styles-merged.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/template/css/style.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/template/css/custom.css">


</head>

<!-- jquery -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

<body>

	<!-- START: header -->
	<div id="includeHeader"></div>
	<!-- END: header -->

	<section class="probootstrap-slider flexslider2 page-inner">
		<div class="overlay"></div>
		<div class="probootstrap-wrap-banner">
			<div class="container">
				<div class="row">
					<div class="col-md-8">

						<div class="page-title probootstrap-animate">
							<div class="probootstrap-breadcrumbs">
								<a href="${pageContext.request.contextPath}/template/#">Home</a><span>About</span>
							</div>
							<h1>Insert Poolvilla</h1>
						</div>

					</div>
				</div>
			</div>
		</div>
		<ul class="slides">
			<li
				style="background-image: url(${pageContext.request.contextPath}/template/img/slider_1.jpg);"></li>
			<li
				style="background-image: url(${pageContext.request.contextPath}/template/img/slider_4.jpg);"></li>
			<li
				style="background-image: url(${pageContext.request.contextPath}/template/img/slider_2.jpg);"></li>
		</ul>
	</section>
	<!-- END: slider  -->

	<section class="probootstrap-section probootstrap-section-lighter">
		
		<div class="container">
			<div class="row heading">
				<div class="col-md-12">
					<h2 class="mt0 mb50 text-center">${selectPoolvillaOne.pvName}</h2>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
   						<img src="${pageContext.request.contextPath}/image/poolvilla/${poolvillaPhoto.photoName}" width="1140" height="700" alt="">
				</div>
			</div>
			<br>
			<div class="row heading">
				<h2 class="mt0 mb50 text-center">풀빌라 정보</h2>
			</div>
			
			<div class="probootstrap-card probootstrap-person text-left">

						<div class="probootstrap-card-text">
							<h2 class="probootstrap-card-heading mb0">풀빌라 정보</h2>
							<p>
								<small>poolvilla info</small>
							</p>
							<table class="table table-hover">
								<thead>
									<tr>
										<th class="text text-center">판매자</th>
										<th class="text text-center">지역 이름</th>
										<th class="text text-center">주소</th>
										<th class="text text-center">상세 주소</th>
										<th class="text text-center">풀빌라 이름</th>
										<th class="text text-center">가격</th>
										<th class="text text-center">면적</th>
										<th class="text text-center">층수</th>
										<th class="text text-center">최대 인원</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<tr>
											<td>${ selectPoolvillaOne.hostId }</td>
											<td>${ selectPoolvillaOne.locationName }</td>
											<td>${ selectPoolvillaOne.address }</td>
											<td>${ selectPoolvillaOne.pvDetailaddr }</td>
											<td>${ selectPoolvillaOne.pvName }</td>
											<td>${ selectPoolvillaOne.price }</td>
											<td>${ selectPoolvillaOne.pvSize }</td>
											<td>${ selectPoolvillaOne.pvFloor }</td>
											<td>${ selectPoolvillaOne.pvPeople }</td>
										</tr>
									</tr>
								</tbody>
							</table>
							<a href="${pageContext.request.contextPath}/host/updatePoolvillaOneController?pvNo=${ selectPoolvillaOne.pvNo }" class = "btn btn-primary btn-sm">update</a>
						</div>
					</div>
				
				
			<div class="row">
				<div class="col-md-6 col-sm-6">
					<div class="probootstrap-card probootstrap-person text-center">

						<div class="probootstrap-card-text">
							<h2 class="probootstrap-card-heading mb0">취사 시설</h2>
							<p>
								<small>cooking Tool</small>
							</p>
							<table class="table table-hover">
								<thead>
									<tr>
										<th class="text text-center">도구</th>
										<th class="text text-center">수량</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<c:forEach var="ck" items="${ poolvillaCookingToolList }">
											<tr>
												<td>${ ck.cookingToolName }</td>
												<td>${ ck.cookingToolCnt }</td>
											</tr>
										</c:forEach>
									</tr>
								</tbody>
							</table>
							<a href="${pageContext.request.contextPath}/host/insertPoolvillaCookingToolController?pvNo=${ selectPoolvillaOne.pvNo }" class = "btn btn-primary btn-sm">update</a>
						</div>
					</div>
				</div>
				<div class="col-md-6 col-sm-6">
					<div class="probootstrap-card probootstrap-person text-center">

						<div class="probootstrap-card-text">
							<h2 class="probootstrap-card-heading mb0">Over The Top</h2>
							<p>
								<small>ott</small>
							</p>
							<table class="table table-hover">

								<thead>

									<tr>

										<th class="text text-center">이름</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<c:forEach var="po" items="${ poolvillaOttList }">
											<tr>
												<td>${ po.ottName }</td>
											</tr>
										</c:forEach>
									</tr>
								</tbody>
							</table>
							<a href="${pageContext.request.contextPath}/host/insertPoolvillaOttController?pvNo=${ selectPoolvillaOne.pvNo }" class = "btn btn-primary btn-sm">update</a>
						</div>
					</div>
				</div>
			</div>
			<div class="row">

				<div class="col-md-6 col-sm-6">
					<div class="probootstrap-card probootstrap-person text-left">

						<div class="probootstrap-card-text">
							<h2 class="probootstrap-card-heading mb0">구비물품</h2>
							<p>
								<small>supplies</small>
							</p>
							<table class="table table-hover">
								<thead>
									<tr>
										<th class="text text-center">물품</th>
										<th class="text text-center">수량</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<c:forEach var="ps" items="${ poolvillaSuppliesList }">
											<tr>
												<td>${ ps.suppliesName }</td>
												<td>${ ps.suppliesCnt }</td>
											</tr>
										</c:forEach>
									</tr>
								</tbody>
							</table>
							<a href="${pageContext.request.contextPath}/host/insertPoolvillaSuppliesController?pvNo=${ selectPoolvillaOne.pvNo }" class = "btn btn-primary btn-sm">update</a>
						</div>
					</div>
				</div>
				<div class="col-md-6 col-sm-6">
					<div class="probootstrap-card probootstrap-person text-left">

						<div class="probootstrap-card-text">
							<h2 class="probootstrap-card-heading mb0">부대시설</h2>
							<p>
								<small>facilities</small>
							</p>
							<table class="table table-hover">
								<thead>
									<tr>
										<th class="text text-center">시설</th>
										<th class="text text-center">수량</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<c:forEach var="pf" items="${ poolvillaFacilityList }">
											<tr>
												<td>${ pf.facilityName }</td>
												<td>${ pf.facilityCnt }</td>
											</tr>
										</c:forEach>
									</tr>
								</tbody>
							</table>
							<a href="${pageContext.request.contextPath}/host/insertPoolvillaFacilityController?pvNo=${ selectPoolvillaOne.pvNo }" class = "btn btn-primary btn-sm">update</a>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12 col-sm-6">
					<div class="probootstrap-card probootstrap-person text-left">

						<div class="probootstrap-card-text">
							<h2 class="probootstrap-card-heading mb0">방 정보</h2>
							<p>
								<small>room info</small>
							</p>
							<table class="table table-hover">
								<thead>
									<tr>
										<th class="text text-center">방 이름</th>
										<th class="text text-center">방 유형</th>
										<th class="text text-center">방 설명</th>
										<th class="text text-center">방 크기</th>
										<th class="text text-center">침대</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<c:forEach var="prb" items="${ poolvillaRoomNBedList }">
											<tr>
												<td>${ prb.roomName }</td>
												<td>${ prb.roomType }</td>
												<td>${ prb.roomInfo }</td>
												<td>${ prb.roomSize }</td>
												<td>${ prb.bed }</td>
											</tr>
										</c:forEach>
									</tr>
								</tbody>
							</table>
							<a href="${pageContext.request.contextPath}/host/insertPoolvillaRoomNBedController?pvNo=${ selectPoolvillaOne.pvNo }" class = "btn btn-primary btn-sm">update</a>
						</div>
					</div>
				</div>

				<div class="col-md-12 col-sm-6">
					<div class="probootstrap-card probootstrap-person text-left">

						<div class="probootstrap-card-text">
							<h2 class="probootstrap-card-heading mb0">수영장</h2>
							<p>
								<small>pool</small>
							</p>
							<table class="table table-hover">
								<thead>
									<tr>
										<th class="text text-center">수영장 이름</th>
										<th class="text text-center">가로</th>
										<th class="text text-center">세로</th>
										<th class="text text-center">깊이</th>
										<th class="text text-center">온수 여부</th>
										<th class="text text-center">실내/야외</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<c:forEach var="list" items="${poolvillaPoolList}">
											<tr>
												<td>${list.poolName }</td>
												<td>${list.poolWidth }</td>
												<td>${list.poolLength }</td>
												<td>${list.depth }</td>
												<td>${list.hotWater }</td>
												<td>${list.indoorOutdoor }</td>
											</tr>
										</c:forEach>
									</tr>
								</tbody>
							</table>
							<a href="${pageContext.request.contextPath}/host/insertPoolvillaPoolController?pvNo=${ selectPoolvillaOne.pvNo }" class = "btn btn-primary btn-sm">update</a>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
					<form action="${pageContext.request.contextPath}/host/insertPoolvillaPhotoController" method="post" enctype="multipart/form-data">
						<table class = "table table-active" id = "insertPvPhotoForm">
							<tr>
								<td>insert image</td>
								<td><input type = "hidden" name="pvNo" id = "pvNo" value="${param.pvNo}">
								<td><input type = "file" name = "poolvillaPhoto" class = "custom-file"></td>
								<td><button type = "submit" class = "btn btn-dark" id="insertPoolvillaPhoto">insert</button></td>
								<td><span id="poolvillaPhotoHelper" class="helper"></span></td>
							</tr>
						</table>
					</form>
				</div>
			</div>
			
		</div>
	</section>

	<!-- START: footer -->
	<div id="includeFooter"></div>
	<!-- END: footer -->

	<div class="gototop js-top">
		<a href="${pageContext.request.contextPath}/template/#"
			class="js-gotop"><i class="icon-chevron-thin-up"></i></a>
	</div>

</body>

<script>
	$("#includeHeader").load(
			'${pageContext.request.contextPath}/includeHeaderController');
	$("#includeFooter").load(
			'${pageContext.request.contextPath}/includeFooterController');
	
	
</script>


<script
	src="${pageContext.request.contextPath}/template/js/scripts.min.js"></script>
<script src="${pageContext.request.contextPath}/template/js/main.min.js"></script>
<script src="${pageContext.request.contextPath}/template/js/custom.js"></script>

</html>