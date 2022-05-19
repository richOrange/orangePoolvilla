package controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.FacilityDao;
import dao.FacilityDao;
import vo.Facility;

@WebServlet("/host/insertPoolvillaFacilityController")
public class InsertPoolvillaFacilityController extends HttpServlet {

	private FacilityDao facilityDao;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 널체크 null일 경우 home으로 response
		if (request.getParameter("pvNo") == null) {
			response.sendRedirect(request.getContextPath() + "/all/homeController?msg=null");
			return;
		}

		int pvNo = Integer.parseInt(request.getParameter("pvNo"));
		System.out.println("[InsertPoolvillaFacilityController.doGet()] pvNo : " + pvNo);
		facilityDao = new FacilityDao();

		List<Facility> list = facilityDao.selectFacilityList();
		if (Integer.parseInt(request.getParameter("pvNo")) != -1) {
			List<Map<String, Object>> pfList = facilityDao.selectPoolvillaFacilityListByPvNo(Integer.parseInt(request.getParameter("pvNo")));
			request.setAttribute("pfList", pfList);
		}

		request.setAttribute("list", list);
		request.setAttribute("pvNo", pvNo);

		request.getRequestDispatcher("/WEB-INF/view/insertPoolvillaFacility.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int pvNo = -1;
		if (Integer.parseInt(request.getParameter("pvNo")) != -1) {
			pvNo = Integer.parseInt(request.getParameter("pvNo"));
		}
		System.out.println("[InsertPoolvillaFacilityController.doPost()] pvNo : " + pvNo);

		facilityDao = new FacilityDao();

		// 요청값 처리
		int facilityNo = -1;
		String facilityName = null;
		int facilityCnt = -1;

		if (request.getParameter("facilityName") != null || !request.getParameter("facilityNo").equals(-1)
				|| !request.getParameter("facilityCnt").equals(-1)) {
			facilityName = request.getParameter("facilityName");
			facilityNo = Integer.parseInt(request.getParameter("facilityNo"));
			facilityCnt = Integer.parseInt(request.getParameter("facilityCnt"));
			facilityDao.insertPoolvillaFacility(pvNo, facilityNo, facilityCnt);
		}

		// 디버깅
		System.out.println("[InsertPoolvillaFacilityController.doPost()] FacilityNo : " + facilityNo);
		System.out.println("[InsertPoolvillaFacilityController.doPost()] FacilityName : " + facilityName);
		System.out.println("[InsertPoolvillaFacilityController.doPost()] FacilityCnt : " + facilityCnt);

		response.sendRedirect(request.getContextPath() + "/host/insertPoolvillaFacilityController?pvNo=" + pvNo);
	}

}
