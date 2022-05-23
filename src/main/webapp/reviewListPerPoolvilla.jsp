<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>
		<table border="1">
			<thead>
				<tr>
					<th>풀빌라 이름</th>
					<th>사용자 아이디</th>
					<th>만족도</th>
					<th>청결도</th>
					<th>재방문 의사</th>
					<th>리뷰 작성 내용</th>
					<th>최종 수정 날짜</th>
				</tr>
			</thead>

			<!-- 컨트롤러가 없어서 items에 넣어줄 리스트 만들어야 함 -->
			<tbody>
				<c:forEach var="r" items="${reviewListPerPoolvilla}">
					<tr>
						<td>${r.pvName }</td>
						<td>${r.customerId }</td>
						<td>${r.satisfaction }</td>
						<td>${r.cleanliness }</td>
						<td>${r.revisit }</td>
						<td>${r.reviewContents }</td>
						<td>${r.updateDate }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>