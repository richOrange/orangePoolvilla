package controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.PoolvillaPoolDao;
import vo.PoolvillaPool;

@WebServlet("/host/insertPoolvillaPoolController")
public class InsertPoolvillaPoolController extends HttpServlet {
	private PoolvillaPoolDao poolvillaPoolDao;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("pvNo") == null) {
			response.sendRedirect(request.getContextPath() + "/all/homeController?msg=null");
			return;
		}
	int pvNo = Integer.parseInt(request.getParameter("pvNo"));
	System.out.println("[InsertPoolvillaPoolController.doGet()] pvNo : " + pvNo);
	poolvillaPoolDao = new PoolvillaPoolDao();
	List<PoolvillaPool> list = poolvillaPoolDao.selectPoolvillaPoolList();
	
	if (Integer.parseInt(request.getParameter("pvNo")) != -1) {
		List<PoolvillaPool> ppList = poolvillaPoolDao.selectPoolvillaPoolListByPvNo(Integer.parseInt(request.getParameter("pvNo")));
		request.setAttribute("ppList", ppList);
	}
	request.setAttribute("list", list);
	request.setAttribute("pvNo", pvNo);
	
	request.getRequestDispatcher("/WEB-INF/view/insertPoolvillaPool.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		// null 체크
				if (request.getParameter("pvNo") == null 
						|| request.getParameter("poolName") == null
						|| request.getParameter("poolWidth") == null || request.getParameter("poolLength") == null
						|| request.getParameter("depth") == null || request.getParameter("hotWater") == null
						|| request.getParameter("indoorOutdoor") == null) {
					System.out.println("null 체크");
					response.sendRedirect(request.getContextPath() + "/host/insertPoolvillaPoolController");
					return;
				}
				// 변수 등록
				int pvNo = -1;
				String poolName = "";
				double poolWidth = -1;
				double poolLength = -1;
				double depth = -1;
				String hotWater = "";
				String indoorOutdoor = "";
				
				if(request.getParameter("pvNo") != null || request.getParameter("pvNo") !="" || !request.getParameter("pvNo").equals(-1)) {
					System.out.println("[InsertPoolvillaPoolController.doPost()] pvNo : " + pvNo );
					pvNo = Integer.parseInt(request.getParameter("pvNo"));
				}
				if(request.getParameter("poolName") != null || request.getParameter("poolName") !="") {
					System.out.println("[InsertPoolvillaPoolController.doPost()] poolName : " + poolName );
					poolName = request.getParameter("poolName");
				}
				if(request.getParameter("poolWidth") != null || request.getParameter("poolWidth") !="" || !request.getParameter("poolWidth").equals(-1)) {
					System.out.println("[InsertPoolvillaPoolController.doPost()] poolWidth : " + poolWidth );
					poolWidth = Double.parseDouble(request.getParameter("poolWidth"));
				}
				if(request.getParameter("poolLength") != null || request.getParameter("poolLength") !="" || !request.getParameter("poolLength").equals(-1)) {
					System.out.println("[InsertPoolvillaPoolController.doPost()] poolLength : " + poolLength );
					poolLength = Double.parseDouble(request.getParameter("poolLength"));
				}
				if(request.getParameter("depth") != null || request.getParameter("depth") !="" || !request.getParameter("depth").equals(-1)) {
					System.out.println("[InsertPoolvillaPoolController.doPost()] depth : " + depth );
					depth = Double.parseDouble(request.getParameter("depth"));
				}
				if(request.getParameter("hotWater") != null || request.getParameter("hotWater") !="") {
					System.out.println("[InsertPoolvillaPoolController.doPost()] hotWater : " + hotWater );
					hotWater = request.getParameter("hotWater");
				}
				if(request.getParameter("indoorOutdoor") != null || request.getParameter("indoorOutdoor") !="") {
					System.out.println("[InsertPoolvillaPoolController.doPost()] indoorOutdoor : " + indoorOutdoor );
					indoorOutdoor = request.getParameter("indoorOutdoor");
				}
				
				PoolvillaPool pp = new PoolvillaPool();
				pp.setPvNo(pvNo);
				pp.setPoolName(poolName);
				pp.setPoolWidth(poolWidth);
				pp.setPoolLength(poolLength);
				pp.setDepth(depth);
				pp.setHotWater(hotWater);
				pp.setIndoorOutdoor(indoorOutdoor);
				
				poolvillaPoolDao= new PoolvillaPoolDao();
				poolvillaPoolDao.insertPoolvillaPool(pp);
				
				System.out.println("[InsertPoolvillaPoolController.doPost()] poolName : " + poolName );
				System.out.println("[InsertPoolvillaPoolController.doPost()] poolWidth : " + poolWidth );
				System.out.println("[InsertPoolvillaPoolController.doPost()] poolLength : " + poolLength );
				System.out.println("[InsertPoolvillaPoolController.doPost()] depth : " + depth );
				System.out.println("[InsertPoolvillaPoolController.doPost()] hotWater : " + hotWater );
				System.out.println("[InsertPoolvillaPoolController.doPost()] indoorOutdoor : " + indoorOutdoor );
				
				response.sendRedirect(request.getContextPath() + "/host/insertPoolvillaPoolController?pvNo=" + pvNo);
	}

}
