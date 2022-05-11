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

@WebServlet("/DeleteCustomerController")
public class DeleteCustomerController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//session 값 요청
		HttpSession session = request.getSession();
	    String sessionCustomerId = (String)session.getAttribute("sessionCustomerId");
	    //로그인이 안되어있을 경우 LoginController로 보냄
	    if(sessionCustomerId == null) {
	        response.sendRedirect(request.getContextPath()+"/LoginController");
	        System.out.println("noLogin");//디버깅
	        return;
	      }
	    //id정보로 회원탈퇴 폼 호출
	    request.setAttribute("CustomerId", sessionCustomerId);
	    request.getRequestDispatcher("/WEB-INF/view/deleteCustomerForm.jsp").forward(request, response);
			}	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//session 값 요청
		HttpSession session = request.getSession();
	    String sessionCustomerId = (String)session.getAttribute("sessionCustomerId");
	    //로그인이 안되어있을 경우 LoginController로 보냄
	    if(sessionCustomerId == null) {
	        response.sendRedirect(request.getContextPath()+"/LoginController");
	        System.out.println("noLogin");//디버깅
	        return;
	      }
		//널체크
	    if(request.getParameter("CustomerPw")==null||request.getParameter("CustomerId")==null) {
	    	//메세지와 함께 DeleteCustomerController 다시 요청
	    	response.sendRedirect(request.getContextPath()+"/DeleteCustomerController?msg=null");
	    	return;
	    }
	    //요청값 처리
	    Customer customer = new Customer();
	    customer.setCustomerId(request.getParameter("CustomerId"));
	    customer.setCustomerPw(request.getParameter("CustomerPw"));
	    //디버깅
	    System.out.println(customer.toString()+"DeleteCustomerController.dopost");
	    //Dao에 delete 요청
	    CustomerDao CustomerDao = new CustomerDao();
	    int row = CustomerDao.deleteCustomer(customer);
	    if (row==1) { //성공시 로그 아웃 후에 Logincontroller으로 돌려보냄
	    	System.out.println("삭제성공 DeleteCustomerController.dopost");
	    	request.getSession().invalidate();//session 갱신 메서드-로그아웃
	    	response.sendRedirect(request.getContextPath()+"/LoginController");
	    	return;
	    }else if(row==0) {// row==0이면 영향받은 행이 없으므로 (row 기본값 -1), 삭제실패,비밀번호오류
	    	System.out.println("삭제실패 deleteCustomerController.dopost");
	    	response.sendRedirect(request.getContextPath()+"/DeleteCustomerController?msg=wrongPw");
	    	return;
	    }else if (row==-1) {//row가 -1이면 sql이 작동 안함
	    	System.out.println("예외 발생 DeleteCustomerController.dopost");
	    	response.sendRedirect(request.getContextPath()+"/DeleteCustomerController?msg=exception");
	    	return;
	    }
	}

}