package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/all/logoutController")
public class LogoutController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().invalidate(); // session 갱신 메서드 : 기존 세션을 지우고 새로운 세션공간을 부여
		
		response.sendRedirect(request.getContextPath()+"/all/loginController");
		System.out.println("[LogoutController.doGet()] 로그아웃 완료");
	}

	
}
