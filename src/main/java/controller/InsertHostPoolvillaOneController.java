package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.PoolvillaDao;
import dao.PoolvillaLocationDao;
import vo.PoolvillaLocation;

@WebServlet("/host/insertHostPoolvillaOneController")
public class InsertHostPoolvillaOneController extends HttpServlet {
	
	private PoolvillaLocationDao poolvillaLocationDao;
	private PoolvillaDao poolvillaDao;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//		int pvNo = 0;
//		if(request.getParameter("pvNo") != null || request.getParameter("pvNo") != "") {
//	         pvNo = Integer.parseInt(request.getParameter("pvNo"));
//	         System.out.println("pvNo : " + pvNo);
//	         poolvillaDao.selectPoolvillaOne(pvNo);
//		}
		
		//jsp 호출
		request.getRequestDispatcher("/WEB-INF/view/insertHostPoolvillaOne.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// null 체크
		if(request.getParameter("pvNo") == null) {
			response.sendRedirect(request.getContextPath()+"/host/insertHostPoolvillaOneController");
		}
	}

}
