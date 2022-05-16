<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Orange-poolvilla:hostList</title>
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

	<h1>관리자 계정 리스트 관리 페이지</h1>

	<h3>전체 관리자 수</h3>

	<div>
		<table class = "table table-hover">
			<thead>
				<tr>
					<th>관리자 아이디</th>
					<th>관리자 비밀번호</th>
					<th>랭크 번호</th>
					<th>이름</th>
					<th>이메일</th>
					<th>전화번호</th>
					<th>작성 날짜</th>
					<th>수정 날짜</th>
					<th>관리자 정보 삭제</th>
					<th>관리자 정보 수정</th>
				</tr>
			</thead>

			<tbody>
				<c:forEach var="h" items="${hostList}">
					<tr>
						<td>${h.hostId}</td>
						<td>${h.hostPw}</td>
						<td>${h.level}</td>
						<td>${h.name}</td>
						<td>${h.email}</td>
						<td>${h.phone}</td>
						<td>${h.createDate}</td>
						<td>${h.updateDate}</td>
						<td><a
							href="${pageContext.request.contextPath}/host/deleteHostController?hostId=${h.hostId}">관리자
								정보 삭제</a></td>
						<td><a
							href="${pageContext.request.contextPath}/host/updateHostController?hostId=${h.hostId}">관리자
								정보 수정</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

	<div>
		<button>
			<a href="${pageContext.request.contextPath}/host/insertHostController">관리자 정보 추가</a>
		</button>
	</div>

	<div>

		<!-- 페이지 나누는 코드 -->

		<c:if test="${currentPage > 1}">
			<a
				href="${pageContext.request.contextPath}/host/hostController?currentPage=${currentPage - 1}" type="button" class="btn btn-primary">이전
			</a>
		</c:if>

		<c:if test="${currentPage < lastPage}">
			<a
				href="${pageContext.request.contextPath}/host/hostController?currentPage=${currentPage + 1}" type="button" class="btn btn-primary">다음
			</a>
		</c:if>

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