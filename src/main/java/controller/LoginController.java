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


@WebServlet("/loginController")
public class LoginController extends HttpServlet {
	// 로그인 폼
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			HttpSession session = request.getSession();
			String sessionCustomerId = (String)session.getAttribute("sessionCustomerId");
			if(sessionCustomerId != null) {
				// 이미 로그인이 되어 있는 상태라면
				response.sendRedirect(request.getContextPath()+"/loginController");
				return;
			}
			// 로그인 되어있는 멤버라면 리다이렉트
			request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
		}
		// 로그인 액션
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// 로그인 되어있는 멤버라면 리다이렉트
			String customerId = request.getParameter("CustomerId");
			String customerPw = request.getParameter("CustomerPw");
			Customer customer = new Customer();
			customer.setCustomerId(customerId);
			customer.setCustomerPw(customerPw);
			
			// 모델 호출
			CustomerDao customerDao = new CustomerDao();
			String returnCustomerId = customerDao.selectCustomerByIdPw(customer);
			if(returnCustomerId == null) {
				// 로그인 실패시 로그인 폼을 재요청
				System.out.println("로그인실패 <-- loginController.doPost()");
				response.sendRedirect(request.getContextPath()+"/loginController");
				return;
			}
			// 로그인 성공
			HttpSession session = request.getSession(); // 현재 연결한 클라이언트(브라우저)에 대한 세션값을 받아옴
			session.setAttribute("sessionCustomerId", returnCustomerId);
		}
	}