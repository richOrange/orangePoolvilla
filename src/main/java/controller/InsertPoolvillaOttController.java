package controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.OttDao;
import vo.Ott;

@WebServlet("/host/insertPoolvillaOttController")
public class InsertPoolvillaOttController extends HttpServlet {
	private OttDao ottDao;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 널체크 null일 경우 home으로 response
		if (request.getParameter("pvNo") == null) {
			response.sendRedirect(request.getContextPath() + "/all/homeController?msg=null");
			return;
		}

		int pvNo = Integer.parseInt(request.getParameter("pvNo"));
		System.out.println("[InsertPoolvillaOttController.doGet()] pvNo : " + pvNo);
		ottDao = new OttDao();

		List<Ott> list = ottDao.selectOttList();
		if (Integer.parseInt(request.getParameter("pvNo")) != -1) {
			List<Map<String, Object>> poList = ottDao.selectPoolvillaOttByPvNo(Integer.parseInt(request.getParameter("pvNo")));
			request.setAttribute("poList", poList);
		}

		request.setAttribute("list", list);
		request.setAttribute("pvNo", pvNo);

		request.getRequestDispatcher("/WEB-INF/view/insertPoolvillaOtt.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int pvNo = -1;
		if (Integer.parseInt(request.getParameter("pvNo")) != -1) {
			pvNo = Integer.parseInt(request.getParameter("pvNo"));
		}
		System.out.println("[InsertPoolvillaOttController.doPost()] pvNo : " + pvNo);

		ottDao = new OttDao();

		// 요청값 처리
		int ottNo = -1;
		String ottName = null;

		if (request.getParameter("ottName") != null || !request.getParameter("ottNo").equals(-1)){
			ottName = request.getParameter("ottName");
			ottNo = Integer.parseInt(request.getParameter("ottNo"));
			ottDao.insertPoolvillaOtt(pvNo, ottNo);
		}

		// 디버깅
		System.out.println("[InsertPoolvillaOttController.doPost()] OttNo : " + ottNo);
		System.out.println("[InsertPoolvillaOttController.doPost()] OttName : " + ottName);

		response.sendRedirect(request.getContextPath() + "/host/insertPoolvillaOttController?pvNo=" + pvNo);
	}
	

}
