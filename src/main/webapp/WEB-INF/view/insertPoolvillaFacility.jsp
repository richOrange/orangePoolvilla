<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Orange-poolvilla:insertPoolvillaCookingTool</title>
    <meta name="description" content="Free Bootstrap Theme by uicookies.com">
    <meta name="keywords" content="free website templates, free bootstrap themes, free template, free bootstrap, free website template">
    
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400" rel="stylesheet">
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
                <a href="${pageContext.request.contextPath}/template/#">Home</a><span>About</span>
              </div>
              <h1>Insert Poolvilla Cooking Tool</h1>
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
  
  <section class="probootstrap-section">
    <div class="container">
    
    		<table class = "table table-hover">
			<thead>
				<tr>
					<th>Number</th>
					<th>Facility Name</th>
					<th>Count</th>
					<th>&nbsp;</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<c:forEach var = "f" items = "${ list }" varStatus = "status">
						<form method="post" action="${pageContext.request.contextPath}/host/insertPoolvillaFacilityController">
						<input type = "hidden" name = "pvNo" value = "${ param.pvNo }" readonly>
							<tr>
								<td><input type = "hidden" name = "facilityNo" value = "${ f.facilityNo }" readonly>${ status.count }</td>
								<td><input type = "hidden" name = "facilityName" value = "${ f.facilityName }" readonly>${ f.facilityName }</td>
								<td>
									<input type = "number" name = "facilityCnt">
								</td> 
								<td><input type="submit" class="btn btn-outline-secondary btn-sm" id="submit" name="submit" value="추가"></td>
							</tr>
						</form>
					</c:forEach>
				</tr>
			</tbody>
		</table>
		
		<hr>
      
		<table class = "table table-hover">
			<thead>
				<tr>
					<th>Facility Name</th>
					<th>Count</th>
					<th>&nbsp;</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<c:if test="${ not empty pfList }">
						<c:forEach var = "pf" items = "${ pfList }">
								<tr>
									<td>${ pf.facilityName }</td>
									<td>${ pf.facilityCnt }</td>
									<td><a href="${pageContext.request.contextPath}/host/deletePoolvillaFacilityController?pvNo=${ param.pvNo }&facilityNo=${ pf.facilityNo }" class = "btn btn-outline-secondary btn-sm">삭제</a></td>
								</tr>
						</c:forEach>
					</c:if>
				</tr>
			</tbody>
		</table>
		<hr>
		
		<a href="${pageContext.request.contextPath}/host/facilityController?pvNo=${ pvNo }" class = "btn btn-primary btn-sm">다른 facility 목록 추가하러 가기</a>
		<a href="${pageContext.request.contextPath}/host/selectHostPoolvillaOneController?pvNo=${ pvNo }" class = "btn btn-primary btn-sm">update완료</a>

    </div>
  </section>  

  	<!-- START: footer -->
	       <div id="includeFooter"></div>
	<!-- END: footer -->

  <div class="gototop js-top">
    <a href="${pageContext.request.contextPath}/template/#" class="js-gotop"><i class="icon-chevron-thin-up"></i></a>
  </div>

  </body>
  
  <script>
        $("#includeHeader").load('${pageContext.request.contextPath}/includeHeaderController');
        $("#includeFooter").load('${pageContext.request.contextPath}/includeFooterController');
  </script>
  
  <script src="${pageContext.request.contextPath}/template/js/scripts.min.js"></script>
  <script src="${pageContext.request.contextPath}/template/js/main.min.js"></script>
  <script src="${pageContext.request.contextPath}/template/js/custom.js"></script>
  
</html>