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

@WebServlet("/InsertCustomerController")
public class InsertCustomerController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//로그인 되어 있는 멤버면 CashBookByMonthController로 리다이렉트 
		HttpSession session = request.getSession();
		if(session.getAttribute("sessionCustomerId") != null) {
			response.sendRedirect(request.getContextPath()+"/CashBookByMonthController");
			return;
		}
		//insertCustomerForm.jsp 호출
		request.getRequestDispatcher("/WEB-INF/view/insertCustomerForm.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//인코딩
		request.setCharacterEncoding("UTF-8");
		//로그인 되어 있는 멤버면 CashBookByMonthController로 리다이렉트
		HttpSession session = request.getSession();
		if(session.getAttribute("sessionCustomerId") != null) {
			response.sendRedirect(request.getContextPath()+"/CashBookByMonthController");
			return;
		}
	    //널 체크
	    if(request.getParameter("name")==null||request.getParameter("age")==null||request.getParameter("CustomerId")==null||request.getParameter("gender")==null) {
	    	System.out.println("null insertCustomercontroller.dopost");
	    	response.sendRedirect(request.getContextPath()+"/UpdateCustomerController?msg=null");//요청값에 null있으면 UpdateCustomerController로 돌려보냄
	    	return;
	    }
	    //요청값 처리
	    String CustomerPw =null; //비밀번호가 들어갈 변수 초기화
	    if(request.getParameter("CustomerPw1")!=null&&request.getParameter("CustomerPw2")!=null&&!request.getParameter("CustomerPw1").equals("")&&request.getParameter("CustomerPw1").equals(request.getParameter("CustomerPw2"))) {
	    // null, 빈칸이 아닌 비밀번호가 둘이 일치한다면 CustomerPw에 저장
	     CustomerPw = request.getParameter("CustomerPw1");
	    }else if(request.getParameter("CustomerPw1")!=null&&request.getParameter("CustomerPw2")!=null&&!request.getParameter("CustomerPw1").equals("")&&!request.getParameter("CustomerPw1").equals(request.getParameter("CustomerPw2"))){
	    	// null, 빈칸은 아니지만 비밀번호가 둘이 일치하지 않는다면 msg와 함께 돌려보냄
	    	response.sendRedirect(request.getContextPath()+"/InsertCustomerController?msg=notMatch");
	    	return;
	    }
	    Customer customer = new Customer();
	    customer.setCustomerId(request.getParameter("CustomerId"));
	    customer.setName(request.getParameter("name"));
	    customer.setBirthDate(request.getParameter("age"));
	    customer.setGender(request.getParameter("gender"));
	    customer.setCustomerPw(CustomerPw);
	    //디버깅
	    System.out.println(customer.toString()+"<-insertCustomerController.dopost");
	    //Dao에 insert 요청
	    CustomerDao CustomerDao = new CustomerDao();
	    int row = CustomerDao.insertCustomer(customer);
	    if (row==1) { //성공시 Logincontroller으로 돌려보냄
	    	System.out.println("가입성공 InsertCustomerController.dopist");
	    	response.sendRedirect(request.getContextPath()+"/LoginController");
	    	return;
	    }else if(row==0) {// row==0이면 영향받은 행이 없으므로 (row 기본값 -1), 가입실패
	    	System.out.println("가입실패 insertCustomerController.dopist");
	    	response.sendRedirect(request.getContextPath()+"/InsertCustomerController?msg=fail");
	    	
	    }else if (row==-1) {//row가 -1이면 sql이 작동 안함
	    	System.out.println("예외 발생 insertCustomerController.dopist");
	    	response.sendRedirect(request.getContextPath()+"/InsertCustomerController?msg=exception");
	    }
	    
	    
	    
	
	}
}