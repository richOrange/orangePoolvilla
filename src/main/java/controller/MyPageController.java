package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
	private CustomerDao customerDao;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		request.getRequestDispatcher("/WEB-INF/view/openMyPage.jsp").forward(request, response);
		System.out.println("[MyPageController.doGet()]  이상무 ");
		
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Map<String,Object> sessionLoginMember = (Map<String,Object>)session.getAttribute("sessionLoginMember");
		
		String memberId = (String)sessionLoginMember.get("memberId");
		
		String memberPw = request.getParameter("memberPw");
		
		System.out.println("[MyPageController.doPost()] memberId : " + memberId);
		System.out.println("[MyPageController.doPost()] memberPw : " + memberPw);
		//Dao호출
		customerDao = new CustomerDao();
		//로그인 정보를 넣을 리스트 선언
		Map<String,Object> sessionCheckMember = new HashMap<>();
		//고객만 사용가능한 기능이니 고객만
		Customer customer = new Customer();
		customer.setCustomerId(memberId);
		customer.setCustomerPw(memberPw);
		sessionCheckMember = customerDao.loginCustomer(customer);
		
		
		
		if(sessionCheckMember.get("memberId") == null) { //비밀번호 확인 실패시
			System.out.println("[MyPageController.doPost()] sessionCheckMember : 비밀번호 확인 실패 " + sessionCheckMember);
			response.sendRedirect(request.getContextPath()+"/customer/myPageController");
			return;
		}
		
		
		System.out.println("[MyPageController.doPost()] sessionLoginMember : 비밀번호 확인 성공" + sessionCheckMember);
		response.sendRedirect(request.getContextPath()+"/customer/myPageOneController");
	}
}