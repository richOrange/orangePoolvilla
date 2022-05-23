package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.*;
import dao.FacilityDao;
import vo.CookingTool;
import vo.Facility;

@WebServlet("/host/facilityController")
public class FacilityController extends HttpServlet {
	
	private FacilityDao facilityDao;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int pvNo = -1;
		if(Integer.parseInt(request.getParameter("pvNo")) != -1) {
			pvNo = Integer.parseInt(request.getParameter("pvNo"));
		}
		System.out.println("[FacilityController.doGet()] pvNo : " + pvNo);
		
		facilityDao = new FacilityDao();
		List<Facility> list = facilityDao.selectFacilityList();
		
		request.setAttribute("list", list);
		request.setAttribute("pvNo", pvNo);
		
		request.getRequestDispatcher("/WEB-INF/view/facilityList.jsp").forward(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int pvNo = -1;
		if(Integer.parseInt(request.getParameter("pvNo")) != -1) {
			pvNo = Integer.parseInt(request.getParameter("pvNo"));
		}
		
		facilityDao = new FacilityDao();
		String facilityName = request.getParameter("facilityName");
		System.out.println("facilityName : " + facilityName);
		
		if(facilityName != null && facilityName != "") {
			
			facilityDao.insertFacility(facilityName);
		}
		
		response.sendRedirect(request.getContextPath() + "/host/facilityController?pvNo=" + pvNo);
	}
}
