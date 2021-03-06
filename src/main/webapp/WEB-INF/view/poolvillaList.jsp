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
              </div>
              <h1>PoolvillaList</h1>
            </div>

          </div>
        </div>
      </div>
    </div>
    <ul class="slides">
      <li style="background-image: url(${pageContext.request.contextPath}/template/img/slider_4.jpg);"></li>
    </ul>
  </section>
  <!-- END: slider  -->
  
	<!-- poolvillaList??? ?????????????????? ?????? ??????-->
	<form method="post" id="searchForm" action="${pageContext.request.contextPath}//all/poolvillaListController">
	  <section class="probootstrap-section probootstrap-section-lighter">
	    <div class="container">
	    	<!-- poolvillaList ??????  ??????-->
	    	<div class ="col-md-8">
	    		<div class="row">
	  				<c:forEach var="m" items="${poolvillaList}" varStatus="status">
	    				<div class="col-md-6">
	   					<div class="probootstrap-card probootstrap-listing">
	        		<!-- ?????? img ?????? -->
	        			<div class="probootstrap-card-media">
	        			<c:if test="${ not empty m.photoName }">
	        				<img src="${pageContext.request.contextPath}/image/poolvilla/${m.photoName}" class="img-responsive">
	        			</c:if>
	          				<a href="${pageContext.request.contextPath}/customer/insertWishListController?pvNo=${m.pvNo}&customerId=${sessionLoginMember.memberId}" class="probootstrap-love"><i class="icon-heart"></i></a>
	        			</div>
	        		<!-- ????????? ??? ?????????????????? -->
	        			<div class="probootstrap-card-text"> 
	          				<h2 class="probootstrap-card-heading"><a href="${pageContext.request.contextPath}/all/selectPoolvillaOneController?pvNo=${m.pvNo}&reservationBeginDate=${reservationBeginDate}&reservationLastDate=${reservationLastDate}">${m.pvName}</a></h2>
	          				<div class="probootstrap-listing-location">
	            				<i class="icon-location2"></i> <span>${m.address}</span>
	          				</div>
	          				<div class="probootstrap-listing-category for-sale">
	          					<span>????????????</span>
	          					<!-- <span class="btn"><input type="submit" name="compare" value="${m.pvNo}">????????????</span>  -->
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
					              ?????????
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
   				<c:if test="${status.count%2== 0}">
   					</div><div class="row">
   				</c:if>
	    	</c:forEach>
	      </div>
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
	      <!-- poolvillaList ?????? ??? -->
	      <div class = "col-md-4">
                <!-- ???????????? ?????? -->
                	?????? ??????:<input type="date" class="form-control" id="today" name="reservationToday" value="${applicationToday}" readonly="readonly">
    	            ????????? :<input type="date" class="form-control" id="checkIn" name="reservationBeginDate" value="${reservationBeginDate}">
	                ?????? ?????? :<input type="date" class="form-control" id="checkOut" name="reservationLastDate" value="${reservationLastDate}">
                 <!-- ?????????????????? -->
                	???????????? : 
                    <select name="locationNo" class="form-control" onchange ="this.form.submit()">
                     			<option value="-1">:::????????? ??????????????????:::</option>
                     	<c:forEach var="m" items="${locationList}">
                     		<c:if test="${m.locationNo==locationNo}">
                     			<option value="${m.locationNo}" selected>${m.locationName}</option><!-- ?????? ?????? ?????? ?????? selected -->
                     		</c:if>
                     			<option value="${m.locationNo}">${m.locationName}</option>
                     	</c:forEach>
                     </select>
	      <!-- ???????????? ?????? ?????? -->
	      		<h2>????????????</h2>
                <!-- ???????????? ?????? ?????? -->
             		<fieldset>
					<legend>????????????</legend>
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
                  <button type="button" id="search" class="btn btn-success"  style="margin-top: 20px"><i class="icon-magnifying-glass t2"></i>????????????</button>
	      		
	      		<!-- <div class="floating"> 
	      			<div> ???????????? </div> 
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
	      <!-- ?????????????????? ??? -->
	    </div>
	  </section>
	</form>
	<!-- poolvillaList??? ?????????????????? ?????? ???-->
  


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
      	//???????????????
      	$('#search').click(function(){
    		if($('#checkIn').val() == ''){
    			$('#searchHelper').text('????????? ????????? ??????????????????');
    			$('#checkIn').focus();
    		}else if($('#checkIn').val() <= $('#today').val() ){
    			$('#searchHelper').text('???????????? ??? ??????????????? ????????? ????????? ????????? ??? ????????????.');
    			$('#checkIn').focus();
    		}else if($('#checkOut').val() == ''){
    			$('#searchHelper').text('???????????? ????????? ??????????????????');
    			$('#checkOut').focus();
    		}else if($('#checkIn').val() >= $('#checkOut').val() ){
    			$('#searchHelper').text('???????????? ????????? ????????? ???????????????????????????');
    			$('#checkOut').focus();
    		}else if($('#locationNo').val() == '-1') {
    			$('#searchHelper').text('????????? ??????????????????');
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