package controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CookingToolDao;
import dao.CustomerDao;
import vo.Customer;

@WebServlet("/customer/deleteCustomerController")
public class DeleteCustomerController extends HttpServlet {

	private CustomerDao customerDao;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 로그인 세션 불러오기
		HttpSession session = request.getSession();
		Map<String,Object> sessionLoginMember = (Map<String,Object>)session.getAttribute("sessionLoginMember");
		
		// 요청값 호출
		String memberId = (String)sessionLoginMember.get("memberId");
		
		//모델값 호출
		customerDao = new CustomerDao();
		
		//디버깅
		System.out.println("[/customer/deleteCustomerController.doget()] memberId : " + memberId);
		
		// 고객 정보 호출
		Customer myPageCustomer = customerDao.selectCustomerOneByCustomerId(memberId);
		
		//디버깅
		System.out.println("[/customer/deleteCustomerController.doget()] myPageCustomer.toString() : " + myPageCustomer.toString());
		
		// 모델값 setAttiribute
		request.setAttribute("myPageCustomer", myPageCustomer); // 해당 고객의	
		

		// 요청값 처리
		customerDao.deleteCustomer(memberId);
		
		
		response.sendRedirect(request.getContextPath() + "/all/logoutController");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
}