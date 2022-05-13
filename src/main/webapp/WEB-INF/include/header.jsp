<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>

  <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400" rel="stylesheet">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/template/css/styles-merged.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/template/css/style.min.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/template/css/custom.css">
  
  <!-- START: header -->
  
  <div class="probootstrap-loader"></div>

  <header role="banner" class="probootstrap-header">
    <div class="container">
        <a href="${pageContext.request.contextPath}/template/index.html" class="probootstrap-logo">Orange-poolvilla</a>
        
        <a href="${pageContext.request.contextPath}/template/#" class="probootstrap-burger-menu visible-xs" ><i>Menu</i></a>
        <div class="mobile-menu-overlay"></div>

        <nav role="navigation" class="probootstrap-nav hidden-xs">
          <ul class="probootstrap-main-nav">
          	<li><a href="${pageContext.request.contextPath}/customer/">OOO님 환영합니다</a></li>
            <li><a href="${pageContext.request.contextPath}/customer/myPageController">내정보보기</a></li>
            <li><a href="${pageContext.request.contextPath}/customer/myReservationController">예약내역보기</a></li>
            <li><a href="${pageContext.request.contextPath}/customer/myWishListController">찜목록보기</a></li>
            <li><a href="${pageContext.request.contextPath}/customer/myReviewController">리뷰</a></li>
            <li><a href="${pageContext.request.contextPath}/all/logoutController">로그아웃</a></li>
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