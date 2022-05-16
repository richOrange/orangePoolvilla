package filter;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("/host/*")
public class CheckHostLoginFilter implements Filter {

    public CheckHostLoginFilter() {
    }

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//host 페이지에 관리자 이외의 접근을 막는 기능
		HttpServletRequest req=(HttpServletRequest)request;
		HttpServletResponse res=(HttpServletResponse)response;
		HttpSession session = req.getSession();
		//로그인정보가 없으면 loginController로 Redirect
		if(session.getAttribute("sessionLoginMember")==null) {
			res.sendRedirect(req.getContextPath()+"/all/loginController");
			return;
		}
		//세션에서 로그인 정보 요청
		Map<String,Object> sessionLoginMember = (Map<String,Object>)session.getAttribute("sessionLoginMember");
	    //level이 5가 안되는 경우 HomeController로 보냄
	    if((int)sessionLoginMember.get("level")< 5) {
	    	 res.sendRedirect(req.getContextPath()+"/all/homeController?msg=needLevel");
	        return;
	      }
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
