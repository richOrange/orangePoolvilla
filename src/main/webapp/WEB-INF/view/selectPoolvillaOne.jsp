<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Orange-poolvilla:selectPoolvillaOne</title>
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
							<h1>${selectPoolvillaOne.pvName}</h1>
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

	<section class="probootstrap-section">
		<div class="container">
			<div class="row heading">
				<div class="col-md-12">
					<h2 class="mt0 mb50 text-center">${selectPoolvillaOne.pvName}</h2>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
					<img src="${pageContext.request.contextPath}/image/poolvilla/${poolvillaPhoto.photoName}" width="700" height="700" alt="">
						${sppList.PhotoName}
				</div>
			</div>
			<!-- 예약 기능 -->
			<div class="row">
				<form method="get" id="reservationForm"
					action="${pageContext.request.contextPath}/customer/insertReservationController">
					<!-- 고객 정보 -->
					<c:if test="${not empty sessionLoginMember}">
						<input type="hidden" id="memberId" name="memberId"
							value="${sessionLoginMember.memberId}" readonly="readonly">
					</c:if>
					<!-- 방정보 -->
					<input type="hidden" id="pvNo" name="pvNo"
						value="${selectPoolvillaOne.pvNo}" readonly="readonly">
					<!-- 예약날짜 정보 -->
					<div class="container">
						체크인 :<input type="date" class="form-control" id="checkIn"
							name="reservationBeginDate" value="${reservationBeginDate}">
						체크 아웃 :<input type="date" class="form-control" id="checkOut"
							name="reservationLastDate" value="${reservationLastDate}">
						<button type="button" id="reservation" class="btn">예약</button>
						<div id="reservationHelper"></div>
					</div>
				</form>

			</div>
		</div>
	</section>
	<br>
	<section class="probootstrap-section probootstrap-section-lighter">
		
		<div class="container">
		
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
										<c:forEach var="list"
											items="${selectPoolvillaFacilityListByPvNo}">
											<tr>
												<td>${list.facilityName }</td>
												<td>${list.facilityCnt }</td>
											</tr>
										</c:forEach>
									</tr>
								</tbody>
							</table>
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
										<th class="text text-center">침대 사이즈</th>
										<th class="text text-center">침대 수량</th>
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
												<td>${ prb.bedSize }</td>
												<td>${ prb.bedCnt }</td>
											</tr>
										</c:forEach>
									</tr>
								</tbody>
							</table>
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
										<c:forEach var="list" items="${selectPoolvillaPoolListByPvNo}">
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
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	
	<section>
		
			
					<h2 class="mt0 mb50 text-center">리뷰</h2>
						<div>
							<table class="table table-hover">
								<thead>
									<tr>
										<th>사용자 아이디</th>
										<th>만족도</th>
										<th>청결도</th>
										<th>재방문 의사</th>
										<th>최종 수정 날짜</th>
									</tr>
								</thead>
		
								<tbody>
									<c:forEach var="p" items="${poolvillaReviewListPerPoolvilla}">
										<tr>
											<td>${p.customerId }</td>
											<td>${p.satisfaction }</td>
											<td>${p.cleanliness }</td>
											<td>${p.revisit }</td>
											
											
											<td>${p.updateDate }</td>
										</tr>
										<tr>
											<td>
												<h5>리뷰 작성 내용</h5>
											</td>
											<td>${p.reviewContents }</td>
										</tr>
										
									</c:forEach>
								</tbody>
							</table>
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
	$('#reservation').click(function() {
						//로그인정보가 없으면 로그인 페이지로 이동
						if ($('#memberId').val() == '') {
							location.href = '${pageContext.request.contextPath}/all/loginController';
							//상품 정보가 없으면 home으로 이동
						} else if ($('#pvNo').val() == '') {
							location.href = '${pageContext.request.contextPath}/all/homeController';
							//체크인정보가 없는 경우 체크인 입력으로 커서 이동
						} else if ($('#checkIn').val() == '') {
							$('#reservationHelper').text('체크인 날짜를 선택해주세요');
							$('#checkIn').focus();
							//체크아웃 정보가 없는경우 체크아웃 입력으로 커서 이동
						} else if ($('#checkOut').val() == '') {
							$('#reservationHelper').text('체크아웃 날짜를 선택해주세요');
							$('#checkOut').focus();
							//유효성 검사 끝 submit
						} else {
							$('#reservationForm').submit();
						}
					});
</script>

<script
	src="${pageContext.request.contextPath}/template/js/scripts.min.js"></script>
<script src="${pageContext.request.contextPath}/template/js/main.min.js"></script>
<script src="${pageContext.request.contextPath}/template/js/custom.js"></script>

</html>