package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CookingToolDao;

@WebServlet("/host/deleteCookingToolController")
public class DeleteCookingToolController extends HttpServlet {
	
	private CookingToolDao cookingToolDao;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		cookingToolDao = new CookingToolDao();
		
		int pvNo = -1;
		if(Integer.parseInt(request.getParameter("pvNo")) != -1) {
			pvNo = Integer.parseInt(request.getParameter("pvNo"));
		}
		System.out.println("[DeleteCookingToolController.doGet()] pvNo : " + pvNo);
		
		// 요청값 처리
		int cookingToolNo  = 0;
		if(!request.getParameter("cookingToolNo").equals(0)) {
			cookingToolNo =  Integer.parseInt(request.getParameter("cookingToolNo"));
		}
		
		// 디버깅
		System.out.println("[DeleteCookingToolController.doGet()] cookingToolNo : " + cookingToolNo);
		
		cookingToolDao.deleteCookingTool(cookingToolNo);
		
		response.sendRedirect(request.getContextPath() + "/host/cookingToolController?pvNo=" + pvNo);
	}

}
