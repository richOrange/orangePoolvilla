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
import dao.PoolvillaLocationDao;
import vo.PoolvillaLocation;
@WebServlet("/all/poolvillaListController")
public class PoolvillaListController extends HttpServlet {
	private PoolvillaDao poolvillaDao;//PoolvillaDao 호출
	private PoolvillaLocationDao poolvillaLocationDao;//poolvillaLocationDao 호출

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//home에서의 검색결과에 따른 리스트
		//유효성 검사 예약기간과, 지역이 없으면 홈화면으로 redirect
		if(request.getParameter("reservationBeginDate")== null||request.getParameter("reservationLastDate")==null||Integer.parseInt(request.getParameter("locationNo"))==-1) {
		response.sendRedirect(request.getContextPath()+"/all/homeController");
		return;
		}
		//요청값 받기
		String reservationBeginDate = request.getParameter("reservationBeginDate");
		String reservationLastDate = request.getParameter("reservationLastDate");
		int locationNo = Integer.parseInt(request.getParameter("locationNo"));
		//요청값 디버깅
		System.out.println("[/all/poolvillaListController.doget()] reservationBeginDate : " + reservationBeginDate);
		System.out.println("[/all/poolvillaListController.doget()] reservationLastDate : " + reservationLastDate);
		System.out.println("[/all/poolvillaListController.doget()] locationNo : " + locationNo);
		//페이징
		//변수 선언
		int currentPage =1; // 현재페이지의 기본값이 1페이지
		if(request.getParameter("currentPage")!=null) { //currentPage 요청값이 0이 아니라면 요청값 받기
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
			System.out.println("[/all/poolvillaListController.doget()] currentPage : " + currentPage);
		}
		int rowPerPage = 6;//페이지당 상품 수 : 기본값 6 
		int beginRow = (currentPage-1)*rowPerPage; //시작 행의 값
		int minPage =1; // 목록 첫 페이지 표시 숫자 1
		if(request.getParameter("minPage")!=null) { //minPage 요청값이 0이 아니라면 요청값 받기
			currentPage = Integer.parseInt(request.getParameter("minPage"));
			System.out.println("[/all/poolvillaListController.doget()] minPage : " + minPage);
		}
		
		//Dao 호출
		poolvillaDao = new PoolvillaDao();
		poolvillaLocationDao = new PoolvillaLocationDao();
		//poolvillaList
		List<Map<String,Object>> poolvillaList = poolvillaDao.selectPoolvillaListByDateLocation(reservationBeginDate, reservationLastDate,locationNo, beginRow, rowPerPage);
		System.out.println("[/all/poolvillaListController.doget()] poolvillaList.size() : " + poolvillaList.size());
		//locationList
		List<PoolvillaLocation> locationList = poolvillaLocationDao.selectPoolvillaLocationList();
		//totalRow : 전체 행의 수 변수
		int totalRow = 13; 
		//마지막 페이지를 구하는 연산식
		int lastPage = ((totalRow - 1) / rowPerPage + 1); 
		//디버깅
		System.out.println("[/all/poolvillaListController.doget()] totalRow : " +totalRow);
		System.out.println("[/all/poolvillaListController.doget()] lastPage : " +lastPage);
		//모델값 setAttribute
		request.setAttribute("poolvillaList", poolvillaList); // 풀빌라 리스트
		request.setAttribute("locationList", locationList); // 지역 리스트
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
