package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ComparePoolvillaDao;

@WebServlet("/all/comparePoolvillaController")
public class ComparePoolvillaController extends HttpServlet {
	
	private ComparePoolvillaDao comparePoolvillaDao;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		comparePoolvillaDao = new ComparePoolvillaDao();
		//comparePoolvillaDao.selectPoolvillaPvName(session.getAttribute("sessionComparePvNoList"));
		
		request.getRequestDispatcher("/all/#####비교페이지comparePoolvilla.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
