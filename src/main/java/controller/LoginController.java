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


@WebServlet("/all/loginController")
public class LoginController extends HttpServlet {
	// 로그인 폼
	CustomerDao customerDao = new CustomerDao();
	
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			HttpSession session = request.getSession();
			String sessionCustomerId = (String)session.getAttribute("sessionCustomerId");
			
			if(sessionCustomerId != null) {
				response.sendRedirect(request.getContextPath()+"/all/loginController");
				System.out.println("[loginController.doPost()] notLogin");
				return;
			}
			request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
		}
		
		// 로그인 액션
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			String customerId = request.getParameter("customerId");
			String customerPw = request.getParameter("customerPw");
			System.out.println("[loginController.doPost()] CustomerId : " + customerId);
			System.out.println("[loginController.doPost()] CustomerPw : " + customerPw);
			Customer customer = new Customer();
			customer.setCustomerId(customerId);
			customer.setCustomerPw(customerPw);
			
			
			String returnCustomerId = customerDao.loginCustomer(customer);
			if(returnCustomerId == null) { //로그인 실패시
				System.out.println("[loginController.doPost()] returnCustomerId : " + returnCustomerId);
				response.sendRedirect(request.getContextPath()+"/all/loginController");
				return;
			}
			HttpSession session = request.getSession();
			session.setAttribute("sessionCustomerId", returnCustomerId);
			response.sendRedirect(request.getContextPath()+"/all/homeController");
		}
	}