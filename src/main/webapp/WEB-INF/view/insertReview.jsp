<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Orange-poolvilla:myReviewList</title>
<meta name="description" content="Free Bootstrap Theme by uicookies.com">
<meta name="keywords"
	content="free website templates, free bootstrap themes, free template, free bootstrap, free website template">

<!-- <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400" rel="stylesheet"> -->
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
								<h1>리뷰 목록</h1>
							</div>
							
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
	
	<div>
		<!-- 자바스크립트 적용 해야함 -->
		<form method="post" action="${pageContext.request.contextPath}/customer/insertReviewController">
			<table class = "table table-hover">
				<tr>
					<td>청결도(0~5)</td>
					<td>
						<input type="number" name="cleanliness">
					</td>
				</tr>
				<tr>
					<td>재방문 의사</td>
					<td>
						<select name="revisit">
							<option value="">::선택::</option>
							<option value="Y">있음</option>
							<option value="N">없음</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>만족도(0~5)</td>
					<td>
						<input type="number" name="satisfaction">
					</td>
				</tr>
				<tr>
					<td>판매자에게 보내는 의견 (공백 가능)</td>
					<td>
						<input type="text" name="opinin">
					</td>
				</tr>
				<tr>
					<td>리뷰 내용 (공백 가능)</td>
					<td>
						<input type="text" name="reviewContents">
					</td>
				</tr>
				<tr>
					<td>타인에게 리뷰 보여주기 여부</td>
					<td>
						<select name="reviewActive">
							<option value="">::선택::</option>
							<option value="Y">공개</option>
							<option value="N">비공개</option>	
						</select>
					</td>
				</tr>
				<!--  -->
				<tr>
					<td><input type="hidden" name="예약 번호"></td>
					<td>
						 <input type="hidden" id="reservationNo" name="reservationNo" value="${reservationNo }">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<button type = "submit" class="btn btn-info">리뷰 입력</button>
					</td>
					
				</tr>
			</table>
		</form>
		
	</div>

<section class="probootstrap-section">
		<div class="container">
			<div class="row heading">
				<h2 class="mt0 mb50 text-center">Explore Our Neighborhoods</h2>
			</div>
			<div class="row probootstrap-gutter10">
				<div class="col-md-6 col-sm-6">
					<a href="${pageContext.request.contextPath}/template/#"
						class="probootstrap-hover-overlay"> <img
						src="${pageContext.request.contextPath}/template/img/slider_2.jpg"
						alt="Free Bootstrap Template by uicookies.com"
						class="img-responsive">
						<div class="probootstrap-text-overlay">
							<h3>New York</h3>
							<p>430 Properties</p>
						</div>
					</a>
				</div>
				<div class="col-md-6 col-sm-6">
					<a href="${pageContext.request.contextPath}/template/#"
						class="probootstrap-hover-overlay"> <img
						src="${pageContext.request.contextPath}/template/img/slider_1.jpg"
						alt="Free Bootstrap Template by uicookies.com"
						class="img-responsive">
						<div class="probootstrap-text-overlay">
							<h3>San Francisco</h3>
							<p>294 Properties</p>
						</div>
					</a>
				</div>
				<div class="clearfix visible-sm-block"></div>

				<div class="col-md-4 col-sm-6">
					<a href="${pageContext.request.contextPath}/template/#"
						class="probootstrap-hover-overlay"> <img
						src="${pageContext.request.contextPath}/template/img/slider_3.jpg"
						alt="Free Bootstrap Template by uicookies.com"
						class="img-responsive">
						<div class="probootstrap-text-overlay">
							<h3>Brooklyn</h3>
							<p>300 Properties</p>
						</div>
					</a>
				</div>
				<div class="col-md-4 col-sm-6">
					<a href="${pageContext.request.contextPath}/template/#"
						class="probootstrap-hover-overlay"> <img
						src="${pageContext.request.contextPath}/template/img/slider_4.jpg"
						alt="Free Bootstrap Template by uicookies.com"
						class="img-responsive">
						<div class="probootstrap-text-overlay">
							<h3>Chicago</h3>
							<p>268 Properties</p>
						</div>
					</a>
				</div>
				<div class="clearfix visible-sm-block"></div>
				<div class="col-md-4 col-sm-6">
					<a href="${pageContext.request.contextPath}/template/#"
						class="probootstrap-hover-overlay"> <img
						src="${pageContext.request.contextPath}/template/img/slider_2.jpg"
						alt="Free Bootstrap Template by uicookies.com"
						class="img-responsive">
						<div class="probootstrap-text-overlay">
							<h3>Los Angeles</h3>
							<p>342 Properties</p>
						</div>
					</a>
				</div>

			</div>
		</div>
	</section>
	<!-- END: section -->

	<section class="probootstrap-half reverse">
		<div class="image-wrap">
			<div class="image"
				style="background-image: url(${pageContext.request.contextPath}/template/img/slider_5.jpg);"></div>
		</div>
		<div class="text">
			<p class="mb10 subtitle">Why Choose Us</p>
			<h3 class="mt0 mb40">You Will Love Our Services</h3>
			<p>Far far away, behind the word mountains, far from the
				countries Vokalia and Consonantia, there live the blind texts.
				Separated they live in Bookmarksgrove right at the coast of the
				Semantics, a large language ocean.</p>
			<p class="mb50">A small river named Duden flows by their place
				and supplies it with the necessary regelialia. It is a paradisematic
				country, in which roasted parts of sentences fly into your mouth.</p>
			<p>
				<a href="${pageContext.request.contextPath}/template/#"
					class="btn btn-primary mb10">Find out more</a>
			</p>
		</div>
	</section>

	<!-- START: footer -->
	<div id="includeFooter"></div>
	<!-- END: footer -->

	<div class="gototop js-top">
		<a href="#" class="js-gotop"><i class="icon-chevron-thin-up"></i></a>
	</div>

</body>

<script>
        $("#includeHeader").load('${pageContext.request.contextPath}/includeHeaderController');
        $("#includeFooter").load('${pageContext.request.contextPath}/includeFooterController');
  </script>

<script src="js/scripts.min.js"></script>
<script src="js/main.min.js"></script>
<script src="js/custom.js"></script>

</html>