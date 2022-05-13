package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CustomerDao;
import vo.Customer;

@WebServlet("/customer/myPageOneController")
public class MyPageOneController extends HttpServlet {
	private CustomerDao customerDao;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//session 값 요청
				HttpSession session = request.getSession();
			    String sessionCustomerId = (String)session.getAttribute("sessionCustomerId");
			    //모델 호출
			    customerDao = new CustomerDao();
			    Customer customer = customerDao.selectMyPage(sessionCustomerId);
			    ArrayList<Customer> list = customerDao.myPageCustomer();
			    request.setAttribute("list", list);
			    request.getRequestDispatcher("/WEB-INF/view/myPageOne.jsp").forward(request, response);
	}



}
