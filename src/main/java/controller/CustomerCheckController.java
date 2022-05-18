package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CustomerDao;
import dao.HostDao;
//관리자가 회원리스트를 보는 컨트롤러
@WebServlet("/host/customerCheckController")
public class CustomerCheckController extends HttpServlet {
	private HostDao hostDao;
	private CustomerDao customerDao;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//DAO호출
		hostDao = new HostDao();
		customerDao = new CustomerDao();
		//페이징
		int currentPage = 1;
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		request.setAttribute("currentPage", currentPage);
		
		int rowPerPage = 10; 
		request.setAttribute("rowPerPage", rowPerPage);
		
		int beginRow = (currentPage-1) * rowPerPage;
		System.out.println("[HostController.doGet()] beginRow : "+beginRow);
		request.setAttribute("beginRow", beginRow);

        int totalRow = customerDao.selectCustomerTotalRow(); 
		request.setAttribute("totalRow", totalRow);
		
		int lastPage = 0;
		
		if(totalRow % rowPerPage == 0) {
			lastPage = totalRow / rowPerPage;
		} else {
			lastPage = (totalRow / rowPerPage) + 1;
		}
		
		request.setAttribute("lastPage", lastPage);
		//회원리스트 모델값 요청
		ArrayList<HashMap<String, Object>> customerList = customerDao.selectCustomerList();
		//모델값 setAttribute
		request.setAttribute("customerList", customerList);
		//뷰
		request.getRequestDispatcher("/WEB-INF/view/customerList.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
