package kr.co.bit.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	// init destroy는 오버라이딩하지 않아도 된다.
	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("init : 한번만 실행되는 메소드입니다");
	}

	@Override
	public void service(HttpServletRequest arg0, HttpServletResponse arg1)
			throws ServletException, IOException {
		
		System.out.println("service : 실제 작업이 수행되는 메소드입니다");
		System.out.println("service : 사용자의 요청을 처리하여 응답하는 메소드입니다");
	}

	@Override
	public void destroy() {
		System.out.println("destroy : 서블릿 변경이나 서버 재구동시 호출되는 메소드입니다");
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

	}

}
