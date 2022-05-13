package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.PoolvillaLocationDao;

@WebServlet("/host/deletePoolvillaLocationController")
public class DeletePoolvillaLocationController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 삭제할 정보 번호 받아오기
		int locationNo = 0;
		if (request.getParameter("locationNo") != null) {
			locationNo = Integer.parseInt(request.getParameter("locationNo"));
		}

		// 디버깅
		System.out.println("[DeletePoolvillaLocationController.doGet()] locationNo : " + "locationNo");

		PoolvillaLocationDao poolvillaLocationDao = new PoolvillaLocationDao(); // 메서드 사용을 위한 객체 생성
		int row = poolvillaLocationDao.deletePoolvillaLocation(locationNo);

		if (row == 1) {
			System.out.println("삭제 성공");
			response.sendRedirect(request.getContextPath() + "/host/poolvillaLocationController");
		} else {
			System.out.println("삭제 실패");
			response.sendRedirect(request.getContextPath() + "/host/poolvillaLocationController?locationNo=" + locationNo);
		}

	}
}
