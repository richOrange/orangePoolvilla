<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>customerList</title>
</head>
<body>
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
						
						<td><a href="${pageContext.request.contextPath}/customerDetailController?customerId=${cl.customerId}">${cl.customerId}</a></td>
						<td>${cl.name}</td>
					</tr>
				</c:forEach> 
			</tbody>
		</table>
	</div>
	
	<div>
		<c:if test="${currentPage > 1}">
			<button>
				<a href="${pageContext.request.contextPath}/customerCheckController?currentPage=${currentPage - 1}">이전</a>
			</button>
		</c:if>
	
		<c:if test="${currentPage < lastPage}">	
			<button>
				<a href="${pageContext.request.contextPath}/customerCheckController?currentPage=${currentPage + 1}">다음</a>
			</button>
		</c:if>
	</div>
</body>
</html>