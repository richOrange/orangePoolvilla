package controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ReviewDao;

@WebServlet("/customer/deleteReviewController")
public class DeleteReviewController extends HttpServlet {
	
	private ReviewDao reviewDao;
	
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
		System.out.println("[/customer/deleteReviewController.doGet()] reservationNo : " + reservationNo);
		
		// 리뷰 모델 호출 
		reviewDao = new ReviewDao();
		
		reviewDao.deleteReview(reservationNo);
		
		request.getRequestDispatcher("/WEB-INF/view/myReviewList.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
