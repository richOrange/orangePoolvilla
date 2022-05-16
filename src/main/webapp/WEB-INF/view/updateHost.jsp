<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>updateHost</title>
</head>
<body>
	<h1>관리자 정보 수정</h1>
	
	<form method="post" action="${pageContext.request.contextPath}/updateHostController">
		<table border="1">
			<tr>
				<td>관리자 아이디</td>
				<td>
					<input type="text" name="hostId" value="${hostId}" readonly="readonly">
				</td>
			</tr>
			<tr>
				<td>관리자 비밀번호</td>
				<td>
					<input type="text" name="hostPw">
				</td>
			</tr>
			<tr>
				<td>랭크 (5이상 7이하 입력)</td>
				<td>
					<input type="text" name="level">
				</td>
			</tr>
			<tr>
				<td>이름</td>
				<td>
					<input type="text" name="name">
				</td>
			</tr>
			<tr>
				<td>이메일</td>
				<td>
					<input type="text" name="email">
				</td>
			</tr>
			<tr>
				<td>전화번호</td>
				<td>
					<input type="text" name="phone">
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<button type="submit">관리자 입력</button>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>