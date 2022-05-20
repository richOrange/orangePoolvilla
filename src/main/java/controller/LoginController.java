package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
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
			//로그인 여부 체크
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
			//로그인 여부 체크
			HttpSession session = request.getSession();
			//session에 로그인 정보 Map 요청
			Map<String,Object> sessionLoginMember = (Map<String,Object>)session.getAttribute("sessionLoginMember");
			if(sessionLoginMember != null) {
				// 이미 로그인이 되어 있는 상태라면 home으로 redirect
				response.sendRedirect(request.getContextPath()+"/all/homeController");
				return;
			}
			//널체크 널이면 로그인 폼으로 돌려보냄
			if(request.getParameter("memberId")==null||request.getParameter("memberPw")==null) {
				response.sendRedirect(request.getContextPath()+"/all/loginController");
				return;
			}
			//요청값 받기
			String memberId = request.getParameter("memberId");//로그인 아이디
			String memberPw = request.getParameter("memberPw");//로그인 비밀번호
			//디버깅
			System.out.println("[loginController.doPost()] memberId : " + memberId);
			System.out.println("[loginController.doPost()] memberPw : " + memberPw);
			//Dao호출
			customerDao = new CustomerDao();
			hostDao = new HostDao();
			//1. 관리자 로그인인지 체크
			//vo.host에 아이디 비밀번호 저장
			Host host = new Host();
			host.setHostId(memberId);
			host.setHostPw(memberPw);
			//관리자 로그인 정보 요청
			sessionLoginMember = hostDao.loginHost(host);
			if(sessionLoginMember.get("memberId")!=null) {//관리자 아이디가 들어온 경우 -> 로그인 성공
				System.out.println("[loginController.doPost()] 관리자 로그인 성공" + sessionLoginMember.toString());
				session.setAttribute("sessionLoginMember", sessionLoginMember); // 세션에 로그인 정보, 아이디와 level 셋팅
				response.sendRedirect(request.getContextPath()+"/all/homeController"); //로그인 완료, home으로 redirect
				return;
			}
			//관리자 로그인이 실패 한경우 (sessionLoginMember == null) 일반 회원 로그인 시도
			//vo.Customer 에 로그인 ID, PW 입력
			Customer customer = new Customer();
			customer.setCustomerId(memberId);
			customer.setCustomerPw(memberPw);
			//2.일반회원(customer) 로그인 정보 요청
			sessionLoginMember = customerDao.loginCustomer(customer);
			if(sessionLoginMember.get("memberId") == null) { //로그인 실패시 home으로 redirect
				System.out.println("[loginController.doPost()] customer 로그인실패 ");
				response.sendRedirect(request.getContextPath()+"/all/loginController?msg=fail");
			return;
			}
			//로그인 성공, 세션에 아이디와 level 저장
			System.out.println("[loginController.doPost()] customer 로그인 성공 : "+sessionLoginMember.toString());
			session.setAttribute("sessionLoginMember", sessionLoginMember);
			
			//3.비밀번호가 3개월 지났는지 확인 하는 기능
			//3개월전 날짜 구하기
			//오늘 날짜 요청
	    	Calendar now = Calendar.getInstance();
	    	int checkDate = 0;//3개월전 날짜 넣을 변수 초기화
	    	//해당 날짜 입력
	    	checkDate = now.get(Calendar.DATE);
	    	//해당 년,월 입력
	    	if(now.get(Calendar.MONTH)-2<=0) { //Calendar는 현재 월 -1  ->  3개월 전은 현재 월 -2 , 0보다 작거나 같으면 년은 1감소, 월은 12증가 -> 현재월 +10
	    		checkDate = checkDate + ((now.get(Calendar.YEAR)-1)*10000)+((now.get(Calendar.MONTH)+10)*100);
	    	}else {//(Calendar.MONTH)-2 > 0 인 경우, 연도 변경 없음
	    		checkDate = checkDate +(now.get(Calendar.YEAR)*10000)+((now.get(Calendar.MONTH)-2)*100);
	    	}
	    	System.out.println("[loginController.doPost()] 3개월전 날짜 : "+ checkDate);
	    	
		//마지막 비밀번호 변경 날짜 요청
		String LastCustomerPwDate = customerDao.selectLastUpdateDateCustomerPw(memberId);
		System.out.println("[loginController.doPost()]마지막 비밀번호 변경 날짜 : "+LastCustomerPwDate);
		//비밀번호 변경 날짜 int 값으로 변경
		int LastCustomerPwDateNumber = Integer.parseInt(LastCustomerPwDate.replace("-","")); // -를 ""으로 바꿔서 int에 저장
		System.out.println("[loginController.doPost()] LastCustomerPwDateNumber :"+LastCustomerPwDateNumber);
		if(checkDate>LastCustomerPwDateNumber) { //3개월 전 날짜보다 비밀변경 날짜가 작으면 3개월 경과한 비밀번호 정보 updatePw로 redirect
			response.sendRedirect(request.getContextPath()+"/customer/updatePasswordController?msg=over3month");
			return;
		}
		//비밀번호 변경 3개월 경과 안된 경우 홈으로 redirect
		response.sendRedirect(request.getContextPath()+"/all/homeController");
		
		}
	}