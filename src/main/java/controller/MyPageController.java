package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CustomerDao;
import vo.Customer;

@WebServlet("/customer/myPageController")
public class MyPageController extends HttpServlet {
	CustomerDao customerDao = new CustomerDao();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String sessionCustomerId = (String)session.getAttribute("sessionCustomerId");
		
		request.getRequestDispatcher("/WEB-INF/view/openMyPage.jsp").forward(request, response);
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String customerPw = request.getParameter("customerPw");
		System.out.println("[/customer/myPageController.doPost()] CustomerPw : " + customerPw);
		HttpSession session = request.getSession();
		Customer sessionLogincustomer =new Customer();
		sessionLogincustomer = (Customer)session.getAttribute("sessionLogincustomer");
		Customer customer = new Customer();
		customer.setCustomerId(sessionLogincustomer.getCustomerId());
		customer.setCustomerPw(customerPw);
		System.out.println(customer.toString());
		
		
		Customer returnCustomerId = customerDao.loginCustomer(customer);
		if(returnCustomerId == null) { //로그인 실패시
			System.out.println("[/customer/MyPageController.doPost()] returnCustomerId : " + returnCustomerId);
			response.sendRedirect(request.getContextPath()+"/customer/myPageController");
			return;
		}
		
		response.sendRedirect(request.getContextPath()+"/customer/myPageOneController");
	}
}