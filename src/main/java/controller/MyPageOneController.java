package controller;

import java.io.IOException;
import java.util.Map;

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
		HttpSession session = request.getSession();
		Map<String,Object> sessionLoginMember = (Map<String,Object>)session.getAttribute("sessionLoginMember");
		
		// 요청값 호출
		String memberId = (String)sessionLoginMember.get("memberId");
			//디버깅
			System.out.println("[/customer/myPageOneController.doget()] memberId : " + memberId);
		// 모델값 호출
		customerDao = new CustomerDao();
		// 고객 정보 호출
		Customer myPageCustomer = customerDao.myPageCustomer(memberId);
			// 디버깅
			System.out.println("[/customer/myPageOneController.doget()] myPageCustomer : " + myPageCustomer.toString());
		
		// 모델값 setAttiribute
		request.setAttribute("myPageCustomer", myPageCustomer); // 해당 고객의	
		
		
		// jsp 호출
		request.getRequestDispatcher("/WEB-INF/view/myPageOne.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}

}
