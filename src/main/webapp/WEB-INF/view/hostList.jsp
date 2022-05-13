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
					<th>이메일</th>
					<th>전화번호</th>
					<th>작성 날짜</th>
					<th>수정 날짜</th>
				</tr>
			</thead>
			
			<tbody>
				<c:forEach var="h" items="${hostList}">	
					<tr>
						<td>${h.hostId}</td>
						<td>${h.hostPw}</td>
						<td>${h.level}</td>
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
		<button>
			<a href="${pageContext.request.contextPath}/deleteHostController">관리자 정보 삭제</a>
		</button>
	</div>
	
	<div>
		<button>
			<a href="${pageContext.request.contextPath}/updateHostController">관리자 정보 수정</a>
		</button>
	</div>
</body>
</html>