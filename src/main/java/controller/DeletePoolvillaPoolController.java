package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.PoolvillaPoolDao;

/**
 * Servlet implementation class DeletePoolvillaPoolController
 */
@WebServlet("/host/deletePoolvillaPoolController")
public class DeletePoolvillaPoolController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 삭제할 정보 번호 받아오기
		int poolNo = 0;
		if(request.getParameter("poolNo") != null) {
			poolNo = Integer.parseInt(request.getParameter("poolNo"));
		}
		int pvNo = 0;
		if(request.getParameter("pvNo") != null) {
			pvNo = Integer.parseInt(request.getParameter("pvNo"));
		}
		
		// 디버깅
		System.out.println("[DeletePoolController.doGet()] poolNo : " + poolNo);
		System.out.println("[DeletePoolController.doGet()] pvNo : " + pvNo);
		
		
		PoolvillaPoolDao poolvillapoolDao = new PoolvillaPoolDao(); // 메서드 사용을 위한 객체 생성
		int row = poolvillapoolDao.deletePoolvillaPool(pvNo, poolNo);
		
		if(row == 1 ) {
			System.out.println("삭제 성공");
			response.sendRedirect(request.getContextPath() + "/host/insertPoolvillaPoolController?pvNo=" + pvNo);
		} else {
			System.out.println("삭제 실패");
			response.sendRedirect(request.getContextPath() + "/host/insertPoolvillaPoolController?poolNo="+poolNo);
		}

	}

}
