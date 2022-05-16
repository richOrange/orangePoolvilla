package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CustomerDao;
import vo.Customer;

@WebServlet("/customer/myPageOneController")
public class MyPageOneController extends HttpServlet {
	private CustomerDao customerDao;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String customerId = request.getParameter("customerId");
		
		Customer customer = customerDao.myPageCustomer(customerId);
		
		request.setAttribute("customer", customer);
	}



}
