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
  
	<!-- poolvillaList??? ?????????????????? ?????? ??????-->
	<form method="get" action="${pageContext.request.contextPath}/host/selectHostPoolvillaListController">
	  <section class="probootstrap-section probootstrap-section-lighter">
	    <div class="container">
	    	<!-- poolvillaList ??????  ??????-->
				<div class="row">
	  				<c:forEach var="m" items="${poolvillaList}" varStatus="status">
	    				<div class="col-md-4">
		   					<div class="probootstrap-card probootstrap-listing">
		        			<!-- ?????? img ?????? -->
			        			<div class="probootstrap-card-media">
				        			<c:if test="${ not empty m.photoName }">
				        				<img src="${pageContext.request.contextPath}/image/poolvilla/${m.photoName}" class="img-responsive">
				        			</c:if>
			        			</div>
			        			<!-- ????????? ??? ?????????????????? -->
			        			<div class="probootstrap-card-text"> 
			          				<h2 class="probootstrap-card-heading"><a href="${pageContext.request.contextPath}/host/selectHostPoolvillaOneController?pvNo=${m.pvNo}">${m.pvName}</a></h2>
			          				<div class="probootstrap-listing-location">
			            				<i class="icon-location2"></i> <span>${m.address}</span>
			          				</div>
			          				<div class="probootstrap-listing-price"><strong>${m.price}???</strong> / Day</div>
		       					</div>
			        			<div class="probootstrap-card-extra">
			          				<ul>
			            				<li>
		             					  ??????
		              					  <span>${m.pvSize} m2</span>
			            				</li>
							            <li>
							              ??? ??????
							              <span>${m.roomCnt}</span>
							            </li>
							            <li>
							              ????????????
							              <span>${m.pvPeople}</span>
							            </li>
							            <li>
							              ?????? ?????????
							              <span>${m.reviewSatisfaction}/10 </span>
							            </li>
							          </ul>
			        			</div>
		        			</div>
	        			</div>
	    		<!-- ????????? 3?????? ????????? ?????? if??? -->
	    				<c:if test="${status.count%3== 0}">
	    					</div><div class="row">
	    				</c:if>
    			</c:forEach>
	      <a href="${pageContext.request.contextPath}/host/insertPoolvillaController" class = "btn btn-primary btn-sm">?????? ??????</a>
   	      <!-- ????????? ?????? ?????? -->
			<div>
			<!-- ?????? ?????? ?????? -->
				<c:if test="${minPage > 10 }">
					<button type = "submit" value ="${minPage-10}" name ="minPage" class="btn">????????????</button>
				</c:if>				
			<!-- ?????? ?????? -->
				<c:if test="${currentPage>1}">
			 		<button type = "submit" value ="${currentPage-1}" name = "currentPage" class="btn" >??????</button>
				</c:if>
			<!-- ?????? ?????? ?????? ?????? -->
				<c:forEach var="i" begin="${minPage}" end="${minPage+9}" step="1">
					<c:if test= "${i <= lastPage}" >
						<c:if test="${currentPage == i}">
							<button type = "submit" value="${i}" name = "currentPage" class="btn btn-primary">${i}</button>
						</c:if>
						<c:if test="${currentPage != i}">
							<button type = "submit" value ="${i}" name = "currentPage" class="btn btn-default">${i}</button>
						</c:if>
					</c:if>
				
				</c:forEach>
				<!-- ?????? ?????? -->
				<c:if test="${currentPage< lastPage }">
			 		<button type = "submit" value ="${currentPage+1}" name = "currentPage" class="btn" >??????</button>
				</c:if>
				<!-- ???????????? ?????? -->
				<c:if test="${minPage+10<= lastPage }">
			 		<button type = "submit" value ="${minPage+10 }" name = "minPage" class="btn">????????????</button>
				</c:if>
			 </div>
		</div>
	      <!-- ????????? ?????? ??? --> 
	  </section>
	</form>
	<!-- poolvillaList ?????? ???-->
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