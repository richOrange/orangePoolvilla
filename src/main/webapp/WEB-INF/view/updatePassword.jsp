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
						action="${pageContext.request.contextPath}/customer/updatePasswordController"
						method="post" id="updatePasswordForm">
						<div class="row">
							<div class="col-12">
								<!-- 이전비밀번호와 비밀번호 중복 오류 메세지 표시 부분 -->
								<c:if test="${param.msg=='duplication'}">
									<div class="helper">이전 비밀번호와 같게 변경하실 수 없습니다.</div>
								</c:if>
								<table class="table table-hover">
									<tr>
										<td>ID :</td>
										<td>
											<input type="text" name="memberid" id="memberid"
											value="${myPageCustomer.customerId}" readonly>
										</td>
									</tr>
									<tr>
										<td> 현재 PW :</td>
										<td><input type="password" name="customerPw"
											id="customerPw"> <span id="customerPwHelper"
											class="helper"></span></td>
									</tr>
									<tr>
										<td> 신규 PW :</td>
										<td><input type="password" name="customerPw1"
											id="customerPw1"> <span id="customerPwHelper1"
											class="helper"></span></td>
									</tr>
									<tr>
										<td> 신규 PW Check :</td>
										<td><input type="password" name="customerPw2"
											id="customerPw2"> <span id="customerPwHelper2"
											class="helper"></span></td>
											
										<td colspan="2">
											<button type="button" name="updatePassword" id="updatePassword"
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
	
	$('#updatePassword').click(function() {
				if ($('#customerPw').val() == '' 
						|| $('#customerPw').val().length < 4){
					$('#customerPwHelper').text('pw는 4자 이상 입력해주세요');
					$('#customerPw').focus();
				
				}else if($('#customerPw1').val() == ''
						|| $('#customerPw1').val().length < 4) {
					$('#customerPwHelper1').text('pw는 4자 이상 입력해주세요');
					$('#customerPw1').focus();

				} else if ($('#customerPw1').val() != $('#customerPw2').val()) {
					$('#customerPwHelper1').text('');
					$('#customerPwHelper2').text('pw가 일치하지 않습니다.');
					$('#customerPw2').focus();
					
				} else {
					$('#customerPwHelper2').text('');
					$('#updatePasswordForm').submit();
				}
			});
</script>

<script
	src="${pageContext.request.contextPath}/template/js/scripts.min.js"></script>
<script src="${pageContext.request.contextPath}/template/js/main.min.js"></script>
<script src="${pageContext.request.contextPath}/template/js/custom.js"></script>

</html>
</html>