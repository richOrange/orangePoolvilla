<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Orange-poolvilla</title>
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
              <h1>Cooking Tool List</h1>
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
					<th>Cooking Tool Name</th>
					<th>Update Date</th>
					<th>&nbsp;</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<c:forEach var = "ck" items = "${ list }">
							<tr>
								<td>${ ck.cookingToolNo }</td>
								<td>${ ck.cookingToolName }</td>
								<td>${ ck.updateDate }</td> 
								<td><a href="${pageContext.request.contextPath}/host/deleteCookingToolController?cookingToolNo=${ ck.cookingToolNo }" class = "btn btn-outline-secondary btn-sm">삭제</a></td>
							</tr>
					</c:forEach>
				</tr>
			</tbody>
		</table>
		<hr>
		<h2>Enter</h2>
		<br>
        <!-- Form -->
        <form action="${pageContext.request.contextPath}/host/cookingToolController" method="post" class="probootstrap-form mb60">
            <div class="row">
            <div class="form-group">
              <input type="text" name="cookingToolName" class="form-control" placeholder="Please enter the cooking tool">
            </div>
            <div class="form-group">
              <input type="submit" class="btn btn-primary" id="submit" name="submit" value="Save">
            </div>
          </form>

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