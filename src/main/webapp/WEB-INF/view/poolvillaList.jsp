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

	<style>
		.floating { 
			position: fixed; 
			right: 50%; 
			top: 180px; 
			margin-right: -720px; 
			text-align:center; 
			width: 120px; 
			margin-top: 300px;
			}
	
		  table {
		    width: 100%;
		    border: 1px solid #D8D8D8;
		  }
	</style>
	
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
                <a href="${pageContext.request.contextPath}/template/#">PoolvillaList</a><span>${poolvillaList[0].locationName}</span>
              </div>
              <h1>${poolvillaList[0].locationName}</h1>
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
	<form method="post" id="searchForm" action="${pageContext.request.contextPath}//all/poolvillaListController">
	  <section class="probootstrap-section probootstrap-section-lighter">
	    <div class="container">
	    	<!-- poolvillaList 부분  시작-->
	    	<div class ="col-md-8">
	    		<div class="row">
	  				<c:forEach var="m" items="${poolvillaList}" varStatus="status">
	    				<div class="col-md-6">
	   					<div class="probootstrap-card probootstrap-listing">
	        		<!-- 상품 img 부분 -->
	        			<div class="probootstrap-card-media">
	        			<c:if test="${ not empty m.photoName }">
	        				<img src="${pageContext.request.contextPath}/image/poolvilla/${m.photoName}" class="img-responsive">
	        			</c:if>
	          				<a href="${pageContext.request.contextPath}/customer/insertWishListController?pvNo=${m.pvNo}&customerId=${sessionLoginMember.memberId}" class="probootstrap-love"><i class="icon-heart"></i></a>
	        			</div>
	        		<!-- 이미지 내 상품설명부분 -->
	        			<div class="probootstrap-card-text"> 
	          				<h2 class="probootstrap-card-heading"><a href="${pageContext.request.contextPath}/all/selectPoolvillaOneController?pvNo=${m.pvNo}&reservationBeginDate=${reservationBeginDate}&reservationLastDate=${reservationLastDate}">${m.pvName}</a></h2>
	          				<div class="probootstrap-listing-location">
	            				<i class="icon-location2"></i> <span>${m.address}</span>
	          				</div>
	          				<div class="probootstrap-listing-category for-sale">
	          					<span>예약가능</span>
	          					<!-- <span class="btn"><input type="submit" name="compare" value="${m.pvNo}">비교하기</span>  -->
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
					              방갯수
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
	    		<!-- 한줄에 3개씩 나오게 하는 if문 -->
   				<c:if test="${status.count%2== 0}">
   					</div><div class="row">
   				</c:if>
	    	</c:forEach>
	      </div>
	      <!-- 페이징 부분 시작 -->
			<div>
			<!-- 이전 목록 표시 -->
				<c:if test="${minPage > 10 }">
					<button type = "submit" value ="${minPage-10}" name ="minPage" class="btn">이전목록</button>
				</c:if>				
			<!-- 이전 부분 -->
				<c:if test="${currentPage>1}">
			 		<button type = "submit" value ="${currentPage-1}" name = "currentPage" class="btn" >이전</button>
				</c:if>
			<!-- 목록 사이 번호 표시 -->
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
				<!-- 다음 부분 -->
				<c:if test="${currentPage< lastPage }">
			 		<button type = "submit" value ="${currentPage+1}" name = "currentPage" class="btn" >다음</button>
				</c:if>
				<!-- 다음목록 표시 -->
				<c:if test="${minPage+10<= lastPage }">
			 		<button type = "submit" value ="${minPage+10 }" name = "minPage" class="btn">다음목록</button>
				</c:if>
			 </div>
		</div>
	      <!-- 페이징 부분 끝 -->
	      <!-- poolvillaList 부분 끝 -->
	      <div class = "col-md-4">
                <!-- 예약날짜 부분 -->
                	오늘 날짜:<input type="date" class="form-control" id="today" name="reservationToday" value="${applicationToday}" readonly="readonly">
    	            체크인 :<input type="date" class="form-control" id="checkIn" name="reservationBeginDate" value="${reservationBeginDate}">
	                체크 아웃 :<input type="date" class="form-control" id="checkOut" name="reservationLastDate" value="${reservationLastDate}">
                 <!-- 지역검색부분 -->
                	지역선택 : 
                    <select name="locationNo" class="form-control" onchange ="this.form.submit()">
                     	<c:forEach var="m" items="${locationList}">
                     		<c:if test="${m.locationNo==locationNo}">
                     			<option value="${m.locationNo}" selected>${m.locationName}</option><!-- 기존 지역 검색 정보 selected -->
                     		</c:if>
                     			<option value="${m.locationNo}">${m.locationName}</option>
                     	</c:forEach>
                     </select>
	      <!-- 상세검색 부분 시작 -->
	      		<h2>상세검색</h2>
                <!-- 부대시설 검색 부분 -->
             		<fieldset>
					<legend>부대시설</legend>
					<div class="row">
               	    	<c:forEach var="m" items="${facilityList}">
               	    	<div class= "col-md-6">
	               	    	<label class="checkbox-inline">
	               	    		<input type="checkbox" name ="checkedFacilityNo" value="${m.facilityNo}" <c:forEach var="ch" items="${checkedFacilityList}"><c:if test="${ch == m.facilityNo}">checked</c:if></c:forEach>>${m.facilityName}
	               	    	</label>
               	    	</div>
                   		</c:forEach>
					</div>
					</fieldset>
                  <button type="button" id="search" class="btn btn-success"  style="margin-top: 20px"><i class="icon-magnifying-glass t2"></i>상세검색</button>
	      		
	      		<!-- <div class="floating"> 
	      			<div> 비교하기 </div> 
	      			<br>
	      			<div>
	      			<c:forEach var="m" items="${sessionComparePvNoList}">
	      				<table>
	      					<tr>
	      						<c:forEach var="m" items="${poolvillaList}">
	      						<td>${ m.pvName }</td>
	      						</c:forEach>
	      					</tr>
	      					<tr>
	      						<td>${ m }</td>
	      					</tr>
	      				</table>
	      				<br>
      				</c:forEach>
	      			</div>
	      			<br>
	      			<a href="${pageContext.request.contextPath}/all/comparePoolvillaController" class = "btn btn-primary float-right btn-sm">compare</a>
	      		</div>  -->
	      </div>
	      <!-- 상세검색부분 끝 -->
	    </div>
	  </section>
	</form>
	<!-- poolvillaList와 상세검색기능 부분 끝-->
  


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
      	//유효성검사
      	$('#search').click(function(){
    		if($('#checkIn').val() == ''){
    			$('#searchHelper').text('체크인 날짜를 선택해주세요');
    			$('#checkIn').focus();
    		}else if($('#checkIn').val() <= $('#today').val() ){
    			$('#searchHelper').text('과거날짜 및 오늘날짜는 체크인 날짜로 지정할 수 없습니다.');
    			$('#checkIn').focus();
    		}else if($('#checkOut').val() == ''){
    			$('#searchHelper').text('체크아웃 날짜를 선택해주세요');
    			$('#checkOut').focus();
    		}else if($('#checkIn').val() >= $('#checkOut').val() ){
    			$('#searchHelper').text('체크아웃 날짜는 체크인 날짜이후여야합니다');
    			$('#checkOut').focus();
    		}else if($('#locationNo').val() == '-1') {
    			$('#searchHelper').text('지역을 선택해주세요');
    			$('#locationNo').focus();
    		} else {
    			$('#searchForm').submit();
    		}
    	});
  </script>
  
  <script src="js/scripts.min.js"></script>
  <script src="js/main.min.js"></script>
  <script src="js/custom.js"></script>
  
</html>