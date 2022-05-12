<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ page import ="vo.Customer"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
	<title>CustomerOne</title>
</head>
<body class = "container">
<%
	//컨트롤러 값 받기
	Customer customer = (Customer)request.getAttribute("Customer");
%>
<h1>회원정보 상세보기</h1>
	<table class="table table-bordered">
	<tr>
		<td>CustomerId</td>
		<td><%=customer.getCustomerId() %></td>
	</tr>
	<tr>
		<td>name</td>
		<td><%=customer.getName() %></td>
	</tr>
	<tr>
		<td>gender</td>
		<td><%=customer.getGender() %></td>
	</tr>
	<tr>
		<td>birthdate</td>
		<td><%=customer.getBirthDate() %></td>
	</tr>
	<tr>
		<td>createDate</td>
		<td><%=customer.getCreateDate() %></td>
	</tr>
	</table>
		<a  href="<%=request.getContextPath()%>/updateCustomerController" type ="button" class="btn btn-primary">수정</a>
		<a  href="<%=request.getContextPath()%>/deleteCustomerController" type ="button" class="btn btn-danger">회원탈퇴</a>
		
	
</body>
</html>