package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ReviewDao;
import vo.Review;

@WebServlet("/customer/insertReviewController")
public class InsertReviewController extends HttpServlet {
	
	private ReviewDao reviewDao;
	
	// myReviewList.jsp 페이지에서 넘어오고 난 뒤에 실행되는 메서드 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 현재 연결한 클라이언트(브라우저)에 대한 세션값을 받아옴. 
		HttpSession session = request.getSession();
		
		// sessionLoginMember 받을 변수 선언 
		Map<String,Object> sessionLoginMember = (Map<String,Object>)session.getAttribute("sessionLoginMember");
		
		// 세션에 로그인된 사용자 아이디를 받는다 
		String customerId = (String)sessionLoginMember.get("memberId");
		System.out.println("[/customer/myWishListController.doGet()] customerId : " + customerId);	
		
		// 유효성 검사 코드. customerId 값을 받지 못하면 로그인 페이지로 이동 
		if(customerId == null) {
			response.sendRedirect(request.getContextPath()+"/all/loginController");
			return;
		}
				
		int reservationNo = Integer.parseInt(request.getParameter("reservationNo"));
		System.out.println("[/customer/insertReviewController.doGet()] reservationNo : " + reservationNo);
		request.setAttribute("reservationNo", reservationNo);
		
		request.getRequestDispatcher("/WEB-INF/view/insertReview.jsp").forward(request, response);
	}

	// insertReview.jsp 페이지에서 넘긴 값들 받아서 처리 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 요청값 참조 변수로 정리해서 review 테이블에 삽입 
		Review review = new Review(); 
		
		// 요청값 담을 변수 선언 및 초기화 
		int cleanliness = 0;
		String revisit = null; 
		int satisfaction = 0;
		String opinion = null;
		String reviewContents = null; 
		String reviewActive = null; 
		int reservationNo = 0;
		
		// 넘겨받은 값으로 변수 값 변경 
		if(Integer.parseInt(request.getParameter("cleanliness")) != 0) {
			cleanliness = Integer.parseInt(request.getParameter("cleanliness"));
			review.setCleanliness(cleanliness);
			System.out.println("[/customer/insertReviewController.doPost()] cleanliness : " + cleanliness);
			
		}
		if(request.getParameter("revisit") != null) {
			revisit = request.getParameter("revisit");
			review.setRevisit(revisit);
			System.out.println("[/customer/insertReviewController.doPost()] revisit : " + revisit);
			
		}
		if(Integer.parseInt(request.getParameter("satisfaction")) != 0) {
			satisfaction = Integer.parseInt(request.getParameter("satisfaction"));
			review.setSatisfaction(satisfaction);
			System.out.println("[/customer/insertReviewController.doPost()] satisfaction : " + satisfaction);
		
		}
		if(request.getParameter("opinion") != null) {
			opinion = request.getParameter("opinion");
			review.setOpinion(opinion);
			System.out.println("[/customer/insertReviewController.doPost()] opinion : " + opinion);
			
		}
		if(request.getParameter("reviewContents") != null) {
			reviewContents = request.getParameter("reviewContents");
			review.setReviewContents(reviewContents);
			System.out.println("[/customer/insertReviewController.doPost()] reviewContents : " + reviewContents);
			
		}
		if(request.getParameter("reviewActive") != null) {
			reviewActive = request.getParameter("reviewActive");
			review.setReviewActive(reviewActive);
			System.out.println("[/customer/insertReviewController.doPost()] reviewActive : " + reviewActive);
			
		}
		if(request.getParameter("reservationNo") != null) {
			reservationNo = Integer.parseInt(request.getParameter("reservationNo"));
			review.setReservationNo(reservationNo);
			System.out.println("[/customer/insertReviewController.doPost()] reservationNo : " + reservationNo);
			
		}
		
		//디버깅
	    System.out.println("[/customer/insertReviewController.doPost()] review.toString() : " + review.toString());
	    
	    this.reviewDao = new ReviewDao();
	    
	    // 리뷰 추가하는 메서드 작성 
	    reviewDao.insertReview(review);
	    
	    response.sendRedirect(request.getContextPath()+"/customer/myReviewController");
	}

}
