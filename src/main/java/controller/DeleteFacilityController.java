package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.FacilityDao;

@WebServlet("/deleteFacilityController")
public class DeleteFacilityController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 삭제할 정보 번호 받아오기
		int facilityNo = 0;
		if(request.getParameter("facilityNo") != null) {
			facilityNo = Integer.parseInt(request.getParameter("facilityNo"));
		}
		
		// 디버깅
		System.out.println("[DeleteFacilityController.doGet()] facilityNo : " + "facilityNo");
		
		
		FacilityDao facilityDao = new FacilityDao(); // 메서드 사용을 위한 객체 생성
		int row = facilityDao.deleteFacility(facilityNo);
		
		if(row == 1 ) {
			System.out.println("삭제 성공");
			response.sendRedirect(request.getContextPath() + "/facilityController");
		} else {
			System.out.println("삭제 실패");
			response.sendRedirect(request.getContextPath() + "/facilityController?facilityNo="+facilityNo);
		}

	}

}
