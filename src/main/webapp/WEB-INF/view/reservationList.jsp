<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Orange-poolvilla:reservationList</title>
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
	              </div>
	              <h1>ADMIN PAGE</h1>
	            </div>
	
	          </div>
	        </div>
	      </div>
	      
	    </div>
	    <ul class="slides">
	      <li style="background-image: url(${pageContext.request.contextPath}/template/img/slider_1.jpg);"></li>
	      <li style="background-image: url(${pageContext.request.contextPath}/template/img/slider_4.jpg);"></li>
	      <li style="background-image: url(${pageContext.request.contextPath}/template/img/slider_2.jpg);"></li>
	    </ul>
  </section>
  	<div class="container">
	<!-- 거래 관리 페이지 부분 -->
	<h1>거래 목록 관리 페이지</h1>

	<h3>전체 거래량 ( total : ${totalRow} )</h3>

	<!-- 예약 상태와 예약 상태 갯수를 동시에 표시하는 버튼 -->
	<c:forEach var="m" items="${reservationStatusList}">
			<li>
					<!-- controller 이동 안되면 소문자 확인 -->
					<!-- m.get() 해당 값 어떻게 Controller로 넘길지 ?  -->
					<a  type ="button" href="${pageContext.request.contextPath}/host/reservationController?reservationStatus=${m.reservationStatus}"> ${m.reservationStatus} (${m.cnt}) </a>
			</li>
	</c:forEach>

	<!-- 예약(reservation) 테이블의 전체 컬럼을 가져오는 코드-->
	<div>
	<!-- 예약상태 변경 form -->
	<form method ="post" action ="${pageContext.request.contextPath}/host/reservationController">
		<table class = "table table-hover">
			<thead>
				<tr>
					<th>회원 아이디</th>
					<th>풀빌라 이름</th>
					<th>예약 날짜</th>
					<th>예약 상태</th>
					<th>작성 날짜</th>
					<th>최종 상태 업데이트 날짜</th>
					<th>결제확인,이용확인</th>
				</tr>
			</thead>

			<tbody>
				<c:forEach var="m" items="${reservationList }">
					<tr>
						<td>${m.customerId}</td>
						<td>${m.pvName}</td>
						<td>${m.reservationDate}</td>
						<td>${m.reservationStatus}</td>
						<td>${m.createDate}</td>
						<td>${m.updateDate}</td>
						<td>
							<!-- 입금 확인시, 결제 대기상태를 결제 완료로 변경하는 버튼 -->
							<c:if test="${m.reservationStatus=='결제대기'}">
								<button type ="submit" name="checkStatus" value = "결제완료">결제 확인</button>
							</c:if>
							<!-- 환불 완료시, 취소 대기상태를 취소 완료로 변경하는 버튼 -->
							<c:if test="${m.reservationStatus=='취소대기'}">
								<button type ="submit" name="checkStatus" value = "취소완료">취소확인</button>
							</c:if>
							<!-- 이용 완료시, 결제완료 상태를 이용 완료로 변경하는 버튼 -->
							<c:if test="${m.reservationStatus=='결제완료'}">
								<button type ="submit" name="checkStatus" value = "이용완료">이용확인</button>
							</c:if>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</form>
	</div>
	<div>

		<!-- 페이지 나누는 코드 -->

		<c:if test="${currentPage > 1}">
			
			<a
				href="${pageContext.request.contextPath}/host/reservationController?currentPage=${currentPage - 1}&reservationStatus=${reservationStatus}">이전
			</a>
			
		</c:if>

		<c:if test="${currentPage < lastPage}">
			<a
				href="${pageContext.request.contextPath}/host/reservationController?currentPage=${currentPage + 1}&reservationStatus=${reservationStatus}">다음
			</a>
		</c:if>

	</div>
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