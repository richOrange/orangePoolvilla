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
	    Customer myPageCustomer = customerDao.myPageCustomer(memberId);
		// 디버깅
		System.out.println("[/customer/updateCustomerController.doget()] myPageCustomer : " + myPageCustomer.toString());
		
		// 모델값 setAttiribute
		request.setAttribute("myPageCustomer", myPageCustomer); // 해당 고객의	
				
	    request.getRequestDispatcher("/WEB-INF/view/updateCustomer.jsp").forward(request, response);
	}	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//인코딩
		request.setCharacterEncoding("UTF-8");
		//session 값 요청
		HttpSession session = request.getSession();
		Map<String,Object> sessionLoginMember = (Map<String,Object>)session.getAttribute("sessionLoginMember");
		
	    //널 체크
	    if(request.getParameter("name")==null||request.getParameter("age")==null||request.getParameter("CustomerPw")==null||request.getParameter("CustomerId")==null||request.getParameter("gender")==null) {
	    	System.out.println("[/customer/updateCustomerController.doget()] sessionLoginMember null" + sessionLoginMember.toString());
	    	response.sendRedirect(request.getContextPath()+"/UpdateCustomerController");//요청값에 null있으면 UpdateCustomerController로 돌려보냄
	    	return;
	    }
	    //form 요청 값 처리
	    Customer customer = new Customer();
	    customer.setCustomerId(request.getParameter("CustomerId"));
	    customer.setName(request.getParameter("name"));
	    customer.setBirthDate(request.getParameter("BirthDate"));
	    customer.setGender(request.getParameter("gender"));
	    customer.setCustomerPw(request.getParameter("CustomerPw"));
	    System.out.println(customer.toString()+"<-UPdateCustomerController.dopost");//디버깅
	    
	    String newCustomerPw="";
	    if(request.getParameter("newPw")==null) {
	    	System.out.println("1245");
	    }
	    if(request.getParameter("newPw").equals("open")){//비밀번호 변경 요청 여부 확인
	    	if(request.getParameter("newCustomerPw1")!=null&&!request.getParameter("newCustomerPw1").equals("")&&request.getParameter("newCustomerPw1").equals(request.getParameter("newCustomerPw2"))) {
	    		//새로운 비밀번호가 null,"" 이 아니고 pw1,pw2가 일치하면 newCustomerPw에 저장
	    		newCustomerPw=request.getParameter("newCustomerPw1");
	    		System.out.println(newCustomerPw+"<- newCustomerPw UpdateCustomerController.dopost");//디버깅
	    	}else if(request.getParameter("newCustomerPw1")!=null&&!request.getParameter("newCustomerPw1").equals("")&&!request.getParameter("newCustomerPw1").equals(request.getParameter("newCustomerPw2"))){
	    		//null, ""은 아니지만, pw1,pw2가 일치하지 않을 경우 msg와 함께 돌려보냄
	    		response.sendRedirect(request.getContextPath()+"/UpdateCustomerController?msg=notMatch");
	    		return;
	    	}
	    }
	    //Dao에 update 요청
	    customerDao = new CustomerDao();
	    int row = customerDao.updateCustomer(customer, newCustomerPw);
	    System.out.println(row+"<-row UpdateCustomerController.dopost");
	    if (row==1) { //성공시 SelectCustomerOnecontroller으로 돌려보냄
	    	System.out.println("수정성공 UpdateCustomerController.dopost");
	    	response.sendRedirect(request.getContextPath()+"/SelectCustomerOneController");
	    	return;
	    }else if(row==0) {// row==0이면 영향받은 행이 없으므로 (row 기본값 -1), 비밀번호 오류
	    	System.out.println("수정실패비밀번호오류 UpdateCustomerController.dopost");
	    	response.sendRedirect(request.getContextPath()+"/UpdateCustomerController?msg=wrongPw");
	    	
	    }else if (row==-1) {//row가 -1이면 sql이 작동 안함
	    	System.out.println("예외 발생 UpdateCustomerController.dopost");
	    	response.sendRedirect(request.getContextPath()+"/UpdateCustomerController?msg=exception");
	    }
	    
	    

	    
	}
}