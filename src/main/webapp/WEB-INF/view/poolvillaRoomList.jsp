<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>OrangePoolvilla:poolvillaRoomList</title>
</head>
<body>
	<h1>PoolvillaRoom List</h1>
	<table border="1">
		<thead>
			<tr>
				<th>roomNo</th>
				<th>pvNo</th>
				<th>roomType</th>
				<th>roomName</th>
				<th>roomInfo</th>
				<th>roomSize</th>
				<th>updateDate</th>
				<th>&nbsp;</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<c:forEach var="pr" items="${list}">
					<tr>
						<td>${pr.getRoomNo()}</td>
						<td>${pr.getPvNo()}</td>
						<td>${pr.getRoomType()}</td>
						<td>${pr.getRoomName()}</td>
						<td>${pr.getRoomInfo()}</td>
						<td>${pr.getRoomSize()}</td>
						<td>${pr.getUpdateDate()}</td>
						<td><a
							href="${pageContext.request.contextPath}/host/deletePoolvillaRoomController?roomNo=${pr.getRoomNo()}">삭제</a>
						</td>
					</tr>
				</c:forEach>
			</tr>
		</tbody>
	</table>
	<br>
	<br>
	<form
		action="${pageContext.request.contextPath }/host/poolvillaRoomController"
		method="post">
		<table border="1">
			<tr>
				<td>pvNo</td>
				<td>
					<div>
						<input type="number" name="pvNo" id="pvNo">
					</div>
				</td>
			</tr>
			<tr>
				<td>roomType</td>
				<td>
					<div>
						<input type="text" name="roomType" id="roomType">
					</div>
				</td>
			</tr>
			<tr>
				<td>roomName</td>
				<td>
					<div>
						<input type="text" name="roomName" id="roomName">
					</div>
				</td>
			</tr>
			<tr>
				<td>roomInfo</td>
				<td>
					<div>
						<input type="text" name="roomInfo" id="roomInfo">
					</div>
				</td>
			</tr>
			<tr>
				<td>roomSize</td>
				<td>
					<div>
						<input type="number" name="roomSize" id="roomSize">
					</div>
				</td>
			</tr>
			
		</table>
		<div>
			<button type="submit">Save</button>
		</div>
	</form>
</body>
</html>





