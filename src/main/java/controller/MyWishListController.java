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

import dao.WishListDao;

@WebServlet("/customer/myWishListController")
public class MyWishListController extends HttpServlet {
	
	private WishListDao wishListDao;
	
	// header.jsp 페이지에서 찜 목록을 선택하면 실행되는 내용 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 현재 연결한 클라이언트(브라우저)에 대한 세션값을 받아옴. 
		HttpSession session = request.getSession();
		
		// sessionLoginMember 받을 변수 선언 
		Map<String,Object> sessionLoginMember = (Map<String,Object>)session.getAttribute("sessionLoginMember");
		
		// 세션에 로그인한 사용자는 customer, host 두 부류가 있다. 
		// 로그인 과정의 복잡함을 줄이기 위해 sessionLoginMember로 통일 
		// 실질적으로 host는 사용하지 않음 
		
		String customerId = (String)sessionLoginMember.get("memberId");
		System.out.println("[/customer/myWishListController.doGet()] customerId : " + customerId);		
				
		// 유효성 검사 코드. customerId 값을 받지 못하면 로그인 페이지로 이동 
		if(customerId == null) {
			response.sendRedirect(request.getContextPath()+"/all/loginController");
			return;
		}
		
		/*
		// 처음에는 페이지에서 값을 넘겨받는 방식으로 코드를 작성했는데 
		// `session`에서 값을 받는게 낫다고 판단해서 수정함
		// 값으로 받아 넘기면 그 페이지에서만 넘어갈 수 있는 점이 있어서 사용 범위가 좁아짐 
		 * 
		// 고객 아이디를 받고 문자열 변수에 저장한다 
		String customerId = request.getParameter("customerId");
		// 디버깅 
		System.out.println("[/customer/myWishListController.doGet()] customerId : " + customerId);
		*/
		
		
		// 찜 목록 모델 인스턴스 생성 
		this.wishListDao = new WishListDao();
		
		// 페이징 처리 코드 시작 
		// 현재 페이지 구하는 코드 
		int currentPage = 1;
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		request.setAttribute("currentPage", currentPage);
		
		// 페이지당 보여줄 행의 개수 
		int rowPerPage = 10; 
		request.setAttribute("rowPerPage", rowPerPage);
		
		int beginRow = (currentPage-1) * rowPerPage;
		System.out.println("[/customer/myWishListController.doGet()] beginRow : "+beginRow);
		request.setAttribute("beginRow", beginRow);
		
		// 전체 행의 개수 구하는 코드 
        int totalRow = wishListDao.selectWishListTotalRow(customerId); 
		request.setAttribute("totalRow", totalRow);
		
		// 마지막 페이지 구하는 로직 
		int lastPage = 0;
		if(totalRow % rowPerPage == 0) {
			lastPage = totalRow / rowPerPage;
		} else {
			lastPage = (totalRow / rowPerPage) + 1;
		}
		request.setAttribute("lastPage", lastPage);
		// 페이징 처리 코드 끝 
		
		ArrayList<HashMap<String, Object>> wishList = wishListDao.selectWishList(beginRow, rowPerPage, customerId);
		request.setAttribute("wishList", wishList);
		
		request.getRequestDispatcher("/WEB-INF/view/myWishList.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
