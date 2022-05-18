<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Orange-poolvilla:myReservationList</title>
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
								<h1>예약 목록</h1>
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
	<!-- 예약 목록 -->
	<!-- 페이징 및 예약상태 검색 폼 -->
	<form method="get" action="${pageContext.request.contextPath}/customer/myReservationListController">
		<!-- 예약상태 select-->
		<select name="reservationStatus" onchange ="this.form.submit()">
			<c:if test="${not empty reservationStatus}">
				<option value="${reservationStatus}">${reservationStatus}</option>
			</c:if>
			<option value="">::예약상태::</option>
			<option value="결제대기">결제대기</option>
			<option value="결제완료">결제완료</option>
			<option value="취소대기">취소대기</option>
			<option value="취소">취소</option>
			<option value="이용완료">이용완료</option>
		</select>
	
		<section class="probootstrap-section">
			<div class="container">
				<table class="table table-hover">
					<thead>
						<tr>
							<th>풀빌라이름</th>
							<th>체크인날짜</th>
							<th>체크아웃날짜</th>
							<th>예약상태</th>
							<th>예약날짜</th>
							<th>수정날짜</th>
							<th>예약취소</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="m" items="${selectMyReservationList}">
							<tr>
								<td>${m.pvName}</td>
								<td>${m.reservationBeginDate}</td>
								<td>${m.reservationLastDate}</td>
								<td>${m.reservationStatus}</td>
								<td>${m.createDate}</td>
								<td>${m.updateDate}</td>
								<c:if test="${m.reservationStatus=='결제대기'||m.reservationStatus=='결제완료'}">
									<td>
										<a href="${pageContext.request.contextPath}/customer/myReservationListController?reservationNo=${ m.reservationNo}&checkStatus=취소대기" type="button" class="btn btn-secondary btn-sm">예약취소</a>
									</td>
								</c:if>
								<c:if test="${m.reservationStatus=='취소대기'||m.reservationStatus=='취소'||m.reservationStatus=='이용완료'}">
									<td></td>
								</c:if>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</section>
	</form>

	<!-- START: footer -->
	<div id="includeFooter"></div>
	<!-- END: footer -->

	<div class="gototop js-top">
		<a href="${pageContext.request.contextPath}/template/#"
			class="js-gotop"><i class="icon-chevron-thin-up"></i></a>
	</div>

</body>

<script>
        $("#includeHeader").load('${pageContext.request.contextPath}/includeHeaderController');
        $("#includeFooter").load('${pageContext.request.contextPath}/includeFooterController');
  </script>

<script
	src="${pageContext.request.contextPath}/template/js/scripts.min.js"></script>
<script src="${pageContext.request.contextPath}/template/js/main.min.js"></script>
<script src="${pageContext.request.contextPath}/template/js/custom.js"></script>

</html>