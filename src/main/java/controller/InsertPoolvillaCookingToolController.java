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

import dao.CookingToolDao;
import vo.CookingTool;
import vo.PoolvillaCookingTool;

@WebServlet("/host/insertPoolvillaCookingToolController")
public class InsertPoolvillaCookingToolController extends HttpServlet {
	
	private CookingToolDao cookingToolDao;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 널체크 null일 경우 home으로 response
		if(request.getParameter("pvNo") == null) {
			response.sendRedirect(request.getContextPath()+"/all/homeController?msg=null");
			return;
		}
		
		int pvNo = Integer.parseInt(request.getParameter("pvNo"));
		System.out.println("[InsertPoolvillaCookingToolController.doGet()] pvNo : " + pvNo);
		cookingToolDao = new CookingToolDao();
		
		ArrayList<CookingTool> list = cookingToolDao.selectCookingTool();
		if(Integer.parseInt(request.getParameter("pvNo")) != -1) {
			List<Map<String,Object>> pctList = cookingToolDao.selectPoolvillaCookingTool(Integer.parseInt(request.getParameter("pvNo")));
			request.setAttribute("pctList", pctList);
		}
		
		request.setAttribute("list", list);
		request.setAttribute("pvNo", pvNo);
		
		request.getRequestDispatcher("/WEB-INF/view/insertPoolvillaCookingTool.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int pvNo = -1;
		if(Integer.parseInt(request.getParameter("pvNo")) != -1) {
			pvNo = Integer.parseInt(request.getParameter("pvNo"));
		}
		System.out.println("[InsertPoolvillaCookingToolController.doPost()] pvNo : " + pvNo);
		
		cookingToolDao = new CookingToolDao();
		
		// 요청값 처리
		int cookingToolNo = -1;
		String cookingToolName = null;
		int cookingToolCnt = -1;
		
		if(request.getParameter("cookingToolName") != null 
				|| !request.getParameter("cookingToolNo").equals(-1) 
				|| !request.getParameter("cookingToolCnt").equals(-1)) {
			cookingToolName =  request.getParameter("cookingToolName");
			cookingToolNo = Integer.parseInt(request.getParameter("cookingToolNo"));
			cookingToolCnt = Integer.parseInt(request.getParameter("cookingToolCnt"));
			cookingToolDao.insertPoolvillaCookingTool(pvNo, cookingToolNo, cookingToolCnt);
		}
		
		// 디버깅
		System.out.println("[InsertPoolvillaCookingToolController.doPost()] cookingToolNo : " + cookingToolNo);
		System.out.println("[InsertPoolvillaCookingToolController.doPost()] cookingToolName : " + cookingToolName);
		System.out.println("[InsertPoolvillaCookingToolController.doPost()] cookingToolCnt : " + cookingToolCnt);
		
		response.sendRedirect(request.getContextPath() + "/host/insertPoolvillaCookingToolController?pvNo=" + pvNo);
	}

}