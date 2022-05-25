<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Orange-poolvilla:myReviewList</title>
<style>
	.helper {
    	color: #FF0000;
    }
</style>

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
						<input type="number" id = "cleanliness" name="cleanliness">
						<span id="cleanlinessHelper" class="helper"></span>
					</td>
				</tr>
				<tr>
					<td>재방문 의사</td>
					<td>
						<select id = "revisit" name="revisit">
							<option value="">::선택::</option>
							<option value="Y">있음</option>
							<option value="N">없음</option>
						</select>
						<span id="revisitHelper" class="helper"></span>
					</td>
				</tr>
				<tr>
					<td>만족도(0~5)</td>
					<td>
						<input type="number" id = "satisfaction" name="satisfaction">
						<span id = "satisfactionHelper" class="helper"></span>
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
				
				<!--  -->
				<tr>
					<td><input type="hidden" name="예약 번호"></td>
					<td>
						 <input type="hidden" id="reservationNo" name="reservationNo" value="${reservationNo }">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<button type = "submit" id="submit" class="btn btn-info">리뷰 입력</button>
						<button type="reset">reset</button>
					</td>
					
				</tr>
			</table>
		</form>
		
	</div>



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
	
	// (1) 청결도 유효성 확인 
	document.querySelector('#cleanliness').addEventListener('blur',function(){
		if
	});
</script>

<script src="js/scripts.min.js"></script>
<script src="js/main.min.js"></script>
<script src="js/custom.js"></script>

</html>