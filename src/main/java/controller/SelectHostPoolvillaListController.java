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
		
		HttpSession session = request.getSession();
		Map<String,Object> sessionLoginMember = (Map<String,Object>)session.getAttribute("sessionLoginMember");
		String hostId = (String)sessionLoginMember.get("memberId");
		System.out.println("[/host/selectHostPoolvillaListController.doget()] hostId(sessionMemberId) : " + hostId);
		
		/*
		// 페이징
		// 변수 선언
		int currentPage =1; // 현재 페이지의 기본값이 1페이지
		if(request.getParameter("currentPage")!=null) { //currentPage 요청값이 0이 아니라면 요청값 받기
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
			System.out.println("[/host/selectHostPoolvillaListController.doget()] currentPage : " + currentPage);
		}
		int rowPerPage = 6;// 페이지 당 상품 수 : 기본값 6 
		int beginRow = (currentPage-1)*rowPerPage; // 시작 행의 값
		int minPage =1; // 목록 첫 페이지 표시 숫자 1
		if(request.getParameter("minPage")!=null) { // minPage 요청값이 0이 아니라면 요청값 받기
			currentPage = Integer.parseInt(request.getParameter("minPage"));
			System.out.println("[/host/selectHostPoolvillaListController.doget()] minPage : " + minPage);
		}
		
		hostDao = new HostDao();
		List<Map<String,Object>> poolvillaList = hostDao.selectPoolvillaList(beginRow, rowPerPage); // 페이징을 적용한 리스트
		*/
		
		poolvillaDao = new PoolvillaDao();
		List<Map<String,Object>> poolvillaList = poolvillaDao.selectPoolvillaList();
		System.out.println("[/host/selectHostPoolvillaListController.doget()] poolvillaList.size() : " + poolvillaList.size());
		request.setAttribute("poolvillaList", poolvillaList);
		
		request.getRequestDispatcher("/WEB-INF/view/selectHostPoolvillaList.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
