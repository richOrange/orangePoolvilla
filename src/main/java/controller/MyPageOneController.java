package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CustomerDao;
import vo.CookingTool;
import vo.Customer;

@WebServlet("/customer/myPageOneController")
public class MyPageOneController extends HttpServlet {
	private CustomerDao customerDao;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		customerDao = new CustomerDao();
		
		HttpSession session = request.getSession();
		Map<String,Object> sessionLoginMember = (Map<String,Object>)session.getAttribute("sessionLoginMember");
		
		ArrayList<Customer> list = customerDao.myPageCustomer();
		
		session.setAttribute("list", list);
		
		request.getRequestDispatcher("/WEB-INF/view/myPageOne.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
