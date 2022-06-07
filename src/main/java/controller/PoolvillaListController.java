package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.FacilityDao;
import dao.PoolvillaDao;
import dao.PoolvillaLocationDao;
import dao.ReservationDao;
import vo.Facility;
import vo.PoolvillaLocation;
@WebServlet("/all/poolvillaListController")
public class PoolvillaListController extends HttpServlet {
	private PoolvillaDao poolvillaDao;//PoolvillaDao 호출 , 풀빌라 메인 테이블 정보
	private PoolvillaLocationDao poolvillaLocationDao;//poolvillaLocationDao 호출, 지역정보
	private FacilityDao facilityDao;//facilityDao 호출, 부대시설
	private ReservationDao reservationDao;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//날짜 관련기능
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
    	
		//풀빌라 리스트 모델값 요청
		 poolvillaDao = new PoolvillaDao();
		 List<Map<String,Object>> poolvillaList = poolvillaDao.selectPoolvillaList(0, 10);
		 System.out.println("[/all/poolvillaListController.dopost()] poolvillaList.size() : " + poolvillaList.size());
		//locationList 요청
		 poolvillaLocationDao = new PoolvillaLocationDao();
		List<PoolvillaLocation> locationList = poolvillaLocationDao.selectPoolvillaLocationList();
		System.out.println("[/all/poolvillaListController.dopost()] locationList.size() : " + locationList.size());
		//facilityList 요청
		facilityDao = new FacilityDao();
		List<Facility> facilityList = facilityDao.selectFacilityList();
		System.out.println("[/all/poolvillaListController.dopost()] facilityList.size() : " + facilityList.size());
		//checkedFacilityList 초기화
		List<String> checkedFacilityList = new ArrayList<>();
		 //모델값 저장
		 request.setAttribute("poolvillaList", poolvillaList);//풀빌라 리스트
		 request.setAttribute("locationList", locationList);//지역선택 리스트
		 request.setAttribute("facilityList", facilityList); //부대시설 리스트
		request.setAttribute("checkedFacilityList", checkedFacilityList);//선택한 부대시설 리스트
		 //poolvillaList.jsp 요청
		request.getRequestDispatcher("/WEB-INF/view/poolvillaList.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//유효성 검사 예약기간 및 지역(기본검색 필수 값)이 없으면 홈화면으로 redirect
		if(request.getParameter("reservationBeginDate")== null||request.getParameter("reservationLastDate")==null||Integer.parseInt(request.getParameter("locationNo"))==-1) {
			response.sendRedirect(request.getContextPath()+"/all/homeController");
			return;
		}
		//필수 요청값 받기
		String reservationBeginDate = request.getParameter("reservationBeginDate");
		String reservationLastDate = request.getParameter("reservationLastDate");
		int locationNo = Integer.parseInt(request.getParameter("locationNo"));
		//상세검색 요청값 받기
		List<String> checkedFacilityList = new ArrayList<>();
		if (request.getParameterValues("checkedFacilityNo")!=null) {
			checkedFacilityList=Arrays.asList(request.getParameterValues("checkedFacilityNo")); //배열 값을 list로 형변환
			System.out.println("[/all/poolvillaListController.dopost()] checkedFacilityList.size : " + checkedFacilityList.size());
			request.setAttribute("checkedFacilityList", checkedFacilityList);
		}
		//요청값 디버깅
		System.out.println("[/all/poolvillaListController.dopost()] reservationBeginDate : " + reservationBeginDate);
		System.out.println("[/all/poolvillaListController.dopost()] reservationLastDate : " + reservationLastDate);
		System.out.println("[/all/poolvillaListController.dopost()] locationNo : " + locationNo);
		
		//페이징 요청값 받기
		//변수 선언
		int currentPage =1; // 현재페이지의 기본값이 1페이지
		if(request.getParameter("currentPage")!=null) { //currentPage 요청값이 0이 아니라면 요청값 받기
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
			System.out.println("[/all/poolvillaListController.dopost()] currentPage : " + currentPage);
		}
		int rowPerPage = 4;//페이지당 상품 수 : 기본값 6 
		int beginRow = (currentPage-1)*rowPerPage; //시작 행의 값
		int minPage =1; // 목록 첫 페이지 표시 숫자 1
		if(request.getParameter("minPage")!=null) { //minPage 요청값이 0이 아니라면 요청값 받기
			currentPage = Integer.parseInt(request.getParameter("minPage"));
			System.out.println("[/all/poolvillaListController.dopost()] minPage : " + minPage);
		}
		//정렬 부분
		String orderValue = "pv.update_date DESC"; //기본값
		
		//Dao 호출
		poolvillaDao = new PoolvillaDao();
		poolvillaLocationDao = new PoolvillaLocationDao();
		facilityDao = new FacilityDao();
		//검색 결과에 따른 모델값(poolvillaList) 요청
		List<Map<String,Object>> poolvillaList = poolvillaDao.selectPoolvillaListByDateLocation(reservationBeginDate, reservationLastDate, locationNo, orderValue, beginRow, rowPerPage, checkedFacilityList);
		System.out.println("[/all/poolvillaListController.dopost()] poolvillaList.size() : " + poolvillaList.size());
		//locationList 요청
		List<PoolvillaLocation> locationList = poolvillaLocationDao.selectPoolvillaLocationList();
		System.out.println("[/all/poolvillaListController.dopost()] locationList.size() : " + locationList.size());
		//facilityList 요청
		List<Facility> facilityList = facilityDao.selectFacilityList();
		System.out.println("[/all/poolvillaListController.dopost()] facilityList.size() : " + facilityList.size());
		
		//totalRow : 전체 행의 수 Dao에 요청
		int totalRow = poolvillaDao.selectPoolvillaTotalRowByDateLocation(reservationBeginDate, reservationLastDate, locationNo, orderValue, beginRow, rowPerPage, checkedFacilityList); 
		//마지막 페이지를 구하는 연산식
		int lastPage = ((totalRow - 1) / rowPerPage + 1); 
		//디버깅
		System.out.println("[/all/poolvillaListController.dopost()] totalRow : " +totalRow);
		System.out.println("[/all/poolvillaListController.dopost()] lastPage : " +lastPage);
		
		//모델값 setAttribute
		request.setAttribute("poolvillaList", poolvillaList); // 풀빌라 리스트
		request.setAttribute("locationList", locationList); // 지역 리스트
		request.setAttribute("facilityList", facilityList); //부대시설 리스트
		request.setAttribute("checkedFacilityList", checkedFacilityList);//선택한 부대시설 리스트
		request.setAttribute("reservationBeginDate", reservationBeginDate); // checkIn 날짜
		request.setAttribute("reservationLastDate", reservationLastDate); // ceckOut 날짜
		request.setAttribute("locationNo", locationNo); // locationNo
		//페이징 setAttribute
		request.setAttribute("currentPage", currentPage); //currentPage
		request.setAttribute("lastPage", lastPage); //lastPage
		request.setAttribute("minPage", minPage); //minPage
		//모델값 디버깅
		
		
		request.getRequestDispatcher("/WEB-INF/view/poolvillaList.jsp").forward(request, response);
	}

}
