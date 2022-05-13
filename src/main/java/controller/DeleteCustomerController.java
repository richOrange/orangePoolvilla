package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CookingToolDao;
import dao.CustomerDao;
import vo.Customer;

@WebServlet("/all/deleteCustomerController")
public class DeleteCustomerController extends HttpServlet {

	private CustomerDao customerDao;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		customerDao = new CustomerDao();

		// 요청값 처리
		String customerId = "";
		String customerPw = "";
		if (!request.getParameter("customerId").equals(0)) {
			customerId = request.getParameter("customerId");
		}

		// 디버깅
		System.out.println("[DeleteCustomerController.doGet()] customerNo : " + customerId);

		customerDao.deleteCustomer(customerId, customerPw);

		response.sendRedirect(request.getContextPath() + "/all/updateCustomerController");
	}

}