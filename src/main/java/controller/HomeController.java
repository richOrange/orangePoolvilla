package controller;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.PoolvillaLocationDao;
import dao.ReservationDao;
import vo.PoolvillaLocation;
@WebServlet("/all/homeController")
public class HomeController extends HttpServlet {
	private PoolvillaLocationDao poolvillaLocationDao;	
	private ReservationDao reservationDao;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//오늘 날짜 체크
    	Calendar now = Calendar.getInstance();
    	String today ="";//오늘 날짜가 들어갈 today 변수 초기화
    	today = today + now.get(Calendar.YEAR);//연도 입력
    	//월 입력
    	if(now.get(Calendar.MONTH)+1<10) { //Calendar는 현재 월 -1, 10보다 작으면 0x이 아닌 x 이 들어감으로
    		today = today + "-0"+(now.get(Calendar.MONTH)+1);
    	}else if(now.get(Calendar.MONTH)+1>=10) {//10보다크면 2자리임으로 바로 입력
    			today = today + "-"+(now.get(Calendar.MONTH)+1);
    		}
    	//날짜 입력
    	if(now.get(Calendar.DATE)<10) { //10보다 작으면 0x이 아닌 x 이 들어감으로
    		today = today + "-0"+now.get(Calendar.DATE);
    	}else if(now.get(Calendar.DATE)>=10) {//10보다크면 2자리임으로 바로 입력
    		today = today + "-"+now.get(Calendar.DATE);
    	}
    	System.out.println("[/all/homeController] 오늘 날짜 : "+today);
    	
    	//오늘 날짜가 application에 저장된 오늘 날짜랑 다르면 application 갱신
    	if(!today.equals((String)request.getServletContext().getAttribute("applicationToday"))){
    		request.getServletContext().setAttribute("applicationToday", today);
    		System.out.println("[/all/homeController] application에 오늘 날짜 변경 : "+request.getServletContext().getAttribute("applicationToday"));
    		//날짜가 변경 되었으므로 예약테이블에 예약상태 : 결제완료 -> 예약상태 : 이용중 으로 변경, 이용중 -> 이용완료로 변경
    		//Dao 호출
    		reservationDao = new ReservationDao();
    		//예약상태가 변경되어야 하는 예약 목록 요청
    		int row = -1; //예약상태 변경 메서드 결과 값 받을 변수 초기화
    		String reservationStatus = "이용중"; //변경할 예약상태 값
    		List<Integer> reservationBytodayCheckInlist = reservationDao.selectReservationBytodayCheckIn(today);
    		System.out.println("[/all/homeController] reservationBytodayCheckInlist.size() : "+reservationBytodayCheckInlist.size());
    		//체크인이 오늘날짜인 예약을 결제완료 -> 이용중으로 변경 하는 기능
    		if(reservationBytodayCheckInlist.size()!=0) {//리스트가 비어있지 않으면 리스트안에 있는 예약은 예약상태를 이용중으로 변경
    			for (Integer integer : reservationBytodayCheckInlist) {
    				row = reservationDao.updateReservationStatusOfReservation(reservationStatus, integer, "poolvillaSystem"); // integer = reservationNo, poolvillaSystem <- 설정에서 변경 했을시 변경자
    				System.out.println("[/all/homeController]예약번호"+integer+" 이용중으로 변경 결과:"+row+"<- 1:성공 0 :실패");		
				}
    		}
    		//체크아웃이 오늘인 예약을 이용중 -> 이용완료으로 변경 하는 기능
    		//예약상태가 변경되어야 하는 예약 목록 요청
    		row = -1; //예약상태 변경 메서드 결과 값 받을 변수 초기화
    		reservationStatus = "이용완료"; //변경할 예약상태 값
    		List<Integer> reservationBytodayCheckOutlist = reservationDao.selectReservationBytodayCheckOut(today);
    		System.out.println("[/all/homeController] reservationBytodayCheckOutlist.size() : "+reservationBytodayCheckOutlist.size());
    		if(reservationBytodayCheckOutlist.size()!=0) {//리스트가 비어있지 않으면 리스트안에 있는 예약은 예약상태를 이용완료으로 변경
    			for (Integer integer : reservationBytodayCheckOutlist) {
    				row = reservationDao.updateReservationStatusOfReservation(reservationStatus, integer, "poolvillaSystem"); // integer = reservationNo, poolvillaSystem <- 설정에서 변경 했을시 변경자
    				System.out.println("[/all/homeController]예약번호"+integer+" 이용완료로 변경 결과:"+row+"<- 1:성공 0 :실패");		
    			}
    		}
    		
    	}
    	
		//지역 리스트 모델값 요청
		poolvillaLocationDao = new PoolvillaLocationDao();
		 List<PoolvillaLocation> locationList = poolvillaLocationDao.selectPoolvillaLocationList();
		 //모델값 저장
		 request.setAttribute("locationList", locationList);
		 //home.jsp 요청
		request.getRequestDispatcher("/WEB-INF/view/home.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
