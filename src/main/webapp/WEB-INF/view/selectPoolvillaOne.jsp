<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Orange-poolvilla:selectPoolvillaOne</title>
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
              <h1>${selectPoolvillaOne.pvName}</h1>
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
      <div class="row heading">
        <div class="col-md-12"><h2 class="mt0 mb50 text-center">${selectPoolvillaOne.pvName}</h2></div>
      </div>
      <div class="row">
        <div class="col-md-12">
          <p><img src="${pageContext.request.contextPath}/template/img/slider_5.jpg" class="img-responsive" alt="Free Bootstrap Template by uicookies.com"></p>
        </div>
      </div>
      <!-- 예약 기능 -->
      <div class="row">
      <form method="get" id="reservationForm" action="${pageContext.request.contextPath}/insertReservationController">
      <!-- 고객 정보 -->
      <c:if test="${not empty sessionLoginMember}">
      	<input type ="hidden" id="memberId" name ="memberId" value = "${sessionLoginMember.memberId}" readonly="readonly">
      </c:if>
      <!-- 방정보 -->
      	<input type ="hidden" id="pvNo" name ="pvNo" value = "${selectPoolvillaOne.pvNo}" readonly="readonly" >
      <!-- 예약날짜 정보 -->
      	체크인 :<input type="date" class="form-control" id="checkIn" name="reservationBeginDate" value="${reservationBeginDate}">
		체크 아웃 :<input type="date" class="form-control" id="checkOut" name="reservationLastDate" value="${reservationLastDate}">
		<button type ="button" id = "reservation" class = "btn">예약</button>
		<div id ="reservationHelper"></div>
      </form>
      
      </div>
    </div>
  </section>

  <section class="probootstrap-section probootstrap-section-lighter">
    <div class="container">
      <div class="row heading">
        <h2 class="mt0 mb50 text-center">방정보</h2>
      </div>
      <div class="row">
        <div class="col-md-3 col-sm-6">
          <div class="probootstrap-card probootstrap-person text-left">
            <div class="probootstrap-card-media">
              <img src="${pageContext.request.contextPath}/template/img/person_1.jpg" class="img-responsive" alt="Free HTML5 Template by uicookies.com">
            </div>
            <div class="probootstrap-card-text">
              <h2 class="probootstrap-card-heading mb0">Jeremy Slater</h2>
              <p><small>Real Estate Brooker</small></p>
              <p><a href="${pageContext.request.contextPath}/template/#">View Details</a></p>
            </div>
          </div>
        </div>
        <div class="col-md-3 col-sm-6">
          <div class="probootstrap-card probootstrap-person text-left">
            <div class="probootstrap-card-media">
              <img src="${pageContext.request.contextPath}/template/img/person_2.jpg" class="img-responsive" alt="Free HTML5 Template by uicookies.com">
            </div>
            <div class="probootstrap-card-text">
              <h2 class="probootstrap-card-heading mb0">James Butterly</h2>
              <p><small>Buying Agent</small></p>
              <p><a href="${pageContext.request.contextPath}/template/#">View Details</a></p>
            </div>
          </div>
        </div>
        <div class="col-md-3 col-sm-6">
          <div class="probootstrap-card probootstrap-person text-left">
            <div class="probootstrap-card-media">
              <img src="${pageContext.request.contextPath}/template/img/person_3.jpg" class="img-responsive" alt="Free HTML5 Template by uicookies.com">
            </div>
            <div class="probootstrap-card-text">
              <h2 class="probootstrap-card-heading mb0">James Smith</h2>
              <p><small>Real Estate Brooker</small></p>
              <p><a href="${pageContext.request.contextPath}/template/#">View Details</a></p>
            </div>
          </div>
        </div>
        <div class="col-md-3 col-sm-6">
          <div class="probootstrap-card probootstrap-person text-left">
            <div class="probootstrap-card-media">
              <img src="${pageContext.request.contextPath}/template/img/person_4.jpg" class="img-responsive" alt="Free HTML5 Template by uicookies.com">
            </div>
            <div class="probootstrap-card-text">
              <h2 class="probootstrap-card-heading mb0">Chris White</h2>
              <p><small>Selling Agent</small></p>
              <p><a href="${pageContext.request.contextPath}/template/#">View Details</a></p>
            </div>
          </div>
        </div>

        <div class="col-md-3 col-sm-6">
          <div class="probootstrap-card probootstrap-person text-left">
            <div class="probootstrap-card-media">
              <img src="${pageContext.request.contextPath}/template/img/person_3.jpg" class="img-responsive" alt="Free HTML5 Template by uicookies.com">
            </div>
            <div class="probootstrap-card-text">
              <h2 class="probootstrap-card-heading mb0">James Smith</h2>
              <p><small>Real Estate Brooker</small></p>
              <p><a href="${pageContext.request.contextPath}/template/#">View Details</a></p>
            </div>
          </div>
        </div>
        <div class="col-md-3 col-sm-6">
          <div class="probootstrap-card probootstrap-person text-left">
            <div class="probootstrap-card-media">
              <img src="${pageContext.request.contextPath}/template/img/person_4.jpg" class="img-responsive" alt="Free HTML5 Template by uicookies.com">
            </div>
            <div class="probootstrap-card-text">
              <h2 class="probootstrap-card-heading mb0">Chris White</h2>
              <p><small>Selling Agent</small></p>
              <p><a href="${pageContext.request.contextPath}/template/#">View Details</a></p>
            </div>
          </div>
        </div>
        <div class="col-md-3 col-sm-6">
          <div class="probootstrap-card probootstrap-person text-left">
            <div class="probootstrap-card-media">
              <img src="${pageContext.request.contextPath}/template/img/person_1.jpg" class="img-responsive" alt="Free HTML5 Template by uicookies.com">
            </div>
            <div class="probootstrap-card-text">
              <h2 class="probootstrap-card-heading mb0">Jeremy Slater</h2>
              <p><small>Real Estate Brooker</small></p>
              <p><a href="${pageContext.request.contextPath}/template/#">View Details</a></p>
            </div>
          </div>
        </div>
        <div class="col-md-3 col-sm-6">
          <div class="probootstrap-card probootstrap-person text-left">
            <div class="probootstrap-card-media">
              <img src="${pageContext.request.contextPath}/template/img/person_2.jpg" class="img-responsive" alt="Free HTML5 Template by uicookies.com">
            </div>
            <div class="probootstrap-card-text">
              <h2 class="probootstrap-card-heading mb0">James Butterly</h2>
              <p><small>Buying Agent</small></p>
              <p><a href="${pageContext.request.contextPath}/template/#">View Details</a></p>
            </div>
          </div>
        </div>

		<table class = "table table-hover">
			<thead>
				<tr>
					<th>pv_no</th>
					<th>Number</th>
					<th>Cooking Tool Name</th>
					<th>Update Date</th>
					<th>cookingToolCnt</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<c:forEach var = "ck" items = "${ poolvillaCookingToolList }">
							<tr>
								<td>${ ck.pvNo }</td>
								<td>${ ck.cookingToolNo }</td>
								<td>${ ck.cookingToolName }</td>
								<td>${ ck.updateDate }</td> 
								<td>${ ck.cookingToolCnt }</td>
							</tr>
					</c:forEach>
				</tr>
			</tbody>
		</table>
		
		<table class = "table table-hover">
			<thead>
				<tr>
					<th>pv_no</th>
					<th>ott no</th>
					<th>ott Name</th>
					<th>Update Date</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<c:forEach var = "po" items = "${ poolvillaOttList }">
							<tr>
								<td>${ po.pvNo }</td>
								<td>${ po.ottNo }</td>
								<td>${ po.ottName }</td>
								<td>${ po.updateDate }</td> 
							</tr>
					</c:forEach>
				</tr>
			</tbody>
		</table>
		
		<table class = "table table-hover">
			<thead>
				<tr>
					<th>pv_no</th>
					<th>supplies no</th>
					<th>supplies Name</th>
					<th>supplies cnt</th>
					<th>Update Date</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<c:forEach var = "ps" items = "${ poolvillaSuppliesList }">
							<tr>
								<td>${ ps.pvNo }</td>
								<td>${ ps.suppliesNo }</td>
								<td>${ ps.suppliesName }</td>
								<td>${ ps.suppliesCnt }</td>
								<td>${ ps.updateDate }</td> 
							</tr>
					</c:forEach>
				</tr>
			</tbody>
		</table>
		
		<table class = "table table-hover">
			<thead>
				<tr>
					<th>pv_no</th>
					<th>bed no</th>
					<th>room no</th>
					<th>bed size</th>
					<th>bed cnt</th>
					<th>updateDateRB</th>
					<th>roomType</th>
					<th>roomName</th>
					<th>roomInfo</th>
					<th>roomSize</th>
					<th>Update Date PR</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<c:forEach var = "prb" items = "${ poolvillaRoomNBedList }">
							<tr>
								<td>${ prb.pvNo }</td>
								<td>${ prb.bedNo }</td>
								<td>${ prb.roomNo }</td>
								<td>${ prb.bedSize }</td>
								<td>${ prb.bedCnt }</td>
								<td>${ prb.updateDateRB }</td>
								<td>${ prb.roomType }</td>
								<td>${ prb.roomName }</td>
								<td>${ prb.roomInfo }</td>
								<td>${ prb.roomSize }</td>
								<td>${ prb.updateDatePR }</td>
							</tr>
					</c:forEach>
				</tr>
			</tbody>
		</table>
		<table class = "table table-hover">
					<thead>
						<tr>
							<th>pvNo</th>
							<th>facilityNo</th>
							<th>updateDate</th>
							<th>facilityCnt</th>
							<th>facilityName</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<c:forEach var="list" items="${selectPoolvillaFacilityListByPvNo}">
								<tr>
									<td>${list.pvNo }</td>
									<td>${list.facilityNo }</td>
									<td>${list.updateDate }</td>
									<td>${list.facilityCnt }</td>
									<td>${list.facilityName }</td>
								</tr>
							</c:forEach>
						</tr>
					</tbody>
				</table>
				<table class = "table table-hover">
					<thead>
						<tr>
							<th>poolNo</th>
							<th>pvNo</th>
							<th>poolName</th>
							<th>poolWidth</th>
							<th>poolLength</th>
							<th>depth</th>
							<th>hotWater</th>
							<th>indoorOutdoor</th>
							<th>updateDate</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<c:forEach var="list" items="${selectPoolvillaPoolListByPvNo}">
								<tr>
									<td>${list.poolNo }</td>
									<td>${list.pvNo }</td>
									<td>${list.poolName }</td>
									<td>${list.poolWidth }</td>
									<td>${list.poolLength }</td>
									<td>${list.depth }</td>
									<td>${list.hotWater }</td>
									<td>${list.indoorOutdoor }</td>
									<td>${list.updateDate }</td>
								</tr>
							</c:forEach>
						</tr>
					</tbody>
				</table>

      </div>
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
      //유효성검사
      console.log(document.querySelector('#customerId').value, '<-#customerId');
      	$('#reservation').click(function(){
     		 //로그인정보가 없으면 로그인 페이지로 이동
    		if($('#customerId').val() == ''){
    			location.href='${pageContext.request.contextPath}/all/loginController';
    		//상품 정보가 없으면 home으로 이동
    		}else if($('#pvNo').val() == ''){
    			location.href='${pageContext.request.contextPath}/all/homeController';
    		//체크인정보가 없는 경우 체크인 입력으로 커서 이동
    		}else if($('#checkIn').val() == ''){
    			$('#reservationHelper').text('체크인 날짜를 선택해주세요');
    			$('#checkIn').focus();
    		//체크아웃 정보가 없는경우 체크아웃 입력으로 커서 이동
    		}else if($('#checkOut').val() == ''){
    			$('#reservationHelper').text('체크아웃 날짜를 선택해주세요');
    			$('#checkOut').focus();
    		//유효성 검사 끝 submit
    		}else{
    			$('#reservationForm').submit();
    		}
    	});
  </script>
  
  <script src="${pageContext.request.contextPath}/template/js/scripts.min.js"></script>
  <script src="${pageContext.request.contextPath}/template/js/main.min.js"></script>
  <script src="${pageContext.request.contextPath}/template/js/custom.js"></script>
  
</html>