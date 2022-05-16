<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Orange-poolvilla:deleteHost</title>
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

	<h1>관리자 탈퇴</h1>

	<form
		action="${pageContext.request.contextPath}/host/deleteHostController"
		method="post">
		<table class = "table table-hover">
			<tr>
				<td>삭제할 관리자 아이디</td>
				<td><input type="text" name="hostId" value="${hostId}"
					readonly="readonly"></td>
			</tr>
			<tr>
				<td>비밀번호 입력</td>
				<td><input type="text" name="hostPw"></td>
			</tr>
			<tr>
				<td colspan="2">
					<button type="submit">관리자 삭제</button>
				</td>
			</tr>
		</table>
	</form>


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