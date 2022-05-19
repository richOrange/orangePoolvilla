package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ReservationDao;

@WebServlet("/host/selectHostReservationListController")
public class SelectHostReservationListController extends HttpServlet {
	private ReservationDao reservationDao;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//dao 호출
		reservationDao = new ReservationDao();
		//예약상태 변경 기능
		if(request.getParameter("checkStatus")!=null&&request.getParameter("reservationNo")!=null) {
			//요청값 받기
			String checkStatus = request.getParameter("checkStatus");//변경될 예약상태 값
			int reservationNo = Integer.parseInt(request.getParameter("reservationNo")); //변경할 예약 주문 넘버
			//디버깅
			System.out.println("[selectHostReservationListController.doGet()] checkStatus : "+checkStatus);
			System.out.println("[selectHostReservationListController.doGet()] reservationNo : "+reservationNo);
			//Dao에 예약상태 변경요청
			int row = reservationDao.updateReservationStatusOfReservation(checkStatus, reservationNo);
			System.out.println("[selectHostReservationListController.doGet()] reservationDao.updateReservationStatusOfReservation 실패");
			if(row==-1||row==0) {//-1일시 DB에 요청실패, 0일시 update or insert 실패
			}else if(row==1) {//성공
				System.out.println("[selectHostReservationListController.doGet()] reservationDao.updateReservationStatusOfReservation 성공");
			}
		}
		
		//페이징 요청값 받기
		int currentPage = 1; //현재페이지 기본값 1
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
			System.out.println("[selectHostReservationListController.doGet()] currentPage : "+currentPage);
		}
		request.setAttribute("currentPage", currentPage);//현재페이지 setAttribute
		
		String reservationStatus = ""; //페이징 관련 예약상태 담을 변수,""이면 모든 예약상태를 다 요청
		if(request.getParameter("reservationStatus") != null) { // null이 아닐경우 요청값 받기
			reservationStatus = request.getParameter("reservationStatus");
			System.out.println("[selectHostReservationListController.doGet()] reservationStatus : "+reservationStatus);
			request.setAttribute("reservationStatus", reservationStatus);
		}
		
		int rowPerPage = 10; //페이지 당 목록 수 기본값 10;
		System.out.println("[selectHostReservationListController.doGet()] rowPerPage : "+rowPerPage);
		
		int beginRow = (currentPage-1) * rowPerPage; //시작 목록
		System.out.println("[selectHostReservationListController.doGet()] beginRow : "+beginRow);
		request.setAttribute("beginRow", beginRow);
		
		// RESERVATION 테이블의 예약 상태 중 결제대기,취소대기,이용중 갯수 요청
		ArrayList<HashMap<String, Object>> selectWaitReservationStatusCount = reservationDao.selectWaitReservationStatusCount();
		request.setAttribute("selectWaitReservationStatusCount", selectWaitReservationStatusCount);
		// RESERVATION 테이블의 예약 상태 중 결제대기,취소대기,이용중 갯수 요청
		ArrayList<HashMap<String, Object>> selectCompleteReservationStatusCount = reservationDao.selectCompleteReservationStatusCount();
		request.setAttribute("selectCompleteReservationStatusCount", selectCompleteReservationStatusCount);
		//전체 예약목록 요청
		ArrayList<HashMap<String, Object>> reservationList = reservationDao.selectReservationList(rowPerPage, beginRow, reservationStatus); 
		request.setAttribute("reservationList", reservationList);
		//페이징 관련 전체 행의 수 모델값 요청
		int totalRow = reservationDao.selectReservationTotalRow(); 
		request.setAttribute("totalRow", totalRow);
		
		int lastPage = 0; //마지막 페이지 담을 변수 초기화
		
		if(totalRow % rowPerPage == 0) {//lastPage는 , totalRow값으로 계산
			lastPage = totalRow / rowPerPage;
		} else {
			lastPage = (totalRow / rowPerPage) + 1;
		}
		request.setAttribute("lastPage", lastPage);
					
		request.getRequestDispatcher("/WEB-INF/view/selectHostReservationList.jsp").forward(request, response);
	}
		
		

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		
	}

}
