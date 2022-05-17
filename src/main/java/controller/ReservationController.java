package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ReservationDao;

@WebServlet("/host/reservationController")
public class ReservationController extends HttpServlet {
	private ReservationDao reservationDao;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 예약 상태에 값이 들어왔다면 
		//reservationStatus를 받을 변수 초기화, ""이면 모든 상태를 다 요청
		String reservationStatus = "";
		//페이징
		int currentPage = 1;
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		request.setAttribute("currentPage", currentPage);
		
		int rowPerPage = 10; 
		request.setAttribute("rowPerPage", rowPerPage);
		
		int beginRow = (currentPage-1) * rowPerPage;
		System.out.println("[ReservationController.doGet()] beginRow : "+beginRow);
		request.setAttribute("beginRow", beginRow);
		
		//dao 호출
		reservationDao = new ReservationDao();
		ArrayList<HashMap<String, Object>> reservationStatusList = reservationDao.selectReservationStatusCount();
		request.setAttribute("reservationStatusList", reservationStatusList);
		
		ArrayList<HashMap<String, Object>> reservationList = reservationDao.selectReservationList(rowPerPage, beginRow, reservationStatus); 
		request.setAttribute("reservationList", reservationList);
		
		int totalRow = reservationDao.selectReservationTotalRow(); 
		request.setAttribute("totalRow", totalRow);
		
		int lastPage = 0;
		
		if(totalRow % rowPerPage == 0) {
			lastPage = totalRow / rowPerPage;
		} else {
			lastPage = (totalRow / rowPerPage) + 1;
		}
		
		request.setAttribute("lastPage", lastPage);
		
		if(request.getParameter("reservationStatus") == null || request.getParameter("reservationStatus") =="") {
			request.setAttribute("reservationStatus", reservationStatus);

			request.getRequestDispatcher("/WEB-INF/view/reservationList.jsp").forward(request, response);
		}
		
		if(request.getParameter("reservationStatus") != null) {
			System.out.println("[ReservationController.doGet()] reservationStatus : "+reservationStatus);
				if(request.getParameter("currentPage") == null) {
					request.setAttribute("reservationStatus", reservationStatus);
					
					request.getRequestDispatcher("/WEB-INF/view/reservationList.jsp?reservationStatus="+reservationStatus).forward(request, response);
				} else if(request.getParameter("currentPage") != null) {
					request.setAttribute("reservationStatus", reservationStatus);
					currentPage = Integer.parseInt(request.getParameter("currentPage"));
					request.setAttribute("currentPage", currentPage);
					
					request.getRequestDispatcher("/WEB-INF/view/reservationList.jsp?currentPage="+currentPage+"&reservationStatus="+reservationStatus).forward(request, response);
					System.out.println("[ReservationController.doGet()] currentPage : "+currentPage);
				}
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
