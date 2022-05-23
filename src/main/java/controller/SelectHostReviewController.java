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

@WebServlet("/host/selectHostReviewController")
public class SelectHostReviewController extends HttpServlet {
	
	private ReviewDao reviewDao;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 현재 연결한 클라이언트(브라우저)에 대한 세션값을 받아옴. 
		HttpSession session = request.getSession();
		
		// sessionLoginMember 받을 변수 선언 
		Map<String,Object> sessionLoginMember = (Map<String,Object>)session.getAttribute("sessionLoginMember");
		
		// 세션에 로그인된 사용자 아이디를 받는다 
		String hostId = (String)sessionLoginMember.get("memberId");
		System.out.println("[/host/selectHostReviewController.doGet()] hostId : " + hostId);	
		
		// 유효성 검사 코드. hostId 값을 받지 못하면 로그인 페이지로 이동 
		if(hostId == null) {
			response.sendRedirect(request.getContextPath()+"/all/loginController");
			return;
		}
		
		// 리뷰 목록 모델 인스턴스 생성 
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
		System.out.println("[/host/selectHostReviewController.doGet()] beginRow : "+beginRow);
		request.setAttribute("beginRow", beginRow);
		
		// 전체 행의 개수 구하는 코드 
        int totalRow = reviewDao.selectReviewListTotalRow(hostId);
        System.out.println("[/host/selectHostReviewController.doGet()] totalRow : "+totalRow);
		request.setAttribute("totalRow", totalRow);
		
		// 마지막 페이지 구하는 로직 
		int lastPage = 0;
		if(totalRow % rowPerPage == 0) {
			lastPage = totalRow / rowPerPage;
		} else {
			lastPage = (totalRow / rowPerPage) + 1;
		}
		System.out.println("[/host/selectHostReviewController.doGet()] lastPage : "+lastPage);
		request.setAttribute("lastPage", lastPage);
		
		// ` 페이징 처리 코드 끝 `
		
		// 검색 카테고리 변수 초기화, 기본값 customerId 
		String search = "rv.customer_id";
		if(request.getParameter("search") != null ) {
			search = request.getParameter("search");
			System.out.println("[/host/selectHostReviewController.doGet()] search : " + search);
			
		}
		request.setAttribute("search", search);
		
		// 검색창에 입력할 내용 
		String keyword = "";
		if(request.getParameter("keyword") != null) {
			keyword = request.getParameter("keyword");
			System.out.println("[/host/selectHostReviewController.doGet()] keyword : " + keyword); 
			
		}
		request.setAttribute("keyword", keyword);
		
		// DAO 메서드 생성 후 받아오기 
		// 리뷰 리스트 뽑아오는 메서드 호출 DAO 
		// selectCustomerReviewList.jsp 페이지로 리뷰 목록 보냄 
		ArrayList<HashMap<String, Object>> customerReviewList = new ArrayList<>(); // reviewDao.selectCustomerReviewList(beginRow, rowPerPage);
		// 검색 전 고객 리뷰 목록 호출 
		if(keyword.equals("")) {
			customerReviewList = reviewDao.selectCustomerReviewList(beginRow, rowPerPage);
		} else {
			customerReviewList = reviewDao.searchCustomerReviewList(search, keyword, beginRow, rowPerPage);
		}
		
		request.setAttribute("customerReviewList", customerReviewList);
		
		String checkedReviewContents = null;
		if(request.getParameter("checkReviewContents")!=null) {
			checkedReviewContents = request.getParameter("checkReviewContents");
			
			System.out.println("[/host/selectHostReviewController.doGet()] checkedReviewContents : " + checkedReviewContents);
		}
		request.setAttribute("checkedReviewContents", checkedReviewContents);
		
		String checkedOpinion;
		if(request.getParameter("checkOpinion")!=null) {
			checkedOpinion = request.getParameter("checkOpinion");
			
			System.out.println("[/host/selectHostReviewController.doGet()] checkedOpinion : " + checkedOpinion);
		}
		request.setAttribute("checkedOpinion", request.getParameter("checkOpinion"));
		
		request.getRequestDispatcher("/WEB-INF/view/selectHostReviewList.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
