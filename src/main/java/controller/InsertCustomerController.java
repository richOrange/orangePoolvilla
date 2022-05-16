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

@WebServlet("/all/insertCustomerController")
public class InsertCustomerController extends HttpServlet {
	
	private CustomerDao customerDao;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		if(session.getAttribute("sessionCustomerId") != null) {
			return;
		}
		// insertCustomerForm.jsp 호출
		request.getRequestDispatcher("/WEB-INF/view/insertCustomerForm.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 요청값 처리
		String customerId = null;
		String customerPw = null;
		String name = null;
		String gender = null;
		String birthDate = null;
		String emailId = null;
		String emailUrl = null;
		String phone = null;
		Customer customer = new Customer();
		
		if(request.getParameter("customerId") != null) {
			customerId = request.getParameter("customerId");
			customer.setCustomerId(customerId);
		}
		if(request.getParameter("customerPw1") != null) {
			customerPw = request.getParameter("customerPw1");
			customer.setCustomerPw(customerPw);
		}
		if(request.getParameter("name") != null) {
			name = request.getParameter("name");
			customer.setName(name);
		}
		if(request.getParameter("gender") != null) {
			gender = request.getParameter("gender");
			customer.setGender(gender);
		}
		if(request.getParameter("birth") != null) {
			birthDate = request.getParameter("birth");
			customer.setBirthDate(birthDate);
		}
		if(request.getParameter("emailId") != null && request.getParameter("emailUrl") != null) {
			emailId = request.getParameter("emailId");
			emailUrl = request.getParameter("emailUrl");
			customer.setEmail(emailId + "@" + emailUrl);
		}
		if(request.getParameter("phone") != null) {
			phone = request.getParameter("phone");
			customer.setPhone(phone);
		}
		
		//디버깅
	    System.out.println("[InsertCustomerController.doPost()] customer.toString() : " + customer.toString());
	    
	    customerDao = new CustomerDao();
	    customerDao.insertCustomer(customer);
	    
	    response.sendRedirect(request.getContextPath() + "/all/loginController");
	    
	}
}