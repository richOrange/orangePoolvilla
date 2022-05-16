<%@page import="java.util.ArrayList"%>
<%@page import="vo.Host"%>
<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%	
	// ArrayList<Host> hostList = (ArrayList<Host>)request.getAttribute("hostList");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>hostList</title>
</head>
<body>
	<h1>관리자 계정 리스트 관리 페이지</h1>
	
	<h3>전체 관리자 수 </h3>
	
	<div>
		<table border="1">
			<thead>
				<tr>
					<th>관리자 아이디</th>
					<th>관리자 비밀번호</th>
					<th>랭크 번호</th>
					<th>이름</th>
					<th>이메일</th>
					<th>전화번호</th>
					<th>작성 날짜</th>
					<th>수정 날짜</th>
					<th>관리자 정보 삭제</th>
					<th>관리자 정보 수정</th>
				</tr>
			</thead>
			
			<tbody>
				<c:forEach var="h" items="${hostList}">	
					<tr>
						<td>${h.hostId}</td>
						<td>${h.hostPw}</td>
						<td>${h.level}</td>
						<td>${h.name}</td>
						<td>${h.email}</td>
						<td>${h.phone}</td>
						<td>${h.createDate}</td>
						<td>${h.updateDate}</td>
						<td><a href="${pageContext.request.contextPath}/deleteHostController?hostId=${h.hostId}">관리자 정보 삭제</a></td>
						<td><a href="${pageContext.request.contextPath}/updateHostController?hostId=${h.hostId}">관리자 정보 수정</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	
	<div>
		<button>
			<a href="${pageContext.request.contextPath}/insertHostController">관리자 정보 추가</a>
		</button>
	</div>
	
	<div>
	
	<!-- 페이지 나누는 코드 -->
	
	<c:if test="${currentPage > 1}">
		<button>
			<a href="${pageContext.request.contextPath}/hostController?currentPage=${currentPage - 1}">이전</a>
		</button>
	</c:if>
	
	<c:if test="${currentPage < lastPage}">	
		<button>
			<a href="${pageContext.request.contextPath}/hostController?currentPage=${currentPage + 1}">다음</a>
		</button>
	</c:if>
	
	</div>
</body>
</html>