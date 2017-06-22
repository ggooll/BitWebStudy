package kr.co.bit.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.bit.util.HandlerMapping;

public class FrontController extends HttpServlet {

	private HandlerMapping handlerMapping;
	private static final long serialVersionUID = 1L;

	// 서블릿이 생성자를 호출하고 가장먼저 실행
	@Override
	public void init(ServletConfig config) throws ServletException {
		// web.xml에 init-param 태그 복습
		String beanPath = config.getInitParameter("beanPath");
		
		handlerMapping = new HandlerMapping(beanPath);
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uri = request.getRequestURI();
		uri = uri.replace(request.getContextPath(), "");

		System.out.println(uri);
		try {
			Controller controller = handlerMapping.getController(uri);

			// 기능별 Controller에게 처리를 맡기며 request와 response를 함께 넘겨줌
			String callpage = controller.handleRequest(request, response);
			RequestDispatcher dispatcher = request.getRequestDispatcher(callpage);
			dispatcher.forward(request, response);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

}
