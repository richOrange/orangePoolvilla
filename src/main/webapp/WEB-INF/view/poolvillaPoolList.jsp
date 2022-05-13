<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>OrangePoolvilla:poolvillaPoolList</title>
</head>
<body>
	<h1>PoolvillaPool List</h1>
	<table border="1">
		<thead>
			<tr>
				<th>poolNo</th>
				<th>pvNo</th>
				<th>poolName</th>
				<th>poolWidth</th>
				<th>poolLength</th>
				<th>depth</th>
				<th>hotWater</th>
				<th>indoorOutdoor</th>
				<th>updateDate</th>
				<th>&nbsp;</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<c:forEach var="pp" items="${list}">
					<tr>
						<td>${pp.getPoolNo()}</td>
						<td>${pp.getPvNo()}</td>
						<td>${pp.getPoolName()}</td>
						<td>${pp.getPoolWidth()}</td>
						<td>${pp.getPoolLength()}</td>
						<td>${pp.getDepth()}</td>
						<td>${pp.getHotWater()}</td>
						<td>${pp.getIndoorOutdoor()}</td>
						<td>${pp.getUpdateDate()}</td>
						<td><a
							href="${pageContext.request.contextPath}/host/deletePoolvillaPoolController?poolNo=${pp.getPoolNo()}">삭제</a>
						</td>
					</tr>
				</c:forEach>
			</tr>
		</tbody>
	</table>
	<br>
	<br>
	<form
		action="${pageContext.request.contextPath }/host/poolvillaPoolController"
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
				<td>poolName</td>
				<td>
					<div>
						<input type="text" name="poolName" id="poolName">
					</div>
				</td>
			</tr>
			<tr>
				<td>poolWidth</td>
				<td>
					<div>
						<input type="number" name="poolWidth" id="poolWidth">
					</div>
				</td>
			</tr>
			<tr>
				<td>poolLength</td>
				<td>
					<div>
						<input type="number" name="poolLength" id="poolLength">
					</div>
				</td>
			</tr>
			<tr>
				<td>poolDepth</td>
				<td>
					<div>
						<input type="number" name="depth" id="depth">
					</div>
				</td>
			</tr>
			<tr>
				<td>hotWater</td>
				<td>
					<div>
						<input type="checkbox" name="hotWater" value="Y">Y
						<input type="checkbox" name="hotWater" value="N">N
					</div>
				</td>
			</tr>
			<tr>
				<td>indoorOutdoor</td>
				<td>
					<div>
						<input type="checkbox" name="indoorOutdoor" Value="실내">실내
						<input type="checkbox" name="indoorOutdoor" Value="야외">야외
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





