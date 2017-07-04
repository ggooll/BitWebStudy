package kr.co.bit.bonddebt.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FrontController extends HttpServlet {

	private HandlerMapping handlerMapping;
	private static final long serialVersionUID = 1L;

	@Override
	public void init(ServletConfig config) throws ServletException {
		String beanPath = config.getInitParameter("beanPath");
		handlerMapping = new HandlerMapping(beanPath);
		System.out.println("?????????");
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uri = request.getRequestURI();
		uri = uri.replace(request.getContextPath(), "");
		System.out.println("URI = " + uri);

		try {
			Controller controller = handlerMapping.getController(uri);
			String callpage = controller.handleRequest(request, response);
			System.out.println("con = " + controller);

			if (callpage.startsWith("redirect:")) {
				callpage = callpage.substring("redirect:".length());
				response.sendRedirect(callpage);
			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher(callpage);
				dispatcher.forward(request, response);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

}
