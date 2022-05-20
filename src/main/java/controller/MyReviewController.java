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
import dao.WishListDao;

@WebServlet("/customer/myReviewController")
public class MyReviewController extends HttpServlet {
	
	private ReviewDao reviewDao;
	
	// header.jsp 페이지에서 리뷰를 선택하면 실행되는 내용 
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
		
		// 찜 목록 모델 인스턴스 생성 
		this.reviewDao = new ReviewDao();
		
		// ` 페이징 처리 코드 시작 `
		
		// 현재 페이지 구하는 코드 
		int currentPage = 1;
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		request.setAttribute("currentPage", currentPage);
		
		// 페이지당 보여줄 행의 개수 
		int rowPerPage = 10; 
		request.setAttribute("rowPerPage", rowPerPage);
		
		// 시작 행 구하는 로직 
		int beginRow = (currentPage-1) * rowPerPage;
		System.out.println("[/customer/myWishListController.doGet()] beginRow : "+beginRow);
		request.setAttribute("beginRow", beginRow);
		
		// 전체 행의 개수 구하는 코드 
        int totalRow = reviewDao.selectReviewListTotalRow(customerId);
        System.out.println("[/customer/myWishListController.doGet()] totalRow : "+totalRow);
		request.setAttribute("totalRow", totalRow);
		
		// 마지막 페이지 구하는 로직 
		int lastPage = 0;
		if(totalRow % rowPerPage == 0) {
			lastPage = totalRow / rowPerPage;
		} else {
			lastPage = (totalRow / rowPerPage) + 1;
		}
		System.out.println("[/customer/myWishListController.doGet()] lastPage : "+lastPage);
		request.setAttribute("lastPage", lastPage);
		
		// ` 페이징 처리 코드 끝 ` 
		
		// 리뷰 리스트 뽑아오는 메서드 호출 DAO 
		ArrayList<HashMap<String, Object>> reviewList = reviewDao.selectReviewList(customerId, beginRow, rowPerPage);
		// myReviewList.jsp 페이지로 리뷰 목록 보냄 
		request.setAttribute("reviewList", reviewList);
		
		// 리뷰 리스트 뽑아오는 메서드 호출 DAO 
		ArrayList<HashMap<String, Object>> reviewListWroteReview = reviewDao.selectReviewListWroteReview(customerId, beginRow, rowPerPage);
		// myReviewList.jsp 페이지로 리뷰 목록 보냄 
		request.setAttribute("reviewListWroteReview", reviewListWroteReview);
		
		request.getRequestDispatcher("/WEB-INF/view/myReviewList.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
