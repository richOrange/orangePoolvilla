<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

  <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400" rel="stylesheet">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/template/css/styles-merged.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/template/css/style.min.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/template/css/custom.css">
  
  <!-- START: header -->
  
  <div class="probootstrap-loader"></div>

  <header role="banner" class="probootstrap-header">
    <div class="container">
        <a href="${pageContext.request.contextPath}/all/homeController" class="probootstrap-logo">Orange-poolvilla</a>
        

        <nav role="navigation" class="probootstrap-nav hidden-xs">
          <ul class="probootstrap-main-nav">
          <!-- customer 로그인 정보가 있을 경우 -->
          <c:if test="${sessionLoginMember.level >2 && sessionLoginMember.level < 4}">
          	<li><a href="${pageContext.request.contextPath}/customer/">${sessionLoginMember.memberId}님 환영합니다</a></li>
            <li><a href="${pageContext.request.contextPath}/customer/myPageController">내정보보기</a></li>
            <li><a href="${pageContext.request.contextPath}/customer/myReservationListController">예약내역보기</a></li>
            <li><a href="${pageContext.request.contextPath}/customer/myWishListController">찜목록보기</a></li>
            <li><a href="${pageContext.request.contextPath}/customer/myReviewController">리뷰</a></li>
            <li><a href="${pageContext.request.contextPath}/all/logoutController">로그아웃</a></li>
          </c:if>
          <!-- host 로그인 정보가 있을 경우 -->
          <c:if test="${sessionLoginMember.level > 4}">
          	<li><a href="${pageContext.request.contextPath}/host/">${sessionLoginMember.memberId}님 환영합니다</a></li>
            <li><a href="${pageContext.request.contextPath}/host/customerCheckController">회원 관리 페이지</a></li>
            <li><a href="${pageContext.request.contextPath}/host/reservationController">거래 목록 관리 페이지</a></li>
            <li><a href="${pageContext.request.contextPath}/host/hostController">관리자 계정 리스트 관리 페이지</a></li>
            <li><a href="${pageContext.request.contextPath}/host/selectHostPoolvillaListController">풀빌라 리스트 관리 페이지</a></li>
            <li><a href="${pageContext.request.contextPath}/host/myReviewController">추가 기능 구현 대기중</a></li>
            <li><a href="${pageContext.request.contextPath}/all/logoutController">로그아웃</a></li>
          </c:if>
          <!-- 로그인정보가 없을경우 -->
          <c:if test="${empty sessionLoginMember}">
          	<li><a href="${pageContext.request.contextPath}/all/insertCustomerController">회원가입</a></li>
          	<li><a href="${pageContext.request.contextPath}/all/loginController" id="login">로그인</a></li>
          </c:if>
          </ul>
          <div class="extra-text visible-xs"> 
            <a href="${pageContext.request.contextPath}/template/#" class="probootstrap-burger-menu"><i>Menu</i></a>
            <h5>Address</h5>
            <p>198 West 21th Street, Suite 721 New York NY 10016</p>
            <h5>Connect</h5>
            <ul class="social-buttons">
              <li><a href="${pageContext.request.contextPath}/template/#"><i class="icon-twitter"></i></a></li>
              <li><a href="${pageContext.request.contextPath}/template/#"><i class="icon-facebook2"></i></a></li>
              <li><a href="${pageContext.request.contextPath}/template/#"><i class="icon-instagram2"></i></a></li>
            </ul>
          </div>
        </nav>
    </div>
  </header>
  <!-- END: header -->
  
  <script src="${pageContext.request.contextPath}/template/js/scripts.min.js"></script>
  <script src="${pageContext.request.contextPath}/template/js/main.min.js"></script>
  <script src="${pageContext.request.contextPath}/template/js/custom.js"></script>