package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.PoolvillaRoomDao;
import vo.PoolvillaRoom;

@WebServlet("/host/poolvillaRoomController")
public class PoolvillaRoomController extends HttpServlet {
       
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PoolvillaRoomDao poolvillaRoomDao = new PoolvillaRoomDao();
		List<PoolvillaRoom> list = poolvillaRoomDao.selectPoolvillaRoomList();

		request.setAttribute("list", list);
		request.getRequestDispatcher("/WEB-INF/view/poolvillaRoomList.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// null 체크
				if (request.getParameter("pvNo") == null || request.getParameter("roomType") == null
						|| request.getParameter("roomName") == null || request.getParameter("roomInfo") == null
						|| request.getParameter("roomSize") == null){
					System.out.println("null 체크");
					response.sendRedirect(request.getContextPath() + "/host/poolvillaRoomController");
				}
				// 변수 등록
				int pvNo = 0;
				String roomType = "";
				String roomName = "";
				String roomInfo = "";
				double roomSize = 0;
				
				if(request.getParameter("pvNo") != null || request.getParameter("pvNo") !="") {
					pvNo = Integer.parseInt(request.getParameter("pvNo"));
				}
				if(request.getParameter("roomType") != null || request.getParameter("roomType") !="") {
					roomType = request.getParameter("roomType");
				}
				if(request.getParameter("roomName") != null || request.getParameter("roomName") !="") {
					roomName = request.getParameter("roomName");
				}
				if(request.getParameter("roomInfo") != null || request.getParameter("roomInfo") !="") {
					roomInfo = request.getParameter("roomInfo");
				}
				if(request.getParameter("roomSize") != null || request.getParameter("roomSize") !="") {
					roomSize = Double.parseDouble(request.getParameter("roomSize"));
				}
				
				
				PoolvillaRoom pr = new PoolvillaRoom();
				pr.setPvNo(pvNo);
				pr.setRoomType(roomType);
				pr.setRoomName(roomName);
				pr.setRoomInfo(roomInfo);
				pr.setRoomSize(roomSize);
				
				
				PoolvillaRoomDao poolvillaRoomDao= new PoolvillaRoomDao();
				poolvillaRoomDao.insertPoolvillaRoom(pr);
				
				response.sendRedirect(request.getContextPath() + "/host/poolvillaRoomController");
				
				
	}

}
