<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Title -->
    <title>OrangePoolvilla:pollvillaList</title>

    <!-- Favicon -->
    <link rel="icon" href="${pageContext.request.contextPath}/template/img/core-img/favicon.ico">

    <!-- Stylesheet -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/template/style.css">

</head>

<body>
    <!-- Preloader -->
    <div id="preloader">
        <div class="loader"></div>
    </div>
    <!-- /Preloader -->
    <!-- jquery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <!-- Header Area Start -->
	<div id="includeHeader"> <!-- header 내용 --></div>
    <!-- Header Area End -->

    <!-- Breadcrumb Area Start -->
    <div class="breadcrumb-area bg-img bg-overlay jarallax" style="background-image: url(${pageContext.request.contextPath}/template/img/bg-img/16.jpg);">
        <div class="container h-100">
            <div class="row h-100 align-items-center">
                <div class="col-12">
                    <div class="breadcrumb-content text-center">
                        <h2 class="page-title">Our Room</h2>
                        <nav aria-label="breadcrumb">
                            <ol class="breadcrumb justify-content-center">
                                <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/template/index.html">Home</a></li>
                                <li class="breadcrumb-item active" aria-current="page">Room</li>
                            </ol>
                        </nav>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Breadcrumb Area End -->

    <!-- Rooms Area Start -->
    <div class="roberto-rooms-area section-padding-100-0">
        <div class="container">
            <div class="row">
                <div class="col-12 col-lg-8">
	                <c:forEach var="m" items="${poolvillaList}">
	                    <!-- Single Room Area -->
	                    <div class="single-room-area d-flex align-items-center mb-50 wow fadeInUp" data-wow-delay="100ms">
	                        <!-- Room Thumbnail -->
	                        <div class="room-thumbnail">
	                            <img src="${pageContext.request.contextPath}/template/img/bg-img/43.jpg" alt="">
	                        </div>
	                        <!-- Room Content -->
	                        <div class="room-content">
	                            <h2>${m.pvName}</h2>
	                            <h4>${m.price}원 <span>/ Day</span></h4>
	                            <h4>${m.reviewSatisfaction}/10 <span>이용자 평균 만족도</span></h4>
	                            <div class="room-feature">
	                                <h6>면적: <span>${m.pvSize} m^2</span></h6>
	                                <h6>층수 : <span>${m.pvFloor}층</span></h6>
	                                <h6>최대인원 : <span> ${m.pvPeople}</span></h6>
	                                <h6>침대갯수: <span> ${m.roomCnt}</span></h6>
	                            </div>
	                            <a href="${pageContext.request.contextPath}/all/poolvillaOneController" class="btn view-detail-btn">상세보기 <i class="fa fa-long-arrow-right" aria-hidden="true"></i></a>
	                        </div>
	                    </div>
	               	</c:forEach>
	
	                   

                    <!-- Pagination -->
                    <nav class="roberto-pagination wow fadeInUp mb-100" data-wow-delay="1000ms">
                        <ul class="pagination">
                            <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/template/#">1</a></li>
                            <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/template/#">2</a></li>
                            <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/template/#">3</a></li>
                            <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/template/#">Next <i class="fa fa-angle-right"></i></a></li>
                        </ul>
                    </nav>
                </div>

                <div class="col-12 col-lg-4">
                    <!-- Hotel Reservation Area -->
                    <div class="hotel-reservation--area mb-100">
                        <form action="#" method="post">
                            <div class="form-group mb-30">
                                <label for="checkInDate">Date</label>
                                <div class="input-daterange" id="datepicker">
                                    <div class="row no-gutters">
                                        <div class="col-6">
                                            <input type="text" class="input-small form-control" id="checkInDate" name="checkInDate" placeholder="Check In">
                                        </div>
                                        <div class="col-6">
                                            <input type="text" class="input-small form-control" name="checkOutDate" placeholder="Check Out">
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group mb-30">
                                <label for="guests">Guests</label>
                                <div class="row">
                                    <div class="col-6">
                                        <select name="adults" id="guests" class="form-control">
                                            <option value="adults">Adults</option>
                                            <option value="01">01</option>
                                            <option value="02">02</option>
                                            <option value="03">03</option>
                                            <option value="04">04</option>
                                            <option value="05">05</option>
                                            <option value="06">06</option>
                                        </select>
                                    </div>
                                    <div class="col-6">
                                        <select name="children" id="children" class="form-control">
                                            <option value="children">Children</option>
                                            <option value="01">01</option>
                                            <option value="02">02</option>
                                            <option value="03">03</option>
                                            <option value="04">04</option>
                                            <option value="05">05</option>
                                            <option value="06">06</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group mb-50">
                                <div class="slider-range">
                                    <div class="range-price">Max Price: $0 - $3000</div>
                                    <div data-min="0" data-max="3000" data-unit="$" class="slider-range-price ui-slider ui-slider-horizontal ui-widget ui-widget-content ui-corner-all" data-value-min="0" data-value-max="3000" data-label-result="Max Price: ">
                                        <div class="ui-slider-range ui-widget-header ui-corner-all"></div>
                                        <span class="ui-slider-handle ui-state-default ui-corner-all" tabindex="0"></span>
                                        <span class="ui-slider-handle ui-state-default ui-corner-all" tabindex="0"></span>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <button type="submit" class="btn roberto-btn w-100">Check Available</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Rooms Area End -->



   
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
</html>