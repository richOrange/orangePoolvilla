package controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ReservationDao;

@WebServlet("/customer/myReservationListController")
public class MyReservationListController extends HttpServlet {
	private ReservationDao reservationDao;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Dao 호출
		reservationDao = new ReservationDao();
		//session에 login 정보 호출
		HttpSession session = request.getSession();
		Map<String,Object> sessionLoginMember = (Map<String,Object>)session.getAttribute("sessionLoginMember");
		String memberId = (String)sessionLoginMember.get("memberId");
		//요청값 받기
		//예약 상태 값, 아무것도 선택 안할시, 처음 접속시 "" <- 예약상태 전체 검색
		String reservationStatus = "";
		//예약상태 있을시 그 값으로 검색 및 attribute
		if(request.getParameter("reservationStatus")!=null) {
			reservationStatus=request.getParameter("reservationStatus");
			request.setAttribute("reservationStatus", reservationStatus);
		}
		System.out.println("[MyReservationListController.doGet()] reservationStatus : " + reservationStatus);
		
		//예약상태 변경 기능
		if(request.getParameter("checkStatus")!=null&&request.getParameter("reservationNo")!=null) {
			//요청값 받기
			String checkStatus = request.getParameter("checkStatus");//변경될 예약상태 값
			int reservationNo = Integer.parseInt(request.getParameter("reservationNo")); //변경할 예약 주문 넘버
			//디버깅
			System.out.println("[myReservationListController.doGet()] checkStatus : "+checkStatus);
			System.out.println("[myReservationListController.doGet()] reservationNo : "+reservationNo);
			//Dao에 예약상태 변경요청
			int row = reservationDao.updateReservationStatusOfReservation(checkStatus, reservationNo,(String)sessionLoginMember.get("memberId")); // memberId = customerId 예약상태 변경한 회원 Id
			if(row==-1) {//-1일시 reservationDao.updateReservationStatus 쿼리문 작동안함,다른 성공 실패 여부는 dao에서 출력됨
				System.out.println("[ReservationController.doGet()] reservationDao.updateReservationStatus 요청실패 ");
			}else if(row==1) {//성공시 /customer/myReservationListController목록으로 redirect
				response.sendRedirect(request.getContextPath()+"/customer/myReservationListController"); 
				return;
			}
		}
		List<Map<String,Object>> selectMyReservationList = reservationDao.selectMyReservationList(memberId,reservationStatus);
		//모델값 setAttribute
		request.setAttribute("selectMyReservationList", selectMyReservationList);
		request.getRequestDispatcher("/WEB-INF/view/myReservationList.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
