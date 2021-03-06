package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.HostDao;
import vo.Host;

@WebServlet("/host/hostController")
public class HostController extends HttpServlet {
	HostDao hostDao = new HostDao();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 
		int currentPage = 1;
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		request.setAttribute("currentPage", currentPage);
		
		int rowPerPage = 10; 
		request.setAttribute("rowPerPage", rowPerPage);
		
		int beginRow = (currentPage-1) * rowPerPage;
		System.out.println("[HostController.doGet()] beginRow : "+beginRow);
		request.setAttribute("beginRow", beginRow);

        int totalRow = hostDao.selectHostTotalRow(); 
		request.setAttribute("totalRow", totalRow);
		
		int lastPage = 0;
		
		if(totalRow % rowPerPage == 0) {
			lastPage = totalRow / rowPerPage;
		} else {
			lastPage = (totalRow / rowPerPage) + 1;
		}
		
		request.setAttribute("lastPage", lastPage);
		// 
		
		ArrayList<Host> hostList = hostDao.selectHostList();
		
		request.setAttribute("hostList", hostList);
		
		request.getRequestDispatcher("/WEB-INF/view/hostList.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
