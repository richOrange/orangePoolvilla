<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>

    <!-- Header Area Start -->
    <header class="header-area">
        <div class="main-header-area">
            <div class="classy-nav-container breakpoint-off">
                <div class="container">
                    <!-- Classy Menu -->
                    <nav class="classy-navbar justify-content-between" id="robertoNav">

      					 <!-- Logo -->
                        <a class="nav-brand" href="${pageContext.request.contextPath}/all/homeController"><img src="${pageContext.request.contextPath}/template/img/core-img/logo.png" alt=""></a>

                        <!-- Navbar Toggler -->
                        <div class="classy-navbar-toggler">
                            <span class="navbarToggler"><span></span><span></span><span></span></span>
                        </div>

                        <!-- Menu -->
                        <div class="classy-menu">
                            <!-- Menu Close Button -->
                            <div class="classycloseIcon">
                                <div class="cross-wrap"><span class="top"></span><span class="bottom"></span></div>
                            </div>
                            <!-- Nav Start -->
                            <div class="classynav">
                                <ul id="nav">
                                    <li><a href="${pageContext.request.contextPath}/customer/">OOO님 환영합니다</a></li>
                                    <li><a href="${pageContext.request.contextPath}/template/#">my Page</a>
                                        <ul class="dropdown">
                                            <li><a href="${pageContext.request.contextPath}/customer/myPageController">- 내정보보기</a></li>
                                            <li><a href="${pageContext.request.contextPath}/customer/myReservationController">- 예약내역보기</a></li>
                                            <li><a href="${pageContext.request.contextPath}/customer/myWishListController">- 찜목록보기</a></li>
                                            <li><a href="${pageContext.request.contextPath}/customer/myReviewController">- 리뷰</a></li>
                                        </ul>
                                    </li>
                                    <li><a href="${pageContext.request.contextPath}/all/logoutController">로그아웃</a></li>
                                </ul>
                             </div>
                            <!-- Nav End -->
                        </div>
                    </nav>
                </div>
            </div>
        </div>
    </header>
    <!-- Header Area End -->

    <!-- **** All JS Files ***** -->
    <!-- jQuery 2.2.4 -->
    <script src="${pageContext.request.contextPath}/template/js/jquery.min.js"></script>
    <!-- Popper -->
    <script src="${pageContext.request.contextPath}/template/js/popper.min.js"></script>
    <!-- Bootstrap -->
    <script src="${pageContext.request.contextPath}/template/js/bootstrap.min.js"></script>
    <!-- All Plugins -->
    <script src="${pageContext.request.contextPath}/template/js/roberto.bundle.js"></script>
    <!-- Active -->
    <script src="${pageContext.request.contextPath}/template/js/default-assets/active.js"></script>