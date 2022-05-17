<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Orange-poolvilla:home</title>
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
							<div class="probootstrap-breadcrumbs"></div>
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
	<section class="probootstrap-section">
		<div class="container">
			<div class="row heading">
				<div class="col-md-12">
					<h2 class="mt0 mb50 text-center">${selectPoolvillaOne.pvName}</h2>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
					<p>
						<img
							src="${pageContext.request.contextPath}/template/img/slider_5.jpg"
							class="img-responsive"
							alt="Free Bootstrap Template by uicookies.com">
					</p>
				</div>
			</div>
			<form
				action="${pageContext.request.contextPath}/customer/insertReservationController"
				id="insertReservation" method="post" class="probootstrap-form mb60">
				<div class="row">
					<div class="col-12">
						<div class="container">
							<!-- 고객 정보 -->
							<input type="hidden" id="memberId" name="memberId" value="${sessionLoginMember.memberId}" readonly="readonly">

							<!-- 풀빌라정보 -->
							<input type="hidden" id="pvNo" name="pvNo" value="${selectPoolvillaOne.pvNo}" readonly="readonly">

							체크인 :<input type="date" class="form-control" id="checkIn" name="checkIn" value="${reservationBeginDate}">
							체크 아웃 :<input type="date" class="form-control" id="checkOut" name="checkOut" value="${reservationLastDate}">

							<div id="reservationHelper"></div>
						</div>
						<h2>투숙객 정보</h2>
						<div>
							투숙객 이름은 체크인 시 제시할 유효한 신분증 이름과 정확히 일치해야 합니다.
						</div>
						<table class="table table-hover">
						
							<tr>
								<td>이름</td>
								<td><input type="text" name="memberName" id="memberName" value="${myPageCustomer.name}" readonly></td>
							</tr>
							<tr>
								<td>email</td>
								<td><input type="text" name="memberEmail" id="memberEmail" value="${myPageCustomer.email}" readonly></td>
							</tr>
							<tr>
								<td>휴대폰번호</td>
								<td><input type="text" name="memberPhone" id="memberPhone" value="${myPageCustomer.phone}" readonly></td>
							</tr>
							<tr>
								<td colspan="2">
									<button type="button" id="reservation" class="btn btn-primary">예약</button>
								</td>
							</tr>

						</table>
					</div>
				</div>
			</form>
		</div>
	</section>
</body>

<script>
	//include
	$("#includeHeader").load('${pageContext.request.contextPath}/includeHeaderController');
  	$("#includeFooter").load('${pageContext.request.contextPath}/includeFooterController');
  	//
  		$('#reservation').click(function(){
  		if ($('#checkIn').val == ''){
  			$('#reservationHelper').text('체크인 날짜를 선택해주세요');
  		} else if ( $('#checkOut').val == '' ) {
  			$('#reservationHelper').text('체크아웃 날짜를 선택해주세요');
  		} else {
  			$('#insertReservation').submit();
  		}
  	});
</script>

<script
	src="${pageContext.request.contextPath}/template/js/scripts.min.js"></script>
<script src="${pageContext.request.contextPath}/template/js/main.min.js"></script>
<script src="${pageContext.request.contextPath}/template/js/custom.js"></script>

</html>