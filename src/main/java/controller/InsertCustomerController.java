package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CustomerDao;
import dao.HostDao;
import vo.Customer;

@WebServlet("/all/insertCustomerController")
public class InsertCustomerController extends HttpServlet {
	
	private CustomerDao customerDao;
	private HostDao hostDao;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//로그인 여부 체크
		HttpSession session = request.getSession();
		if(session.getAttribute("sessionLoginMember") != null) {
			response.sendRedirect(request.getContextPath()+"/all/homeController");
			return;
		}
		//아이디 중복 체크 기능
		//dao 호출
		customerDao = new CustomerDao();
		hostDao = new HostDao();
		String checkId = null;
		if(request.getParameter("checkId") != null) {
			checkId = request.getParameter("checkId");
			//customerTable에서 중복 체크
			if(customerDao.checkIdInCustomer(checkId)==1) { //customer에서 아이디 중복
				System.out.println("[InsertCustomerController.doGet() checkIdInCustomer] 아이디중복" );
				request.setAttribute("msg", "중복된 아이디가 존재합니다");
				request.getRequestDispatcher("/WEB-INF/view/insertCustomerForm.jsp").forward(request, response);
				return;
			}
			//host에서 중복 체크
			if(hostDao.checkIdInHost(checkId)==1) { //host에서 아이디 중복
				System.out.println("[InsertCustomerController.doGet() checkIdInCustomer]  아이디 중복");
				request.setAttribute("msg", "중복된 아이디가 존재합니다");
				request.getRequestDispatcher("/WEB-INF/view/insertCustomerForm.jsp").forward(request, response);
				return;
			}
			//아이디 중복확인이 끝나면 setAttribute
			request.setAttribute("customerId", checkId);
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
	    int row = 0; // insert결과 받을 변수 선언
	    customerDao = new CustomerDao();
	    row = customerDao.insertCustomer(customer);
		// 디버깅
		if (row == 1) { // row가 1이면 sql 가입 성공
	    	System.out.println("[InsertCustomerController.doPost()] 가입 성공");
	    	response.sendRedirect(request.getContextPath() + "/all/loginController");
	    	return;
	    } else if(row == 0) {// row == 0이면 영향받은 행이 없으므로 (row 기본값 -1), 가입실패
	    	System.out.println("[InsertCustomerController.doPost()] 가입 실패");
	    } else if (row == -1) {//row가 -1이면 sql이 작동 안함
	    	System.out.println("[InsertCustomerController.doPost()] 예외 발생");
	    	request.setAttribute("msg", "가입에 실패하셨습니다.");
	    	request.getRequestDispatcher("/WEB-INF/view/insertCustomerForm.jsp").forward(request, response);
	    	return;
	    }
	    
	}
}