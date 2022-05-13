<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>insertHost</title>
</head>
<body>
	<h1>관리자 추가</h1>
	
	<form method="post" action="<%=request.getContextPath() %>/insertHostController">
		<table border="1">
			<tr>
				<td>관리자 아이디</td>
				<td>
					<input type="text" name="hostId">
				</td>
			</tr>
			<tr>
				<td>관리자 비밀번호</td>
				<td>
					<input type="text" name="hostPw">
				</td>
			</tr>
			<tr>
				<td>랭크 번호(5~7)</td>
				<td>
					<input type="number" name="level">
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