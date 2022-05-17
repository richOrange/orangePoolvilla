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
				<h2 class="mt0 mb50 text-center">풀빌라 정보</h2>
			</div>
			
			<div class="probootstrap-card probootstrap-person text-left">

						<div class="probootstrap-card-text">
							<h2 class="probootstrap-card-heading mb0">풀빌라 정보</h2>
							<p>
								<small>poolvilla info</small>
							</p>
							<a href="${pageContext.request.contextPath}/host/insertPoolvillaController" class = "btn btn-primary btn-sm">insert</a>
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
							<a href="${pageContext.request.contextPath}/host/#" class = "btn btn-primary btn-sm">insert</a>
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
							<a href="${pageContext.request.contextPath}/host/#" class = "btn btn-primary btn-sm">insert</a>
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
							<a href="${pageContext.request.contextPath}/host/#" class = "btn btn-primary btn-sm">insert</a>
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
							<a href="${pageContext.request.contextPath}/host/#" class = "btn btn-primary btn-sm">insert</a>
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
							<a href="${pageContext.request.contextPath}/host/#" class = "btn btn-primary btn-sm">insert</a>
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
							<a href="${pageContext.request.contextPath}/host/#" class = "btn btn-primary btn-sm">insert</a>
						</div>
					</div>
				</div>
			</div>
			
			<form action="#" method="post" enctype="multipart/form-data">
				<table class = "table table-active">
					<tr>
						<td>insert image</td>
						<td><input type = "file" name = "photo" class = "custom-file"></td>
						<td><button type = "button" class = "btn btn-dark">insert</button></td>
					</tr>
				</table>
			</form>
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