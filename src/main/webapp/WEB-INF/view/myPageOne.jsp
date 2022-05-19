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
							<h1>My Page</h1>
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
	<!-- Form -->
	<section class="probootstrap-section">
		<div class="container">
			<div class="row">
				<div class="col-md-8">
					<form action="${pageContext.request.contextPath}/customer/myPageOneController" method="post" class="probootstrap-form mb60">
						<div class="row">
							<div class="col-12">
								<table class="table table-hover">
									<tr>
										<td>ID :</td>
										<td><input type="text" name="memberid" id="memberid" value="${myPageCustomer.customerId}" readonly></td>
									</tr>
									<tr>
										<td>NAME :</td>
										<td><input type="text" name="membername" id="membername" value="${myPageCustomer.name}" readonly></td>
									</tr>
									<tr>
										<td>GENDER :</td>
										<td><input type="text" name="membergender" id="membergender" value="${myPageCustomer.gender}" readonly></td>
									</tr>
									<tr>
										<td>BIRTHDATE :</td>
										<td><input type="text" name="memberbirthdate" id="memberbirthdate" value="${myPageCustomer.birthDate}" readonly></td>
									</tr>
									<tr>
										<td>EMAIL :</td>
										<td><input type="text" name="memberemail" id="memberemail" value="${myPageCustomer.email}" readonly></td>
									</tr>
									<tr>
										<td>PHONE :</td>
										<td><input type="text" name="memberphone" id="memberphone" value="${myPageCustomer.phone}" readonly></td>
									</tr>
									<tr>
										<td><a href="${pageContext.request.contextPath}/customer/updateCustomerController" class="btn btn-primary">회원 정보 변경</a></td>
										<td><a href="${pageContext.request.contextPath}/customer/updatePasswordController" class="btn btn-primary">회원 비밀번호 변경</a></td>
										<td><a href="${pageContext.request.contextPath}/customer/deleteCustomerController" class="btn btn-primary">회원 탈퇴</a></td>
										<td><a href="${pageContext.request.contextPath}/all/homeController" class="btn btn-primary">홈으로</a></td>
									</tr>
								</table>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</section>







</body>

<script>
	//include
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