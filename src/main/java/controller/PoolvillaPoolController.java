package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.PoolvillaPoolDao;
import vo.PoolvillaPool;

@WebServlet("/host/poolvillaPoolController")
public class PoolvillaPoolController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PoolvillaPoolDao poolvillaPoolDao = new PoolvillaPoolDao();
		List<PoolvillaPool> list = poolvillaPoolDao.selectPoolvillaPoolList();

		request.setAttribute("list", list);
		request.getRequestDispatcher("/WEB-INF/view/poolvillaPoolList.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// null 체크
		if (request.getParameter("pvNo") == null || request.getParameter("poolName") == null
				|| request.getParameter("poolWidth") == null || request.getParameter("poolLength") == null
				|| request.getParameter("depth") == null || request.getParameter("hotWater") == null
				|| request.getParameter("indoorOutdoor") == null) {
			System.out.println("null 체크");
			response.sendRedirect(request.getContextPath() + "/host/poolvillaPoolController");
		}
		// 변수 등록
		int pvNo = 0;
		String poolName = "";
		double poolWidth = 0;
		double poolLength = 0;
		double depth = 0;
		String hotWater = "";
		String indoorOutdoor = "";
		
		if(request.getParameter("pvNo") != null || request.getParameter("pvNo") !="") {
			pvNo = Integer.parseInt(request.getParameter("pvNo"));
		}
		if(request.getParameter("poolName") != null || request.getParameter("poolName") !="") {
			poolName = request.getParameter("poolName");
		}
		if(request.getParameter("poolWidth") != null || request.getParameter("poolWidth") !="") {
			poolWidth = Double.parseDouble(request.getParameter("poolWidth"));
		}
		if(request.getParameter("poolLength") != null || request.getParameter("poolLength") !="") {
			poolLength = Double.parseDouble(request.getParameter("poolLength"));
		}
		if(request.getParameter("depth") != null || request.getParameter("depth") !="") {
			depth = Double.parseDouble(request.getParameter("depth"));
		}
		if(request.getParameter("hotWater") != null || request.getParameter("hotWater") !="") {
			hotWater = request.getParameter("hotWater");
		}
		if(request.getParameter("indoorOutdoor") != null || request.getParameter("indoorOutdoor") !="") {
			indoorOutdoor = request.getParameter("indoorOutdoor");
		}
		
		System.out.println("1111111111");
		
		PoolvillaPool pp = new PoolvillaPool();
		pp.setPvNo(pvNo);
		pp.setPoolName(poolName);
		pp.setPoolWidth(poolWidth);
		pp.setPoolLength(poolLength);
		pp.setDepth(depth);
		pp.setHotWater(hotWater);
		pp.setIndoorOutdoor(indoorOutdoor);
		
		
		PoolvillaPoolDao poolvillaPoolDao= new PoolvillaPoolDao();
		poolvillaPoolDao.insertPoolvillaPool(pp);
		
		

		/*
		 * PoolvillaPoolDao poolvillaPoolDao = new PoolvillaPoolDao(); PoolvillaPool pp
		 * = new PoolvillaPool();
		 * 
		 * pp.setPvNo(Integer.parseInt(request.getParameter("pvNo")));
		 * pp.setPoolName(request.getParameter("poolName"));
		 * pp.setPoolWidth(Double.parseDouble(request.getParameter("poolWidth")));
		 * pp.setPoolLength(Double.parseDouble(request.getParameter("poolLength")));
		 * pp.setDepth(Double.parseDouble(request.getParameter("depth")));
		 * pp.setHotWater(request.getParameter("hotWater"));
		 * pp.setIndoorOutdoor(request.getParameter("indoorOutdoor"));
		 * 
		 * 
		 * System.out.println("pool : " + pp.toString());
		 * 
		 * poolvillaPoolDao.insertPoolvillaPool(pp);
		 */
		response.sendRedirect(request.getContextPath() + "/host/poolvillaPoolController");
	}

}
