<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Orange-poolvilla:insertCustomer</title>
    <meta name="description" content="Free Bootstrap Theme by uicookies.com">
    <meta name="keywords" content="free website templates, free bootstrap themes, free template, free bootstrap, free website template">
    
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/template/css/styles-merged.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/template/css/style.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/template/css/custom.css">
	
	<style>
	    .helper {
	    	color : #FF0000;
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
                <a href="${pageContext.request.contextPath}/all/homeController">Home</a><span>Sign up</span>
              </div>
              <h1>Sign up</h1>
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
		<!-- 아이디 중복 체크 폼 -->
		<c:if test="${empty customerId}">
			사용하실 ID를 입력해 주세요 :
			<form method="get" action="${pageContext.request.contextPath}/all/insertCustomerController" id="checkIdForm">
				<input type="text" name="checkId" id="checkId">
				<button type ="button" id="check" class="btn btn-outline-secondary btn-sm" >중복 확인</button>
				<span id="checkIdHelper" class="helper"></span>
				<span class="helper">${msg}</span>
			</form>
		</c:if>
		<c:if test="${not empty customerId }">
			<form method="post" action="${pageContext.request.contextPath}/all/insertCustomerController" id="insertForm">
				<table class="table table-bordered">
					<tr>
						<td>ID</td>
						<td>
							<input type="text" name="customerId" id="customerId" value ="${customerId}" readonly="readonly" >
						</td>
					</tr>
					<tr>
						<td>PW</td>
						<td>
							<input type="password" name="customerPw1" id="customerPw1">
							&nbsp; 
							<span id="customerPwHelper1" class="helper"></span>
						</td>
					</tr>
					<tr>
						<td>PW Check</td>
						<td>
							<input type="password" name="customerPw2" id="customerPw2">
							&nbsp; 
							<span id="customerPwHelper2" class="helper"></span>
						</td>
					</tr>
					<tr>
						<td>name</td>
						<td>
							<input type="text" name="name" id="name"> 
							&nbsp; 
							<span id="nameHelper" class="helper"></span>
						</td>
					</tr>
					<tr>
						<td>gender</td>
						<td>
							<input type="radio" value="M" name="gender" class="gender">M 
							&nbsp; 
							<input type="radio" value="F" name="gender" class="gender">F 
							&nbsp; 
							<span id="genderHelper" class="helper"></span>
						</td>
					</tr>
					<tr>
						<td>birth</td>
						<td>
							<input type="date" name="birth" id="birth"> 
							&nbsp; 
							<span id="birthHelper" class="helper"></span>
						</td>
					</tr>
					<tr>
						<td>email</td>
						<td>
							<input type="text" id="emailId" name="emailId"> @
							<select id="emailUrl" name="emailUrl">
								<!-- 필수 -->
									<option value="">::선택::</option>
									<option value="naver.com">naver.com</option>
									<option value="daum.net">daum.net</option>
									<option value="gmail.com">gmail.com</option>
								</select> 
								&nbsp; 
								<span id="emailHelper" class="helper"></span>
							</td>
						</tr>
						<tr>
							<td>phone</td>
							<td>
								<input type="text" id="phone" name="phone"> 
								&nbsp; 
								<span id="phoneHelper" class="helper"></span>
							</td>
						</tr>
					</table>
					
					<!-- 폼 text, radio, checkbox 공백이 있는지 체크 -->
					<button type = "button" id = "signup" class="btn btn-3 mt-15">sign up</button>
					<a href="${pageContext.request.contextPath}/all/insertCustomerController" type = "button" class="btn btn-3 mt-15">reset</button>
					<a href="${pageContext.request.contextPath}/loginController" type="button" class="btn btn-outline-secondary float-right">cancle</a>
		</form>
		</c:if>
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
        
        //아이디 체크 부분
    	$('#checkId').focus();
    	
    	$('#checkId').blur(function() {
    		if($('#checkId').val().length < 4) {
    			$('#checkIdHelper').text('id는 4자 이상 입력해주세요');
    			$('#checkId').focus();
    		} else {
    			$('#checkIdHelper').text('');
    		}
    	});
    	$('#check').click(function() {
    		if($('#checkId').val()==''){
    			$('#checkIdHelper').text('id는 4자 이상 입력해주세요');
    			$('#checkId').focus();
    		}else{
    			$('#checkIdForm').submit();
    		}
    	});    	
    	
    	
    	$('#customerPw2').blur(function() {
    		if($('#customerPw1').val().length < 4) {
    			$('#customerPwHelper2').text('');
    			$('#customerPwHelper1').text('pw는 4자 이상 입력해주세요');
    			$('#customerPw1').focus();
    		} else if($('#customerPw1').val() != $('#customerPw2').val()) {
    			$('#customerPwHelper1').text('');
    			$('#customerPwHelper2').text('pw가 일치하지 않습니다');
    			$('#customerPw1').focus();
    		} else {
    			$('#customerPwHelper2').text('');
    		}
    	});
    	
    	$('#name').blur(function(){
    		if($('#name').val() == '') {
    			$('#nameHelper').text('name을 입력하세요');
    			$('#name').focus();
    		} else {
    			$('#nameHelper').text('');
    		}
    	});
    	
    	$('#phone').blur(function() {
    		if($('#phone').val() == '') {
    			$('#phoneHelper').text('phone number를 입력하세요');
    			$('#phone').focus();
    		} else {
    			$('#phoneHelper').text('');
    		}
    	});
    	
    	$('#birth').blur(function() {
    		if($('#birth').val() == '') {
    			$('#birthHelper').text('birth을 입력하세요');
    			$('#birth').focus();
    		} else {
    			$('#birthHelper').text('');
    		}
    	});
    	
    	$('#signup').click(function() {
    		if($('#customerId').val()==''){
    			$('#checkIdHelper').text('id 중복체크를 해주세요');
    			$('#checkId').focus();
    		}else if($('#customerPw1').val() == '') {
    			$('#checkIdHelper').text('');
    			$('#customerPwHelper1').text('pw는 4자 이상 입력해주세요');
    			$('#customerPw1').focus();
    		} else if($('#customerPw1').val() != $('#customerPw2').val()) {
    			$('#customerPwHelper1').text('');
    			$('#customerPwHelper2').text('pw가 일치하지 않습니다.');
    			$('#customerPw2').focus();
    		} else if($('#name').val() == '') {
    			$('#customerPwHelper2').text('');
    			
    			$('#idHelper').text('id는 4자 이상 입력해주세요');
    			$('#name').focus();
    		} else if($('.gender:checked').length == 0) {
    			$('#customerPwHelper').text('');
    			
    			$('#genderHelper').text('gender를 선택하세요');
    			$('.gender').focus(); // ?
    		} else if($('#birth').val() == '') {
    			$('#genderHelper').text('');
    			
    			$('#birthHelper').text('birth를 입력하세요');
    			$('.gender').focus();
    		} else if($('#emailId').val() == '' || $('#emailUrl').val() == '') {
    			$('#birthtHelper').text('');
    			
    			$('#emailHelper').text('email을 입력하세요');
    			$('#emailId').focus();
    		} else if($('#phone').val() == '') {
    			$('#emailHelper').text('');
    			
    			$('#phoneHelper').text('phone number를 입력하세요');
    			$('#phone').focus();
    		} else {
    			$('#insertForm').submit();
    		}
    	});
  </script>
  
  <script src="${pageContext.request.contextPath}/template/js/scripts.min.js"></script>
  <script src="${pageContext.request.contextPath}/template/js/main.min.js"></script>
  <script src="${pageContext.request.contextPath}/template/js/custom.js"></script>
  
</html>