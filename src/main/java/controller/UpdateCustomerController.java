package controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CustomerDao;
import vo.Customer;
@WebServlet("/customer/updateCustomerController")
public class UpdateCustomerController extends HttpServlet {
	
	private CustomerDao customerDao;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//session 값 요청 
		HttpSession session = request.getSession();
		Map<String,Object> sessionLoginMember = (Map<String,Object>)session.getAttribute("sessionLoginMember");
		
		// 요청값 호출
			String memberId = (String)sessionLoginMember.get("memberId");
			//디버깅
			System.out.println("[/customer/updateCustomerController.doget()] memberId : " + memberId);
				
	    //모델값 호출
		customerDao = new CustomerDao();
	    //id정보로 DB의 상세보기 값 호출
	    Customer Customer = new Customer();
	    Customer myPageCustomer = customerDao.selectCustomerOneByCustomerId(memberId);
		// 디버깅
		System.out.println("[/customer/updateCustomerController.doget()] myPageCustomer : " + myPageCustomer.toString());
		
		// 모델값 setAttiribute
		request.setAttribute("myPageCustomer", myPageCustomer); // 해당 고객의	
				
	    request.getRequestDispatcher("/WEB-INF/view/updateCustomer.jsp").forward(request, response);
	}	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("[/customer/updateCustomerController.doPost()]");
		//session 값 요청
		HttpSession session = request.getSession();
		Map<String,Object> sessionLoginMember = (Map<String,Object>)session.getAttribute("sessionLoginMember");
		
		String memberPw = request.getParameter("memberPw");
		//form 요청 값 처리
	    Customer customer = new Customer();
	    customer.setCustomerId(request.getParameter("memberid"));
	    customer.setName(request.getParameter("name"));
	    customer.setBirthDate(request.getParameter("birth"));
	    customer.setGender(request.getParameter("gender"));
	    customer.setEmail(request.getParameter("emailId")+"@"+request.getParameter("emailUrl"));
	    customer.setPhone(request.getParameter("phone"));
	    customer.setCustomerPw(request.getParameter(memberPw));
	    System.out.println("[/customer/updateCustomerController.doPost()] customer " + customer.toString());//디버깅
	    
	    String newCustomerPw = request.getParameter(memberPw);
	    //디버깅
	    System.out.println("[/customer/updateCustomerController.doPost()] newCustomerPw : " + newCustomerPw );
	    //Dao에 update 요청
	    customerDao = new CustomerDao();
	    
	    int row = customerDao.updateCustomer(customer, newCustomerPw);
	    System.out.println("[/customer/updateCustomerController.doPost()] row" + row);
	    
	    if (row==1) { //성공시 SelectCustomerOnecontroller으로 돌려보냄
	    	System.out.println("[/customer/updateCustomerController.doPost()] 수정성공 ");
	    	response.sendRedirect(request.getContextPath()+"/customer/myPageOneController");
	    	return;
	    }else if(row==0) {// row==0이면 영향받은 행이 없으므로 (row 기본값 -1), 비밀번호 오류
	    	System.out.println("[/customer/updateCustomerController.doPost()] 수정실패 비밀번호오류 ");
	    	response.sendRedirect(request.getContextPath()+"/customer/updateCustomerController?msg=wrongPw");
	    	
	    }else if (row==-1) {//row가 -1이면 sql이 작동 안함
	    	System.out.println("[/customer/updateCustomerController.doPost()] 예외 발생");
	    	response.sendRedirect(request.getContextPath()+"/customer/updateCustomerController?msg=exception");
	    }
	    
	    

	    
	}
}