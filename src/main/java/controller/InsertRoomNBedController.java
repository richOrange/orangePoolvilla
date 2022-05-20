package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.PoolvillaRoomDao;
import vo.PoolvillaRoom;

@WebServlet("/host/insertRoomNBedController")
public class InsertRoomNBedController extends HttpServlet {

	private PoolvillaRoomDao poolvillaRoomDao;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 디버깅
		System.out.println("[InsertPoolvillaRoomNBedController.doPost()] request pvNo : " + request.getParameter("pvNo"));
		System.out.println("[InsertPoolvillaRoomNBedController.doPost()] request roomNo : " + request.getParameter("roomNo"));
		System.out.println("[InsertPoolvillaRoomNBedController.doPost()] request bedSize : " + request.getParameter("bedSize"));
		System.out.println("[InsertPoolvillaRoomNBedController.doPost()] request bedCnt : " + request.getParameter("bedCnt"));
		
		// null 체크
		if (request.getParameter("pvNo") == null 
				|| request.getParameter("roomNo") == null
				|| request.getParameter("bedSize") == null 
				|| request.getParameter("bedCnt") == null) {
			System.out.println("[InsertRoomNBed.doPost()] null 존재");
			response.sendRedirect(request.getContextPath()+"/all/homeController?msg=null");
		}
		
		// 변수 등록
		int pvNo = 0;
		int roomNo = 0;
		String bedSize = "";
		int bedCnt = 0;
		
		if(Integer.parseInt(request.getParameter("pvNo")) != 0) {
			pvNo = Integer.parseInt(request.getParameter("pvNo"));
		}
		if(request.getParameter("roomType") != "") {
			roomNo = Integer.parseInt(request.getParameter("roomNo"));
		}
		if(request.getParameter("bedSize") != "") {
			bedSize = request.getParameter("bedSize");
		}
		if(request.getParameter("roomInfo") != "") {
			bedCnt = Integer.parseInt(request.getParameter("bedCnt"));
		}
		
		poolvillaRoomDao = new PoolvillaRoomDao();
		poolvillaRoomDao.insertRoomBed(roomNo, bedSize, bedCnt);
		
		response.sendRedirect(request.getContextPath() + "/host/insertPoolvillaRoomNBedController?pvNo=" + pvNo);
	}

}
