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
	<section class="probootstrap-slider flexslider2 page-inner">
	    <div class="overlay"></div>
	    <div class="probootstrap-wrap-banner">
	      <div class="container">
	        <div class="row">
	          <div class="col-md-8">
	
	            <div class="page-title probootstrap-animate">
	              <div class="probootstrap-breadcrumbs">
	              </div>
	              <h1>Login</h1>
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
	<section class="probootstrap-section">
		<div class="container">
			<div class="row">
        		<div class="col-md-8">
          			<form action="${pageContext.request.contextPath}/all/loginController" id="loginForm" method="post" class="probootstrap-form mb60">
						<div class="row">
							<div class="col-12">
								<table class="table table-hover">
									<tr>
										<td>ID</td>
										<td><input type="text" name="memberId" id ="memberId"></td>
									</tr>
									<tr>
										<td>PW</td>
										<td><input type="password" name="memberPw" id="memberPw"></td>
									</tr>
									<tr>
										<td colspan="2">
											<input type="checkbox" name ="hostLogin" id="hostLogin" value="check"'>관리자 로그인</checkbox>
										</td>
									</tr>
									<tr>
										<td colspan="2">
											<button type="button" id="login" class="btn btn-primary">로그인</button>
											<a href="${pageContext.request.contextPath}/all/insertCustomerController" class="btn roberto-btn btn-3 mt-15">회원가입</a>
										</td>
									</tr>
								</table>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</section>
</body>

<script>
	//include
	$("#includeHeader").load('${pageContext.request.contextPath}/includeHeaderController');
  	$("#includeFooter").load('${pageContext.request.contextPath}/includeFooterController');
  	//유효성검사
  	$('#login').click(function(){
		if($('#memberId').val() == ''){
			$('#memberIdHelper').text('아이디를 입력해주세요');
			$('#memberId').focus();
			
		}else if($('#memberPw').val() == ''){
			$('#memberIdHelper').text('');
			$('#memberPwHelper').text('비밀번호를 입력해주세요');
			$('#memberPw').focus();
		} else {
			$('#loginForm').submit();
		}
	});
  
</script>
  
  <script src="${pageContext.request.contextPath}/template/js/scripts.min.js"></script>
  <script src="${pageContext.request.contextPath}/template/js/main.min.js"></script>
  <script src="${pageContext.request.contextPath}/template/js/custom.js"></script>
  
</html>