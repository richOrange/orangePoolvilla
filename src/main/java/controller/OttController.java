package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.OttDao;
import vo.Ott;

@WebServlet("/host/ottController")
public class OttController extends HttpServlet {
	private OttDao ottDao;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int pvNo = -1;
		if(Integer.parseInt(request.getParameter("pvNo")) != -1) {
			pvNo = Integer.parseInt(request.getParameter("pvNo"));
		}
		System.out.println("[OttController.doGet()] pvNo : " + pvNo);
		
		ottDao = new OttDao();
		List<Ott> list = ottDao.selectOttList();
		
		request.setAttribute("list", list);
		request.setAttribute("pvNo", pvNo);
		
		request.getRequestDispatcher("/WEB-INF/view/ottList.jsp").forward(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int pvNo = -1;
		if(Integer.parseInt(request.getParameter("pvNo")) != -1) {
			pvNo = Integer.parseInt(request.getParameter("pvNo"));
		}
		
		ottDao = new OttDao();
		String ottName = request.getParameter("ottName");
		System.out.println("ottName : " + ottName);
		if(ottName != null) {
			ottDao.insertOtt(ottName);
		}
		
		response.sendRedirect(request.getContextPath() + "/host/ottController?pvNo=" +pvNo);
	}

}
