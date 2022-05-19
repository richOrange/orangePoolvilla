package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.SuppliesDao;
import vo.CookingTool;
import vo.Supplies;

@WebServlet("/host/insertPoolvillaSuppliesController")
public class InsertPoolvillaSuppliesController extends HttpServlet {
	
	private SuppliesDao suppliesDao;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 널체크 null일 경우 home으로 response
		if(request.getParameter("pvNo") == null) {
			response.sendRedirect(request.getContextPath()+"/all/homeController?msg=null");
			return;
		}
		
		suppliesDao = new SuppliesDao();
		ArrayList<Supplies> sList = suppliesDao.selectSupplies();
		
		int pvNo = -1;
		System.out.println("[InsertPoolvillaSuppliesController.doGet()] pvNo : " + Integer.parseInt(request.getParameter("pvNo")));
		if(Integer.parseInt(request.getParameter("pvNo")) != -1) {
			pvNo = Integer.parseInt(request.getParameter("pvNo"));
			List<Map<String,Object>> psList = suppliesDao.selectPoolvillaSuppliesByPvNo(pvNo);
			request.setAttribute("psList", psList);
		}
				
		request.setAttribute("sList", sList);
		request.setAttribute("pvNo", pvNo);
		
		request.getRequestDispatcher("/WEB-INF/view/insertPoolvillaSupplies.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int pvNo = -1;
		if(Integer.parseInt(request.getParameter("pvNo")) != -1) {
			pvNo = Integer.parseInt(request.getParameter("pvNo"));
		}
		System.out.println("[InsertPoolvillaSuppliesController.doPost()] pvNo : " + pvNo);
		
		suppliesDao = new SuppliesDao();
		
		// 요청값 처리
		int suppliesNo = -1;
		String suppliesName = null;
		int suppliesCnt = -1;
		
		if(request.getParameter("suppliesName") != null 
				|| !request.getParameter("suppliesNo").equals(-1) 
				|| !request.getParameter("suppliesCnt").equals(-1)) {
			suppliesName =  request.getParameter("suppliesName");
			suppliesNo = Integer.parseInt(request.getParameter("suppliesNo"));
			suppliesCnt = Integer.parseInt(request.getParameter("suppliesCnt"));
			suppliesDao.insertPoolvillaSupplies(pvNo, suppliesNo, suppliesCnt);
		}
		
		response.sendRedirect(request.getContextPath() + "/host/insertPoolvillaSuppliesController?pvNo=" + pvNo);
	}

}