<%@page import="java.util.ArrayList"%>
<%@page import="vo.Host"%>
<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	ArrayList<Host> hostList = (ArrayList<Host>)request.getAttribute("hostList");
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
				<%
					for(Host h : hostList) {
				%>
						<tr>
							<td><%=h.getHostId() %></td>
							<td><%=h.getHostPw() %></td>
							<td><%=h.getLevel() %></td>
							<td><%=h.getEmail() %></td>
							<td><%=h.getPhone() %></td>
							<td><%=h.getCreateDate() %></td>
							<td><%=h.getUpdateDate() %></td>
						</tr>
				<%
					}
				%>
			</tbody>
		</table>
	</div>
	
	<div>
		<button>
			<a href="<%=request.getContextPath() %>/insertHostController">관리자 추가</a>
		</button>
	</div>
</body>
</html>