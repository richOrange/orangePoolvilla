<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Orange-poolvilla:myWishList</title>
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
								<h1>찜 목록</h1>
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

	<!-- 아래 내용 찜 목록 형태로 만들기 62 ~ 170 -->
	
	<!-- poolvillaList와 상세검색기능 부분 시작-->
	
		<section class="probootstrap-section probootstrap-section-lighter">
			<div class="container">
				<!-- poolvillaList 부분  시작-->
				<div class="col-sm-8">
					<div class="row">
						<c:forEach var="m" items="${wishList}" varStatus="status">
							<div class="col-md-6 col-sm-6">
								<div class="probootstrap-card probootstrap-listing">
								
									<!-- 상품 img 부분 -->
									<div class="probootstrap-card-media">
										<img
											src="${pageContext.request.contextPath}/image/poolvilla/${m.photoName}"
											class="img-responsive"
											alt="Free HTML5 Template by uicookies.com"> 
									</div>
									
									<!-- 이미지 내 상품설명부분 -->
									
									<!-- 풀빌라 이름 -->
									<div class="probootstrap-card-text">
										<h2 class="probootstrap-card-heading">
											${m.pvName}
										</h2>
										
										<div class="probootstrap-listing-location">
											<i class="icon-location2"></i> <span>${m.address}</span>
										</div>
										
										<!-- 예약 상태에 따라 값이 변하도록 수정 필요함 -->
										<div class="probootstrap-listing-category for-sale">
											<span>예약가능</span>
										</div>
										
										<div class="probootstrap-listing-price">
											<strong>${m.price}원</strong> / Day
											
											
										</div>
										
										<div>
											<button>
												<a href="${pageContext.request.contextPath}/customer/deleteWishListController?pvNo=${m.pvNo}&customerId=${sessionLoginMember.memberId}" class="probootstrap-love">
													찜 삭제
												</a>
											</button>
										</div>
										
									</div>
									
									<div class="probootstrap-card-extra">
										<ul>
											<li>면적 <span>${m.pvSize} m2</span>
											</li>
											<li>방갯수 <span>${m.roomCnt}</span>
											</li>
											<li>최대인원 <span>${m.pvPeople}</span>
											</li>
											<li>평균 만족도 <span>${m.reviewSatisfaction}/10 </span>
											</li>
										</ul>
									</div>
								</div>
							</div>
							<!-- 윗부분 처리했음 -->
							
							<!-- 한줄에 3개씩 나오게 하는 if문 -->
							<c:if test="${status.index % 3 == 0 && status.index != 0 }">
					</div>
					
					<div class="row">
							</c:if>
						</c:forEach>
					</div>
					
					<!-- 여기부터 작업 시작하기 !  -->
					
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
							<button type="submit" value="${currentPage-1}" name="currentPage" class="btn">
								이전
							</button>
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
							<button type="submit" value="${currentPage+1}" name="currentPage" class="btn">
								다음
							</button>
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
				
				<!-- poolvillaList 부분 끝 -->
				
				
			</div>
		</section>
	
	<!-- poolvillaList와 상세검색기능 부분 끝-->

	<!-- 찜 목록 기능은 이 위에 작성 -->
	
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