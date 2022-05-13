package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.HostDao;

@WebServlet("/deleteHostController")
public class DeleteHostController extends HttpServlet {
	
	HostDao hostDao = new HostDao();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String hostId = request.getParameter("hostId");
		
		request.setAttribute("hostId", hostId);
		
		request.getRequestDispatcher("/WEB-INF/view/deleteHost.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String hostId = request.getParameter("hostId");
		String hostPw = request.getParameter("hostPw");
		
		hostDao.deleteHost(hostId, hostPw);
		
		response.sendRedirect(request.getContextPath()+"/hostController");
	}

}
