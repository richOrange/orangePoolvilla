<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Orange-poolvilla:poolvillaList</title>
<meta name="description" content="Free Bootstrap Theme by uicookies.com">
<meta name="keywords"
	content="free website templates, free bootstrap themes, free template, free bootstrap, free website template">

<!-- <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400" rel="stylesheet"> -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/template/css/styles-merged.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/template/css/style.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/template/css/custom.css">

</head>

<!-- jquery -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

<body>

	<!-- START: header -->
	<div id="includeHeader"></div>
	<!-- END: header -->


	<h1>관리자 탈퇴</h1>

	<form
		action="${pageContext.request.contextPath}/host/deleteHostController"
		method="post">
		<table border="1">
			<tr>
				<td>삭제할 관리자 아이디</td>
				<td><input type="text" name="hostId" value="${hostId}"
					readonly="readonly"></td>
			</tr>
			<tr>
				<td>비밀번호 입력</td>
				<td><input type="text" name="hostPw"></td>
			</tr>
			<tr>
				<td colspan="2">
					<button type="submit">관리자 삭제</button>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>