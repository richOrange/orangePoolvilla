package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.HostDao;
import vo.Host;

@WebServlet("/insertHostController")
public class InsertHostController extends HttpServlet {
	HostDao hostDao = new HostDao();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/view/insertHost.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String hostId = request.getParameter("hostId");
		String hostPw = request.getParameter("hostPw");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		
		Host host = new Host();
		
		host.setHostId(hostId);
		host.setHostPw(hostPw);
		host.setName(name);
		host.setEmail(email);
		host.setPhone(phone);
		
		hostDao.insertHost(host);
		
		response.sendRedirect(request.getContextPath()+"/hostController");
	}

}
