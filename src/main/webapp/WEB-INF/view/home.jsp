<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Orange-poolvilla:home</title>
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
	
  <section class="probootstrap-slider flexslider">
    <div class="probootstrap-wrap-banner">
      <div class="container">
        <div class="row">
          <div class="col-md-8 col-md-offset-2">

            <div class="probootstrap-home-search probootstrap-animate">
              <form id ="searchForm" action="${pageContext.request.contextPath}/all/poolvillaListController" method="get">
              <!-- 유효성 메세지 출력 부분-->
              <c:if test="${msg = needLevel}">
              		<h2>권한이 없는 페이지 입니다.</h2>
              </c:if>
              		<h2 id="searchHelper" class="heading"></h2>
                <div class="probootstrap-field-group">
                  <div class="probootstrap-fields">
                    
                      
                      <div>
	                      체크인 :<input type="date" class="form-control" id="checkIn" name="reservationBeginDate">
    	                  체크 아웃 :<input type="date" class="form-control" id="checkOut" name="reservationLastDate">
                      </div>
                      <!-- 지역검색부분 -->
                      지역선택 : 
                     <select id="locationNo" name="locationNo" class="form-control">
                     	<option value="-1">::지역선택::</option>
                      <c:forEach var ="m" items="${locationList}">
                         <option value="${m.locationNo}">${m.locationName}</option>
                      </c:forEach>
                     </select>
                  <button type="button" id="search" class="btn btn-success" style="margin-top: 20px"><i class="icon-magnifying-glass t2"></i> Start Search</button>
                  </div>
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
    <ul class="slides">
      <li style="background-image: url(${pageContext.request.contextPath}/template/img/slider_1.jpg);" class="overlay"></li>
      <li style="background-image: url(${pageContext.request.contextPath}/template/img/slider_4.jpg);" class="overlay"></li>
      <li style="background-image: url(${pageContext.request.contextPath}/template/img/slider_2.jpg);" class="overlay"></li>
    </ul>
  </section>
  <!-- END: slider  -->

  	<!-- START: footer -->
	       <div id="includeFooter"></div>
	<!-- END: footer -->

  <div class="gototop js-top">
    <a href="${pageContext.request.contextPath}/template/#" class="js-gotop"><i class="icon-chevron-thin-up"></i></a>
  </div>

  </body>
  
<script>
	//include
	$("#includeHeader").load('${pageContext.request.contextPath}/includeHeaderController');
  	$("#includeFooter").load('${pageContext.request.contextPath}/includeFooterController');
  	//유효성검사
  	$('#search').click(function(){
		if($('#checkIn').val() == ''){
			$('#searchHelper').text('체크인 날짜를 선택해주세요');
			$('#checkIn').focus();
			
		}else if($('#checkOut').val() == ''){
			$('#searchHelper').text('체크아웃 날짜를 선택해주세요');
			$('#checkOut').focus();
			
		}else if($('#locationNo').val() == '-1') {
			$('#searchHelper').text('지역을 선택해주세요');
			$('#locationNo').focus();
		} else {
			$('#searchForm').submit();
		}
	});
  
</script>
  
  <script src="${pageContext.request.contextPath}/template/js/scripts.min.js"></script>
  <script src="${pageContext.request.contextPath}/template/js/main.min.js"></script>
  <script src="${pageContext.request.contextPath}/template/js/custom.js"></script>
  
</html>