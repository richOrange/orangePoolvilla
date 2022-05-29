package controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.PoolvillaDao;

@WebServlet("/host/selectHostPoolvillaListController")
public class SelectHostPoolvillaListController extends HttpServlet {
	
	private PoolvillaDao poolvillaDao;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		
		poolvillaDao = new PoolvillaDao();
		List<Map<String,Object>> poolvillaList = poolvillaDao.selectPoolvillaList(beginRow,rowPerPage);
		System.out.println("[/host/selectHostPoolvillaListController.doget()] poolvillaList.size() : " + poolvillaList.size());
		int totalRow = poolvillaDao.selectPoolvillatotalRow(beginRow, rowPerPage); // 페이징에 이용할 total Row
		//마지막 페이지를 구하는 연산식
		int lastPage = ((totalRow - 1) / rowPerPage + 1); 
		//디버깅
		System.out.println("[/all/selectHostPoolvillaListController.doGet()] totalRow : " +totalRow);
		System.out.println("[/all/selectHostPoolvillaListController.doGet()] lastPage : " +lastPage);
		
		//모델값 setAttribute
		request.setAttribute("poolvillaList", poolvillaList); // 풀빌라 리스트
		//페이징 setAttribute
		request.setAttribute("currentPage", currentPage); //currentPage
		request.setAttribute("lastPage", lastPage); //lastPage
		request.setAttribute("minPage", minPage); //minPage
		request.getRequestDispatcher("/WEB-INF/view/selectHostPoolvillaList.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
