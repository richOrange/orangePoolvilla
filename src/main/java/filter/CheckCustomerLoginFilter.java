package filter;

import java.io.IOException;

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

import vo.Customer;

@WebFilter("/customer")
public class CheckCustomerLoginFilter implements Filter {

    public CheckCustomerLoginFilter() {
    }

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req=(HttpServletRequest)request;
		HttpServletResponse res=(HttpServletResponse)response;
		HttpSession session = req.getSession();
		//세션에서 로그인 정보 요청
		Customer sessionLoginMember = (Customer)session.getAttribute("sessionLoginMember");
	    //로그인이 안되어있을 경우 LoginController로 보냄
	    if(sessionLoginMember.getLevel() <3) {
	        res.sendRedirect(req.getContextPath()+"/LoginController");
	        return;
	      }
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
