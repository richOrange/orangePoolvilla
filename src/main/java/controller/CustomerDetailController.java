package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.HostDao;
import vo.Customer;

@WebServlet("/customerDetailController")
public class CustomerDetailController extends HttpServlet {
	HostDao hostDao = new HostDao();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String customerId = request.getParameter("customerId");
		
		Customer customer = hostDao.selectCustomerDetail(customerId);
		
		request.setAttribute("customer", customer);
		
		request.getRequestDispatcher("/WEB-INF/view/customerDetail.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
