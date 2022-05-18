package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CustomerDao;
import vo.Customer;

@WebServlet("/host/customerDetailController")
//관리자가 회원하나의 상세정보를 보는 컨트롤러
public class CustomerDetailController extends HttpServlet {
	private CustomerDao customerDao;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//널체크 customerId가 없으면, 회원관리리스트로 넘어감
		if(request.getParameter("customerId")==null) {
			response.sendRedirect(request.getContextPath()+"/host/customerCheckController");
			return;
		}
		//요청값받기
		String customerId = request.getParameter("customerId");
		//dao 호출
		customerDao = new CustomerDao();
		Customer customer = customerDao.selectCustomerOneByCustomerId(customerId);
		//모델값 setAttribute
		request.setAttribute("customer", customer);
		//뷰
		request.getRequestDispatcher("/WEB-INF/view/customerDetail.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
