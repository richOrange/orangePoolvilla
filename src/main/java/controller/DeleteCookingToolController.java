package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CookingToolDao;

@WebServlet("/deleteCookingToolController")
public class DeleteCookingToolController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		CookingToolDao cookingToolDao = new CookingToolDao();
		
		int cookingToolNo  = 0;
		System.out.println("cookingToolNo : " + cookingToolNo);
		if(!request.getParameter("cookingToolNo").equals(0)) {
			cookingToolNo =  Integer.parseInt(request.getParameter("cookingToolNo"));
		}
		
		cookingToolDao.deleteCookingTool(cookingToolNo);
		
		response.sendRedirect(request.getContextPath() + "/cookingToolController");
	}

}
