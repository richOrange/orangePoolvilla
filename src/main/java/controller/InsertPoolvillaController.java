package controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.PoolvillaDao;
import dao.PoolvillaLocationDao;
import dao.PoolvillaAddressDao;
import vo.Poolvilla;
import vo.PoolvillaLocation;

@WebServlet("/host/insertPoolvillaController")
public class InsertPoolvillaController extends HttpServlet {
	
	private PoolvillaLocationDao poolvillaLocationDao;
	private PoolvillaDao poolvillaDao;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//지역 리스트 모델값 요청
		poolvillaLocationDao = new PoolvillaLocationDao();
		List<PoolvillaLocation> locationList = poolvillaLocationDao.selectPoolvillaLocationList();
		//모델값 저장
		request.setAttribute("locationList", locationList);
		//jsp 호출
		
		
		
		request.getRequestDispatcher("/WEB-INF/view/insertPoolvilla.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Poolvilla poolvilla = new Poolvilla();
		poolvilla.setHostId(request.getParameter("hostId"));		
		poolvilla.setLocationNo(Integer.parseInt(request.getParameter("locationNo")));
		poolvilla.setAddressNo(Integer.parseInt(request.getParameter("addressNo")));
		poolvilla.setPvDetailaddr(request.getParameter("pvDetailaddr"));
		poolvilla.setPvName(request.getParameter("pvName"));
		poolvilla.setPrice(Integer.parseInt(request.getParameter("price")));
		poolvilla.setPvSize(Double.parseDouble(request.getParameter("pvSize")));
		poolvilla.setPvFloor(Integer.parseInt(request.getParameter("pvFloor")));
		poolvilla.setPvPeople(Integer.parseInt(request.getParameter("pvPeople")));
		
		poolvillaDao = new PoolvillaDao();
		poolvillaDao.insertPoolvilla(poolvilla);
		
		String searchAddress = request.getParameter("searchAddress"); // 검색한 주소값 받아오기
		
		PoolvillaAddressDao poolvillaTestDao = new PoolvillaAddressDao();
		List<Map<String, Object>> list = poolvillaTestDao.searchAddress(searchAddress); // 주소 찾기 메서드 실행 후 찾아온 주소 list에 저장
		request.setAttribute("searchAddressList", list); // 리스트 값 searchAddressList에 세팅
		
		response.sendRedirect(request.getContextPath() + "/host/insertHostPoolvillaOneController");
	}

}