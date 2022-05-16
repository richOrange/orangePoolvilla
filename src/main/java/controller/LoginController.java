package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CustomerDao;
import dao.HostDao;
import vo.Customer;
import vo.Host;


@WebServlet("/all/loginController")
public class LoginController extends HttpServlet {
	// 로그인 폼
	private CustomerDao customerDao;
	private HostDao hostDao;
	
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			HttpSession session = request.getSession();
			Map<String,Object> sessionLoginMember = (Map<String,Object>)session.getAttribute("sessionLoginMember");
			
			if(sessionLoginMember != null) {
				// 이미 로그인이 되어 있는 상태라면
				response.sendRedirect(request.getContextPath()+"/all/homeController");
				return;
			}
			
			request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
		}
		
		// 로그인 액션
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			String memberId = request.getParameter("memberId");
			String memberPw = request.getParameter("memberPw");
			
			System.out.println("[loginController.doPost()] memberId : " + memberId);
			System.out.println("[loginController.doPost()] memberPw : " + memberPw);
			//Dao호출
			customerDao = new CustomerDao();
			hostDao = new HostDao();
			//로그인 정보를 넣을 리스트 선언
			Map<String,Object> sessionLoginMember = new HashMap<>();
			//관리자 로그인이 체크 되어 있을경우
			if(request.getParameter("hostLogin") != null) {
				System.out.println("[loginController.doPost()] hostLogin : 관리자로그인 " + request.getParameter("hostLogin"));
				Host host = new Host();
				host.setHostId(memberId);
				host.setHostPw(memberPw);
				sessionLoginMember = hostDao.loginHost(host);
				
			} else {
				Customer customer = new Customer();
				customer.setCustomerId(memberId);
				customer.setCustomerPw(memberPw);
				sessionLoginMember = customerDao.loginCustomer(customer);
				
			}
			
			if(sessionLoginMember.get("memberId") == null) { //로그인 실패시
				System.out.println("[loginController.doPost()] sessionLoginMember : 로그인실패 " + sessionLoginMember);
				response.sendRedirect(request.getContextPath()+"/all/loginController");
				return;
			}
			HttpSession session = request.getSession();
			
			session.setAttribute("sessionLoginMember", sessionLoginMember);
			
			response.sendRedirect(request.getContextPath()+"/all/homeController");
		}
	}