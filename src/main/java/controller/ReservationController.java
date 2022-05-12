package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.HostDao;

@WebServlet("/reservationController")
public class ReservationController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*
		String reservationStatus = request.getParameter("reservationStatus");
		System.out.println("[ReservationController.doGet()] reservationStatus : " + reservationStatus);
		*/
		
		String reservationStatus = "";
		// 예약 상태에 값이 들어왔다면 
		if(request.getParameter("reservationStatus") != null) {
			request.setAttribute("reservationStatus", reservationStatus);
			request.getRequestDispatcher("/WEB-INF/view/reservationList.jsp?reservationStatus="+reservationStatus).forward(request, response);
			return;
		}
		
		int currentPage = 1;
		
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
			request.setAttribute("currentPage", currentPage);
			request.getRequestDispatcher("/WEB-INF/view/reservationList.jsp?currentPage="+currentPage+"&reservationStatus="+reservationStatus).forward(request, response);
			System.out.println("[ReservationController.doGet()] currentPage : "+currentPage);
			return;
		}
		
		// 한 페이지당 보여줄 행의 개수 
		int rowPerPage = 10; 
		
		int beginRow = (currentPage-1) * rowPerPage;
		System.out.println("[reservationList.jsp] beginRow : "+beginRow);
		
		HostDao hostDao = new HostDao();

		ArrayList<HashMap<String, Object>> reservationStatusList = hostDao.selectReservationStatusCount();

		ArrayList<HashMap<String, Object>> reservationList = hostDao.selectReservationList(rowPerPage, beginRow, reservationStatus); 

		int totalRow = hostDao.selectTotalRow();
		request.setAttribute("totalRow", totalRow);
		
		int lastPage = 0;
		if(totalRow % rowPerPage == 0) {
			lastPage = totalRow / rowPerPage;
		} else {
			lastPage = (totalRow / rowPerPage) + 1;
		}
		request.setAttribute("lastPage", lastPage);
		
		// 아무 값도 못받은 경우 reservationList.jsp 페이지로 바로 보낸다 
		request.getRequestDispatcher("/WEB-INF/view/reservationList.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
