<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Orange-poolvilla:home</title>
<meta name="description" content="Free Bootstrap Theme by uicookies.com">
<meta name="keywords"
	content="free website templates, free bootstrap themes, free template, free bootstrap, free website template">

<link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400"
	rel="stylesheet">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/template/css/styles-merged.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/template/css/style.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/template/css/custom.css">
	
<style>
.helper {
	color: #FF0000;
}
</style>

</head>

<!-- jquery -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

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
							<div class="probootstrap-breadcrumbs"></div>
							<h1>My Page</h1>
						</div>
					</div>
				</div>
			</div>
		</div>
		<ul class="slides">
			<li
				style="background-image: url(${pageContext.request.contextPath}/template/img/slider_1.jpg);"></li>
			<li
				style="background-image: url(${pageContext.request.contextPath}/template/img/slider_4.jpg);"></li>
			<li
				style="background-image: url(${pageContext.request.contextPath}/template/img/slider_2.jpg);"></li>
		</ul>
	</section>
	<!-- Form -->
	<section class="probootstrap-section">
		<div class="container">
			<div class="row">
				<div class="col-md-8">
					<form
						action="${pageContext.request.contextPath}/customer/updateCustomerController"
						method="post" id="updatecustomerForm">
						<div class="row">
							<div class="col-12">
								<table class="table table-hover">
									<tr>
										<td>ID :</td>
										<td>
											<input type="text" name="memberid" id="memberid"
											value="${myPageCustomer.customerId}" readonly>
										</td>
									</tr>
									<tr>
										<td>PW :</td>
										<td>
											<input type="password" name="customerPw1" id="customerPw1"> 
											
											<span id="customerPwHelper1" class="helper"></span>
										</td>
									</tr>
									<tr>
										<td>PW Check :</td>
										<td>
											<input type="password" name="customerPw2" id="customerPw2"> 
											
											<span id="customerPwHelper2" class="helper"></span>
										</td>
									</tr>
									<tr>
										<td>NAME :</td>
										<td>
											<input type="text" name="name" id="name">
											
											<span id="nameHelper" class="helper"></span>
										</td>
									</tr>
									<tr>
										<td>GENDER :</td>
										<td>
											<input type="radio" value="M" name="gender" class="gender">M 
											 
											<input type="radio" value="F" name="gender" class="gender">F 
											
											<span id="genderHelper" class="helper"></span>
										</td>
									</tr>
									<tr>
										<td>BIRTH DATE :</td>
										<td>
											<input type="date" name="birth" id="birth">
											
											<span id="birthHelper" class="helper"></span>
										</td>
									</tr>
									<tr>
										<td>EMAIL :</td>
										<td>
											<input type="text" id="emailId" name="emailId">
											@ <select id="emailUrl" name="emailUrl">
												<!-- 필수 -->
												<option value=''>::선택::</option>
												<option value="naver.com">naver.com</option>
												<option value="daum.net">daum.net</option>
												<option value="gmail.com">gmail.com</option>
											</select> 
											&nbsp; 
											<span id="emailHelper" class="helper"></span>
										</td>
									</tr>
									<tr>
										<td>PHONE :</td>
										<td>
											<input type="text" name="phone" id="phone">
											<span id="phoneHelper" class="helper"></span>
										</td>
									</tr>
									<tr>
										<td colspan="2">
											<button type="button" name="updateCustomer" id="updateCustomer"
												class="btn btn-primary">수정</button>
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
	$("#includeHeader").load(
			'${pageContext.request.contextPath}/includeHeaderController');
	$("#includeFooter").load(
			'${pageContext.request.contextPath}/includeFooterController');
	//유효성 검사
	
	
	$('#updateCustomer').click(function(){
		if($('#customerPw1').val() == '' || $('#customerPw1').val().length < 4) {
			$('#customerPwHelper1').text('pw는 4자 이상 입력해주세요');
			$('#customerPw1').focus();
			
		} else if($('#customerPw1').val() != $('#customerPw2').val()) {
			$('#customerPwHelper1').text('');
			$('#customerPwHelper2').text('pw가 일치하지 않습니다.');
			$('#customerPw2').focus();
			
		} else if($('#name').val() == '') {
			$('#customerPwHelper1').text('');
			$('#customerPwHelper2').text('');
			$('#nameHelper').text('이름을 입력해주세요');
			$('#name').focus();
			
		} else if($('.gender:checked').length == 0) {
			$('#nameHelper').text('');
			$('#genderHelper').text('성별을 선택해주세요');
			$('.gender').focus();
			
		} else if($('#birth').val() == '') {
			$('#nameHelper').text('');
			$('#genderHelper').text('');
			$('#birthHelper').text('생일을 입력해주세요');
			
		} else if($('#emailId').val() == '') {
			$('#birthHelper').text('');
			$('#emailHelper').text('이메일을 입력해주세요');
			$('#emailId').focus();
			
		} else if($('#phone').val() == '') {
			$('#emailHelper').text('');
			$('#phoneHelper').text('번호를 입력해주세요');
			$('#phone').focus();
			
		} else {
  			$('#updatecustomerForm').submit();
  		}
	});
</script>

<script
	src="${pageContext.request.contextPath}/template/js/scripts.min.js"></script>
<script src="${pageContext.request.contextPath}/template/js/main.min.js"></script>
<script src="${pageContext.request.contextPath}/template/js/custom.js"></script>

</html>