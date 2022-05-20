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
		
		// 디버깅
		System.out.println("[InsertPoolvillaRoomNBedController.doPost()] request pvNo : " + request.getParameter("pvNo"));
		System.out.println("[InsertPoolvillaRoomNBedController.doPost()] request roomType : " + request.getParameter("roomType"));
		System.out.println("[InsertPoolvillaRoomNBedController.doPost()] request roomName : " + request.getParameter("roomName"));
		System.out.println("[InsertPoolvillaRoomNBedController.doPost()] request roomInfo : " + request.getParameter("roomInfo"));
		System.out.println("[InsertPoolvillaRoomNBedController.doPost()] request roomSize : " + request.getParameter("roomSize"));
		
		// null 체크
		if (request.getParameter("pvNo") == null || request.getParameter("roomType") == null
				|| request.getParameter("roomName") == null || request.getParameter("roomInfo") == null
				|| request.getParameter("roomSize") == null){
			System.out.println("[InsertPoolvillaRoomNBedController.doPost()] null 체크");
			response.sendRedirect(request.getContextPath()+"/all/homeController?msg=null");
		}
		
		// 변수 등록
		int pvNo = 0;
		String roomType = "";
		String roomName = "";
		String roomInfo = "";
		double roomSize = 0;
		
		if(Integer.parseInt(request.getParameter("pvNo")) != 0) {
			pvNo = Integer.parseInt(request.getParameter("pvNo"));
		}
		if(request.getParameter("roomType") != "") {
			roomType = request.getParameter("roomType");
		}
		if(request.getParameter("roomName") != "") {
			roomName = request.getParameter("roomName");
		}
		if(request.getParameter("roomInfo") != "") {
			roomInfo = request.getParameter("roomInfo");
		}
		if(request.getParameter("roomSize") != "") {
			roomSize = Double.parseDouble(request.getParameter("roomSize"));
		}
		
		PoolvillaRoom pr = new PoolvillaRoom();
		pr.setPvNo(pvNo);
		pr.setRoomType(roomType);
		pr.setRoomName(roomName);
		pr.setRoomInfo(roomInfo);
		pr.setRoomSize(roomSize);
		
		poolvillaRoomDao = new PoolvillaRoomDao();
		poolvillaRoomDao.insertPoolvillaRoom(pr);
		
		response.sendRedirect(request.getContextPath() + "/host/insertPoolvillaRoomNBedController?pvNo=" + pvNo);
	}

}
