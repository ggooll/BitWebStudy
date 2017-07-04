package kr.co.bit.framework;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DispatcherServlet
 */
@WebServlet(urlPatterns = {"*.do"},
		initParams = {@WebInitParam(name = "controllers",
				value = "kr.co.bit.board.controller.BoardController|"
						+ "kr.co.bit.login.controller.LoginController")})
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HandlerMapping mappings = null;

	/**
	 * 서블릿이 처음 생성된 후 등록된 컨트롤러들을 모두 맵핑시킴
	 * 
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// WebInitParam ?
		String controllerNames = config.getInitParameter("controllers");

		try {
			mappings = new HandlerMapping(controllerNames);
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String uri = request.getRequestURI();
		uri = uri.substring(request.getContextPath().length());
		System.out.println("uri : " + uri);

		// uri에 대한 맵핑 CtrlAndMethod를 받아옴 (어떤 컨트롤러의 어떤 메소드를 호출해야 하는지)
		CtrlAndMethod ctrlAndMethod = mappings.getCtrlAndMethod(uri);

		String view = "";
		try {
			if (ctrlAndMethod == null) {
				throw new Exception("잘못된 URL입니다");
			}

			Object target = ctrlAndMethod.getTarget();
			Method method = ctrlAndMethod.getMethod();

			// new LoginController().login(request, response);
			ModelAndView mv = (ModelAndView) method.invoke(target, request, response);
			view = mv.getView();

			// model에 등록된 객체들을 모두 request영역에 등록
			Map<String, Object> model = mv.getModel();
			Set<String> keys = model.keySet();
			for (String key : keys) {
				request.setAttribute(key, model.get(key));
			}

		} catch (Exception e) {
			request.setAttribute("exception", e);
			view = "/ErrorServlet";
		}

		// redirect에 대한 처리
		if (view.startsWith("redirect:")) {
			view = view.substring("redirect:".length());
			response.sendRedirect(view);
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher(view);
			dispatcher.forward(request, response);
		}
	}

}
