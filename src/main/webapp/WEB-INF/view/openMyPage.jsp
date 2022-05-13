<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta name="description" content="">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Title -->
<title>OrangePoolvilla:home</title>

<!-- Favicon -->
<link rel="icon"
	href="${pageContext.request.contextPath}/template/img/core-img/favicon.ico">

<!-- Stylesheet -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/template/style.css">

</head>
<!-- jquery -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<body>
	<!-- Preloader -->
	<div id="preloader">
		<div class="loader"></div>
	</div>
	<!-- /Preloader -->

	<!-- Header Area Start -->

	<div id="includeHeader">
		<!-- Insert your file here -->
	</div>

	<!-- Header Area End -->
	<!-- Breadcrumb Area Start -->

	<div class="breadcrumb-area bg-img bg-overlay jarallax"
		style="background-image: url(${pageContext.request.contextPath}/template/img/bg-img/17.jpg);">
		<div class="container h-100">
			<div class="row h-100 align-items-center">
				<div class="col-12">
					<div class="breadcrumb-content text-center">
						<div class="breadcrumb-post-content">
							<h2 class="post-title">Open My Page</h2>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- Breadcrumb Area End -->
	<!-- Breadcrumb Area End -->

	<!-- Blog Area Start -->
	<div class="roberto-news-area section-padding-100-0">
		<div class="container">
			<div class="row justify-content-center">
				<div class="col-12 col-lg-8">
					<!-- Leave A Reply -->
					<div class="roberto-contact-form mt-80 mb-100">
						<h2>Open My Page</h2>

						<!-- Form -->
						<form action="${pageContext.request.contextPath}/customer/myPageController"
							method="post">
							<div class="row">
								<div class="col-12">
									<table class="table table-hover">
										<tr>
											<td>PW</td>
											<td><input type="password" name="customerPw"></td>
										</tr>
										<tr>
											<td>
												<button type="submit" class="btn roberto-btn btn-3 mt-15">확인</button>
											</td>
										</tr>
									</table>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>






</body>

<script>
	$("#includeHeader").load(
			'${pageContext.request.contextPath}/includeController');
</script>
<!-- **** All JS Files ***** -->
<!-- jQuery 2.2.4 -->
<script
	src="${pageContext.request.contextPath}/template/js/jquery.min.js"></script>
<!-- Popper -->
<script
	src="${pageContext.request.contextPath}/template/js/popper.min.js"></script>
<!-- Bootstrap -->
<script
	src="${pageContext.request.contextPath}/template/js/bootstrap.min.js"></script>
<!-- All Plugins -->
<script
	src="${pageContext.request.contextPath}/template/js/roberto.bundle.js"></script>
<!-- Active -->
<script
	src="${pageContext.request.contextPath}/template/js/default-assets/active.js"></script>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

</html>