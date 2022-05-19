package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.FacilityDao;

/**
 * Servlet implementation class DeletePoolvillaFacilityController
 */
@WebServlet("/host/deletePoolvillaFacilityController")
public class DeletePoolvillaFacilityController extends HttpServlet {
       
	private FacilityDao facilityDao;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		facilityDao = new FacilityDao();
		
		// 요청값 처리
		int facilityNo  = 0;
		int pvNo = 0;
		if(!request.getParameter("facilityNo").equals(0) || !request.getParameter("pvNo").equals(0)) {
			facilityNo =  Integer.parseInt(request.getParameter("facilityNo"));
			pvNo = Integer.parseInt(request.getParameter("pvNo"));
		}
		
		// 디버깅
		System.out.println("[DeletePoolvillaFacilityController.doGet()] facilityNo : " + facilityNo);
		System.out.println("[DeletePoolvillaFacilityController.doGet()] pvNo : " + pvNo);
		
		facilityDao.deletePoolvillFacility(pvNo, facilityNo);
		
		response.sendRedirect(request.getContextPath() + "/host/insertPoolvillaFacilityController?pvNo=" + pvNo);
	}
		

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
