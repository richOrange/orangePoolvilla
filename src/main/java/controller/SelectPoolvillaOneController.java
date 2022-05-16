package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.PoolvillaDao;
import vo.Poolvilla;

@WebServlet("/all/selectPoolvillaOneController")
public class SelectPoolvillaOneController extends HttpServlet {
	private PoolvillaDao poolvillaDao;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//널체크 null일경우 home으로 response
		if(request.getParameter("pvNo")==null||request.getParameter("reservationBeginDate")==null||request.getParameter("reservationLastDate")==null) {
			response.sendRedirect(request.getContextPath()+"/all/homeController?msg=null");
			return;
		}
		//요청값 호출
		int pvNo = Integer.parseInt(request.getParameter("pvNo"));
		String reservationBeginDate = request.getParameter("reservationBeginDate");
		String reservationLastDate = request.getParameter("reservationLastDate");
		//디버깅
		System.out.println("[/all/selectPoolvillaOneController.doget()] pvNo : " + pvNo);
		System.out.println("[/all/selectPoolvillaOneController.doget()] reservationBeginDate : " + reservationBeginDate);
		System.out.println("[/all/selectPoolvillaOneController.doget()] reservationLastDate : " + reservationLastDate);
		//모델값 호출
		poolvillaDao = new PoolvillaDao();
		//풀빌라 정보 호출
		Poolvilla selectPoolvillaOne = poolvillaDao.selectPoolvillaOne(pvNo);
		//디버깅
		System.out.println("[/all/selectPoolvillaOneController.doget()] selectPoolvillaOne : " + selectPoolvillaOne.toString());
		//모델값 setAttiribute
		request.setAttribute("reservationBeginDate", reservationBeginDate);//체크인날짜
		request.setAttribute("reservationLastDate", reservationLastDate);//체크아웃날짜
		request.setAttribute("selectPoolvillaOne", selectPoolvillaOne);//poolvillaOne 정보
		//jsp 호출
		request.getRequestDispatcher("/WEB-INF/view/selectPoolvillaOne.jsp").forward(request, response);
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
