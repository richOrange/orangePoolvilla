<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>

    <meta name="description" content="">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Title -->
    <title>OrangePoolvilla:insertCustomer</title>

    <!-- Favicon -->
    <link rel="icon" href="${pageContext.request.contextPath}/template/img/core-img/favicon.ico">

    <!-- Stylesheet -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/template/style.css">
	
	<style>
	    .helper {
	    	color : #FF0000;
	    }
	</style>
	
</head>

	<!-- jquery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    
<body>
    <!-- Preloader -->
    <div id="preloader">
        <div class="loader"></div>
    </div>
    <!-- /Preloader -->

    <!-- Header Area Start -->
        <div id="includeHeader"> <!-- Insert your file here --></div>
	<!-- Header Area End -->

    <!-- Breadcrumb Area Start -->
    <div class="breadcrumb-area bg-img bg-overlay jarallax" style="background-image: url(${pageContext.request.contextPath}/template/img/bg-img/17.jpg);">
        <div class="container h-100">
            <div class="row h-100 align-items-center">
                <div class="col-12">
                    <div class="breadcrumb-content text-center">
                        <div class="breadcrumb-post-content">
                            <h2 class="post-title">Sign up</h2>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Breadcrumb Area End -->

    <!-- Blog Area Start -->
    <div class="roberto-news-area section-padding-100-0">
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-12 col-lg-8">
                
                    <!-- Blog Details Text -->
                    <div class="blog-details-text">
                        <!-- Blockquote -->
                        <blockquote class="roberto-blockquote d-flex">
                            <div class="text">
                                <h5>Sign up</h5>
                            </div>
                        </blockquote>
         
				        <form method="post" action="${pageContext.request.contextPath}/all/insertCustomerController" id="form">
						<div>
							<table class="table table-bordered">
								<tr>
									<td>ID</td>
									<td>
										<div>
										<input type="text" name="customerId" id="customerId">
										<a href="${pageContext.request.contextPath}/all/Controller?customerId=${ customerId }" class="btn btn-outline-secondary btn-sm">중복 확인</a>
										&nbsp; 
										<span id="customerIdHelper" class="helper"></span>
										</div>
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
		                    <button type = "button" id = "signup" class="btn roberto-btn btn-3 mt-15">sign up</button>
		                    <button type = "reset" class="btn roberto-btn btn-3 mt-15">reset</button>
		                    <a href="${pageContext.request.contextPath}/loginController" type="button" class="btn btn-outline-secondary float-right">cancle</a>
						</div>
						</form>
                    </div>
                </div>

</body>

<script>
        $("#includeHeader").load('${pageContext.request.contextPath}/includeController');
        
    	$('#customerId').focus();
    	
    	$('#customerId').blur(function() {
    		if($('#customerId').val().length < 4) {
    			$('#customerIdHelper').text('id는 4자 이상');
    			$('#customerId').focus();
    		} else {
    			$('#customerIdHelper').text('');
    		}
    	});
    	
    	$('#customerPw2').blur(function() {
    		if($('#customerPw1').val().length < 4) {
    			$('#customerPwHelper2').text('');
    			$('#customerPwHelper1').text('pw는 4자 이상');
    			$('#customerPw1').focus();
    		} else if($('#customerPw1').val() != $('#customerPw2').val()) {
    			$('#customerPwHelper1').text('');
    			$('#customerPwHelper2').text('pw가 일치하지 않습니다');
    			$('#customerPw2').focus();
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
    		if($('#customerId').val() == '') {
    			$('#idHelper').text('id는 4자 이상');
    			$('#customerId').focus();
    		} else if($('#customerPw1').val() == '') {
    			$('#customerIdHelper').text('');
    			
    			$('#customerPwHelper1').text('pw는 4자 이상');
    			$('#customerPw1').focus();
    		} else if($('#customerPw1').val() != $('#customerPw2').val()) {
    			$('#customerPwHelper1').text('');
    			
    			$('#customerPwHelper2').text('pw가 일치하지 않습니다.');
    			$('#customerPw2').focus();
    		} else if($('#name').val() == '') {
    			$('#customerPwHelper2').text('');
    			
    			$('#idHelper').text('id는 4자 이상');
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
    			$('#form').submit();
    		}
    	});
    	
</script>

    <!-- **** All JS Files ***** -->
    <!-- jQuery 2.2.4 -->
    <script src="${pageContext.request.contextPath}/template/js/jquery.min.js"></script>
    <!-- Popper -->
    <script src="${pageContext.request.contextPath}/template/js/popper.min.js"></script>
    <!-- Bootstrap -->
    <script src="${pageContext.request.contextPath}/template/js/bootstrap.min.js"></script>
    <!-- All Plugins -->
    <script src="${pageContext.request.contextPath}/template/js/roberto.bundle.js"></script>
    <!-- Active -->
    <script src="${pageContext.request.contextPath}/template/js/default-assets/active.js"></script>
    
</html>