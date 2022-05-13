<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>deleteHost</title>
</head>
<body>
	<h1>관리자 탈퇴</h1>
	
	<form action="${pageContext.request.contextPath}/deleteHostController" method="post">
		<table border="1">
			<tr>
				<td>삭제할 관리자 아이디</td>
				<td>
					<input type="text" name="hostId" value="${hostId}" readonly="readonly">
				</td>
			</tr>
			<tr>
				<td>비밀번호 입력</td>
				<td>
					<input type="text" name="hostPw">
				</td>
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