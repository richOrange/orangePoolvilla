package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.WishListDao;

@WebServlet("/customer/deleteWishListController")
public class DeleteWishListController extends HttpServlet {
	
	private WishListDao wishListDao;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 찜 목록 페이지(myWishList.jsp)에서 받는 회원 아이디와 풀빌라 번호가 유효하지 않다면  
		if(request.getParameter("pvNo")==null || request.getParameter("customerId")==null) {
			response.sendRedirect(request.getContextPath()+"/customer/myWishListController?msg=null");
			return;
		}
		
		// 요청값 받기 
		int pvNo = Integer.parseInt(request.getParameter("pvNo"));
		String customerId = request.getParameter("customerId");
		
		// 디버깅
		System.out.println("[/customer/deleteWishListController.doGet()] pvNo : " + pvNo);
		System.out.println("[/customer/deleteWishListController.doGet()] customerId : " + customerId);
		
		// 모델 호출 
		wishListDao = new WishListDao();
		
		// 찜 목록 삭제
		wishListDao.deleteWishList(pvNo, customerId);
		
		// 페이지 옮기는 코드 수정 필요함 
		// 상품 목록 페이지로 이동 
		// 만약 풀빌라 상품 정보를 확인하는 페이지(poolvillaList.jsp)에서 찜 표시 기능을 제대로 사용한다면 수정이 되야 할 수도 있음 
		request.getRequestDispatcher("/WEB-INF/view/myWishList.jsp?customerId="+customerId).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
