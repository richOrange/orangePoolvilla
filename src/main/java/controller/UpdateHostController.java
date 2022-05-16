package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.HostDao;
import vo.Host;

@WebServlet("/updateHostController")
public class UpdateHostController extends HttpServlet {
	
	HostDao hostDao = new HostDao();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String hostId = request.getParameter("hostId");
		
		request.setAttribute("hostId", hostId);
		
		request.getRequestDispatcher("/WEB-INF/view/updateHost.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// updateHost.jsp 페이지에서 값 받음 
		
		String hostId = request.getParameter("hostId");
		System.out.println("[UpdateHostController.doPost()] hostId : " + hostId);
		
		String hostPw = request.getParameter("hostPw");
		System.out.println("[UpdateHostController.doPost()] hostPw : " + hostPw);
		
		int level = Integer.parseInt(request.getParameter("level"));
		System.out.println("[UpdateHostController.doPost()] level : " + level);
		
		String name = request.getParameter("name");
		System.out.println("[UpdateHostController.doPost()] name : " + name);
		
		String email = request.getParameter("email");
		System.out.println("[UpdateHostController.doPost()] email : " + email);
		
		String phone = request.getParameter("phone");
		System.out.println("[UpdateHostController.doPost()] phone : " + phone);
		
		Host host = new Host();
		
		host.setHostId(hostId);
		host.setHostPw(hostPw);
		host.setLevel(level);
		host.setName(name);
		host.setEmail(email);
		host.setPhone(phone);
		
		hostDao.updateHost(host);
		
		response.sendRedirect(request.getContextPath()+"/hostController");
	}

}
