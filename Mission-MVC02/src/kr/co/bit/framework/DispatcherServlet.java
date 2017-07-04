package kr.co.bit.framework;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Map.Entry;
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
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {

		String ctrlNames = config.getInitParameter("controllers");
		System.out.println(ctrlNames);

		try {
			mappings = new HandlerMapping(ctrlNames);
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

		CtrlAndMethod cam = mappings.getCtrlAndMethod(uri);
		/*
		 * http://localhost:8000/Mission-MVC02/board/list.do �Է½�
		 * uri : /board/list.do
		 * cam : target ==> kr.co.bit.controller.BoardController ��ü
		 * method ==> select()
		 */

		String view = "";
		try {
			if (cam == null) {
				throw new Exception("��û�Ͻ� URL�� �������� �ʽ��ϴ�");
			}

			Object target = cam.getTarget();
			Method method = cam.getMethod();

			ModelAndView mav = (ModelAndView) method.invoke(target, request, response);

			view = mav.getView();

			// request ���������� ��ü ���
			Map<String, Object> model = mav.getModel();

			Set<String> keys = model.keySet();
			for (String key : keys) {
				request.setAttribute(key, model.get(key));
			}

			// Set<Entry<String, Object>> entrySet = model.entrySet();
			// for(Entry<String, Object> entry : entrySet) {
			// request.setAttribute(entry.getKey(), entry.getValue());
			// }

		} catch (Exception e) {
			request.setAttribute("exception", e);
			// view = "/error/error.jsp";
			view = "/ErrorServlet";
		}

		// ������ ���� callpage�� ��û�̵�(forward, sendredirect ����)
		if (view.startsWith("redirect:")) {

			view = view.substring("redirect:".length());
			response.sendRedirect(view);
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher(view);
			dispatcher.forward(request, response);
		}
	}

}
