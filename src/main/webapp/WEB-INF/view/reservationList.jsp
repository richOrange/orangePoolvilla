<%@page import="java.util.HashMap"%>
<%@page import="dao.HostDao"%>
<%@page import="vo.Reservation"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	
	int currentPage = 1;
	if(request.getParameter("currentPage") != null) {
		currentPage = Integer.parseInt(request.getParameter("currentPage"));
	}
	System.out.println(currentPage+ " <-- currentPage // [장주현] reservationList.jsp ");

	String reservationStatus = "";
	if(request.getParameter("reservationStatus") != null) {
		reservationStatus = request.getParameter("reservationStatus");
	}
	System.out.println(reservationStatus+ " <-- reservationStatus // [장주현] reservationList.jsp ");
	
	// 한 페이지당 보여줄 행의 개수 
	int rowPerPage = 10; 
	
	int beginRow = (currentPage-1) * rowPerPage;
	System.out.println(beginRow + " <-- beginRow // [장주현] reservationList.jsp");
	
	HostDao hostDao = new HostDao();

	ArrayList<HashMap<String, Object>> reservationStatusList = hostDao.selectReservationStatusCount();

	ArrayList<Reservation> reservationList = null; 
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>reservationList</title>
</head>
<body>
	<h1>거래 목록 관리 페이지</h1>
	
	<%
		for(HashMap<String, Object> m : reservationStatusList) {
	%>
			<div>
				<li>
					<button> <!-- controller 이동 안되면 소문자 확인 -->
						<a href="<%=request.getContextPath()%>/ReservationController?reservationStatus=<%=m.get("reservationStatus")%>"><%=m.get("reservationStatus")%>
							<button>
								(<%=m.get("cnt")%>)
							</button>
						</a>
					</button>	
				</li>
			</div>	
	<%
		}
	%>
	
	<div>
		<table>
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
				<%
					for(Reservation r : reservationList) {
				%>
						<tr>
							<td><%=r.getCustomerId()%></td>
							<td><%=r.getPvNo()%></td>
							<td><%=r.getReservationBeginDate()%> ~ <%=r.getReservationLastDate()%></td>
							<td><%=r.getReservationStatus()%></td>
							<td><%=r.getCreateDate() %></td>
							<td><%=r.getUpdateDate() %></td>
						</tr>
				<%
					}
				%>
			</tbody>
		</table>
	</div>
	
</body>
</html>