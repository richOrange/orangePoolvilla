package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.SuppliesDao;

@WebServlet("/host/deleteSuppliesController")
public class DeleteSuppliesController extends HttpServlet {

	private SuppliesDao suppliesDao;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 요청값 처리
		int suppliesNo  = 0;
		if(!request.getParameter("suppliesNo").equals(0)) {
			suppliesNo =  Integer.parseInt(request.getParameter("suppliesNo"));
		}
		
		// 디버깅
		System.out.println("[DeleteSuppliesController.doGet()] suppliesNo : " + suppliesNo);
		
		suppliesDao = new SuppliesDao();
		suppliesDao.deleteSupplies(suppliesNo);
		
		response.sendRedirect(request.getContextPath() + "/host/suppliesController");
	}

}
