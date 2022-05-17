package controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.PoolvillaDao;
import dao.ReservationDao;
import vo.Poolvilla;
import vo.PoolvillaPool;
import vo.Reservation;

/**
 * Servlet implementation class InsertReservationController
 */
@WebServlet("/customer/insertReservationController")
public class InsertReservationController extends HttpServlet {
	private PoolvillaDao poolvillaDao;
	private ReservationDao reservationDao;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 널체크 null일경우 home으로 response
		if (request.getParameter("pvNo") == null || request.getParameter("reservationBeginDate") == null
				|| request.getParameter("reservationLastDate") == null) {
			System.out.println("[/customer/insertReservationcontroller.doget)] null오류발생 ");
			response.sendRedirect(request.getContextPath() + "/all/homeController?msg=null");
			return;
		}
		
		
		// 요청값 호출
		int pvNo = Integer.parseInt(request.getParameter("pvNo"));
		String reservationBeginDate = request.getParameter("reservationBeginDate");
		String reservationLastDate = request.getParameter("reservationLastDate");
		// 디버깅
		System.out.println("[/customer/insertReservationcontroller.doget()] pvNo : " + pvNo);
		System.out.println("[/customer/insertReservationcontroller.doget()] reservationBeginDate : " + reservationBeginDate);
		System.out.println("[/customer/insertReservationcontroller.doget()] reservationLastDate : " + reservationLastDate);
		// 모델값 호출
		poolvillaDao = new PoolvillaDao();
		// 풀빌라 정보 호출
		Poolvilla selectPoolvillaOne = poolvillaDao.selectPoolvillaOne(pvNo);
		List<Map<String, Object>> poolvillaCookingToolList = poolvillaDao.selectPoolvillaCookingToolByPvNo(pvNo);
		List<Map<String, Object>> poolvillaOttList = poolvillaDao.selectPoolvillaOttByPvNo(pvNo);
		List<Map<String, Object>> poolvillaSuppliesList = poolvillaDao.selectPoolvillaSuppliesByPvNo(pvNo);
		List<Map<String, Object>> poolvillaRoomNBedList = poolvillaDao.selectPoolvillaRoomNBedByPvNo(pvNo);
		List<PoolvillaPool> selectPoolvillaPoolListByPvNo = poolvillaDao.selectPoolvillaPoolListByPvNo(pvNo);
		List<Map<String, Object>> selectPoolvillaFacilityListByPvNo = poolvillaDao.selectPoolvillaFacilityListByPvNo(pvNo);

		// 디버깅
		System.out.println("[customer/insertReservationcontroller.doget()] selectPoolvillaOne : " + selectPoolvillaOne.toString());
		// 모델값 setAttiribute
		request.setAttribute("reservationBeginDate", reservationBeginDate);// 체크인날짜
		request.setAttribute("reservationLastDate", reservationLastDate);// 체크아웃날짜
		request.setAttribute("selectPoolvillaOne", selectPoolvillaOne);// poolvillaOne 정보
		request.setAttribute("poolvillaCookingToolList", poolvillaCookingToolList); // 해당 풀빌라의 cooking_tool 정보
		request.setAttribute("poolvillaOttList", poolvillaOttList); // 해당 풀빌라의 ott 정보
		request.setAttribute("poolvillaSuppliesList", poolvillaSuppliesList); // 해당 풀빌라의 supplies 정보
		request.setAttribute("poolvillaRoomNBedList", poolvillaRoomNBedList); // 해당 풀빌라의 room_info 정보와 bed 정보
		request.setAttribute("selectPoolvillaPoolListByPvNo", selectPoolvillaPoolListByPvNo); // 해당 풀빌라의 pool 정보
		request.setAttribute("selectPoolvillaFacilityListByPvNo", selectPoolvillaFacilityListByPvNo); // 해당 풀빌라의
																										// facility 정보
		// jsp 호출
		request.getRequestDispatcher("/WEB-INF/view/insertReservation.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	      //null 체크 입력값이 하나라도 null이면 home으로 돌려보냄
		
	      if(request.getParameter("memberId")==null||request.getParameter("checkIn")==null||request.getParameter("checkOut")==null||request.getParameter("pvNo")==null) {
	    	  System.out.println("[insertReservationController.doPost()] 널오류발생");
	    	  
	         response.sendRedirect(request.getContextPath()+"/all/homeController");
	         return;
	      }
	      //요청값 받기
	      Reservation insertReservation = new Reservation();
	      insertReservation.setCustomerId(request.getParameter("memberId"));
	      insertReservation.setPvNo(Integer.parseInt(request.getParameter("pvNo")));
	      insertReservation.setReservationBeginDate(request.getParameter("checkIn"));
	      insertReservation.setReservationLastDate(request.getParameter("checkOut"));
	      System.out.println("[insertReservationController.doPost()] insertReservation : " + insertReservation);
	      //Dao 호출
	      reservationDao = new ReservationDao();
	      //예약 기능
	      int row = reservationDao.insertReservation(insertReservation);
	      if(row == 0) {//중복은 없지만 입력이 안된 경우, 홈으로 보냄
	    	  response.sendRedirect(request.getContextPath()+"/all/homeController");
	    	  return;
	      } else if(row == 1) {//성공했으면 예약목록 페이지로 이동
	    	  response.sendRedirect(request.getContextPath()+"/customer/myReservationListController");
	    	  return;
	      } else if(row == 2) {//실패 날짜가 중복된 상태
	    	  response.sendRedirect(request.getContextPath()+"/all/homeController");
	    	  return;
	      }
	      
	}

}
