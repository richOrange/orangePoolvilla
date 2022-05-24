<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page import = "java.io.File" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Orange-poolvilla:insertPoolvillaRoom</title>
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
              <h1>Insert Poolvilla Room</h1>
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
    
    		<h2>Room Info List</h2>
    		<br>
    		<table class = "table table-hover text text-center">
			<thead>
				<tr>
					<th class="text text-center">방 이름</th>
					<th class="text text-center">방 유형</th>
					<th class="text text-center">방 설명</th>
					<th class="text text-center">방 크기</th>
					<th class="text text-center">침대</th>
					<th class="text text-center">사진</th>
					<th class="text text-center">&nbsp;</th>
					
					
					<th>&nbsp;</th>
				</tr>
			</thead>
			<tbody>
				<tr>
				<c:if test="${ not empty prbList }">
					<c:forEach var = "prb" items = "${ prbList }" varStatus = "status">
							<tr>
								<td>${ prb.roomName }</td>
								<td>${ prb.roomType }</td>
								<td>${ prb.roomInfo }</td>
								<td>${ prb.roomSize }</td>
								<td>${prb.bed}</td>
   								<form action="${pageContext.request.contextPath}/host/insertRoomPhotoController" method="post" enctype="multipart/form-data">
									<input type = "hidden" name = "pvNo" value = "${ pvNo }" readonly>
									<input type = "hidden" name = "roomNo" value = "${ prb.roomNo }" readonly>
									<c:if test="${empty prb.photoName }">
									<td><input type="file" placeholder="search images" name="roomPhoto" ></td>
									<td><button type = "submit" class = "btn btn-primary btn-sm" id="insertRoomPhotoButton">insert</button></td>
									</c:if>
									<c:if test="${not empty prb.photoName }">
									<td><img src="${pageContext.request.contextPath}/image/room/${prb.photoName}" width="100" height="100" alt=""></td>
									</c:if>
									<td><a href="${pageContext.request.contextPath}/host/deletePoolvillaRoomNBedController?pvNo=${ pvNo }&roomNo=${ prb.roomNo }" class = "btn btn-outline-secondary btn-sm">삭제</a></td>
							</form>
							</tr>
					</c:forEach>
				</c:if>
				</tr>
			</tbody>
		</table>
		
		<hr>
      
		<h2>Enter the room info</h2>
		<br>
        <!-- Form -->
        <form action="${pageContext.request.contextPath}/host/insertPoolvillaRoomNBedController" method="post" class="probootstrap-form mb60">
            <div class="row">
            <div class="form-group">
            <input type = "hidden" name = "pvNo" value = "${ pvNo }" readonly>
              <input type="text" name="roomName" class="form-control" placeholder="Please enter the room name">
              <select name="roomType" class="form-control">
               	<option value="-1">:: room type ::</option>
                    <option value="침대방">침대방</option>
                    <option value="온돌방">온돌방</option>
                    <option value="놀이방">놀이방</option>
              </select>
              <input type="text" name="roomInfo" class="form-control" placeholder="Please enter the room info">
              <input type="text" name="roomSize" class="form-control" placeholder="Please enter the room size">
            </div>
            <div class="form-group">
              <input type="submit" class="btn btn-primary btn-sm" id="submit" name="submit" value="추가">
            </div>
          </form>
		</table>
		<hr>
		
		<h2>Enter the room bed</h2>
		<br>
        <!-- Form -->
        <table>
        
        <form action="${pageContext.request.contextPath}/host/insertRoomNBedController" method="post" class="probootstrap-form mb60">
        	<div class="row">
           		<div class="form-group">
            		<input type = "hidden" name = "pvNo" value = "${ pvNo }" readonly>
              			<select name="roomNo" class="form-control">
               			<option value="-1">:: room ::</option>
                 			<c:forEach var ="r" items="${ roomNameList }">
                    			<option value="${ r.roomNo }">${ r.roomName }</option>
                 		</c:forEach>
              			</select>
              		<select name="bedSize" class="form-control">
	               		<option value="-1">:: bed Size ::</option>
	                    <option value="싱글">싱글</option>
	                    <option value="슈퍼싱글">슈퍼싱글</option>
	                    <option value="더블">더블</option>
	                    <option value="퀸">퀸</option>
	                    <option value="킹">킹</option>
                    	<option value="라지킹">라지킹</option>
              		</select>
              <input type="text" name="bedCnt" class="form-control" placeholder="Please enter the bed count">
            </div>
		<div class="form-group">
              <input type="submit" class="btn btn-primary btn-sm" id="submit" name="submit" value="추가">
        </div>
            </div>
        </form>
		</table>
		
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