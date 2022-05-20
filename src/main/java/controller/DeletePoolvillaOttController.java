package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.OttDao;

@WebServlet("/host/deletePoolvillaOttController")
public class DeletePoolvillaOttController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	// 요청값 처리
	int ottNo  = 0;
	int pvNo = 0;
	if(!request.getParameter("ottNo").equals(0) || !request.getParameter("pvNo").equals(0)) {
		ottNo =  Integer.parseInt(request.getParameter("ottNo"));
		pvNo = Integer.parseInt(request.getParameter("pvNo"));
	}
				
	// 디버깅
	System.out.println("[DeletePoolvillaOttController.doGet()] ottNo : " + ottNo);
	System.out.println("[DeletePoolvillaOttController.doGet()] pvNo : " + pvNo);
	
	OttDao ottDao = new OttDao();
	
	ottDao.deletePoolvillaOtt(pvNo, ottNo);
				
	response.sendRedirect(request.getContextPath() + "/host/insertPoolvillaOttController?pvNo=" + pvNo);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
