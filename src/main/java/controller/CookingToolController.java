package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CookingToolDao;
import vo.CookingTool;

@WebServlet("/host/cookingToolController")
public class CookingToolController extends HttpServlet {

	private CookingToolDao cookingToolDao;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int pvNo = -1;
		if(Integer.parseInt(request.getParameter("pvNo")) != -1) {
			pvNo = Integer.parseInt(request.getParameter("pvNo"));
		}
		System.out.println("[CookingToolController.doGet()] pvNo : " + pvNo);
		
		cookingToolDao = new CookingToolDao();
		ArrayList<CookingTool> list = cookingToolDao.selectCookingTool();
		
		request.setAttribute("list", list);
		request.setAttribute("pvNo", pvNo);
		
		request.getRequestDispatcher("/WEB-INF/view/cookingToolList.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		cookingToolDao = new CookingToolDao();
		
		// 요청값 처리
		int pvNo = -1;
		if(Integer.parseInt(request.getParameter("pvNo")) != -1) {
			pvNo = Integer.parseInt(request.getParameter("pvNo"));
		}
		System.out.println("[CookingToolController.doPost()] pvNo : " + pvNo);
		
		String cookingToolName = null;
		if(request.getParameter("cookingToolName") != null) {
			cookingToolName =  request.getParameter("cookingToolName");
			cookingToolDao.insertCookingTool(cookingToolName);
		}
		
		// 디버깅
		System.out.println("[CookingToolController.doPost()] cookingToolName : " + cookingToolName);
		
		response.sendRedirect(request.getContextPath() + "/host/cookingToolController?pvNo=" + pvNo);
	}

}
