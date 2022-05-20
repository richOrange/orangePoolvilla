package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.PoolvillaRoomDao;

@WebServlet("/host/deletePoolvillaRoomNBedController")
public class DeletePoolvillaRoomNBedController extends HttpServlet {
	
	private PoolvillaRoomDao poolvillaRoomDao;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		poolvillaRoomDao = new PoolvillaRoomDao();
		
		// 요청값 처리
		int roomNo  = 0;
		int pvNo = 0;
		if(!request.getParameter("roomNo").equals(0) || !request.getParameter("pvNo").equals(0)) {
			roomNo =  Integer.parseInt(request.getParameter("roomNo"));
			pvNo = Integer.parseInt(request.getParameter("pvNo"));
		}
		
		// 디버깅
		System.out.println("[DeletePoolvillaRoomNBed.doGet()] request roomNo : " + request.getParameter("roomNo"));
		System.out.println("[DeletePoolvillaRoomNBed.doGet()] request pvNo : " + request.getParameter("pvNo"));
		System.out.println("[DeletePoolvillaRoomNBed.doGet()] roomNo : " + roomNo);
		System.out.println("[DeletePoolvillaRoomNBed.doGet()] pvNo : " + pvNo);
		
		poolvillaRoomDao.deletePoolvillaRoomNBed(pvNo, roomNo);
		
		response.sendRedirect(request.getContextPath() + "/host/insertPoolvillaRoomNBedController?pvNo=" + pvNo);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
