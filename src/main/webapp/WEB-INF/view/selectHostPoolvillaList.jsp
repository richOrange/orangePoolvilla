<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Orange-poolvilla:poolvillaList</title>
    <meta name="description" content="Free Bootstrap Theme by uicookies.com">
    <meta name="keywords" content="free website templates, free bootstrap themes, free template, free bootstrap, free website template">
    
    <!-- <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400" rel="stylesheet"> -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/template/css/styles-merged.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/template/css/style.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/template/css/custom.css">

  </head>
  
  	<!-- jquery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    
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
                <a href="${pageContext.request.contextPath}/template/#">List</a><span>Host Poolvilla List</span>
              </div>
              <h1>Poolvilla List</h1>
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
  <!-- END: slider  -->
  
	<!-- poolvillaList와 상세검색기능 부분 시작-->
	<form method="get" action="${pageContext.request.contextPath}//all/poolvillaListController">
	  <section class="probootstrap-section probootstrap-section-lighter">
	    <div class="container">
	    	<!-- poolvillaList 부분  시작-->
	    	<div class ="col-sm-12">
	  				<c:forEach var="m" items="${poolvillaList}" varStatus="status">
	    				<div class="col-md-4 col-sm-4">
	   					<div class="probootstrap-card probootstrap-listing">
	        		<!-- 상품 img 부분 -->
	        			<div class="probootstrap-card-media">
	        				<img src="${pageContext.request.contextPath}/template/img/slider_1.jpg" class="img-responsive" alt="Free HTML5 Template by uicookies.com">
	        			</div>
	        		<!-- 이미지 내 상품설명부분 -->
	        			<div class="probootstrap-card-text"> 
	          				<h2 class="probootstrap-card-heading"><a href="${pageContext.request.contextPath}/all/selectPoolvillaOneController?pvNo=${m.pvNo}">${m.pvName}</a></h2>
	          				<div class="probootstrap-listing-location">
	            				<i class="icon-location2"></i> <span>${m.address}</span>
	          				</div>
	          				<div class="probootstrap-listing-price"><strong>${m.price}원</strong> / Day</div>
	       				</div>
	        			<div class="probootstrap-card-extra">
	          				<ul>
	            				<li>
             					  면적
              					  <span>${m.pvSize} m2</span>
	            				</li>
					            <li>
					              방 개수
					              <span>${m.roomCnt}</span>
					            </li>
					            <li>
					              최대인원
					              <span>${m.pvPeople}</span>
					            </li>
					            <li>
					              평균 만족도
					              <span>${m.reviewSatisfaction}/10 </span>
					            </li>
					          </ul>
	        			</div>
	      			</div>
	    		</div>
	    		<!-- 한줄에 2개씩 나오게 하는 if문 -->
	    	</c:forEach>
	      <a href="${pageContext.request.contextPath}/host/insertHostPoolvillaOneController" class = "btn btn-primary btn-sm">상품 추가</a>
		</div>
	      <!-- poolvillaList 부분 끝 -->
	    </div>
	  </section>
	</form>
	<!-- poolvillaList와 상세검색기능 부분 끝-->
  <!-- END: section -->

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