<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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

	<h3>리뷰 작성하지 않은 목록</h3>

	<div>
		<table class="table table-hover">
			<thead>
				<tr>
					<th>풀빌라 이름</th>
					<th>지역</th>
					<th>체크 인</th>
					<th>체크 아웃</th>
					
					<!-- <th>나의 리뷰 최종 업데이트 날짜</th>  -->
					
					<th>나의 리뷰</th>
				</tr>
			</thead>

			<tbody>
				<c:forEach var="r" items="${reviewList}">
					<tr>
						<td><a
							href="${pageContext.request.contextPath}/all/selectPoolvillaOneController?pvNo=${r.pvNo}">${r.pvName }</a>
						</td>

						<td>${r.locationName }</td>
						<td>${r.reservationBeginDate }</td>
						<td>${r.reservationLastDate }</td>

						<!-- <td>${r.updateDate }</td> -->

						<!-- 리뷰 작성을 한번도 한적이 없다면 리뷰 입력폼으로 이동하고, 
									 리뷰 작성을 한번이라도 헀다면 리뷰 수정하는 폼으로 이동 -->
						<!-- 리뷰 삭제는 리뷰 수정폼에서만 가능하다 -->
						<td><a
							href="${pageContext.request.contextPath}/customer/insertReviewController?reservationNo=${r.reservationNo}">리뷰
								작성</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

	<h3>리뷰 작성한 목록</h3>

	<div>
		<table class="table table-hover">
			<thead>
				<tr>
					<th>풀빌라 이름</th>
					<th>지역</th>
					<th>체크 인</th>
					<th>체크 아웃</th>
					<!-- <th>나의 리뷰 최종 업데이트 날짜</th> -->
					<th>나의 리뷰</th>
					
				</tr>
			</thead>

			<c:forEach var="rw" items="${reviewListWroteReview}">


				<tr>
					<td><a
						href="${pageContext.request.contextPath}/all/selectPoolvillaOneController?pvNo=${rw.pvNo}">${rw.pvName }</a>
					</td>
					<td>${rw.locationName }</td>
					<td>${rw.reservationBeginDate }</td>
					<td>${rw.reservationLastDate }</td>
					<!-- <td>${r.updateDate }</td> -->
					<td>
						<a href="${pageContext.request.contextPath}/customer/selectReviewOneController?reservationNo=${rw.reservationNo}">
							리뷰 	상세보기
						</a>
					</td>
				</tr>


			</c:forEach>


		</table>
		
	</div>


	<!-- 페이징 부분 시작 -->
	<div>

		<!-- 이전 목록 표시 -->
		<!--  
						<c:if test="${minPage > 10 }">
							<button type="submit" value="${minPage-10}" name="minPage"
								class="btn">이전목록</button>
						</c:if>
						-->

		<!-- 이전 부분 -->
		<c:if test="${currentPage > 1}">
			<button type="submit" value="${currentPage-1}" name="currentPage"
				class="btn">이전</button>
		</c:if>

		<!-- 목록 사이 번호 표시 -->
		<!--  
						<c:forEach var="i" begin="${minPage}" end="${minPage+9}" step="1">
							<c:if test="${i <= lastPage}">
								<c:if test="${currentPage == i}">
									<button type="submit" value="${i}" name="currentPage"
										class="btn btn-primary">${i}</button>
								</c:if>
								<c:if test="${currentPage != i}">
									<button type="submit" value="${i}" name="currentPage"
										class="btn btn-default">${i}</button>
								</c:if>
							</c:if>

						</c:forEach>
						-->

		<!-- 다음 부분 -->
		<c:if test="${currentPage < lastPage }">
			<button type="submit" value="${currentPage+1}" name="currentPage"
				class="btn">다음</button>
		</c:if>

		<!-- 다음목록 표시 -->
		<!--  
						<c:if test="${minPage+10<= lastPage }">
							<button type="submit" value="${minPage+10 }" name="minPage"
								class="btn">다음목록</button>
						</c:if>
						-->
	</div>
	</div>
	<!-- 페이징 부분 끝 -->

	
	

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