package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.PoolvillaPhotoDao;

@WebServlet("/host/deletePoolvillaPhotoController")
public class DeletePoolvillaPhotoController extends HttpServlet {
    
	private PoolvillaPhotoDao poolvillaPhotoDao;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		poolvillaPhotoDao = new PoolvillaPhotoDao();
		
		// 요청값 처리
		//int photoNo  = 0;
		String photoName ="";
		int pvNo = 0;
		if(!request.getParameter("photoName").equals("") || !request.getParameter("pvNo").equals(0)) {
			//photoNo =  Integer.parseInt(request.getParameter("photoNo"));
			photoName =  request.getParameter("photoName");
			pvNo = Integer.parseInt(request.getParameter("pvNo"));
		}
		
		// 디버깅
		//System.out.println("[DeletePoolvillaPhotoController.doGet()] photoNo : " + photoNo);
		System.out.println("[DeletePoolvillaPhotoController.doGet()] pvNo : " + pvNo);
		
		poolvillaPhotoDao.deletePoolvillaPhoto(photoName);
		
		response.sendRedirect(request.getContextPath() + "/host/selectHostPoolvillaOneController?pvNo=" + pvNo);
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
