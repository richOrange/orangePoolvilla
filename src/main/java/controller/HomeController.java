package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.PoolvillaLocationDao;
import vo.PoolvillaLocation;
@WebServlet("/all/homeController")
public class HomeController extends HttpServlet {
	private PoolvillaLocationDao poolvillaLocationDao;	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//지역 리스트 모델값 요청
		poolvillaLocationDao = new PoolvillaLocationDao();
		 List<PoolvillaLocation> locationList = poolvillaLocationDao.selectPoolvillaLocationList();
		 //모델값 저장
		 request.setAttribute("locationList", locationList);
		 //home.jsp 요청
		request.getRequestDispatcher("/WEB-INF/view/home.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
