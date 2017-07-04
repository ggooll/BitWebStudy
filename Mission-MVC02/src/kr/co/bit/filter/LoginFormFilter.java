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
import javax.servlet.http.HttpServletRequest;

@WebFilter(urlPatterns = {"/login/loginForm.do"})
public class LoginFormFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;

		Cookie[] cookies = httpRequest.getCookies();
		String saveId = null;
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("saveUserId")) {
				saveId = cookie.getValue();
			}
		}

		httpRequest.setAttribute("saveId", saveId);
		chain.doFilter(httpRequest, response);
	}

	@Override
	public void destroy() {

	}

}
