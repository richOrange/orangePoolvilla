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
				<div class="col-md-12 text-center">
					<img src="${pageContext.request.contextPath}/image/poolvilla/${poolvillaPhoto.photoName}" width="1140" height="700" alt="">
						${sppList.PhotoName}
				</div>
			</div>
			<br>
			<!-- ?????? ??????, ?????????????????? ?????????????????? ??????(???????????? level 5 ??????) -->
			<c:if test="${sessionLoginMember.level < 5 }">
				<div class="row">
					<div class = "col-md-3"></div>
					<div class = "col-md-6">
					<form method="get" id="reservationForm" action="${pageContext.request.contextPath}/customer/insertReservationController">
						<!-- ?????? ?????? -->
						<c:if test="${not empty sessionLoginMember}">
							<input type="hidden" id="memberId" name="memberId"
								value="${sessionLoginMember.memberId}" readonly="readonly">
						</c:if>
						<!-- ????????? -->
						<input type="hidden" id="pvNo" name="pvNo"
							value="${selectPoolvillaOne.pvNo}" readonly="readonly">
						<!-- ???????????? ?????? -->
							<h4>?????????:</h4> <input type="date" class="form-control" id="checkIn" name="reservationBeginDate" value="${reservationBeginDate}">
							<h4>?????? ?????? :</h4> <input type="date" class="form-control" id="checkOut" name="reservationLastDate" value="${reservationLastDate}">
							<button type="button" id="reservation" class="btn btn-success text-center">??????</button>
							<div id="reservationHelper"></div>
							<div class = "col-md-3"></div>
					</form>
					</div>
				</div>
			</c:if>
			
			<!-- ???????????? ?????? ?????? ???????????? ?????? ??????(???????????? level 5?????? ???, 4??????) -->
			<c:if test="${sessionLoginMember.level > 4}">
				<div class="row text-center">
					<a href="${pageContext.request.contextPath}/host/selectHostPoolvillaOneController?pvNo=${selectPoolvillaOne.pvNo}" type ="button" class="btn btn-info" >?????? ?????? ???????????? ??????</a>
				</div>
			</c:if>
			
		</div>
	</section>
	<br>
	<section class="probootstrap-section probootstrap-section-lighter">
		
		<div class="container">
		
			<div class="row heading">
				<h2 class="mt0 mb50 text-center">????????? ??????</h2>
			</div>
			
			<div class="probootstrap-card probootstrap-person text-left">

						<div class="probootstrap-card-text">
							<h2 class="probootstrap-card-heading mb0">????????? ??????</h2>
							<p>
								<small>poolvilla info</small>
							</p>
							<table class="table table-hover">
								<thead>
									<tr>
										<th class="text text-center">?????????</th>
										<th class="text text-center">?????? ??????</th>
										<th class="text text-center">??????</th>
										<th class="text text-center">?????? ??????</th>
										<th class="text text-center">????????? ??????</th>
										<th class="text text-center">??????</th>
										<th class="text text-center">??????</th>
										<th class="text text-center">??????</th>
										<th class="text text-center">?????? ??????</th>
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
							<h2 class="probootstrap-card-heading mb0">?????? ??????</h2>
							<p>
								<small>cooking Tool</small>
							</p>
							<table class="table table-hover">
								<thead>
									<tr>
										<th class="text text-center">??????</th>
										<th class="text text-center">??????</th>
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

										<th class="text text-center">??????</th>
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
							<h2 class="probootstrap-card-heading mb0">????????????</h2>
							<p>
								<small>supplies</small>
							</p>
							<table class="table table-hover">
								<thead>
									<tr>
										<th class="text text-center">??????</th>
										<th class="text text-center">??????</th>
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
							<h2 class="probootstrap-card-heading mb0">????????????</h2>
							<p>
								<small>facilities</small>
							</p>
							<table class="table table-hover">
								<thead>
									<tr>
										<th class="text text-center">??????</th>
										<th class="text text-center">??????</th>
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
							<h2 class="probootstrap-card-heading mb0">??? ??????</h2>
							<p>
								<small>room info</small>
							</p>
							<table class="table table-hover">
								<thead>
									<tr>
										<th class="text text-center">??? ??????</th>
										<th class="text text-center">??? ??????</th>
										<th class="text text-center">??? ??????</th>
										<th class="text text-center">??? ??????</th>
										<th class="text text-center">??? ??????</th>
										<th class="text text-center">?????? ?????????</th>
										<th class="text text-center">?????? ??????</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<c:forEach var="prb" items="${ poolvillaRoomNBedList }">
											<tr>
												<td><img src="${pageContext.request.contextPath}/image/room/${prb.photoName}" width="100" height="100" alt=""></td>
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
							<h2 class="probootstrap-card-heading mb0">?????????</h2>
							<p>
								<small>pool</small>
							</p>
							<table class="table table-hover">
								<thead>
									<tr>
										<th class="text text-center">????????? ??????</th>
										<th class="text text-center">??????</th>
										<th class="text text-center">??????</th>
										<th class="text text-center">??????</th>
										<th class="text text-center">?????? ??????</th>
										<th class="text text-center">??????/??????</th>
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
		
			
					<h2 class="mt0 mb50 text-center">??????</h2>
						<div>
							<table class="table table-hover">
								<thead>
									<tr>
										<th>????????? ?????????</th>
										<th>?????????</th>
										<th>?????????</th>
										<th>????????? ??????</th>
										<th>?????? ?????? ??????</th>
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
												<h5>?????? ?????? ??????</h5>
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
						//?????????????????? ????????? ????????? ???????????? ??????
						if ($('#memberId').val() == '') {
							location.href = '${pageContext.request.contextPath}/all/loginController';
							//?????? ????????? ????????? home?????? ??????
						} else if ($('#pvNo').val() == '') {
							location.href = '${pageContext.request.contextPath}/all/homeController';
							//?????????????????? ?????? ?????? ????????? ???????????? ?????? ??????
						} else if ($('#checkIn').val() == '') {
							$('#reservationHelper').text('????????? ????????? ??????????????????');
							$('#checkIn').focus();
							//???????????? ????????? ???????????? ???????????? ???????????? ?????? ??????
						} else if ($('#checkOut').val() == '') {
							$('#reservationHelper').text('???????????? ????????? ??????????????????');
							$('#checkOut').focus();
							//????????? ?????? ??? submit
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