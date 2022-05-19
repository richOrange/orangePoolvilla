package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.SuppliesDao;

@WebServlet("/host/deletePoolvillaSuppliesController")
public class DeletePoolvillaSuppliesController extends HttpServlet {

	private SuppliesDao suppliesDao;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		suppliesDao = new SuppliesDao();
		
		// 요청값 처리
		int suppliesNo  = 0;
		int pvNo = 0;
		if(!request.getParameter("suppliesNo").equals(0) || !request.getParameter("pvNo").equals(0)) {
			suppliesNo =  Integer.parseInt(request.getParameter("suppliesNo"));
			pvNo = Integer.parseInt(request.getParameter("pvNo"));
		}
		
		// 디버깅
		System.out.println("[DeletePoolvillaSuppliesController.doGet()] suppliesNo : " + suppliesNo);
		System.out.println("[DeletePoolvillaSuppliesController.doGet()] pvNo : " + pvNo);
		
		suppliesDao.deletePoolvillaSupplies(pvNo, suppliesNo);
		
		response.sendRedirect(request.getContextPath() + "/host/insertPoolvillaSuppliesController?pvNo=" + pvNo);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
