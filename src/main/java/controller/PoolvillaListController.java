package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.FacilityDao;
import dao.PoolvillaDao;
import dao.PoolvillaLocationDao;
import vo.Facility;
import vo.PoolvillaLocation;
@WebServlet("/all/poolvillaListController")
public class PoolvillaListController extends HttpServlet {
	private PoolvillaDao poolvillaDao;//PoolvillaDao 호출 , 풀빌라 메인 테이블 정보
	private PoolvillaLocationDao poolvillaLocationDao;//poolvillaLocationDao 호출, 지역정보
	private FacilityDao facilityDao;//facilityDao 호출, 부대시설
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//상세검색 및 페이징을 위해 dopost만 이용
		//doget으로 요청시 home으로 redirect
		response.sendRedirect(request.getContextPath()+"/all/homeController");
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
		int rowPerPage = 6;//페이지당 상품 수 : 기본값 6 
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
		
		//totalRow : 전체 행의 수 변수
		int totalRow = 13; 
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
