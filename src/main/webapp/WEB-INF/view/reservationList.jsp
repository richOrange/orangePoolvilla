<%@page import="java.util.HashMap"%>
<%@page import="dao.HostDao"%>
<%@page import="vo.Reservation"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	/*
	int currentPage = (Integer)request.getAttribute("currentPage");
	System.out.println("[reservationList.jsp] currentPage : "+currentPage);

	String reservationStatus = String.valueOf(request.getAttribute("reservationStatus"));
	System.out.println("[reservationList.jsp] reservationStatus : "+reservationStatus);
	
	// 한 페이지당 보여줄 행의 개수 
	int rowPerPage = (Integer)request.getAttribute("rowPerPage");
	System.out.println("[reservationList.jsp] rowPerPage : "+rowPerPage);
	
	int beginRow = (Integer)request.getAttribute("beginRow");
	System.out.println("[reservationList.jsp] beginRow : "+beginRow);
	
	ArrayList<HashMap<String, Object>> reservationStatusList = (ArrayList<HashMap<String, Object>>)request.getAttribute("reservationStatusList");
	
	ArrayList<HashMap<String, Object>> reservationList = (ArrayList<HashMap<String, Object>>)request.getAttribute("reservationList");
	
	int totalRow = (Integer)request.getAttribute("totalRow");
	System.out.println("[reservationList.jsp] totalRow : "+totalRow);
	
	// 아래 코드 30 ~ 36 처리 방법 ? 
	int lastPage = 0;
	
	if(totalRow % rowPerPage == 0) {
		lastPage = totalRow / rowPerPage;
	} else {
		lastPage = (totalRow / rowPerPage) + 1;
	}
	*/
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>reservationList</title>
</head>
<body>
	
	
	
	<h1>거래 목록 관리 페이지</h1>
	
	<h3>전체 거래량 ( total : ${totalRow} )</h3>
	
	<!-- 예약 상태와 예약 상태 갯수를 동시에 표시하는 버튼 -->
	<c:forEach var="m" items="${reservationStatusList}">
			<div>
				<li>
					<button> <!-- controller 이동 안되면 소문자 확인 --> <!-- m.get() 해당 값 어떻게 Controller로 넘길지 ?  -->
						<a href="${pageContext.request.contextPath}/reservationController?reservationStatus=${m.reservationStatus}">${m.reservationStatus}
								(${m.cnt})
						</a>
					</button>	
				</li>
			</div>	
	</c:forEach>
	
	<!-- 예약(reservation) 테이블의 전체 컬럼을 가져오는 코드-->
	<div>
		<table border="1">
			<thead>
				<tr>
					<th>회원 아이디</th>
					<th>풀빌라 번호</th>
					<th>예약 날짜</th>
					<th>예약 상태</th>
					<th>작성 날짜</th>
					<th>최종 상태 업데이트 날짜</th>
				</tr>
			</thead>
			
			<tbody>
				<c:forEach var="m" items="${reservationList }">
					<tr>
						<td>${m.customerId}</td>
						<td>${m.pvNo}</td>
						<td>${m.reservationDate}</td>
						<td>${m.reservationStatus}</td>
						<td>${m.createDate}</td>
						<td>${m.updateDate}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	
	<div>
	
	<!-- 페이지 나누는 코드 -->
	
	<c:if test="${currentPage > 1}">
		<button>
			<a href="${pageContext.request.contextPath}/reservationController?currentPage=${currentPage - 1}&reservationStatus=${reservationStatus}">이전</a>
		</button>
	</c:if>
	
	<c:if test="${currentPage < lastPage}">	
		<button>
			<a href="${pageContext.request.contextPath}/reservationController?currentPage=${currentPage + 1}&reservationStatus=${reservationStatus}">다음</a>
		</button>
	</c:if>
	
	</div>
</body>
</html>