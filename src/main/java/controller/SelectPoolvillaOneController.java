package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
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
import dao.ReviewDao;
import dao.SuppliesDao;
import vo.CookingTool;
import vo.Poolvilla;
import vo.PoolvillaPhoto;
import vo.PoolvillaPool;

@WebServlet("/all/selectPoolvillaOneController")
public class SelectPoolvillaOneController extends HttpServlet {
	
	private PoolvillaDao poolvillaDao;
	private OttDao ottDao;
	private SuppliesDao suppliesDao;
	private CookingToolDao cookingToolDao;
	private PoolvillaRoomDao poolvillaRoomDao;
	private PoolvillaPoolDao poolvillaPoolDao;
	private FacilityDao facilityDao;
	private PoolvillaPhotoDao poolvillaPhotoDao;
	private ReviewDao reviewDao;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		//널체크 null일경우 home으로 response
		
		if(request.getParameter("pvNo")==null) {
			
			response.sendRedirect(request.getContextPath()+"/all/homeController?msg=null");
			return;
		}
		
		
		//요청값 호출
		//풀빌라 상품 번호
		int pvNo = Integer.parseInt(request.getParameter("pvNo"));
		
		//디버깅
		System.out.println("[/all/selectPoolvillaOneController.doget()] pvNo : " + pvNo);
		request.setAttribute("pvNo",pvNo);
		
		//checkIn값 받기
		if(request.getParameter("reservationBeginDate")!=null) {
			String reservationBeginDate = request.getParameter("reservationBeginDate");
			request.setAttribute("reservationBeginDate", reservationBeginDate);//체크인날짜 setAttribute
		}
		//checkOut값 받기
		if(request.getParameter("reservationLastDate")!=null) {
			String reservationLastDate = request.getParameter("reservationLastDate");
			request.setAttribute("reservationLastDate", reservationLastDate);//체크인날짜 setAttribute
			request.setAttribute("reservationLastDate", reservationLastDate);//체크아웃날짜
		}
		
		//모델값 호출
		poolvillaDao = new PoolvillaDao();
		cookingToolDao = new CookingToolDao();
		ottDao = new OttDao();
		suppliesDao = new SuppliesDao();
		poolvillaRoomDao = new PoolvillaRoomDao();
		poolvillaPoolDao = new PoolvillaPoolDao();
		facilityDao = new FacilityDao();
		poolvillaPhotoDao = new PoolvillaPhotoDao();
		reviewDao = new ReviewDao();
		
		// ` 페이징 처리 코드 시작 `
		
		// 현재 페이지 구하는 코드 
		int currentPage = 1;
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
			System.out.println("/all/selectPoolvillaOneController.doget() currentPage : " + currentPage);
		}
		request.setAttribute("currentPage", currentPage);
		
		// 페이지당 보여줄 행의 개수 
		int rowPerPage = 10; 
		System.out.println("/all/selectPoolvillaOneController.doget() rowPerPage : " + rowPerPage);
		request.setAttribute("rowPerPage", rowPerPage);
		
		// 시작 행 구하는 로직 
		int beginRow = (currentPage-1) * rowPerPage;
		System.out.println("[/all/selectPoolvillaOneController.doget()] beginRow : "+ beginRow);
		request.setAttribute("beginRow", beginRow);
		
		// 전체 행의 개수 구하는 코드 
        int totalRow = reviewDao.selectReviewListPerPoolvillaTotalRow(pvNo);
        System.out.println("[/all/selectPoolvillaOneController.doget()] totalRow : "+ totalRow);
		request.setAttribute("totalRow", totalRow);
		
		// 마지막 페이지 구하는 로직 
		int lastPage = 0;
		if(totalRow % rowPerPage == 0) {
			lastPage = totalRow / rowPerPage;
		} else {
			lastPage = (totalRow / rowPerPage) + 1;
		}
		System.out.println("[/all/selectPoolvillaOneController.doget()] lastPage : "+ lastPage);
		request.setAttribute("lastPage", lastPage);
		
		// ` 페이징 처리 코드 끝 `
		
		String checkedReviewContents = null;
		if(request.getParameter("checkReviewContents")!=null) {
			checkedReviewContents = request.getParameter("checkReviewContents");
			System.out.println("[/all/selectPoolvillaOneController.doget()] checkedReviewContents : " + checkedReviewContents);
		}
		request.setAttribute("checkedReviewContents", checkedReviewContents);
		
		//풀빌라 정보 호출
		Poolvilla selectPoolvillaOne = poolvillaDao.selectPoolvillaOne(pvNo);
		List<Map<String, Object>> poolvillaCookingToolList = cookingToolDao.selectPoolvillaCookingToolList(pvNo);
		List<Map<String, Object>> poolvillaOttList = ottDao.selectPoolvillaOttByPvNo(pvNo);
		List<Map<String, Object>> poolvillaSuppliesList = suppliesDao.selectPoolvillaSuppliesByPvNo(pvNo);
		List<Map<String, Object>> poolvillaRoomNBedList = poolvillaRoomDao.selectPoolvillaRoomNBedByPvNo(pvNo);
		List<PoolvillaPool> selectPoolvillaPoolListByPvNo = poolvillaPoolDao.selectPoolvillaPoolListByPvNo(pvNo);
		List<Map<String, Object>> selectPoolvillaFacilityListByPvNo = facilityDao.selectPoolvillaFacilityListByPvNo(pvNo);
		PoolvillaPhoto poolvillaPhoto = poolvillaPhotoDao.selectPoolvillaPhoto(pvNo);
		
		ArrayList<HashMap<String, Object>> poolvillaReviewListPerPoolvilla = reviewDao.selectReviewListPerPoolvilla(pvNo, beginRow, rowPerPage);
		
		//디버깅
		System.out.println("[/all/selectPoolvillaOneController.doget()] selectPoolvillaOne : " + selectPoolvillaOne.toString());
		//모델값 setAttiribute
		request.setAttribute("selectPoolvillaOne", selectPoolvillaOne);//poolvillaOne 정보
		request.setAttribute("poolvillaCookingToolList", poolvillaCookingToolList); // 해당 풀빌라의 cooking_tool 정보
		request.setAttribute("poolvillaOttList", poolvillaOttList); // 해당 풀빌라의 ott 정보
		request.setAttribute("poolvillaSuppliesList", poolvillaSuppliesList); // 해당 풀빌라의 supplies 정보
		request.setAttribute("poolvillaRoomNBedList", poolvillaRoomNBedList); // 해당 풀빌라의 room_info 정보와 bed 정보 
		request.setAttribute("selectPoolvillaPoolListByPvNo", selectPoolvillaPoolListByPvNo); // 해당 풀빌라의 pool 정보
		request.setAttribute("selectPoolvillaFacilityListByPvNo", selectPoolvillaFacilityListByPvNo); // 해당 풀빌라의 facility 정보
		request.setAttribute("poolvillaPhoto", poolvillaPhoto);
		request.setAttribute("poolvillaReviewListPerPoolvilla", poolvillaReviewListPerPoolvilla); // 풀빌라 한채의 리뷰 목록 
		
		//jsp 호출
		request.getRequestDispatcher("/WEB-INF/view/selectPoolvillaOne.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
