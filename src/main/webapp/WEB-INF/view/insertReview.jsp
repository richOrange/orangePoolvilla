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
		<form method="post" action="${pageContext.request.contextPath}/customer/insertReviewController" id = "insertForm">
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
							<option value="-1">::선택::</option>
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
						<button type = "button" id="signup" class="btn btn-info">리뷰 입력</button>
						<button type="reset" id="reset" class="btn">reset</button>
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
	
	/* 
	// 처음 페이지에 들어오면 청결도 입력칸에 커서 이동 
	document.querySelector('#cleanliness').focus();
	
	// (1) 청결도 유효성 확인 
	document.querySelector('#cleanliness').addEventListener('blur',function(){
		if(document.querySelector('#cleanliness').value < -1 || document.querySelector('#cleanliness').value > 6) {
			// 디버깅 
			console.log('청결도는 0이상 5이하'); 
			// 해당 텍스트를 보여준다 
			document.querySelector('#cleanlinessHelper').innerHTML = '청결도는 0이상 5이하';
			// 커서를 청결도 선택창으로 이동 
			document.querySelector('#cleanliness').focus(); 
		} else {
			document.querySelector('#cleanlinessHelper').innerHTML = '';
		}
	});
	
	// (2) 재방문 의사 유효성 확인 
	document.querySelector('#revisit').addEventListener('change', function(){
		if(document.querySelector('#revisit').value == '-1') {
			console.log('재방문 의사를 선택하세요'); 
			document.querySelector('#revisitHelper').innerHTML = '재방문 의사를 선택하세요';
			document.querySelector('#revisit').focus();
		} else {
			
			document.querySelector('#revisitHelper').innerHTML = '';
		}
	});
	
	// (3) 만족도 유효성 확인 
	document.querySelector('#satisfaction').addEventListener('blur',function(){
		if(document.querySelector('#satisfaction').value < -1 || document.querySelector('#satisfaction').value > 6) {
			// 디버깅 
			console.log('만족도는 0이상 5이하'); 
			// 해당 텍스트를 보여준다 
			document.querySelector('#satisfactionHelper').innerHTML = '만족도는 0이상 5이하';
			// 커서를 만족도 선택창으로 이동 
			document.querySelector('#satisfaction').focus(); 
		} else {
			document.querySelector('#satisfactionHelper').innerHTML = '';
		}
	});
	
	*/
	
	$('#signup').click(function(){
		
		$('#cleanlinessHelper').text('');
		$('#revisitHelper').text('');
		$('#satisfaction').text('');
		
		if($('#cleanliness').val() < -1 || $('#cleanliness').val() > 6 || $('#cleanliness').val() == '') {
			$('#cleanlinessHelper').text('청결도는 0이상 5이하');
			$('#cleanliness').focus();
			
		} else if($('#revisit').val() == -1) {
			$('#revisitHelper').text('재방문 의사를 선택하세요');
			$('#revisit').focus();
			
		} else if($('#satisfaction').val() < -1 || $('#satisfaction').val() > 6 || $('#satisfaction').val() == '') {
			$('#satisfactionHelper').text('만족도는 0이상 5이하');
			$('#satisfaction').focus();
			
		} else {
			$('#insertForm').submit();
		}
	});
</script>

<script src="js/scripts.min.js"></script>
<script src="js/main.min.js"></script>
<script src="js/custom.js"></script>

</html>