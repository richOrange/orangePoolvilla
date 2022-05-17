package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.WishListDao;

@WebServlet("/customer/insertWishListController")
public class InsertWishListController extends HttpServlet {
	
	private WishListDao wishListDao;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 유효성 검사 코드. pvNo, customerId 값을 받지 못하면 풀빌라 목록 페이지로 이동 
		if(request.getParameter("pvNo")==null || request.getParameter("customerId")==null) {
			response.sendRedirect(request.getContextPath()+"/all/selectPoolvillaController?msg=null");
			return;
		}
		
		// 요청값 받기 
		int pvNo = Integer.parseInt(request.getParameter("pvNo"));
		String customerId = request.getParameter("customerId");
		
		// 디버깅
		System.out.println("[/customer/insertWishListController.doGet()] pvNo : " + pvNo);
		System.out.println("[/customer/insertWishListController.doGet()] customerId : " + customerId);
		
		// 모델 호출 
		wishListDao = new WishListDao();
		// 찜 목록 생성 
		wishListDao.insertWishList(pvNo, customerId);
		
		// 상품 목록 페이지로 이동 
		request.getRequestDispatcher("/WEB-INF/view/poolvillaList.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
