package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CookingToolDao;

@WebServlet("/host/deletePoolvillaCookingToolController")
public class DeletePoolvillaCookingToolController extends HttpServlet {
	
	private CookingToolDao cookingToolDao;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		cookingToolDao = new CookingToolDao();
		
		// 요청값 처리
		int cookingToolNo  = 0;
		int pvNo = 0;
		if(!request.getParameter("cookingToolNo").equals(0) || !request.getParameter("pvNo").equals(0)) {
			cookingToolNo =  Integer.parseInt(request.getParameter("cookingToolNo"));
			pvNo = Integer.parseInt(request.getParameter("pvNo"));
		}
		
		// 디버깅
		System.out.println("[DeletePoolvillaCookingToolController.doGet()] cookingToolNo : " + cookingToolNo);
		System.out.println("[DeletePoolvillaCookingToolController.doGet()] pvNo : " + pvNo);
		
		cookingToolDao.deletePoolvillaCookingTool(pvNo, cookingToolNo);
		
		response.sendRedirect(request.getContextPath() + "/host/insertPoolvillaCookingToolController?pvNo=" + pvNo);
	}

}
