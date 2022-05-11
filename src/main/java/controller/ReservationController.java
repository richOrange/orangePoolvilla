package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/reservationController")
public class ReservationController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String reservationStatus = request.getParameter("reservationStatus");
		System.out.println(reservationStatus + " <-- reservationStatus // ReservationController.doGet() ");
		
		// 예약 상태에 값이 들어왔다면 
		if(request.getParameter("reservationStatus") != null) {
			request.getRequestDispatcher("/WEB-INF/view/reservationList.jsp?reservationStatus="+reservationStatus).forward(request, response);
			return;
		}
		
		request.getRequestDispatcher("/WEB-INF/view/reservationList.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
