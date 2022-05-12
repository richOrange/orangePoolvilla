package controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.SuppliesDao;
import dao.PoolvillaDao;
@WebServlet("/all/poolvillaListController")
public class PoolvillaListController extends HttpServlet {
	private PoolvillaDao poolvillaDao;//PoolvillaDao 호출

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//home에서의 검색결과에 따른 리스트
		//유효성 검사 예약기간과, 지역이 없으면 홈화면으로 redirect
		if(request.getParameter("reservationBeginDate")==null||request.getParameter("reservationLastDate")==null||request.getParameter("locationNo")==null) {
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
		int beginRow = 0;
		int rowPerPage = 5;
		//Dao 호출
		this.poolvillaDao = new PoolvillaDao();
		List<Map<String,Object>> poolvillaList = poolvillaDao.selectPoolvillaListByDateLocation(reservationBeginDate, reservationLastDate,locationNo, beginRow, rowPerPage);
		request.setAttribute("poolvillaList", poolvillaList);
		request.getRequestDispatcher("/WEB-INF/view/poolvillaList.jsp").forward(request, response);
		//모델값 디버깅
		System.out.println("[/all/poolvillaListController.doget()] poolvillaList.size() : " + poolvillaList.size());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
