package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.OttDao;

@WebServlet("/deleteOttController")
public class DeleteOttController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 삭제할 정보 번호 받아오기
		int ottNo = 0;
		if(request.getParameter("ottNo") != null) {
			ottNo = Integer.parseInt(request.getParameter("ottNo"));
		}
		
		// 디버깅
		System.out.println("[DeletOttController.doGet()] ottNo : " + "ottNo");
		
		
		OttDao ottDao = new OttDao(); // 메서드 사용을 위한 객체 생성
		int row = ottDao.deleteOtt(ottNo);
		
		if(row == 1 ) {
			System.out.println("삭제 성공");
			response.sendRedirect(request.getContextPath() + "/ottController");
		} else {
			System.out.println("삭제 실패");
			response.sendRedirect(request.getContextPath() + "/ottController?ottNo="+ottNo);
		}

	}

}
