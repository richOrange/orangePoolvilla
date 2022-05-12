<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta name="description" content="">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Title -->
    <title>OrangePoolvilla:home</title>

    <!-- Favicon -->
    <link rel="icon" href="${pageContext.request.contextPath}/template/img/core-img/favicon.ico">

    <!-- Stylesheet -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/template/style.css">

</head>
<!-- jquery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<body>
    <!-- Preloader -->
    <div id="preloader">
        <div class="loader"></div>
    </div>
    <!-- /Preloader -->

    <!-- Header Area Start -->
    
	<div id="includeHeader"> <!-- Insert your file here --></div>
	
    <!-- Header Area End -->

    <!-- Welcome Area Start -->
    <section class="welcome-area">
        <div class="welcome-slides owl-carousel">
            <!-- Single Welcome Slide -->
            <div class="single-welcome-slide bg-img bg-overlay" style="background-image: url(${pageContext.request.contextPath}/template/img/bg-img/16.jpg);" data-img-url="${pageContext.request.contextPath}/template/img/bg-img/16.jpg">
                <!-- Welcome Content -->
                <div class="welcome-content h-100">
                    <div class="container h-100">
                        <div class="row h-100 align-items-center">
                            <!-- Welcome Text -->
                            <div class="col-12">
                                <div class="welcome-text text-center">
                                    <h6 data-animation="fadeInLeft" data-delay="200ms">Welcome To </h6>
                                    <h2 data-animation="fadeInLeft" data-delay="500ms">OrangePoolvilla</h2>
                                    <a href="${pageContext.request.contextPath}/LoginController" class="btn roberto-btn btn-2" data-animation="fadeInLeft" data-delay="800ms">Login Now</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Single Welcome Slide -->
            <div class="single-welcome-slide bg-img bg-overlay" style="background-image: url(${pageContext.request.contextPath}/template/img/bg-img/17.jpg);" data-img-url="${pageContext.request.contextPath}/template/img/bg-img/17.jpg">
                <!-- Welcome Content -->
                <div class="welcome-content h-100">
                    <div class="container h-100">
                        <div class="row h-100 align-items-center">
                            <!-- Welcome Text -->
                            <div class="col-12">
                                <div class="welcome-text text-center">
                                    <h6 data-animation="fadeInUp" data-delay="200ms">Welcome To </h6>
                                    <h2 data-animation="fadeInUp" data-delay="500ms">OrangePoolvilla</h2>
                                    <a href="${pageContext.request.contextPath}/signupController" class="btn roberto-btn btn-2" data-animation="fadeInUp" data-delay="800ms">Sign Up Now</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- Single Welcome Slide -->
            <div class="single-welcome-slide bg-img bg-overlay" style="background-image: url(${pageContext.request.contextPath}/template/img/bg-img/18.jpg);" data-img-url="${pageContext.request.contextPath}/template/img/bg-img/18.jpg">
                <!-- Welcome Content -->
                <div class="welcome-content h-100">
                    <div class="container h-100">
                        <div class="row h-100 align-items-center">
                            <!-- Welcome Text -->
                            <div class="col-12">
                                <div class="welcome-text text-center">
                                    <h6 data-animation="fadeInDown" data-delay="200ms">Welcome To</h6>
                                    <h2 data-animation="fadeInDown" data-delay="500ms">OrangePoolvilla</h2>
                                    <a href="${pageContext.request.contextPath}/template/#" class="btn roberto-btn btn-2" data-animation="fadeInDown" data-delay="800ms">Login Now</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Welcome Area End -->

    <!-- About Us Area Start -->
    <section class="roberto-about-area section-padding-100-0">
    
        <!-- poolvilla Search Form Area -->
        <div class="hotel-search-form-area">
            <div class="container-fluid">
                <div class="hotel-search-form">
                    <form action="#" method="post">
                        <div class="row justify-content-between align-items-end">
                            <div class="col-6 col-md-2 col-lg-3">
                                <label for="checkIn">Check In</label>
                                <input type="date" class="form-control" id="checkIn" name="checkin-date">
                            </div>
                            <div class="col-6 col-md-2 col-lg-3">
                                <label for="checkOut">Check Out</label>
                                <input type="date" class="form-control" id="checkOut" name="checkout-date">
                            </div>
                            <div class="col-4 col-md-1">
                            	<!-- 지역검색 부분 -->
                                <label for="room">Location</label>
                                <select name="room" id="room" class="form-control">
                                    <option value="01">가평</option>
                                    <option value="02">대부도</option>
                                </select>
                            </div>
                            <div class="col-12 col-md-3">
                                <button type="submit" class="form-control btn roberto-btn w-100">Check Availability</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <div class="container mt-100">
            <div class="row align-items-center">
                <div class="col-12 col-lg-6">
                    <!-- Section Heading -->
                    <div class="section-heading wow fadeInUp" data-wow-delay="100ms">
                        <h6>About Us</h6>
                        <h2>Welcome to <br>Roberto Hotel Luxury</h2>
                    </div>
                    <div class="about-us-content mb-100">
                        <h5 class="wow fadeInUp" data-wow-delay="300ms">With over 340 hotels worldwide, NH Hotel Group offers a wide variety of hotels catering for a perfect stay no matter where your destination.</h5>
                        <p class="wow fadeInUp" data-wow-delay="400ms">Manager: <span>Michen Taylor</span></p>
                        <img src="${pageContext.request.contextPath}/template/img/core-img/signature.png" alt="" class="wow fadeInUp" data-wow-delay="500ms">
                    </div>
                </div>

                <div class="col-12 col-lg-6">
                    <div class="about-us-thumbnail mb-100 wow fadeInUp" data-wow-delay="700ms">
                        <div class="row no-gutters">
                            <div class="col-6">
                                <div class="single-thumb">
                                    <img src="${pageContext.request.contextPath}/template/img/bg-img/13.jpg" alt="">
                                </div>
                                <div class="single-thumb">
                                    <img src="${pageContext.request.contextPath}/template/img/bg-img/14.jpg" alt="">
                                </div>
                            </div>
                            <div class="col-6">
                                <div class="single-thumb">
                                    <img src="${pageContext.request.contextPath}/template/img/bg-img/15.jpg" alt="">
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- About Us Area End -->


</body>
<script>
	$("#includeHeader").load('${pageContext.request.contextPath}/includeController');
</script>
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
    
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

</html>