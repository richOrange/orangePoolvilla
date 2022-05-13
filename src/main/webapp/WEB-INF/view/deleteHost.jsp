<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>deleteHost</title>
</head>
<body>
	<h1>관리자 탈퇴</h1>
	
	<form action="<%=request.getContextPath() %>/deleteHostController" method="post">
		<table border="1">
			<tr>
				<td>삭제할 관리자 아이디</td>
				<td>
					<input type="text" name="hostId">
				</td>
			</tr>
			<tr>
				<td>비밀번호 입력</td>
				<td>
					<input type="text" name="hostPw1">
				</td>
			</tr>
			<tr>
				<td>비밀번호 재입력</td>
				<td>
					<input type="text" name="hostPw2">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>