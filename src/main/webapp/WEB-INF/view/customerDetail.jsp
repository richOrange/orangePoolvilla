<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Orange-poolvilla:customerDetail</title>
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
							<div class="probootstrap-breadcrumbs"></div>
							<h1>ADMIN PAGE</h1>
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

	<h1>고객 상세보기</h1>

	<div>
		<table class = "table table-hover">
			<thead>
				<tr>
					<th>아이디</th>
					
					<th>이름</th>
					<th>성별</th>
					<th>생일</th>
					<th>이메일</th>
					<th>전화번호</th>
					<th>랭크</th>
					<th>생성된 날짜</th>
					<th>최종 수정 날짜</th>
				</tr>
			</thead>

			<tbody>

				<tr>
					<td>${customer.customerId }</td>
					
					<td>${customer.name }</td>
					<td>${customer.gender }</td>
					<td>${customer.birthDate}</td>
					<td>${customer.email}</td>
					<td>${customer.phone }</td>
					<td>${customer.level}</td>
					<td>${customer.createDate }</td>
					<td>${customer.updateDate }</td>
				</tr>

			</tbody>
		</table>
	</div>

	<div>
		<a href="${pageContext.request.contextPath}/host/customerCheckController" type="button" class="btn btn-primary">관리자 목록</a>
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
  </script>

<script src="js/scripts.min.js"></script>
<script src="js/main.min.js"></script>
<script src="js/custom.js"></script>

</html>