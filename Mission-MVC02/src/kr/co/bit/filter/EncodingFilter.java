package kr.co.bit.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

/**
 * DispatcherServlet에 진입 전 점검
 * 
 * @author 임규철
 */
// web.xml에 설정해뒀던 것
@WebFilter(urlPatterns = {"*"},
		initParams = {@WebInitParam(name = "encoding", value = "utf-8")})
public class EncodingFilter implements Filter {

	private String charSet;

	/**
	 * 필터 생성
	 */
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("필터 init");
		// web.xml에 filter - initparam
		charSet = filterConfig.getInitParameter("encoding");
	}

	/**
	 * 
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("필터 수행");
		// 따라서 이전에 필요한 검증작업 후 넘길지 말지 결정할 수 있음
		request.setCharacterEncoding(charSet);

		// 넘기기 위해 chain에게 doFilter를 수행해야함
		chain.doFilter(request, response);
	}

	/**
	 * 필터 소멸
	 */
	@Override
	public void destroy() {
		System.out.println("필터 destroy");
	}

}
