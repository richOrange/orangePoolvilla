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
@WebServlet("/selectCustomerOneController")
public class SelectCustomerOneController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//session 값 요청
		HttpSession session = request.getSession();
	    String sessionCustomerId = (String)session.getAttribute("sessionCustomerId");
	    //로그인이 안되어있을 경우 LoginController로 보냄
	    if(sessionCustomerId == null) {
	        response.sendRedirect(request.getContextPath()+"/LoginController");
	        System.out.println("notLogin");
	        return;
	    }
	    //모델 호출 
	    CustomerDao CustomerDao = new CustomerDao();
	    Customer customer = CustomerDao.selectCustomerOne(sessionCustomerId);
	    request.setAttribute("Customer", customer);
	    request.getRequestDispatcher("/WEB-INF/view/customerOne.jsp").forward(request, response);
	}

}