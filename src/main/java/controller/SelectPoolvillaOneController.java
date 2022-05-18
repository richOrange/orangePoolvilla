package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.FacilityDao;
import dao.OttDao;
import dao.PoolvillaCookingToolDao;
import dao.PoolvillaDao;
import dao.PoolvillaPoolDao;
import dao.PoolvillaRoomDao;
import dao.SuppliesDao;
import vo.CookingTool;
import vo.Poolvilla;
import vo.PoolvillaPool;

@WebServlet("/all/selectPoolvillaOneController")
public class SelectPoolvillaOneController extends HttpServlet {
	
	private PoolvillaDao poolvillaDao;
	private PoolvillaCookingToolDao poolvillaCookingToolDao;
	private OttDao ottDao;
	private SuppliesDao suppliesDao;
	private PoolvillaRoomDao poolvillaRoomDao;
	private PoolvillaPoolDao poolvillaPoolDao;
	private FacilityDao facilityDao;
	
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
		poolvillaCookingToolDao = new PoolvillaCookingToolDao();
		ottDao = new OttDao();
		suppliesDao = new SuppliesDao();
		poolvillaRoomDao = new PoolvillaRoomDao();
		poolvillaPoolDao = new PoolvillaPoolDao();
		facilityDao = new FacilityDao();
		
		//풀빌라 정보 호출
		Poolvilla selectPoolvillaOne = poolvillaDao.selectPoolvillaOne(pvNo);
		List<Map<String, Object>> poolvillaCookingToolList = poolvillaCookingToolDao.selectPoolvillaCookingToolByPvNo(pvNo);
		List<Map<String, Object>> poolvillaOttList = ottDao.selectPoolvillaOttByPvNo(pvNo);
		List<Map<String, Object>> poolvillaSuppliesList = suppliesDao.selectPoolvillaSuppliesByPvNo(pvNo);
		List<Map<String, Object>> poolvillaRoomNBedList = poolvillaRoomDao.selectPoolvillaRoomNBedByPvNo(pvNo);
		List<PoolvillaPool> selectPoolvillaPoolListByPvNo = poolvillaPoolDao.selectPoolvillaPoolListByPvNo(pvNo);
		List<Map<String, Object>> selectPoolvillaFacilityListByPvNo = facilityDao.selectPoolvillaFacilityListByPvNo(pvNo);
		
		//디버깅
		System.out.println("[/all/selectPoolvillaOneController.doget()] selectPoolvillaOne : " + selectPoolvillaOne.toString());
		//모델값 setAttiribute
		request.setAttribute("reservationBeginDate", reservationBeginDate);//체크인날짜
		request.setAttribute("reservationLastDate", reservationLastDate);//체크아웃날짜
		request.setAttribute("selectPoolvillaOne", selectPoolvillaOne);//poolvillaOne 정보
		request.setAttribute("poolvillaCookingToolList", poolvillaCookingToolList); // 해당 풀빌라의 cooking_tool 정보
		request.setAttribute("poolvillaOttList", poolvillaOttList); // 해당 풀빌라의 ott 정보
		request.setAttribute("poolvillaSuppliesList", poolvillaSuppliesList); // 해당 풀빌라의 supplies 정보
		request.setAttribute("poolvillaRoomNBedList", poolvillaRoomNBedList); // 해당 풀빌라의 room_info 정보와 bed 정보 
		request.setAttribute("selectPoolvillaPoolListByPvNo", selectPoolvillaPoolListByPvNo); // 해당 풀빌라의 pool 정보
		request.setAttribute("selectPoolvillaFacilityListByPvNo", selectPoolvillaFacilityListByPvNo); // 해당 풀빌라의 facility 정보
		//jsp 호출
		request.getRequestDispatcher("/WEB-INF/view/selectPoolvillaOne.jsp").forward(request, response);
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
