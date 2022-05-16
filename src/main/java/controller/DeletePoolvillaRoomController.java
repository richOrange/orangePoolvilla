package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.PoolvillaRoomDao;

@WebServlet("/host/deletePoolvillaRoomController")
public class DeletePoolvillaRoomController extends HttpServlet {
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 삭제할 정보 번호 받아오기
				int roomNo = 0;
				if(request.getParameter("roomNo") != null) {
					roomNo = Integer.parseInt(request.getParameter("roomNo"));
				}
				
				// 디버깅
				System.out.println("[DeletePoolvillaRoomController.doGet()] roomNo : " + "roomNo");
				
				
				PoolvillaRoomDao poolvillaRoomDao = new PoolvillaRoomDao(); // 메서드 사용을 위한 객체 생성
				int row = poolvillaRoomDao.deletePoolvillaRoom(roomNo);
				
				if(row == 1 ) {
					System.out.println("삭제 성공");
					response.sendRedirect(request.getContextPath() + "/host/poolvillaRoomController");
				} else {
					System.out.println("삭제 실패");
					response.sendRedirect(request.getContextPath() + "/host/poolvillaRoomController?roomNo="+roomNo);
				}
	}


}
