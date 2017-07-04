package kr.co.bit.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@WebFilter(urlPatterns = {"/login/login.do"})
public class LoginFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String saveId = request.getParameter("saveId");
		String id = request.getParameter("id");

		HttpServletResponse httpResponse = (HttpServletResponse) response;
		Cookie cookie = new Cookie("saveUserId", id);

		if (saveId != null) {
			cookie.setMaxAge(1000000000);
		} else {
			cookie.setMaxAge(0);
		}

		httpResponse.addCookie(cookie);
		chain.doFilter(request, httpResponse);
	}

	@Override
	public void destroy() {

	}

}
