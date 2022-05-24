package controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CookingToolDao;
import dao.FacilityDao;
import dao.OttDao;
import dao.PoolvillaDao;
import dao.PoolvillaPhotoDao;
import dao.PoolvillaPoolDao;
import dao.PoolvillaRoomDao;
import dao.SuppliesDao;
import vo.Poolvilla;
import vo.PoolvillaPhoto;
import vo.PoolvillaPool;

@WebServlet("/host/selectHostPoolvillaOneController")
public class SelectHostPoolvillaOneController extends HttpServlet {
	
	private PoolvillaDao poolvillaDao;
	private OttDao ottDao;
	private SuppliesDao suppliesDao;
	private CookingToolDao cookingToolDao;
	private PoolvillaRoomDao poolvillaRoomDao;
	private PoolvillaPoolDao poolvillaPoolDao;
	private FacilityDao facilityDao;
	private PoolvillaPhotoDao poolvillaPhotoDao;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 널체크 null일 경우 home으로 response
		if(request.getParameter("pvNo") == null) {
			response.sendRedirect(request.getContextPath()+"/all/homeController?msg=null");
			return;
		}
		
		// 요청값 호출
		int pvNo = Integer.parseInt(request.getParameter("pvNo"));
		String reservationBeginDate = request.getParameter("reservationBeginDate");
		String reservationLastDate = request.getParameter("reservationLastDate");
		
		
		// 디버깅
		System.out.println("[/host/selectHostPoolvillaOneController.doget()] pvNo : " + pvNo);
		
		// 모델값 호출
		poolvillaDao = new PoolvillaDao();
		ottDao = new OttDao();
		suppliesDao = new SuppliesDao();
		cookingToolDao = new CookingToolDao();
		poolvillaRoomDao = new PoolvillaRoomDao();
		poolvillaPoolDao = new PoolvillaPoolDao();
		facilityDao = new FacilityDao();
		poolvillaPhotoDao = new PoolvillaPhotoDao();
		// 풀빌라 정보 호출
		Poolvilla selectPoolvillaOne = poolvillaDao.selectPoolvillaOne(pvNo);
		List<Map<String, Object>> poolvillaCookingToolList = cookingToolDao.selectPoolvillaCookingToolList(pvNo);
		List<Map<String, Object>> poolvillaOttList = ottDao.selectPoolvillaOttByPvNo(pvNo);
		List<Map<String, Object>> poolvillaSuppliesList = suppliesDao.selectPoolvillaSuppliesByPvNo(pvNo);
		List<Map<String, Object>> poolvillaRoomNBedList = poolvillaRoomDao.selectPoolvillaRoomNBedByPvNo(pvNo);
		List<PoolvillaPool> poolvillaPoolList = poolvillaPoolDao.selectPoolvillaPoolListByPvNo(pvNo);
		List<Map<String, Object>> poolvillaFacilityList = facilityDao.selectPoolvillaFacilityListByPvNo(pvNo);
		PoolvillaPhoto poolvillaPhoto = poolvillaPhotoDao.selectPoolvillaPhoto(pvNo);
		// 디버깅
		System.out.println("[/host/selectHostPoolvillaOneController.doget()] selectHostPoolvillaOne : " + selectPoolvillaOne.toString());
		
		// 모델값 setAttiribute
		request.setAttribute("reservationBeginDate", reservationBeginDate);//체크인날짜
		request.setAttribute("reservationLastDate", reservationLastDate);//체크아웃날짜
		request.setAttribute("selectPoolvillaOne", selectPoolvillaOne);//poolvillaOne 정보
		request.setAttribute("poolvillaCookingToolList", poolvillaCookingToolList); // 해당 풀빌라의 cooking_tool 정보
		request.setAttribute("poolvillaOttList", poolvillaOttList); // 해당 풀빌라의 ott 정보
		request.setAttribute("poolvillaSuppliesList", poolvillaSuppliesList); // 해당 풀빌라의 supplies 정보
		request.setAttribute("poolvillaRoomNBedList", poolvillaRoomNBedList); // 해당 풀빌라의 room_info 정보와 bed 정보 
		request.setAttribute("poolvillaPoolList", poolvillaPoolList); // 해당 풀빌라의 pool 정보
		request.setAttribute("poolvillaFacilityList", poolvillaFacilityList); // 해당 풀빌라의 facility 정보
		request.setAttribute("poolvillaPhoto", poolvillaPhoto);
		// jsp 호출	
		request.getRequestDispatcher("/WEB-INF/view/selectHostPoolvillaOne.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		int pvNo = Integer.parseInt(request.getParameter("pvNo"));
		
		response.sendRedirect(request.getContextPath()+"/host/insertPoolvillaPhotoController");
		
	
	}

}
