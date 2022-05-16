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

	<h1>회원 관리 페이지</h1>

	<h3>전체 회원 수</h3>

	<div>
		<table border="1">
			<thead>
				<tr>

					<th>ID</th>
					<th>NAME</th>
				</tr>
			</thead>

			<tbody>
				<c:forEach var="cl" items="${customerList}">
					<tr>

						<td><a
							href="${pageContext.request.contextPath}/host/customerDetailController?customerId=${cl.customerId}">${cl.customerId}</a></td>
						<td>${cl.name}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

	<div>
		<c:if test="${currentPage > 1}">
			<button>
				<a
					href="${pageContext.request.contextPath}/host/customerCheckController?currentPage=${currentPage - 1}">이전</a>
			</button>
		</c:if>

		<c:if test="${currentPage < lastPage}">
			<button>
				<a
					href="${pageContext.request.contextPath}/host/customerCheckController?currentPage=${currentPage + 1}">다음</a>
			</button>
		</c:if>
	</div>
</body>
</html>