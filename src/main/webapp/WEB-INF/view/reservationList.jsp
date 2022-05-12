<%@page import="java.util.HashMap"%>
<%@page import="dao.HostDao"%>
<%@page import="vo.Reservation"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%

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
	
	int lastPage = 0;
	
	if(totalRow % rowPerPage == 0) {
		lastPage = totalRow / rowPerPage;
	} else {
		lastPage = (totalRow / rowPerPage) + 1;
	}
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>reservationList</title>
</head>
<body>
	<h1>거래 목록 관리 페이지</h1>
	
	<h3>전체 거래량 ( total : <%=totalRow %> )</h3>
	
	<%
		for(HashMap<String, Object> m : reservationStatusList) {
	%>
			<div>
				<li>
					<button> <!-- controller 이동 안되면 소문자 확인 --> <!-- m.get() 해당 값 어떻게 Controller로 넘길지 ?  -->
						<a href="<%=request.getContextPath()%>/reservationController?reservationStatus=<%=m.get("reservationStatus")%>"><%=m.get("reservationStatus")%>
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
				<%
					for(HashMap<String, Object> m : reservationList) {
				%>
						<tr>
							<td><%=m.get("customerId")%></td>
							<td><%=m.get("pvNo")%></td>
							<td><%=m.get("reservationDate")%></td>
							<td><%=m.get("reservationStatus")%></td>
							<td><%=m.get("createDate")%></td>
							<td><%=m.get("updateDate")%></td>
						</tr>
				<%
					}
				%>
			</tbody>
		</table>
	</div>
	
	<div>
	
	
		<%
			if(currentPage > 1) {
		%>
				<button>
					<a href="<%=request.getContextPath()%>/reservationController?currentPage=<%=currentPage-1%>&reservationStatus=<%=reservationStatus %>">이전</a>
				</button>
		<%
			}
		%>
		
		<%
			if(currentPage < lastPage) {
		%>
				<button>
					<a href="<%=request.getContextPath()%>/reservationController?currentPage=<%=currentPage+1%>&reservationStatus=<%=reservationStatus %>">다음</a>
				</button>
		<%
			}
		%>
	</div>
</body>
</html>