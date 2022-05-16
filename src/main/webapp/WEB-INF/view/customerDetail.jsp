<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>customerDetail</title>
</head>
<body>
	<h1>고객 상세보기</h1>
	
	<div>
		<table border="1">
			<thead>
				<tr>
					<th>아이디</th>
					<th>비밀번호</th>
					<th>이름</th>
					<th>성별</th>
					<th>생일</th>
					<th>이메일</th>
					<th>전화번호</th>
					<th>랭크</th>
					<th>생성된 날짜</th>
					<th>최종 수정 날짜</th>
				</tr>
			</thead>
			
			<tbody>
				
					<tr>
						<td>${customer.customerId }</td>
						<td>${customer.customerPw }</td>
						<td>${customer.name }</td>
						<td>${customer.gender }</td>
						<td>${customer.birthDate}</td>
						<td>${customer.email}</td>
						<td>${customer.phone }</td>
						<td>${customer.level}</td>
						<td>${customer.createDate }</td>
						<td>${customer.updateDate }</td>
					</tr>
				
			</tbody>
		</table>
	</div>
	
	<div>
		<a href="${pageContext.request.contextPath}/customerCheckController">관리자 목록</a>
	</div>
</body>
</html>