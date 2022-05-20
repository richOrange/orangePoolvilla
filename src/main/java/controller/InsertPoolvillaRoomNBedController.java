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

import dao.PoolvillaRoomDao;
import vo.CookingTool;
import vo.PoolvillaRoom;

@WebServlet("/host/insertPoolvillaRoomNBedController")
public class InsertPoolvillaRoomNBedController extends HttpServlet {
	
	private PoolvillaRoomDao poolvillaRoomDao;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("[InsertPoolvillaRoomNBedController.doGet()] pvNo : " + request.getParameter("pvNo"));
		
		// 널체크 null일 경우 home으로 response
		if(request.getParameter("pvNo") == null) {
			response.sendRedirect(request.getContextPath()+"/all/homeController?msg=null");
			return;
		} else {
			int pvNo = Integer.parseInt(request.getParameter("pvNo"));
			
			poolvillaRoomDao = new PoolvillaRoomDao();
			List<Map<String, Object>> prbList = poolvillaRoomDao.selectPoolvillaRoomNBedByPvNo(pvNo);
			List<Map<String,Object>> roomNameList = poolvillaRoomDao.selectPoolvillaRoomRoomName(pvNo);
			
			request.setAttribute("prbList", prbList);
			request.setAttribute("roomNameList", roomNameList);
			request.setAttribute("pvNo", pvNo);
		}
		
		request.getRequestDispatcher("/WEB-INF/view/insertPoolvillaRoomNBed.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
